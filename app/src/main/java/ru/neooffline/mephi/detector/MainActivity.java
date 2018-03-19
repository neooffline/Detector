package ru.neooffline.mephi.detector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.util.SerialParameters;

import java.util.Random;

import jssc.SerialPort;
import jssc.SerialPortList;

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
//   SimpleMater port = new SimpleMater();
    try {
    SerialParameters serialParameters = new SerialParameters();
    String[] devList = SerialPortList.getPortNames();
    if(devList.length >0) {
        serialParameters.getPortName(devList[0]);
        serialParameters.setBaudRate(38400);
    }

}
    public void calc (View view){
        int sensorTemperature = 12424;
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