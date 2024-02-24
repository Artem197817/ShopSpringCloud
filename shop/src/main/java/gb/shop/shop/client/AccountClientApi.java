package gb.shop.shop.client;

import gb.shop.payment.dto.TransferRequest;
import gb.shop.payment.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account", url = "http://localhost:8089/account")
public interface AccountClientApi {

    @PostMapping("/save")
    void saveAccount(@RequestBody Account account);

    @PostMapping("/transfer")
    String transferMoney(@RequestBody TransferRequest request);

}
