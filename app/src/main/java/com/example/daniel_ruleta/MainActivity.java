package com.example.daniel_ruleta;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView ruleta;
    private TextView txt;
    private ConstraintLayout main;
    private SensorManager sm;
    private Sensor sensor;
    private Random random;
    private int angulo;
    private boolean restart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ruleta = findViewById(R.id.imgRuleta);
        txt = findViewById(R.id.txtResultado);
        main = findViewById(R.id.main);
        sm  = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        random = new Random();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String text1 = String.valueOf(event.values[0]);
        txt.setText(text1);
        float valor = Float.parseFloat(text1);
        if(valor == 0){
            angulo = random.nextInt(3600) + 360;
            RotateAnimation rotate = new RotateAnimation(0, angulo,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            rotate.setFillAfter(true);
            rotate.setDuration(3600);
            rotate.setInterpolator(new AccelerateDecelerateInterpolator());
            ruleta.startAnimation(rotate);
            restart = false;
        }else {
            txt.setText("Pasa tu mano para Jugar");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}