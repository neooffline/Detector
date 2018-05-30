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
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    /*private float hum;
    private float temp;*/

    int schet = 14;
    boolean isConnected = false;
    boolean isPressed = false;
    Detector detector_ma;
    ModBusUSB modBusUSB2;
    BluetoothSPP btClass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detector_ma = new Detector();
        btClass = new BluetoothSPP(this);

        addData();
        Log.i("onCreate","Vse OK!, счет: "+ schet + " нажата: "+ isPressed);
        fillData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        int counter = 0;

        //        MyAsyncTask myAsyncTask = new MyAsyncTask(this);
//        myAsyncTask.doInBackground();

    }
    private void addData(){
        for (int counter = 0; counter < 10; counter++) {
            detector_ma.detCapacity.add(counter+1);
            Log.i("Array", "fill Capacity " + counter);
            detector_ma.detTemperature.add(counter+2);
            Log.i("Array", "fill Temperature" + counter);
            detector_ma.detVoltage.add(counter+3);
            Log.i("Array", "fill Voltage" + counter);
            detector_ma.setDetNumber(counter+4);
            Log.i("Array", "fill Number" + counter);
        }
        detector_ma.setConnectionState(true);
        detector_ma.setDetDate(2018, 3, 15, 15, 15);
    }
    private void fillData(){
        TextView textFill_uDat = findViewById(R.id.uDat_var);
        TextView textFill_cDat = findViewById(R.id.cDat_var);
        TextView textFill_tDat = findViewById(R.id.tDat_var);
        TextView textFil_numDat = findViewById(R.id.numDat_var);
        TextView textFill_dateDat = findViewById(R.id.dateDat_var);
        RadioButton textFill_Connected = findViewById(R.id.radio1);
        for (int counter = 0; counter < 10; counter++) {
            textFil_numDat.setText(String.valueOf(detector_ma.getDetNumber()));
            Log.i("Fill Number", "Number: "+counter);
            textFill_cDat.setText(String.valueOf(detector_ma.detCapacity.get(counter)));
            Log.i("Fill Capacity", "C: "+counter);
            textFill_tDat.setText(String.valueOf(detector_ma.detTemperature.get(counter)));
            Log.i("Fill Temp", "T: "+counter);
            textFill_uDat.setText(String.valueOf(detector_ma.detVoltage.get(counter)));
            Log.i("Fill Voltage", "V: "+counter);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        textFill_Connected.setChecked(detector_ma.getConnectionState());
        Log.i("Fill Check", "check: " + detector_ma.getConnectionState());
        textFill_dateDat.setText(detector_ma.getDetDate());
        Log.i("Fill Date", "Date: "+detector_ma.getDetDate());
    }
    private void startStop(View view){
        isPressed = !isPressed;
        schet++;
        Log.i("Button", "Нажата " + schet + " раз " + isPressed);
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

            int i = 0;
            while (isPressed) {
                //                    modBusUSB2.ReadHoldingRegisters(17, 0x01, 1);
//                    detector_ma.detVoltage.add(modBusUSB2.readPDU().Raw[4]);
                detector_ma.detVoltage.add(i);
                Log.d("USB", "read and write iteration ");
                //                    modBusUSB2.ReadHoldingRegisters(17, 0x02, 1);
//                    detector_ma.detTemperature.add(modBusUSB2.readPDU().Raw[4]);
                detector_ma.detTemperature.add(i);
                Log.d("USB", "read and write iteration ");
                i++;
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