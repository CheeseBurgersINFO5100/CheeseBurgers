/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author siyuanzhang
 */
public class MasterSchedule {
    
    private ArrayList<Airplane> masterSchedule;
    
    public MasterSchedule(){
        
    }

    public ArrayList<Airplane> getMasterSchedule() {
        return masterSchedule;
    }
    
    public void addAirplanesFromOneAirliner(ArrayList<Airplane> airplaneDirectory){
        for(Airplane airplane: airplaneDirectory){
            masterSchedule.add(airplane);
        }
    }
    
    public void addAirplanesFromAirlinerDirectory(ArrayList<Airliner> airlinerDirectory){
        for(Airliner airliner:airlinerDirectory){
            this.addAirplanesFromOneAirliner(airliner.getAirplaneDirectory());
        }
    }
    
    public ArrayList<Airplane> searchAirplaneByFlightNum(ArrayList<Airplane> airplaneDirectory, String flightNum){
        ArrayList<Airplane> searchResult = new ArrayList<Airplane>();
        
        for(Airplane airplane : airplaneDirectory){
            if(airplane.getAirplaneNum().equals(flightNum)){
                searchResult.add(airplane);
                break;
            }
                
        }
        
        return searchResult;
    }
    
    public ArrayList<Airplane> searchAirplaneByDepartureLocation(ArrayList<Airplane> airplaneDirectory, String departureLocation){
        ArrayList<Airplane> searchResult = new ArrayList<Airplane>();
        
        for(Airplane airplane : airplaneDirectory){
            if(airplane.getDepartureLocation().equals(departureLocation))
                searchResult.add(airplane);
        }
        
        return searchResult;
    }
    
    
    public ArrayList<Airplane> searchAirplaneByArrivalLocation(ArrayList<Airplane> airplaneDirectory, String arrivalLocation){
        ArrayList<Airplane> searchResult = new ArrayList<Airplane>();
        
        for(Airplane airplane : airplaneDirectory){
            if(airplane.getArrivalLocation().equals(arrivalLocation))
                searchResult.add(airplane);
        }
        
        return searchResult;
    }
    
    public ArrayList<Airplane> searchAirplaneByPreferredTime(ArrayList<Airplane> airplaneDirectory, String preferredTime){
        ArrayList<Airplane> searchResult = new ArrayList<Airplane>();
        
        for(Airplane airplane : airplaneDirectory){
            if(airplane.getPreferTime().equals(preferredTime))
                searchResult.add(airplane);
        }
        
        return searchResult;
    }
    
    public ArrayList<Airplane> searchAirplaneByDate(ArrayList<Airplane> airplaneDirectory, String dateString){
        ArrayList<Airplane> searchResult = new ArrayList<Airplane>();
        
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try{
            Date date= sdf.parse(dateString);
            for(Airplane airplane : airplaneDirectory){
            if(airplane.getDate().compareTo(date) == 0)
                searchResult.add(airplane);
        }
        }catch(Exception e){
            return searchResult;
        }
       
        return searchResult;
    }
}
