package gb.shop.shop.controller;

import gb.shop.payment.dto.TransferRequest;
import gb.shop.payment.model.Account;
import gb.shop.shop.client.AccountClientApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;


@Controller
@AllArgsConstructor
public class AccountController {

    private final AccountClientApi accountClientApi;


    public void saveAccount(Account account) {
        accountClientApi.saveAccount(account);
    }

    public String transfer(TransferRequest transferRequest) {
        return accountClientApi.transferMoney(transferRequest);
    }
}
