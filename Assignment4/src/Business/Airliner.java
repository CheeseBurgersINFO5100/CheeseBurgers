/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;

/**
 *
 * @author siyuanzhang
 */
public class Airliner {
    private String name;
    private int numOfFlight;
    private String customerEvaluation;
    private float delayProbability;
    private ArrayList<Airplane> airplaneDirectory;
    
    public Airliner(){
        this.airplaneDirectory = new ArrayList<Airplane>();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfFlight() {
        return numOfFlight;
    }

    public void setNumOfFlight(int numOfFlight) {
        this.numOfFlight = numOfFlight;
    }

    public String getCustomerEvaluation() {
        return customerEvaluation;
    }

    public void setCustomerEvaluation(String customerEvaluation) {
        this.customerEvaluation = customerEvaluation;
    }

    public float getDelayProbability() {
        return delayProbability;
    }

    public void setDelayProbability(float delayProbability) {
        this.delayProbability = delayProbability;
    }

    public ArrayList<Airplane> getAirplaneDirectory() {
        return airplaneDirectory;
    }

    public void setAirplaneDirectory(ArrayList<Airplane> airplaneDirectory) {
        this.airplaneDirectory = airplaneDirectory;
    }
    
    public Airplane addAirplane(){
        Airplane airplane = new Airplane();
        airplaneDirectory.add(airplane);
        return airplane;
    }
    
    public void deleteAirplane(Airplane airplane){
        airplaneDirectory.remove(airplane);
        
    }
    
    public String toString(){
        return name;
    }
}
