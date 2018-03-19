package ru.neooffline.mephi.detector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.util.Random;

import gnu.io.*;
import gnu.io.CommPortIdentifier;
import jssc.*;
import jssc.SerialPort;
import purejavacomm.*;

import static gnu.io.CommPortIdentifier.*;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callSettingsActivity(View view) {
        Intent toSettings = new Intent(this, SettingsActivity.class);
        startActivity(toSettings);
    }

    public void callAboutActivity(View view) {
        Intent toAbout = new Intent(this, AboutActivity.class);
        startActivity(toAbout);
    }
    SerialPort serialPort = new SerialPort(PORT_SERIAL);


//    serialPortFinder.getAllDevices();
    //SerialPort port = new SerialPort(serialModel.getSerialPort()); if (port != null && !port.isOpened()) { try { port.openPort(); } catch (SerialPortException e) { e.printStackTrace(); } }
    //        SerialParameters sp = new SerialParameters(spList[0], BAUD_RATE_38400,8,0,NONE);
        //ModbusMaster modbusMaster = new ModbusMasterFactory().createModbusMasterRTU(sp);
       // modbusMaster.connect();



    public void calc (View view){
        String sensorTemperature = "lala";
        Random random = new Random();
        Random random1 = new Random();
        Double randNumber = random.nextDouble() * 15;
        Double randNumber1 = random1.nextDouble() * 5;
        double sensorVoltage = (double) Math.round(randNumber * 100d) / 100d;
        double sensorCapacity = (double) Math.round(randNumber1 * 1000d) / 1000d;
        TextView textFill_uDat = (TextView) findViewById(R.id.uDat_var);
        TextView textFill_cDat = (TextView) findViewById(R.id.cDat_var);
        TextView textFill_tDat = (TextView) findViewById(R.id.tDat_var);
        textFill_cDat.setText(String.valueOf(sensorCapacity));
        textFill_tDat.setText(sensorTemperature);
        textFill_uDat.setText(String.valueOf(sensorVoltage));
    }
}