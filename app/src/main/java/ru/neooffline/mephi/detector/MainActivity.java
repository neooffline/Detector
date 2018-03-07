package ru.neooffline.mephi.detector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        }
    public void callAboutActivity (View view){
        Intent toAbout = new Intent(this, AboutActivity.class);
        startActivity(toAbout);
    }
    public void callSettingsActivity (View view){
        Intent toSettings = new Intent(this, SettingsActivity.class);
        startActivity(toSettings);
    }
    public void sameActivity (View view){
        Intent toSameActivity = new Intent(this, SameActivity.class);
        startActivity(toSameActivity);
    }
    public void textFill (View view) {
        final TextView textFill_uDat = (TextView) findViewById(R.id.uDat_var);
        final TextView textFill_cDat = (TextView) findViewById(R.id.cDat_var);
        final TextView textFill_tDat = (TextView) findViewById(R.id.tDat_var);
        textFill_uDat.setText(String.valueOf(SameActivity.getVoltageValue()));
        textFill_cDat.setText(String.valueOf(SameActivity.getCapacityValue()));
        textFill_tDat.setText(String.valueOf(SameActivity.getTemperatureValue()));
        //textFill_uDat.autofill(R.integer.b_a);
    }
}
