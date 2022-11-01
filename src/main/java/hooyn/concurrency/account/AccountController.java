package hooyn.concurrency.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/account")
    public String createAccount(@RequestParam(name = "name") String name, @RequestParam(name = "money") Integer money){
        try{
            accountService.createAccount(name, money);
            return name;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/deposit")
    public void depositMoney(@RequestParam(name = "id") Long id, @RequestParam(name = "money") Integer money){
        try{
            accountService.depositMoney(id, money);
            log.info("DEPOSIT - " + accountService.currentBalance(id));
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/withdrawal")
    public void withdrawalMoney(@RequestParam(name = "id") Long id, @RequestParam(name = "money") Integer money){
        try{
            accountService.withdrawalMoney(id, money);
            log.info("WITHDRAWAL - " + accountService.currentBalance(id));
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
