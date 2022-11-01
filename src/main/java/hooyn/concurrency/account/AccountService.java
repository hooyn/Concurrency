package hooyn.concurrency.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Integer createAccount(String name, Integer money){
        Account account = Account.builder().name(name).balance(money).build();
        accountRepository.save(account);
        return 200;
    }

    @Transactional(readOnly = true)
    public String currentBalance(Long id){
        Account account = accountRepository.findById(id).get();
        return "[" + account.getName() +"] " + account.getBalance();
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void depositMoney(Long id, Integer money) throws InterruptedException {
        Thread.sleep(3000);
        Account account = accountRepository.findById(id).get();
        account.depositMoney(money);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void withdrawalMoney(Long id, Integer money) throws InterruptedException {
        Thread.sleep(1000);
        Account account = accountRepository.findById(id).get();
        account.withdrawalMoney(money);
    }
}
