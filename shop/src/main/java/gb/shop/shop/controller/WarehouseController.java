package gb.shop.shop.controller;

import gb.shop.shop.aspects.LogAs;
import gb.shop.shop.client.WarehouseClientApi;
import gb.shop.warehouse.model.Warehouse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/warehouse")
@AllArgsConstructor
public class WarehouseController {

    private WarehouseClientApi warehouseClientApi;

    public void saveWarehouse(Warehouse warehouse) {
        warehouseClientApi.saveWarehouse(warehouse);
    }

    @LogAs
    public List<Warehouse> getAllWarehouse() {
        return warehouseClientApi.findAll();
    }

    public Warehouse getWarehouseById(Long id) {
        return warehouseClientApi.findById(id);
    }

    public String reserve(Long id, Integer quantity) {
        return warehouseClientApi.reserve(id, quantity);
    }
}

