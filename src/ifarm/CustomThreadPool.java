/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifarm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author hng
 */
public class CustomThreadPool extends ThreadPoolExecutor{
    
    private List<Runnable> list;
    private Exception exception;
    
    public CustomThreadPool(int PoolSize, int maxPoolSize, long keepAliveTime, TimeUnit unit,BlockingQueue<Runnable> workQueue) {  
        super(PoolSize, maxPoolSize, keepAliveTime, unit, workQueue);  
        list = new ArrayList<>();
    }  
    
    @Override  
    public void afterExecute(Runnable task, Throwable throw1) {  
        super.afterExecute(task, throw1);        
        if (throw1 ==null){            
            // complete execution without other exeception throws             
            if (this.exception !=null){
                list.add(task);                          
            }
        }
        else{
            System.out.println(throw1);
        }
                         
    }
    
    public List<Runnable> geFailedTaskList(){
        return this.list;
    }
    
    public int getSize(){
        return this.list.size();
    }
    
    public void setException(Exception exception){
        this.exception = exception;
    }
}
