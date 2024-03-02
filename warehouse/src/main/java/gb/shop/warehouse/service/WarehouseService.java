package gb.shop.warehouse.service;


import gb.shop.warehouse.model.Warehouse;
import gb.shop.warehouse.model.WarehouseReserve;
import gb.shop.warehouse.repository.WarehouseRepository;
import gb.shop.warehouse.repository.WarehouseReserveRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class WarehouseService {

    private final Counter reserveProductWarehouse = Metrics.counter("reserve_product_warehouse");

    private final WarehouseRepository warehouseRepository;
    private final WarehouseReserveRepository warehouseReserveRepository;

    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }


    public Warehouse findWarehouseById(Long id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    @Transactional
    public String reserveProduct(Long warehouseId, int quantity) {
        reserveProductWarehouse.increment();
        Warehouse warehouse = findWarehouseById(warehouseId);
        if (warehouse == null) return "Продукт не найден";
        int remains = warehouse.getQuantity() - quantity;
        if (remains < 0) return "Недостаточное количество товара на складе";
        warehouse.setQuantity(remains);
        warehouseRepository.save(warehouse);
        warehouseReserveRepository.save(new WarehouseReserve(warehouse.getProductName(), warehouse.getPrice(), quantity));
        return "Зарезеpвирован " + warehouse.getProductName() + " в количестве " + quantity;


    }

    public void saveWarehouse(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }

    @PostConstruct
    public void init(){
        saveWarehouse(new Warehouse("notebook",1.0 , 1000));
    }
}
