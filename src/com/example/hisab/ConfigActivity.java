package com.example.hisab;

import java.util.Calendar;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.hisab.MyAdapter.Holder;

public class ConfigActivity extends Activity  {
	private ListView mainListView ;  
	
	Holder holder;
	private Calendar cal_St;
	private int day_St;
	private int month_St;
	private int year_St;
	private EditText et_St;
	private ImageButton ib_Ed;
	private Calendar cal_Ed;
	private int day_Ed;
	private int month_Ed;
	private int year_Ed;
	private EditText et_Ed;
	private  View vv;
	private ConfigDate CfgDate;
	private String startDate;
	private String endDate;
	private MyAdapter My;
	private int dl;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config);
		mainListView = (ListView) findViewById( R.id.configListView ); 
		//mainListView.addView(findViewById(R.layout.config_row));
		String[]  row = {"Title","Start Date","End Date "};
		//startDate="Enter Start Date";
		 My = new MyAdapter(this,this,row);
	
		
		
		mainListView.setAdapter(My);
		My.notifyDataSetChanged();
		
		CfgDate = (ConfigDate) getApplication();
		
		cal_St = Calendar.getInstance();
		day_St = cal_St.get(Calendar.DAY_OF_MONTH);
		month_St = cal_St.get(Calendar.MONTH);
		year_St = cal_St.get(Calendar.YEAR);
		
		cal_Ed = Calendar.getInstance();
		day_Ed = cal_St.get(Calendar.DAY_OF_MONTH);
		month_Ed = cal_St.get(Calendar.MONTH);
		year_Ed = cal_St.get(Calendar.YEAR);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.config, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeho0lder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_config,
					container, false);
			return rootView;
		}
	}
	protected Dialog onCreateDialog(int id) 
	{
		Log.w("***onCreateDialog*** ", "dialoge");
		dl=id;
		if(id == 0)
		{
			Log.w("***onCreateDialog ****", "dialoge0");
			return new DatePickerDialog(this, datePickerListener, year_St, month_St, day_St);

		}
		else
		{
			Log.w("***onCreateDialog** ", "dialoge1");
			return new DatePickerDialog(this, datePickerListener, year_Ed, month_Ed, day_Ed);
		}
	}


	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			Log.w("DatePickerDialog", "After");
			//holder=(Holder) mainListView.getAdapter().getItem(2);
			
			Log.w("DatePickerDialog", "Before");
			//holder.mEditText.setText(selectedDay + " / " + (selectedMonth + 1) + " / "+ selectedYear);
			
			
			if(dl==0 )
			{
				startDate =selectedDay + " / " + (selectedMonth + 1) + " / "+ selectedYear;
				My.setStartDate(startDate);
				//My.notifyDataSetChanged();
				mainListView.setAdapter(My);
				CfgDate.setStartDay(selectedDay);
				CfgDate.setStartMonth(selectedMonth+1);
				CfgDate.setStartYear(selectedYear);
				Log.w("**********Selcted Mnt", selectedMonth+1 + startDate);
			}
			//date us updated
			else
			{
				endDate =selectedDay + " / " + (selectedMonth + 1) + " / "+ selectedYear;
				My.setEndDate(endDate);
				//My.notifyDataSetChanged();
				mainListView.setAdapter(My);
				CfgDate.setEndDay(selectedDay);
				CfgDate.setEndMonth(selectedMonth+1);
				CfgDate.setEndYear(selectedYear);
			}
		}
	};
}
