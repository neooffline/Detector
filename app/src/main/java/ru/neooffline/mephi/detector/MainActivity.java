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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callSettingsActivity(View view) {
        Intent toSettings = new Intent(this, SettingsActivity.class);
        startActivity(toSettings);
    }
    public void callSerialConsole(View view) {
        Intent toConsole = new Intent(this, SerialConsoleActivity.class);
        startActivity(toConsole);
    }

    public void callAboutActivity(View view) {
        Intent toAbout = new Intent(this, DeviceListActivity.class);
        startActivity(toAbout);
    }

    public void calc (View view) throws IOException {

        ModBusUSB modBusUSB2 = new ModBusUSB(this);
        modBusUSB2.Connect();
        modBusUSB2.SetSerialParams(9600,8,2,UsbSerialPort.PARITY_NONE);
        boolean isConnected = false;
        modBusUSB2.ReadHoldingRegisters(17,0,1);
        isConnected = modBusUSB2.Connected();
        int k = modBusUSB2.ReadRegPDU().length;
//        void disconnect(View view) {
//            modBusUSB2.Disconnect();
//        }
//        int k = 11;
        Detector detector = new Detector();
        detector.setDetCapacity((float) 12.00);
        detector.setDetTemperature((float) 122);
        detector.setDetVoltage(k);
        detector.setDetNumber(14);
        detector.setDetDate(2018,3,15,15,15);

        TextView textFill_uDat = findViewById(R.id.uDat_var);
        TextView textFill_cDat = findViewById(R.id.cDat_var);
        TextView textFill_tDat = findViewById(R.id.tDat_var);
        TextView textFil_numDat = findViewById(R.id.numDat_var);
        TextView textFill_dateDat = findViewById(R.id.dateDat_var);
        RadioButton textFill_Connected = findViewById(R.id.radio1);
        textFil_numDat.setText(String.valueOf(detector.getDetNumber()));
        textFill_dateDat.setText(detector.getDetDate());
        textFill_cDat.setText(String.valueOf(detector.getDetCapacity()));
        textFill_tDat.setText(String.valueOf(detector.getDetTemperature()));
        textFill_uDat.setText(String.valueOf(detector.getDetVoltage()));
        textFill_Connected.setChecked(isConnected);

    }
}