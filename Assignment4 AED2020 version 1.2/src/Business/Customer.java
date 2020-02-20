/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author siyuanzhang
 */
public class Customer {
    private String name;
    private String airplanePicked;
    private int seatColumn;
    private String seatType;
    private int seatRowNum;
    
    public Customer(){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirplanePicked() {
        return airplanePicked;
    }

    public void setAirplanePicked(String airplanePicked) {
        this.airplanePicked = airplanePicked;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getSeatRowNum() {
        return seatRowNum;
    }

    public void setSeatRowNum(int seatRowNum) {
        this.seatRowNum = seatRowNum;
    }

    @Override
    public String toString() {
        return name;
    }

    
    
    
    
    
}
