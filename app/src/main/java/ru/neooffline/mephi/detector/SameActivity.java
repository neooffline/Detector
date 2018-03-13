package ru.neooffline.mephi.detector;

import android.content.Context;
import android.content.Intent;
import android.app.Service;
import android.os.AsyncTask;
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
public class SameActivity extends Service {
    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        SameActivity getService() {
            return SameActivity.this;
        }
    }

    private TextView tv,tv1,tv2;


    public int onStartCommand(Intent intent, int flag, int startId) {
        return START_STICKY;
    }

    public IBinder onBind(Intent intent) {
        return mBinder;
    }

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

