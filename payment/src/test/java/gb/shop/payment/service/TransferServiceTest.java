package gb.shop.payment.service;

import gb.shop.payment.model.Account;
import gb.shop.payment.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransferService transferService;

    @Test
    @DisplayName("transferMoneyTest")
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