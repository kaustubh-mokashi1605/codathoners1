package com.example.housing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.housing.MainActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupAcitivity extends Activity 
{

	String server="http://land1234.comxa.com/signup_req.php?";
	
	String res="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup_acitivity);
		
		Button confirm = (Button) findViewById(R.id.confirmbutton);
		
		confirm.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0)
			{				
				start();
			}
		});
	}
	
	public void start()
	{
		EditText nm=(EditText) findViewById(R.id.name1);
		EditText uid=(EditText) findViewById(R.id.userid1);
		EditText pswd=(EditText) findViewById(R.id.password2);
		EditText ph=(EditText) findViewById(R.id.number1);
		EditText email=(EditText) findViewById(R.id.email1);
		
		String user_name=nm.getText().toString();
		String user_id=uid.getText().toString();
		String password=pswd.getText().toString();
		String phone_number=ph.getText().toString();
		String email_address=email.getText().toString();

		
		
		String query="condition=add&user_name="+user_id+"&full_name="+user_name+"&email_id="+email_address+"&password="+password+"&phone_number="+phone_number;
		                                   
		
		final String fullquery=server+query;
		
		
		Toast.makeText(getApplicationContext(), fullquery, 0).show();
		
		DownloadWebpageTask task=new DownloadWebpageTask();		
		task.execute(fullquery);
		
		//Toast.makeText(getApplicationContext(), "Response="+res,0).show();
		
	}
	
	
	private class DownloadWebpageTask extends AsyncTask<String , Void , String>
	{
		public String response="";
		
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
			Toast.makeText(getApplicationContext(), result,0).show();
			while(result != null)
			{
				res=result;
				break;
			}
			
			Toast.makeText(getApplicationContext(), "Response="+res, 0).show();
		}		
		
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signup_acitivity, menu);
		return true;
	}
	
	public void cancel(View v)
	{
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
	}

}
