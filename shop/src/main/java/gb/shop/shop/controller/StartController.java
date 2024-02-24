package gb.shop.shop.controller;

import gb.shop.payment.model.Account;
import gb.shop.warehouse.model.Warehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequiredArgsConstructor
@RequestMapping("/start")
public class StartController {

    private final AccountController accountController;
    private final WarehouseController warehouseController;
    private final ShopController shopController;


    @PostMapping("/start")
    public ModelAndView start() {
        Warehouse warehouse = new Warehouse("notebook", 100.0, 10);
        Account account = new Account("receiver");
        Account account1 = new Account("sender");
        warehouseController.saveWarehouse(warehouse);
        accountController.saveAccount(account);
        accountController.saveAccount(account1);
        ModelAndView mav = new ModelAndView("shop");
        shopController.shop(mav);
        return mav;
    }

    @GetMapping("/")
    public String init() {
        return "start";
    }
}
