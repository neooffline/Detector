package ru.neooffline.mephi.detector;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hoho.android.usbserial.bluetooth.BluetoothSPP;
import com.hoho.android.usbserial.driver.UsbSerialPort;
//import app.akexorcist;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    /*private float hum;
    private float temp;*/
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
//        try {
////            btClass.connect(98-D3-32-70-BB-0B);
            Log.d("Bluetooth","trying to connect");
//        } catch (IOException e){
//            e.printStackTrace();
//        }
        TextView textFill_uDat = findViewById(R.id.uDat_var);
        TextView textFill_cDat = findViewById(R.id.cDat_var);
        TextView textFill_tDat = findViewById(R.id.tDat_var);
        TextView textFil_numDat = findViewById(R.id.numDat_var);
        TextView textFill_dateDat = findViewById(R.id.dateDat_var);
        RadioButton textFill_Connected = findViewById(R.id.radio1);

//        boolean isConnected = false;
//        System.out.println("fff");

        for (int i = 0; i < 300; i++) {
            textFill_cDat.setText(String.valueOf(detector_ma.detCapacity.get(i)));
            Log.d("Array","fill Capacity");
            textFill_tDat.setText(String.valueOf(detector_ma.detTemperature.get(i)));
            Log.d("Array","fill Temperature");
            textFill_uDat.setText(String.valueOf(detector_ma.detVoltage.get(i)));
            Log.d("Array","fill Voltage");
        }
        detector_ma.setConnectionState(modBusUSB2.Connected());
        detector_ma.setDetNumber(14);
        detector_ma.setDetDate(2018,3,15,15,15);
        textFil_numDat.setText(String.valueOf(detector_ma.getDetNumber()));
        textFill_dateDat.setText(detector_ma.getDetDate());
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
    public class MyAsyncTask extends AsyncTask<String, Void, Integer> {
        private Context mContext;

        public MyAsyncTask(Context context) {
            mContext = context.getApplicationContext();
        }

        @Override
        protected void onPreExecute() {
            modBusUSB2 = new ModBusUSB(mContext);
            Toast.makeText(mContext, "Start", Toast.LENGTH_LONG);
            try {
                modBusUSB2.Connect();
                Toast.makeText(mContext, "Connecting to USB", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                modBusUSB2.SetSerialParams(9600, 8, 2, UsbSerialPort.PARITY_NONE);
                Toast.makeText(mContext, "Set bound ", Toast.LENGTH_LONG);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        protected Integer doInBackground(String... strings) {
            try {
                modBusUSB2.ReadHoldingRegisters(17, 0x01, 1);
                detector_ma.detVoltage.add(modBusUSB2.readPDU().Raw[4]);
                Log.d("USB", "read and write iteration ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                modBusUSB2.ReadHoldingRegisters(17, 0x02, 1);
                detector_ma.detTemperature.add(modBusUSB2.readPDU().Raw[4]);
                Log.d("USB", "read and write iteration ");
            } catch (IOException e) {
                e.printStackTrace();
            }

            detector_ma.detCapacity.add((float) 12.00);
            Log.d("Array", "write Capacity");
            /*detector_ma.detTemperature.add(temp);
            Log.d("Array","write Temperature");
            detector_ma.detVoltage.add(hum);
            Log.d("Array","write voltage");*/
            try {
                Thread.sleep(100);
                Log.d("USB", "sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return -1;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            if (integer != -1) {
                Toast.makeText(mContext, "Got the following code: " + integer, Toast.LENGTH_LONG).show();
            }
        }
    }

}