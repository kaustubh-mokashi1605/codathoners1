package com.example.housing;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Buyerlist extends Activity 
{
List<buyprop> ls1=new ArrayList<buyprop>();


	char Category='A';
	String max="null";
	String area="null";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buyerlist);		
		
		populate1();
		onClickAdapter1();
	}
	
	private void onClickAdapter1()
	{
		ListView list1=(ListView)findViewById(R.id.buyerlist);
		
		list1.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) 
			{
				//Call Intent here.
			}
			
		});
	}
	
	public void populate1()
	{
		String loc[]={},owner_id[]={},rate[]={},area[]={},cat[]={};
		int count=0;
		
		for(int i=0;i<count;i++)
		{
			ls1.add(new buyprop(loc[i],owner_id[i],rate[i],area[i],cat[i]));
		}
		
		ArrayAdapter<buyprop> add=new MyArrayAdapter();
		ListView lv=(ListView) findViewById(R.id.buyerlist);
		lv.setAdapter(add);
	}

	private class MyArrayAdapter extends ArrayAdapter<buyprop>
	{

		public MyArrayAdapter() 
		{
			super(Buyerlist.this,R.layout.buyproplist,ls1);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) 
		{
			View myview=convertView;
			
			if(myview == null)
			{
				myview= getLayoutInflater().inflate(R.layout.buyproplist,parent,false);
			}
			
			buyprop sp=ls1.get(position);
			
			TextView owner1=(TextView) myview.findViewById(R.id.ownerid1);
			TextView price1=(TextView) myview.findViewById(R.id.landprice1);
			TextView area1=(TextView) myview.findViewById(R.id.landarea1);
			TextView category1=(TextView) myview.findViewById(R.id.landcategory1);
			
			owner1.setText(sp.getOwner());
			price1.setText(sp.getRate());
			area1.setText(sp.getArea());
			category1.setText(sp.getCategory());
			
			
			return myview;
		}
		
		
		
		
		
	}
	
	public void filter(View v)
	{
		setContentView(R.layout.activity_filter);
		
		Button filter=(Button) findViewById(R.id.filterbutton);
		
		filter.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				EditText cat=(EditText) findViewById(R.id.catfilter);
				Category=cat.getText().toString().toUpperCase().charAt(0);
				
				EditText pricef=(EditText) findViewById(R.id.pricefilter);
				max=pricef.getText().toString();
				
				EditText areaf=(EditText) findViewById(R.id.areafilter);
				area=areaf.getText().toString();
				
				
				setContentView(R.layout.activity_buyerlist);
			}
		});
		
		Button reset=(Button) findViewById(R.id.resetbutton);
		
		reset.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				Category='A';
				max=null;
				area=null;
				
				EditText cat=(EditText) findViewById(R.id.catfilter);
				//Category=cat.getText().toString().toUpperCase().charAt(0);
				cat.setText("");
				EditText pricef=(EditText) findViewById(R.id.pricefilter);
				//max=pricef.getText().toString();
				pricef.setText("");
				EditText areaf=(EditText) findViewById(R.id.areafilter);
				//area=areaf.getText().toString();
				areaf.setText("");
			}
		});
		
		Button cancel=(Button) findViewById(R.id.cancelfilter);
		
		cancel.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				setContentView(R.layout.activity_buyerlist);
			}
		});
	}
	
	public void interest(View v)
	{
		Intent intent = new Intent(this,Buyerinterest.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buyerlist, menu);
		return true;
	}

}