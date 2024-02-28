package gb.shop.shop.client;

import gb.shop.warehouse.model.Warehouse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "warehouse", url = "http://localhost:8089/warehouse")
public interface WarehouseClientApi {

    @GetMapping("/all")
    List<Warehouse> findAll();

    @GetMapping("/{id}")
    Warehouse findById(@PathVariable Long id);

    @PostMapping("/save")
    void saveWarehouse(@RequestBody Warehouse warehouse);

    @PostMapping("/{id}/{quantity}")
    String reserve(@PathVariable Long id, @PathVariable Integer quantity);
}
