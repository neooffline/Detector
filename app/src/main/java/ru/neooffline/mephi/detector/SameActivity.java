package ru.neooffline.mephi.detector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 * Created by user on 02.03.2018.
 */
public class SameActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_same_page);
    }
    public void backToMain (View view){
        Intent backToMain = new Intent(this, MainActivity.class);
        startActivity(backToMain);

    }
    public void writeDataToFile (String data, Context context){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("data.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e){
            Log.e("Exeprion", "File write failed: "+e.toString());
        }
    }
    public static double getVoltageValue(){
        double[] sensoreVoltageArray = new double[10];
        for (int i = 1; i < 10; i++) {
            Random random = new Random();
            Double randNumber = random.nextDouble() * 15;
            sensoreVoltageArray[i] = randNumber;
        }
        for (int i = 1; i <10; i++) {
            return sensoreVoltageArray[i];
            }
        return 0;
    }
    public static double getCapacityValue(){
        double[] sensoreCapacityArray = new double[10];
        for (int i = 1; i < 10; i++){
            Random random = new Random();
            Double randNumber = random.nextDouble() *5;
            sensoreCapacityArray[i] = randNumber;
        }
        for (int i = 1; i <10; i++){
            return sensoreCapacityArray[i];
        }
        return 0;
    }
    public static double getTemperatureValue(){
        Double sensoreTempereture = 300.0;
        return sensoreTempereture;
    }
}

