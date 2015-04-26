package com.example.housing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.housing.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Addplot extends MainActivity {
	Spinner spn;
	TextView restv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addplot);
		spn=(Spinner)findViewById(R.id.Category1);
		restv=(TextView)findViewById(R.id.displaycategory);
		
		Button confirmsub=(Button) findViewById(R.id.Confirm1);
		
		confirmsub.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				add();
			}
		});
		
	}
	
	public void add()
	{
		EditText propid=(EditText) findViewById(R.id.propid);
		EditText Location=(EditText) findViewById(R.id.LOCATION1);
		//EditText usn=(EditText) findViewById(R.id.Ownername1);
		EditText price=(EditText) findViewById(R.id.Rate1);
		EditText Area=(EditText) findViewById(R.id.Area1);
		Spinner Category=(Spinner) findViewById(R.id.Category1);
		
		String property_id=propid.getText().toString();
		String location=Location.getText().toString();
		//String user_name=usn.getText().toString();
		String user_name=MainActivity.usn;
		String rate=price.getText().toString();
		String area=Area.getText().toString();
		
		String ca=Category.getSelectedItem().toString();
		char category=ca.charAt(0);
		
		String server="http://land1234.comxa.com/prop.php?";
		
		String query="property_id="+property_id+"&location="+location+"&user_name="+user_name+"&rate="+rate+"&area="+area+"&category="+category;
		
		final String fullquery=server+query;
		
		Toast.makeText(getApplicationContext(), fullquery, 0).show();
		
		//final String fullquery="http://land1234.comxa.com/prop.php?property_id=pr1&location=abc&user_name=kr&rate=1000&area=1000sqft&category=residential";
		
		DownloadWebpageTask task=new DownloadWebpageTask();
		task.execute(fullquery);
		
		Intent intent=new Intent(this,Sellerprop.class);
		startActivity(intent);
		
		
	}
	
	
	private class DownloadWebpageTask extends AsyncTask<String , Void , String>
	{

		private String response="";
		
		@Override
		protected String doInBackground(String... urls) 
		{
			for (String url : urls) 
			{
	        DefaultHttpClient client = new DefaultHttpClient();
	        HttpGet httpGet = new HttpGet(url);
	        try 
	          {
	          HttpResponse execute = client.execute(httpGet);
	          InputStream content = execute.getEntity().getContent();

	          BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
	          String s = "";
	          while ((s = buffer.readLine()) != null) {
	            response += s;
	          }

	        } 
	        catch (Exception e) 
	        {
	          e.printStackTrace();
	        }
	      }
	      return response;
		}

		@Override
		protected void onPostExecute(String result) 
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			Toast.makeText(getApplicationContext(), "Record Submitted !", 0).show();
			
		}
		
		
		
		
	}
	
	public void cancel(View v)
	{
		Intent intent = new Intent(this,Sellerprop.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.addplot, menu);
		return true;
	}

}