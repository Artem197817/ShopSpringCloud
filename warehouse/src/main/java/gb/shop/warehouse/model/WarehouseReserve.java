package gb.shop.warehouse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class WarehouseReserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private Double price;
    private int quantity;

    public WarehouseReserve(String productName, Double price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
}
