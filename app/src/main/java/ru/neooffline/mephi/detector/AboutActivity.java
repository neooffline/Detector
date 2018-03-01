package ru.neooffline.mephi.detector;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by user on 26.02.2018.
 */
public class AboutActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    public void sumTwoNumbers (View view){
        Integer a = 15, b=16;
        Integer c = a + b;
    }
}
