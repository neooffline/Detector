package ru.neooffline.mephi.detector;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.net.ModbusSerialListener;
import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.procimg.SimpleDigitalOut;
import net.wimpi.modbus.procimg.SimpleProcessImage;

import java.util.Random;

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

    public void calc (View view){
        double sensorTemperature = 300.0;
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
        textFill_tDat.setText(String.valueOf(sensorTemperature));
        textFill_uDat.setText(String.valueOf(sensorVoltage));
    }
}