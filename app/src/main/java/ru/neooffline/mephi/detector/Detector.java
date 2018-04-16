package ru.neooffline.mephi.detector;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 04.04.2018.
 */

public class Detector {
    float detTemperature;
    float detCapacity;
    float detVoltage;
    Calendar detDate;
    int detNumber;

    public void setDetTemperature(float temp){
        this.detTemperature = temp;
    }
    public void setDetCapacity(float capacity){
        this.detCapacity = capacity;
    }
    public void setDetVoltage(float voltage){
        this.detVoltage = voltage;
    }
    public void setDetNumber(int number){
        this.detNumber = number;
    }
    public void setDetDate(int year, int month, int day, int hour, int minute){
        final Calendar date = Calendar.getInstance();
        date.set(year,month,day,hour,minute);
        this.detDate = date;
    }
    float getDetTemperature(){
        return detTemperature;
    }
    float getDetCapacity(){
        return detCapacity;
    }
    float getDetVoltage(){
        return detVoltage;
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
    int getDetNumber(){
        return detNumber;
    }
}
