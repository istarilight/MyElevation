package com.hailong.elevation.gui;


import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hailong.elevation.client.ElevationClient;
import com.hailong.myelevation.R;
import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;


public class ResultActivity extends Activity {
	
	private double longitude = -1;
	private double latitude = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		Bundle extras = getIntent().getExtras();
		
		if(extras != null){
			this.longitude = (Double) extras.get("longitude");
			this.latitude = (Double) extras.get("latitude");
		}
		//play animation here
		//call google services
		ElevationClient client = new ElevationClient();
		Double[] location = new Double[2];
		location[0] = longitude;
		location[1] = latitude;
		
		AsyncTask<Double, Void, Double> elevationFeet = client.execute(longitude, latitude);
		try {
			GraphViewSeries view = null;
			// init example series data  
			GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {  
			      new GraphViewData(1, 8848d)  
			      , new GraphViewData(2, 1.5d)  
			      , new GraphViewData(3, -2000d)  
			       
			});  
			
			GraphView graphView = new BarGraphView (  
			      this // context  
			      , "GraphViewDemo" // heading  
			);  
			graphView.addSeries(exampleSeries); // data  
			  
			TextView textView = (TextView) findViewById(R.id.textView1);  
			//Uncomment this line for a graph
			//((RelativeLayout) textView.getParent()).addView(graphView);  
			System.out.println( elevationFeet.get());
			TextView text = (TextView) this.findViewById(R.id.textView1);
			text.setText("Elevation in meters: " +elevationFeet.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

}
