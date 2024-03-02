package gb.shop.shop.controller;

import gb.shop.payment.dto.TransferRequest;
import gb.shop.warehouse.model.Warehouse;
import io.micrometer.core.instrument.Timer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.micrometer.core.instrument.Metrics;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@AllArgsConstructor
@RequestMapping("/shop")
public class ShopController {

    private final WarehouseController warehouseController;
    private final AccountController accountController;

    private final Timer timer = Metrics.timer("payment_shop_method");

    @PostMapping("/shop")
    public String shop(ModelAndView modelAndView) {
        List<Warehouse> warehouses = warehouseController.getAllWarehouse();
        modelAndView.getModelMap().addAttribute("warehouses", warehouses);
        return "redirect:/shop";
    }

    @PostMapping("/buy")
    public String buy(Long id, Integer quantity, Model model) {
        Warehouse warehouse = warehouseController.getWarehouseById(id);
        warehouse.setQuantity(quantity);
        model.addAttribute("warehouse", warehouse);
        String message = warehouseController.reserve(id, quantity);
        model.addAttribute("message", message);
        return "buy";
    }

    @PostMapping("/payment")
    public ModelAndView reserve(BigDecimal price, Integer quantity) {
        timer.record(60,TimeUnit.MILLISECONDS);
        ModelAndView modelAndView = new ModelAndView("shop");
        BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));
        TransferRequest transferRequest = new TransferRequest(1, 2, amount);
        String message = accountController.transfer(transferRequest);
        modelAndView.getModelMap().addAttribute("message", message);
        List<Warehouse> warehouses = warehouseController.getAllWarehouse();
        modelAndView.getModelMap().addAttribute("warehouses", warehouses);
        return modelAndView;

    }

}
