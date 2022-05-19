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
public class Farm {

    private LinkedList<String> plant, fertiliser, pesticide;
    private final String id;

    public Farm(String id) {
        this.id = id;
        plant = new LinkedList<>();
        fertiliser = new LinkedList<>();
        pesticide = new LinkedList<>();
    }

    public void insertPlant(String plantid) {
        plant.add(plantid);
    }

    public void insertFertiliser(String fertiliserid) {
        fertiliser.add(fertiliserid);
    }

    public void insertPesticide(String pesticideid) {
        pesticide.add(pesticideid);
    }

    public String getPlant() {
        String str = "";

        for (int i = 0; i < plant.size(); i++) {
            if (i == plant.size() - 1) {
                str += plant.get(i);
            } else {
                str += plant.get(i) + ",";
            }
        }

        return str;
    }

    public String getFertiliser() {
        String str = "";

        for (int i = 0; i < fertiliser.size(); i++) {
            if (i == fertiliser.size() - 1) {
                str += fertiliser.get(i);
            } else {
                str += fertiliser.get(i) + ",";
            }
        }

        return str;
    }

    public String getPesticide() {
        String str = "";

        for (int i = 0; i < pesticide.size(); i++) {
            if (i == pesticide.size() - 1) {
                str += pesticide.get(i);
            } else {
                str += pesticide.get(i) + ",";
            }
        }

        return str;
    }

    public String getId() {
        return id;
    }

}
