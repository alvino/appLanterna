package br.com.applanterna;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LanternaActivity extends Activity {

	Camera cam = null;
	private Button btnOnOff;
	private boolean isFlashLight = false; 

	
	public void turnOnFlashLight() {
		
		
	    try {
	        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
	            cam = Camera.open();
	            Parameters p = cam.getParameters();
	            p.setFlashMode(Parameters.FLASH_MODE_TORCH);
	            cam.setParameters(p);
	            cam.startPreview();
	            btnOnOff.setCompoundDrawablesWithIntrinsicBounds( R.drawable.lanterna_ligada, 0, 0, 0);
	            isFlashLight = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void turnOffFlashLight() {
		
		
	    try {
	        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
	            cam.stopPreview();
	            cam.release();
	            cam = null;
	            btnOnOff.setCompoundDrawablesWithIntrinsicBounds( R.drawable.lanterna_desligada, 0, 0, 0);
	            isFlashLight = false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lanterna);
		
		btnOnOff = (Button) findViewById(R.id.btnOnOff);
		
		btnOnOff.setOnClickListener(new OnClickListener() {
						
			@Override
			public void onClick(View v) {
					if(isFlashLight){
						turnOffFlashLight();
					} else {
						turnOnFlashLight();
					}
			}
		});
		
		turnOnFlashLight();
		
	}

}
