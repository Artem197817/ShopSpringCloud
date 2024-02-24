package gb.shop.payment.service;

import gb.shop.payment.model.Account;
import gb.shop.payment.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void saveAccount (Account account) {
        accountRepository.save(account);
    }

    public List<Account> findAll(){
        return accountRepository.findAll();
    }
}
