package task2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CircularBuffer cb1 = new CircularBuffer(8);
        CircularBuffer cb2 = new CircularBuffer(10);

//      1) П’ять потоків генерують рядки в кільцевий буфер. Рядок має формат: «Потік № … згенерував повідомлення …»
        Thread[] producers = new Thread[5];
        for (int i = 0; i < 5; i++) {
            producers[i] = new Thread(new Producer(cb1, i));
            producers[i].setDaemon(true);
            producers[i].start();
        }

//      2) Інші два потоки перекладають рядки з першого кільцевого у другий кільцевий буфер. У другий кільцевий буфер записується рядок наступного формату: «Потік № … переклав повідомлення …».
        Thread[] consumers = new Thread[2];
        for (int i = 0; i < 2; i++) {
            consumers[i] = new Thread(new Consumer(cb1, cb2, i+5));
            consumers[i].setDaemon(true);
            consumers[i].start();
        }

//      3) Основний потік програми вичитує та роздруковує 100 повідомлень із другого буфера.
        for (int i = 0; i < 100; i++) {
            System.out.println(cb2.take());
        }
    }
}
