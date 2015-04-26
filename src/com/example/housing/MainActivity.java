package com.example.housing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.housing.SignupAcitivity;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity 
{

	String res=null;
	int flag=0;
	public static String usn=null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Button one = (Button)this.findViewById(R.id.signupbutton);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.click);
        one.setOnClickListener(new OnClickListener(){

            public void onClick(View v) {
                mp.start();
            }
        });*/
        
        Button signin=(Button) findViewById(R.id.signinbutton);
        
        signin.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				login();
			}
		});
    }
    
    public void login()
    {
    	TextView uid=(TextView) findViewById(R.id.editText1);
    	TextView psd=(TextView) findViewById(R.id.editText2);
    	
    	String username=uid.getText().toString();
    	String password=psd.getText().toString();
    	
    	String server="http://land1234.comxa.com/signup_req.php?";
    	
    	String query="condition=login&user_name="+username;
    	
    	String fullquery=server+query;
    	
    	//Toast.makeText(getApplicationContext(), fullquery, 0).show();
    	
    	DownloadWebpageTask task=new DownloadWebpageTask();
    	task.setContect(this);
    	task.execute(fullquery);
    	
    	
    	
    	/*try 
    	{
			task.wait();
		} 
    	catch (InterruptedException e1) 
    	{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Toast.makeText(getApplicationContext(), "Still not waiting.", 0).show();
		}*/
    	   
    	//task.onPostExecute("Executed");
    	//Toast.makeText(getApplicationContext(), res,0).show();
    	
    }
    
    

    private class DownloadWebpageTask extends AsyncTask<String , Void , String>
    {

    	private String response="";
    	Context con;
    	
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
			while(result != null)
			{
				response=result;
				break;
			}
			
			
			super.onPostExecute(result);
			performAction();
						
		}
		
		public void setContect(Context c)
		{
			con=c;
		}
		
		public void performAction()
		{
			TextView psd=(TextView) findViewById(R.id.editText2);
			
			String password=psd.getText().toString();
			
			int len=response.length();
			
			int flag=0;
			
			for(int i=0;i<len;i++)
			{
				if(response.charAt(i)=='#')
				{
					flag=i;
					break;
				}
			}
			
			String fpass=response.substring(0,flag);
			
			String msg="You typed "+password+" Original is="+fpass;
			
			//Toast.makeText(getApplicationContext(),msg,0).show();
			
			if(password.equals(fpass))
			{
				Toast.makeText(getApplicationContext(), "Welcome ! to the Application !",0).show();
				
				TextView uid=(TextView) findViewById(R.id.editText1);
				String text=uid.getText().toString();
				
				usn=text;		
				
				
				
				Intent intent=new Intent(con,BuyorSell.class);
				startActivity(intent);
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Username and Password do not match !",0).show();
				//TextView uid=(TextView) findViewById(R.id.editText1);
		    	//TextView psd=(TextView) findViewById(R.id.editText2);
				
				//uid.setText("");
				//psd.setText("");
			}
		}
		
		
    	
    }

    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
       
        return true;
    }
    public void add(View v)
    {
    	MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.click2);
        mp.start();
    	Intent intent = new Intent(this,SignupAcitivity.class);
    	startActivity(intent);
    	
     }
    public boolean onTouch(View v)
    {
    	v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
    	return true;
    }
    
}
