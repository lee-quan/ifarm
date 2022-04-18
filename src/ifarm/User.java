/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifarm;

import java.util.LinkedList;

/**
 *
 * @author hng
 */
public class User {

    private LinkedList<String> farms;
    private final String userid;

    public User(String userid) {
        this.userid = userid;
        farms = new LinkedList<>();
    }

    public String getUserid() {
        return this.userid;
    }

    public void insertFarm(String farmid) {
        farms.add(farmid);
    }

    public String getFarm() {
        String str = "";

        for (int i = 0; i < farms.size(); i++) {
            if (i == farms.size() - 1) {
                str += farms.get(i);
            } else {
                str += farms.get(i) + ",";
            }
        }

        return str;
    }

    public int getFarmSize() {
        return farms.size();
    }

}
