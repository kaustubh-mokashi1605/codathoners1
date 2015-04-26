package com.example.housing;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class BuyorSell extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buyor_sell);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buyor_sell, menu);
		return true;
	}
	
	public void sell(View v)
	{
		Intent intent=new Intent(this,Sellerprop.class);
		startActivity(intent);
	}
	
	public void buy(View v)
	{
		Intent intent=new Intent(this,Buyerlist.class);
		startActivity(intent);
	}

}
