package gb.shop.warehouse.service;

import gb.shop.warehouse.model.Warehouse;
import gb.shop.warehouse.model.WarehouseReserve;
import gb.shop.warehouse.repository.WarehouseRepository;
import gb.shop.warehouse.repository.WarehouseReserveRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WarehouseServiceTest {


    @Mock
    private WarehouseRepository warehouseRepository;
    @Mock
    private WarehouseReserveRepository warehouseReserveRepository;

    @InjectMocks
    private WarehouseService warehouseService;

    @Test
    @DisplayName("reserveProductTest")
    void reserveProduct() {

        Warehouse warehouse = new Warehouse("notebook", 100.0, 10);
        warehouse.setId(1L);

        given(warehouseRepository.findById(warehouse.getId())).willReturn(Optional.of(warehouse));

        warehouseService.reserveProduct(1L,2);

        verify(warehouseRepository).save(warehouse);
        verify(warehouseReserveRepository).save(new WarehouseReserve(warehouse.getProductName(),warehouse.getPrice(),2));
        assertEquals(warehouse.getQuantity(),8);

    }
}

