package com.cristian.facci.huertofamiliar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import NEGOCIO.Mensajes;

public class sensor extends AppCompatActivity {
    ProgressDialog pDialog;
    TextView tv_h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        tv_h=findViewById(R.id.TV_resultado);
        SensorManager sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);
        final Sensor proximitySensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        pDialog=new ProgressDialog(this);
        if(proximitySensor == null) {
            Mensajes.mostrarMensaje("Error", "No hay sensor", pDialog, sensor.this);
            finish(); // Close app
        }
        SensorEventListener proximitySensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[0] < proximitySensor.getMaximumRange()) {
                    // Detected something nearby
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                } else {
                    // Nothing is nearby
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        sensorManager.registerListener(proximitySensorListener,
                proximitySensor, 2 * 1000 * 1000);
    }
}
