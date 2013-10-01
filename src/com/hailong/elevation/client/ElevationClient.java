package com.hailong.elevation.client;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.os.AsyncTask;

public class ElevationClient extends AsyncTask<Double, Void, Double>{
	
	public double getElevationFromGoogleMaps(double longitude, double latitude) {
        double result = Double.NaN;
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        String url = "http://maps.googleapis.com/maps/api/elevation/"
                + "xml?locations=" + String.valueOf(latitude)
                + "," + String.valueOf(longitude)
                + "&sensor=true";
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet, localContext);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                int r = -1;
                StringBuffer respStr = new StringBuffer();
                while ((r = instream.read()) != -1){
                    respStr.append((char) r);
                }
                String tagOpen = "<elevation>";
                String tagClose = "</elevation>";
                
                if (respStr.indexOf(tagOpen) != -1) {
                    int start = respStr.indexOf(tagOpen) + tagOpen.length();
                    int end = respStr.indexOf(tagClose);
                    String value = respStr.substring(start, end);
                    result = (double)(Double.parseDouble(value)*3.2808399); // convert from meters to feet
                }
                instream.close();
            }
        } catch (ClientProtocolException e) {} 
        catch (IOException e) {}

        return result;
    }
	
	
	
	public static void main(String[]args){
		ElevationClient client = new ElevationClient();
		double temp = client.getElevationFromGoogleMaps(2.352426, 48.856908);
		System.out.println("Elevation for Paris is " + temp +" feet Above the sea level");
	}



	@Override
	protected Double doInBackground(Double... params) {
		System.out.println("Elevation" + params[0] + params[1]);
		return this.getElevationFromGoogleMaps(params[0], params[1]);
		
	}





}