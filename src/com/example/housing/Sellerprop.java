package com.example.housing;

import java.util.ArrayList;
import java.util.List;

import com.example.housing.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Sellerprop extends Activity 
{
	
	List<selprop> ls=new ArrayList<selprop>();

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sellerprop);		
		
		populate();
		onClickAdapter();
	}
	
	private void onClickAdapter()
	{
		ListView list=(ListView) findViewById(R.id.sellerlist);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) 
			{
				//Call Intent here.
			}
			
		});
	}
	
	public void populate()
	{
		String loc[]={},owner_id[]={},rate[]={},area[]={},cat[]={};
		int count=0;
		
		for(int i=0;i<count;i++)
		{
			ls.add(new selprop(loc[i],owner_id[i],rate[i],area[i],cat[i]));
		}
		
		ArrayAdapter<selprop> add=new MyArrayAdapter();
		ListView lv=(ListView) findViewById(R.id.sellerlist);
		lv.setAdapter(add);
	}

	private class MyArrayAdapter extends ArrayAdapter<selprop>
	{

		public MyArrayAdapter() 
		{
			super(Sellerprop.this,R.layout.selproplist,ls);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) 
		{
			View myview=convertView;
			
			if(myview == null)
			{
				myview= getLayoutInflater().inflate(R.layout.selproplist,parent,false);
			}
			
			selprop sp=ls.get(position);
			
			TextView owner=(TextView) myview.findViewById(R.id.ownerid);
			TextView price=(TextView) myview.findViewById(R.id.landprice);
			TextView area=(TextView) myview.findViewById(R.id.landarea);
			TextView category=(TextView) myview.findViewById(R.id.landcategory);
			
			owner.setText(sp.getOwner());
			price.setText(sp.getRate());
			area.setText(sp.getArea());
			category.setText(sp.getCategory());
			
			
			return myview;
		}
		
		
		
		
		
	}
	public void addplot(View v)
	{
		Intent intent = new Intent(this,Addplot.class);
		startActivity(intent);
	}
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sellerprop, menu);
		return true;
	}

}