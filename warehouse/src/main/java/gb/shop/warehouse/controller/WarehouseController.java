package gb.shop.warehouse.controller;


import gb.shop.warehouse.model.Warehouse;
import gb.shop.warehouse.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/warehouse")
public class WarehouseController {


    private final WarehouseService warehouseService;

    @PostMapping("/save")
    public void saveWarehouse(@RequestBody Warehouse warehouse) {
        warehouseService.saveWarehouse(warehouse);
    }

    @GetMapping("/all")
    public List<Warehouse> findAll() {
        return warehouseService.findAll();
    }

    @GetMapping("/{id}")
    public Warehouse findById(@PathVariable Long id) {
        return warehouseService.findWarehouseById(id);
    }

    @PostMapping("/{id}/{quantity}")
    public String reserve(@PathVariable Long id, @PathVariable Integer quantity) {
        return warehouseService.reserveProduct(id, quantity);
    }
}
