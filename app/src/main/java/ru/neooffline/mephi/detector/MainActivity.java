package ru.neooffline.mephi.detector;

import android.content.Intent;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hoho.android.usbserial.driver.UsbSerialPort;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private UsbManager mUsbManager;
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

    public boolean clack (View view) throws IOException {

        ModBusUSB modBusUSB1 = new ModBusUSB(this);
        modBusUSB1.SetSerialParams(34800,8,2,UsbSerialPort.PARITY_NONE);
        modBusUSB1.Connect();
        modBusUSB1.ReadHoldingRegisters(1,1,1);
        boolean connect = modBusUSB1.Connected();
        return connect;
    }

    public void calc (View view) throws IOException {

        ModBusUSB modBusUSB2 = new ModBusUSB(this);
        modBusUSB2.SetSerialParams(38400,8,2,UsbSerialPort.PARITY_NONE);
        modBusUSB2.Connect();
        boolean connect = modBusUSB2.Connected();
//        boolean connect = true;
        //int sd = modBusUSB2.ActivePort().getSerial().getBytes().length;
        modBusUSB2.ReadHoldingRegisters(1,1,1);
        int sd = 124;
        Detector detector = new Detector();
        detector.setDetCapacity((float) 12.00);
        detector.setDetTemperature((float) 122);
        detector.setDetVoltage((float) 24);
        detector.setDetNumber(sd);
        detector.setDetDate(2018,3,15,15,15);

//        String sensorTemperature = "lala";
//        Random random = new Random();
//        Random random1 = new Random();
//        Double randNumber = random.nextDouble() * 15;
//        Double randNumber1 = random1.nextDouble() * 5;
//        double sensorVoltage = (double) Math.round(randNumber * 100d) / 100d;
//        double sensorCapacity = (double) Math.round(randNumber1 * 1000d) / 1000d;
        TextView textFill_uDat = findViewById(R.id.uDat_var);
        TextView textFill_cDat = findViewById(R.id.cDat_var);
        TextView textFill_tDat = findViewById(R.id.tDat_var);
        TextView textFil_numDat =  findViewById(R.id.numDat_var);
        TextView textFill_dateDat = findViewById(R.id.dateDat_var);
        RadioButton textFill_Connected = findViewById(R.id.radio1);
        textFil_numDat.setText(String.valueOf(detector.getDetNumber()));
        textFill_dateDat.setText(detector.getDetDate());
        textFill_cDat.setText(String.valueOf(detector.getDetCapacity()));
        textFill_tDat.setText(String.valueOf(detector.getDetTemperature()));
        textFill_uDat.setText(String.valueOf(detector.getDetVoltage()));
        textFill_Connected.setChecked(connect);

    }
}