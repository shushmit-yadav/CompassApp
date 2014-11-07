package com.shushmit.compasdemo;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity implements SensorEventListener {
	 SensorManager manager;
     ImageView ivCompass;
     TextView tvHeading;
     float currentDegree = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ivCompass = (ImageView) findViewById(R.id.ivCompass);
        
        // TextView that will tell the user what degree is he heading
        tvHeading = (TextView) findViewById(R.id.tvHeading);
 
        // initialize your android device sensor capabilities
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        
    }
    
    @Override
    protected void onResume() {	
    	
    	manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    	super.onResume();
    }
    
    @Override
    protected void onPause() {
    	manager.unregisterListener(this);
    	super.onPause();
    }
    
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    	// TODO Auto-generated method stub
    	
    }
    
    @Override
    public void onSensorChanged(SensorEvent event) {
    	 // get the angle around the z-axis rotated
        float degree = Math.round(event.values[0]); 
        tvHeading.setText("Heading: " + Float.toString(degree) + " degrees"); 
        // create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(currentDegree,-degree,Animation.RELATIVE_TO_SELF, 0.5f, 
                Animation.RELATIVE_TO_SELF,
                0.5f);
 
        // how long the animation will take place
        ra.setDuration(210); 
        // set the animation after the end of the reservation status
        ra.setFillAfter(true);
        // Start Animation...
        ivCompass.startAnimation(ra);
        currentDegree = -degree;    	
    }   
}
