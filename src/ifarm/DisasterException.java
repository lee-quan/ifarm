/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifarm;

/**
 *
 * @author hng
 */
public class DisasterException extends Exception{
    
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public DisasterException(String message){
        this.message = message;
        displayMessage();
    }
    
    public void displayMessage(){
        System.out.println(this.message);
    }
}
