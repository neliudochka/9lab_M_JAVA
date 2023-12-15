package task2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CircularBuffer {
    public String[] buffer;
    private final int capacity;
    private int head;
    private int end;
    private  int count;

    final private Lock lock = new ReentrantLock();
    final private Condition notFull  = lock.newCondition();
    final private Condition notEmpty = lock.newCondition();
    public CircularBuffer(int capacity) {
        this.capacity = capacity;
        head = 0;
        end = 0;
        buffer = new String[capacity];
    }

    public void put(String string) throws InterruptedException {
        lock.lock();
        try {
            while(count == capacity)
                notFull.await();
            buffer[end] = string;
            if (++end == capacity)
                end = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
    public String take() throws InterruptedException {
        lock.lock();
        try {
            //чекаємо поки з'являться елементи
            while (count == 0)
                notEmpty.await();
            String res = buffer[head];
            buffer[head] = null;
            if(++head == capacity) head = 0;
            --count;
            notFull.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }

}
