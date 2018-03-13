package ru.neooffline.mephi.detector;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public class AsyncVolt extends AsyncTask<Void, String, Double> {

        @Override
        protected Double doInBackground(Void... params) {
            //double sensoreVoltage;
            //double sensoreCapacity;
            double sensorTemperature = 300.0;
            for (int i = 1; i < 100; i++) {
                Random random = new Random();
                Random random1 = new Random();
                Double randNumber = random.nextDouble() * 15;
                Double randNumber1 = random1.nextDouble() * 5;
                double sensorVoltage = (double) Math.round(randNumber * 100d) / 100d;
                double sensorCapacity = (double) Math.round(randNumber1 * 1000d) / 1000d;
                sensorTemperature = sensorTemperature + i;
            }
            return null;
        }
       // protected void AsyncVolt(Void result){
         //   super.onPostExecute(result);
        //}
        protected void onProgressUpdate(String... values){
            textFill_uDat.setText(values[0]);
            textFill_cDat.setText(values[1]);
            textFill_tDat.setText(values[2]);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void callAboutActivity(View view) {
        Intent toAbout = new Intent(this, AboutActivity.class);
        startActivity(toAbout);
    }

    public void callSettingsActivity(View view) {
        Intent toSettings = new Intent(this, SettingsActivity.class);
        startActivity(toSettings);
    }
    TextView textFill_uDat = (TextView) findViewById(R.id.uDat_var);
    TextView textFill_cDat = (TextView) findViewById(R.id.cDat_var);
    TextView textFill_tDat = (TextView) findViewById(R.id.tDat_var);
    public void testClick (View view) {
        AsyncVolt asyncVolt = new AsyncVolt();
        asyncVolt.execute();
    }
}