package gb.shop.warehouse.service;


import gb.shop.warehouse.model.Warehouse;
import gb.shop.warehouse.model.WarehouseReserve;
import gb.shop.warehouse.repository.WarehouseRepository;
import gb.shop.warehouse.repository.WarehouseReserveRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class WarehouseService {

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
}
