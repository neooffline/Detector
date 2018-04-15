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
        Calendar date = new Calendar() {
            @Override
            protected void computeTime() {

            }

            @Override
            protected void computeFields() {

            }

            @Override
            public void add(int field, int amount) {

            }

            @Override
            public void roll(int field, boolean up) {

            }

            @Override
            public int getMinimum(int field) {
                return 0;
            }

            @Override
            public int getMaximum(int field) {
                return 0;
            }

            @Override
            public int getGreatestMinimum(int field) {
                return 0;
            }

            @Override
            public int getLeastMaximum(int field) {
                return 0;
            }
        };
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
    Calendar getDetDate(){
        return detDate;
    }
    int getDetNumber(){
        return detNumber;
    }
}
