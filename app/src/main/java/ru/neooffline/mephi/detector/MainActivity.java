package ru.neooffline.mephi.detector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hoho.android.usbserial.bluetooth.BluetoothSPP;
import com.hoho.android.usbserial.driver.UsbSerialPort;
//import app.akexorcist;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private float hum;
    private float temp;
    private boolean isConnected;
    Detector detector_ma;
    ModBusUSB modBusUSB2;
    BluetoothSPP btClass;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detector_ma = new Detector();
        btClass = new BluetoothSPP(this);
        try {
            btClass.connect(98-D3-32-70-BB-0B);
        } catch (IOException e){
            e.printStackTrace();
        }
        TextView textFill_uDat = findViewById(R.id.uDat_var);
        TextView textFill_cDat = findViewById(R.id.cDat_var);
        TextView textFill_tDat = findViewById(R.id.tDat_var);
        TextView textFil_numDat = findViewById(R.id.numDat_var);
        TextView textFill_dateDat = findViewById(R.id.dateDat_var);
        Button startBut = findViewById(R.id.startButton);
        startBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected == false){
                    try {
                        modBusUSB2.Connect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    return;
                }
            }
        });
        RadioButton textFill_Connected = findViewById(R.id.radio1);
        modBusUSB2 = new ModBusUSB(this);
        try {
//            modBusUSB2.Disconnect();
            modBusUSB2.Connect();
            modBusUSB2.SetSerialParams(9600,8,2,UsbSerialPort.PARITY_NONE);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        boolean isConnected = false;
//        System.out.println("fff");
        try {
            modBusUSB2.ReadHoldingRegisters(17,0x01,1);
            hum = modBusUSB2.readPDU().Raw[4];
        } catch (IOException e) {
            e.printStackTrace();
        }
        detector_ma.setConnectionState(modBusUSB2.Connected());
        try {
            modBusUSB2.ReadHoldingRegisters(17,0x02,1);
            temp = modBusUSB2.readPDU().Raw[4];
        } catch (IOException e) {
            e.printStackTrace();
        }

        detector_ma.setDetCapacity((float) 12.00);
        detector_ma.setDetTemperature((float) temp);
        detector_ma.setDetVoltage((float) hum);
        detector_ma.setDetNumber(14);
        detector_ma.setDetDate(2018,3,15,15,15);


        textFil_numDat.setText(String.valueOf(detector_ma.getDetNumber()));
        textFill_dateDat.setText(detector_ma.getDetDate());
        textFill_cDat.setText(String.valueOf(detector_ma.getDetCapacity()));
        textFill_tDat.setText(String.valueOf(detector_ma.getDetTemperature()));
        textFill_uDat.setText(String.valueOf(detector_ma.getDetVoltage()));
        textFill_Connected.setChecked(detector_ma.getConnectionState());


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

}