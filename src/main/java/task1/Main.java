package task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int nAcc = 300;
        int maxAmountOfMoney = 10000;

        List<Account> accounts = new ArrayList<>();
        for(int i = 0; i < nAcc; i++) {
            Account acc = new Account(i);
            acc.deposit(randomInt(maxAmountOfMoney));
            accounts.add(acc);
        }

        Bank b = new Bank(accounts);
        int bankBalanceWas = b.getBalance();

        System.out.println(accounts);
        System.out.println(b.getBalance());

        int maxMoneyForTransfer = 1000;
        int nThreads = 10000;
        List<Thread> threads = new ArrayList<>();
        for (int i=0; i < nThreads; i++) {
            Transaction tr = new Transaction(b, accounts.get(randomInt(nAcc)), accounts.get(randomInt(nAcc)), randomInt(maxMoneyForTransfer));
            Thread t = new Thread(tr);
            threads.add(t);
            t.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("bank balance was: " + bankBalanceWas);
        System.out.println("bank balance now: " + b.getBalance());
    }

    static public int randomInt(int max) {
        return new Random().nextInt(max);
    }
}

