package com.example.housing;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Buyerinterest extends Activity {
List<buyint> ls2=new ArrayList<buyint>();

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buyerinterest);		
		
		populate2();
		onClickAdapter2();
	}
	
	private void onClickAdapter2()
	{
		ListView list=(ListView) findViewById(R.id.buyerinterest);
		
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
	
	public void populate2()
	{
		String loc[]={},owner_id[]={},rate[]={},area[]={},cat[]={};
		int count=0;
		
		for(int i=0;i<count;i++)
		{
			ls2.add(new buyint(loc[i],owner_id[i],rate[i],area[i],cat[i]));
		}
		
		ArrayAdapter<buyint> add=new MyArrayAdapter();
		ListView lv=(ListView) findViewById(R.id.buyerinterest);
		lv.setAdapter(add);
	}

	private class MyArrayAdapter extends ArrayAdapter<buyint>
	{

		public MyArrayAdapter() 
		{
			super(Buyerinterest.this,R.layout.buyint,ls2);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) 
		{
			View myview=convertView;
			
			if(myview == null)
			{
				myview= getLayoutInflater().inflate(R.layout.selproplist,parent,false);
			}
			
			buyint sp=ls2.get(position);
			
			TextView owner=(TextView) myview.findViewById(R.id.ownerid2);
			TextView price=(TextView) myview.findViewById(R.id.landprice2);
			TextView area=(TextView) myview.findViewById(R.id.landarea2);
			TextView category=(TextView) myview.findViewById(R.id.landcategory2);
			
			owner.setText(sp.getOwner());
			price.setText(sp.getRate());
			area.setText(sp.getArea());
			category.setText(sp.getCategory());
			
			
			return myview;
		}
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buyerinterest, menu);
		return true;
	}

}
