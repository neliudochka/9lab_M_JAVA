package task2;

public class Consumer implements Runnable{
    private CircularBuffer bufferFrom;
    private CircularBuffer bufferTo;
    private int numberOfThread;

    public Consumer(CircularBuffer bufferFrom, CircularBuffer bufferTo, int numberOfThread) {
        this.bufferFrom = bufferFrom;
        this.bufferTo = bufferTo;
        this.numberOfThread = numberOfThread;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String mes = bufferFrom.take();
                bufferTo.put("Потік " + numberOfThread + " переклав повідомлення: " + mes);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
