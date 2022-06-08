/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifarm;

import java.util.concurrent.atomic.AtomicInteger;


public class Counter {
    private AtomicInteger counter;
    
    public Counter (int i){
         counter = new AtomicInteger(i);
    }
    
    public int getAndIncrease(){
        return counter.getAndAdd(1);
    }
}
