package task1;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Bank {
    @Getter
    private final List<Account> accounts;
    private final Lock lock;
    public Bank(List<Account> accounts) {
        this.accounts = accounts;
        this.lock = new ReentrantLock();
    }

    public int getBalance() {
        lock.lock();
        try {
            return accounts.stream()
                    .mapToInt(Account::getBalance)
                    .sum();
        } finally {
            lock.unlock();
        }
    }
    public void transfer(@NotNull Account from, @NotNull Account to, int amount){
        lock.lock();
        try {
            System.out.print(Thread.currentThread() + "\n");
            System.out.println("Transaction of " + amount + "$ from {" + from + "} to: {" + to +"}. Bank balance: " + getBalance());
            if (from == to)
                throw new RuntimeException("You cant transfer money to yours account.");
            from.withdraw(amount);
            to.deposit(amount);
            System.out.println("Result of transaction of : " + amount + "$ from {" + from + "} to: {" + to +"}. Bank balance: " + getBalance());
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage() +" While transferring : " + amount + "$ from {" + from + "} to: {" + to +"}. Bank balance: " + getBalance());
        } finally {
            lock.unlock();
        }
    }
}
