package ru.neooffline.mephi.detector;

import java.util.Calendar;

/**
 * Created by user on 04.04.2018.
 */

public class Detector {
    private String detName;
    private int detNumber;
    private Calendar detDate;
    private float detTemperature;
    private float detCapacity;
    private float detVoltage;
    private boolean connectionState;

    public String getDetName() {
        return detName;
    }
    boolean getConnectionState(){return connectionState;}


    int getDetNumber(){
        return detNumber;
    }

    public void setDetDate(int year, int month, int day, int hour, int minute){
        final Calendar date = Calendar.getInstance();
        date.set(year,month,day,hour,minute);
        this.detDate = date;
    }

    StringBuilder getDetDate(){
//        final Calendar calendar = Calendar.getInstance();
        int year = detDate.get(Calendar.YEAR);
        int month = detDate.get(Calendar.MONTH);
        int day = detDate.get(Calendar.DAY_OF_MONTH);
        int hour = detDate.get(Calendar.HOUR);
        int minute = detDate.get(Calendar.MINUTE);
        StringBuilder str = new StringBuilder().append(day).append("-").append(month).append("-").append(year)
                .append("  ").append(hour).append(":").append(minute);
        return str;
    }


    float getDetCapacity(){
        return detCapacity;
    }
    float getDetTemperature(){
        return detTemperature;
    }
    float getDetVoltage(){
        return detVoltage;
    }

    public void setDetCapacity(float capacity){
        this.detCapacity = capacity;
    }
    public void setDetTemperature(float temp){
        this.detTemperature = temp;
    }
    public void setDetVoltage(float voltage){
        this.detVoltage = voltage;
    }
    public void setDetName(String detName) {
        this.detName = detName;
    }
    public void setConnectionState(boolean state) {
        this.connectionState = state;
    }
    public void setDetNumber(int number){
        this.detNumber = number;
    }
}
