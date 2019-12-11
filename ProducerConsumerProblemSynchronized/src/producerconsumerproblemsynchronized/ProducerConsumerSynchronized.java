/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumerproblemsynchronized;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author moritz
 */
public class ProducerConsumerSynchronized {

    List<Integer> buffer = new LinkedList<>();
    static final int N = 5;
    Random random = new Random();

    void produce() throws InterruptedException {
        int item;
        while (true) {
            synchronized (this) {
                item = random.nextInt();
                Thread.sleep(100);
                if (buffer.size() == N) {
                    wait();
                }
                buffer.add(item);
                System.out.println("Added Item, Count: " + buffer.size());
                if (buffer.size() == 1) {
                    notify();
                }
            }

        }
    }

    void consume() throws InterruptedException {
        int item;
        while (true) {
            synchronized (this) {
                item = random.nextInt();
                if (buffer.isEmpty()) {
                    wait();
                }
                item = buffer.remove(0);
                System.out.println("Removed Item, Count: " + buffer.size());
                if (buffer.size() == N - 1) {
                    notify();
                }
                Thread.sleep(100);
            }
        }
    }
}
