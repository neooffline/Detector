package ru.neooffline.mephi.detector;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static ru.neooffline.mephi.detector.modBusUSB.*;


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
    /*UsbManager usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
    List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(usbManager);
    if (this.availableDrivers){
/* return;
    }
    UsbSerialDriver usbSerialDriver = availableDrivers.get(0);
    UsbDeviceConnection connection = usbManager.openDevice(usbSerialDriver.getDevice());
    if (connection == null){
        return;
    }
    UsbSerialPort usbSerialPort = usbSerialDriver.getPorts().get(0);
    try {
        usbSerialPort.open(connection);
        usbSerialPort.setParameters(34800,8,2,UsbSerialPort.PARITY_NONE);
        byte buffer[] = new byte[16];
        int numBytesRead = usbSerialPort.read(buffer,1000);
        Log.d(TAG, "Read " + numBytesRead + "bytes.");
        } catch (IOException e) {
        } finally {
        usbSerialPort.close();
    }*/

    public int clack (View view){

        modBusUSB.SetSerialParams(34800,8,2,UsbSerialPort.PARITY_NONE);
        modBusUSB.Connect();
        modBusUSB.ReadHoldingRegisters(1,1,1);

        int cc = 14444444;
        return cc;
    }
    

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