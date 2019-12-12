/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumerproblemsemaphore;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author moritz
 */
public class ProducerConsumerSemaphore {
    List<Integer> buffer = new LinkedList<>();
    static final int N = 5;
    Random random = new Random();
    Semaphore semaphore = new Semaphore(0);
    Semaphore semCon = new Semaphore(0);
    
    void produce() throws InterruptedException {
        int item;
        while (true) {
            item = random.nextInt();
            Thread.sleep(100);
            if(buffer.size() == N)
            {
                semaphore.acquire();
            }
            
            buffer.add(item);
            System.out.println("Added Item, Count: " + buffer.size());
            
            if(buffer.size() == 1 && semCon.hasQueuedThreads())
            {
                semCon.release();
            }
        }
    }
    
    void consume() throws InterruptedException {
        int item;
        while (true) {
            if(buffer.size() == 0)
            {
                semCon.acquire();
            }
            item = buffer.remove(0);
            System.out.println("Removed Item, Count: " + buffer.size());
            Thread.sleep(110);
            
            if(buffer.size() == N - 1 && semaphore.hasQueuedThreads())
            {
                semaphore.release();
            }
            
        }
    }
}
