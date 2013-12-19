package com.example.accelerometerdemo;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager glassSensor;
    private Sensor glassAccelerometer;

    TextView title,xvalue,yvalue,zvalue;
    RelativeLayout layout;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        //grab the main laout from
        layout = (RelativeLayout)findViewById(R.id.container);
        super.onCreate(savedInstanceState);
        //setup the main activity
        setContentView(R.layout.activity_main);

        glassSensor = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        glassAccelerometer= glassSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //call the labels
        title=(TextView)findViewById(R.id.name);
        xvalue=(TextView)findViewById(R.id.xvalue);
        yvalue=(TextView)findViewById(R.id.yvalue);
        zvalue=(TextView)findViewById(R.id.zvalue);


    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy)
    {
        // This is where stuff changes when the acceleration finishes
    }

    @Override
    public final void onSensorChanged(SensorEvent event)
    {
        // returns the values for the different axis
        float x =  event.values[0];
        float y =  event.values[1];
        float z =  event.values[2];


        //display values using TextView
        title.setText(R.string.app_name);
        xvalue.setText("X axis" +"\t\t"+x);
        yvalue.setText("Y axis" + "\t\t" +y);
        zvalue.setText("Z axis" +"\t\t" +z);

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        glassSensor.registerListener(this, glassAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        glassSensor.unregisterListener(this);
    }
}