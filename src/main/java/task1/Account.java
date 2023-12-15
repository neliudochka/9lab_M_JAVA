package task1;

import lombok.Getter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    @Getter
    private int balance;
    final public int id;

    public Account(int id) {
        this.id = id;
    }

    public void withdraw(int amount) {

            if (amount < 0)
                throw new IllegalArgumentException("Amount of money cant be < 0: " + amount);
            if (balance < amount)
                //недостатня кількість коштів для здійснення переказу
                throw new RuntimeException("Insufficient funds: " + balance);

            balance -= amount;
    }

    public void deposit(int amount) {

            if (amount < 0)
                throw new IllegalArgumentException("Amount of money cant be < 0: " + amount);
            balance += amount;


    }

    @Override
    public String toString() {
        return "id: " + id + " balance: " + balance;
    }
}
