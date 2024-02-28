package gb.shop.payment.service;

import gb.shop.payment.model.Account;
import gb.shop.payment.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class TransferServiceSpringBootTest {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private TransferService transferService;

    @Test
    @DisplayName("transferMoneySpringBootTest")
    void transferMoney() {
        Account sender= new Account(1L,"sender",new BigDecimal(1000));
        Account receiver = new Account(2L,"receiver",new BigDecimal(1000));

        given(accountRepository.findById(sender.getId())).willReturn(Optional.of(sender));
        given(accountRepository.findById(receiver.getId())).willReturn(Optional.of(receiver));

        BigDecimal amount= new BigDecimal(100);

        transferService.transferMoney(1L,2L,amount);

        verify(accountRepository).save(new Account(sender.getId(),sender.getName(),new BigDecimal(900)));
        assertEquals(1100,receiver.getAmount().intValue());
    }
}
