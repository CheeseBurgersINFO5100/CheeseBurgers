/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author siyuanzhang
 */
public class Airplane {
    private String airplaneNum;
    private String departureLocation;
    private String arrivalLocation;
    private String preferTime;
    private Date arrivalDate;
    private Date departureDate;
    private boolean[][] seatsTable;
    private int availableSeatsNum;

    
    public Airplane(){
        //Default value of boolean array is false
        this.seatsTable = new boolean[6][25];
        
    }
    
    public String getAirplaneNum() {
        return airplaneNum;
    }

    public void setAirplaneNum(String airplaneNum) {
        this.airplaneNum = airplaneNum;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public String getPreferTime() {
        return preferTime;
    }

    public void setPreferTime(String preferTime) {
        this.preferTime = preferTime;
    }

    public Date getArrivalDate() {

        return arrivalDate;
    }
    
    public Date getDepartureDate(){
        return departureDate;
    }

    public int[] getAvailableCol() {
        int[] i = new int[2];
        
        if (isColSeatAvailable(0) || isColSeatAvailable(1) || isColSeatAvailable(2))
            i[0] = 1;
        
        if (isColSeatAvailable(3) || isColSeatAvailable(4) || isColSeatAvailable(5))
            i[1] = 2;
        
        return i;
    }

    public String[] getAvailableType(int col) {
        String [] i = new String[3];
        if (col == 1){
            if(isColSeatAvailable(0) || isColSeatAvailable(3))
                i[0] = "Window";      
            if(isColSeatAvailable(1))
                i[1] = "Middle";
            if(isColSeatAvailable(2))
                i[2] = "Aisle";
        }
        if (col ==2) {
            if(isColSeatAvailable(3))
                i[0] = "Window"; 
            if(isColSeatAvailable(1))
                i[1] = "Middle";
            if(isColSeatAvailable(2))
                i[2] = "Aisle";
        }        
        return i;
    }

    public int[] getAvailableRowNum(int col, String type){
        int[] i = new int[25];
        int colNum = 0;
        int count = 0;
        if (col == 1 && type.equals("Window")){
            colNum = 0;
            for (boolean selectedCol : this.seatsTable[colNum]){
                if (selectedCol == false)
                    i[count] = count + 1;
                count++;
            }
        }
        
        if(col == 1 && type.equals("Middle")){
            colNum = 1;         
            for (boolean selectedCol : this.seatsTable[colNum]){                
                if (selectedCol == false)
                    i[count] = count + 1;
                count++;
            }
        }
        
        if(col ==1 && type.equals("Aisle")){
            colNum = 2;         
            for (boolean selectedCol : this.seatsTable[colNum]){
                if (selectedCol == false)
                    i[count] = count + 1;
                count++;
            }
        }
        
        if (col == 2 && type.equals("Window")){
            colNum = 3;         
            for (boolean selectedCol : this.seatsTable[colNum]){
                if (selectedCol == false)
                    i[count] = count + 1;
                count++;
            }
        }
        
        if (col == 2 && type.equals("Middle")){
            colNum = 4;         
            for (boolean selectedCol : this.seatsTable[colNum]){
                if (selectedCol == false)
                    i[count] = count + 1;
                count++;
            }
        }
        
        if (col == 2 && type.equals("Aisle")){
            colNum = 5;         
            for (boolean selectedCol : this.seatsTable[colNum]){
                if (selectedCol == false)
                    i[count] = count + 1;
                count++;
            }

        }
        return i; 
    }
     


    
    public void setArrivalDate(String arrivalDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try{
            this.arrivalDate = sdf.parse(arrivalDate);
        }catch(Exception e){
            return;
        }
    }
    
    public void setDepartureDate(String departureDate){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try{
            this.departureDate = sdf.parse(departureDate);
        }catch(Exception e){
            return;
        }
    }
    
    public boolean isDateUnnormal(){
        return this.arrivalDate.before(this.departureDate);
    }
    
    public boolean[][] getSeatsTable() {
        return seatsTable;
    }
    
    public void updateSeatsTable(int colRowNum){
        for(int i = 0;i < this.seatsTable[colRowNum].length;i++){
            this.seatsTable[colRowNum][i] = true;
        }
        
        for(boolean seatRow[] : this.seatsTable){
            seatRow[colRowNum] = true;
        }
        
    }
    
    public int getAvailableSeatsNum() {
        return availableSeatsNum;
    }

    public void updateAvailableSeatsNum() {
        this.availableSeatsNum = 0;
        for(boolean eachColumn[] : this.seatsTable){
            for(boolean eachRow : eachColumn){
                if(eachRow == false)
                    this.availableSeatsNum++;
            }

        }
            
    }
    public boolean isColSeatAvailable(int colNum){
        for(boolean c : this.seatsTable[colNum]){
            if (c == false)   
            return true;               
        }
        return false;  
    }
    
    
    public void setSeatSelected(int col, String type, int rowNum){
        
        if (col == 1 && type.equals("Window") )
            seatsTable[0][rowNum - 1] = true;
        
        if(col == 1 && type.equals("Middle"))
            seatsTable[1][rowNum - 1] = true;
        
        if(col ==1 && type.equals("Aisle"))
            seatsTable[2][rowNum - 1] = true;
        
        if (col == 2 && type.equals("Window"))
            seatsTable[3][rowNum - 1] = true;
        
        if (col == 2 && type.equals( "Middle"))
            seatsTable[4][rowNum - 1] = true;
         
        if (col == 2 && type.equals("Aisle"))
            seatsTable[5][rowNum - 1] = true;
               
        
    }

    @Override
    public String toString() {
        return airplaneNum;
    }
    
    
    
}
