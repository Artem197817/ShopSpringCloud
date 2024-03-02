package gb.shop.payment.service;

import gb.shop.payment.model.Account;
import gb.shop.payment.repository.AccountRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
@AllArgsConstructor
public class TransferService {

    private AccountRepository accountRepository;

    private final Counter countTransferMoney = Metrics.counter("counter_transfer_money");

    @Transactional
    public String transferMoney(long idSender, long idReceiver, BigDecimal amount) {
        countTransferMoney.increment();
        Account sender = accountRepository.findById(idSender).orElse(null);
        Account receiver = accountRepository.findById(idReceiver).orElse(null);
            BigDecimal senderBalance = new BigDecimal(0);
        if (sender != null && receiver != null ) {
            if (sender.getAmount().intValue() > amount.intValue()) {
                senderBalance = sender.getAmount().subtract(amount);

                sender.setAmount(sender.getAmount().subtract(amount));
                receiver.setAmount(receiver.getAmount().add(amount));

                accountRepository.save(sender);
                accountRepository.save(receiver);
                return "Оплачено. Ваш баланс - " + senderBalance + " у.е";
            }
        }
        return "Повторите операцию. Ваш баланс - " + senderBalance + " у.е";
    }

}
