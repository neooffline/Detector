package ru.neooffline.mephi.detector;

import android.content.Context;
import android.content.Intent;
import android.app.Service;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;
import android.os.Binder;
import android.os.IBinder;
import android.widget.TextView;

/**
 * Created by user on 02.03.2018.
 */
public class SameActivity extends AppCompatActivity {

    public void backToMain(View view) {
        Intent backToMain = new Intent(this, MainActivity.class);
        startActivity(backToMain);
    }

    public void writeDataToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("data.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exeprion", "File write failed: " + e.toString());
        }
    }
}

