package gb.shop.payment.controller;

import gb.shop.payment.dto.TransferRequest;
import gb.shop.payment.model.Account;
import gb.shop.payment.service.AccountService;
import gb.shop.payment.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final TransferService transferService;
    private final AccountService accountService;

    @PostMapping("/transfer")
    public String transferMoney(@RequestBody TransferRequest request) {
        return transferService.transferMoney(
                request.getSenderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount());
    }

    @GetMapping("/all")
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @PostMapping("/save")
    public void saveAccount(@RequestBody Account account) {
        accountService.saveAccount(account);
    }


}
