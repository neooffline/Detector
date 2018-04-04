package ru.neooffline.mephi.detector;

import java.util.Date;

/**
 * Created by user on 04.04.2018.
 */

public class Detector {
    float detTemperature;
    float detCapacity;
    float detVoltage;
    Date detDate;
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
    float getDetTemperature(){
        return detTemperature;
    }
    float getDetCapacity(){
        return detCapacity;
    }
    float getDetVoltage(){
        return detVoltage;
    }
    Date getDetDate(){
        return detDate;
    }
    int getDetNumber(){
        return detNumber;
    }
}
