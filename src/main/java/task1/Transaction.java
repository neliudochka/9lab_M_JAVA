package task1;

public class Transaction implements Runnable {
    private final Bank bank;
    private final Account from;
    private final Account to;
    private final int amount;
    public Transaction (Bank bank, Account from, Account to, int amount) {
        this.bank = bank;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
    @Override
    public void run() {
        bank.transfer(from, to, amount);

        try {
            Thread.sleep((int)(Math.random() * 10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
