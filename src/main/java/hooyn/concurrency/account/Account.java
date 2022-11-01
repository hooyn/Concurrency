package hooyn.concurrency.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer balance;

    @Builder
    public Account(String name, Integer balance) {
        this.name = name;
        this.balance = balance;
    }

    public void depositMoney(Integer money){
        balance += money;
    }

    public void withdrawalMoney(Integer money){
        if (balance - money < 0)
            throw new RuntimeException("check your balance");
        balance -= money;
    }
}
