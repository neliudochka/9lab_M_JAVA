package task2;

public class Producer implements Runnable{
    private CircularBuffer buffer;
    private int numberOfThread;
    public Producer(CircularBuffer buffer, int numberOfThread) {
        this.buffer = buffer;
        this.numberOfThread = numberOfThread;
    }

    @Override
    public void run() {
        while(true) {
            try {
                buffer.put("Потік " + numberOfThread + " згенерував повідомлення " );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
