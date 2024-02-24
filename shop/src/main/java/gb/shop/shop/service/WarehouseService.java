package gb.shop.shop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gb.shop.shop.aspects.LogAs;
import gb.shop.warehouse.model.Warehouse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseService {

    @LogAs
    public List<Warehouse> parserWarehouse(Object[] objects) {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(objects)
                .map(object -> mapper.convertValue(object, Warehouse.class))
                .collect(Collectors.toList());
    }

}
