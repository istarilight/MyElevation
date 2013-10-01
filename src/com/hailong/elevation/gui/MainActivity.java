package com.hailong.elevation.gui;

import com.hailong.myelevation.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private double longitude = -1;
	private double latitude = -1;
	private Location location;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.setListeners();
		final Button next = (Button) findViewById(R.id.findMyElevationButton);
		next.setBackgroundResource(R.drawable.intranet_icon);
		



	}

	private void setListeners(){
		
		Button myButton = (Button) findViewById(R.id.findMyElevationButton);
		final LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
		
		final LocationListener locationListener = new LocationListener() {

			public void onLocationChanged(Location location) {
				longitude = location.getLongitude();
				latitude = location.getLatitude();
			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub

			}
		};
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

		myButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
				Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				findMyElevation(location);

			}
		});
		
	}
	
	private void findMyElevation(Location location){
	
		if(location != null){
			Intent intent = new Intent(this, ResultActivity.class);
			intent.putExtra("longitude", location.getLongitude());
			intent.putExtra("latitude", location.getLatitude());
			startActivity(intent);
			System.out.println("current location: " + location.getLongitude() + " " + location.getLatitude());
		}
		else{
			//no GPS, try again later
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}




}
