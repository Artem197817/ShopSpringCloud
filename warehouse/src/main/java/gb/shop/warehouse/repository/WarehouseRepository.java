package gb.shop.warehouse.repository;

import gb.shop.warehouse.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

}
