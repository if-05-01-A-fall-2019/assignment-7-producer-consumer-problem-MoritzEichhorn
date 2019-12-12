/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumerproblemsemaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author moritz
 */
public class ProducerConsumerProblemSemaphore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ExecutorService executerService = Executors.newFixedThreadPool(2);
        ProducerConsumerSemaphore p = new ProducerConsumerSemaphore();
        executerService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    p.produce();
                } catch (InterruptedException ex) {
                    System.out.println("Ups! Something went wrong!");
                }
            }
        });
        
        executerService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    p.consume();
                } catch (InterruptedException ex) {
                    System.out.println("Ups! Something went wrong!");
                }
            }
        });
        
        executerService.shutdown();
    }
    
}
