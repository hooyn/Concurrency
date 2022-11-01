package hooyn.concurrency.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    @DisplayName("동시성 문제 해결")
    void concurrencyProblemCheck() throws InterruptedException {

        //ExecutorService service = Executors.newFixedThreadPool(10);

        accountService.depositMoney(2L, 1000);
        System.out.println("DEPOSIT - " + accountService.currentBalance(2L));

        accountService.withdrawalMoney(2L, 1000);
        System.out.println("WITHDRAWAL - " + accountService.currentBalance(2L));
    }
}