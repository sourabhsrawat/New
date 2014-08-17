package com.example.hisab;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hisab.R.id;

public class MyAdapter extends BaseAdapter implements OnClickListener{

	private Context mContext;
	private LayoutInflater mInflater;
	ArrayList<String> orase;
	private String[] mData;

	private Calendar cal_St;
	private int day_St;
	private int month_St;
	private int year_St;
	private EditText et_St;
	private Calendar cal_Ed;
	private int day_Ed;
	private int month_Ed;
	private int year_Ed;
	private EditText et_Ed;
	private  View vv;
	private ConfigDate CfgDate;
	private Activity aa;
	private String startDate;
	private String endDate;

	public MyAdapter(Context context,Activity a,String[] objects)
	{
		mInflater = LayoutInflater.from(context);
		this.mContext=context;
		mData = objects;

		aa=a;
		Log.w("My Adapter", "Custructor");
		/*CfgDate = (ConfigDate) aa.getApplication();
		cal_St = Calendar.getInstance();
		day_St = cal_St.get(Calendar.DAY_OF_MONTH);
		month_St = cal_St.get(Calendar.MONTH);
		year_St = cal_St.get(Calendar.YEAR);
		et_St = (EditText) vv.findViewById(R.id.editText_St);		
		cal_Ed = Calendar.getInstance();
		day_Ed = cal_St.get(Calendar.DAY_OF_MONTH);
		month_Ed = cal_St.get(Calendar.MONTH);
		year_Ed = cal_St.get(Calendar.YEAR);
		et_Ed = (EditText) vv.findViewById(R.id.editText_Ed);*/
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Log.w("My Adapter getCount ", "count " + mData.length);
		return mData.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		Log.w("My Adapter ", "Item");
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		Log.w("My Adapter", "position" + position);
		return position;
	}
	public int getItemViewType(int position) {
		Log.w("My getItemViewType", "ty" + position % 3+ position % 4 );
		if (position % 3 == 0) {
			return 0;
		} else if (position % 4 == 1) {
			return 1;
		} else {
			return 2;
		}
	}
	public class Holder
	{
		TextView mText;
		EditText mEditText;
	}
	public void setStartDate(String st)
	{
		this.startDate=st;
		//notifyDataSetChanged();
	}
	public void setEndDate(String ed)
	{
		this.endDate=ed;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		vv=convertView;
		final Holder holder;
		int type = getItemViewType(position);
		Log.w("My Adapter getView", "position" + position);
		Log.w("My Adapter getView", "above IF");
		if (convertView == null) {
			Log.w("My Adapter getView IF", "type" + type);
			holder = new Holder();
			switch (type) {
			case 0:
				convertView = mInflater.inflate(R.layout.title, null);
				holder.mText = (TextView) convertView.findViewById(R.id.title);
				Log.w("My Adapter Case", "Case 0");
				break;
			case 1:
				convertView = mInflater.inflate(R.layout.config_row1,null);
				holder.mText=(TextView) convertView.findViewById(id.st_date);
				holder.mEditText = (EditText) convertView.findViewById(R.id.editText_St);
				holder.mEditText.setText(startDate);
				holder.mEditText.setOnClickListener(this);
				holder.mText.setText("Start Date");
				Log.w("My Adapter Case", "Case1");
				break;
			case 2 :
				convertView = mInflater.inflate(R.layout.config_row2,null);
				holder.mText=(TextView) convertView.findViewById(id.ed_date);
				holder.mEditText = (EditText) convertView.findViewById(R.id.editText_Ed);
				holder.mEditText.setText(endDate);
				holder.mEditText.setOnClickListener(this);
				holder.mText.setText("End Date");
				Log.w("My Adapter", "Case2");
				break;

			}
			convertView.setTag(holder);
		}
		else {

			holder = (Holder) convertView.getTag();
		}
		//  holder.mTextST.setText("enter date");
		/* holder.mEditTextSt.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (!hasFocus){
                    final EditText etxt = (EditText) v;
                    holder.mEditTextSt.setText(etxt.getText().toString());
                }

            }
        });*/
		return convertView;
	}
	@Override
	public void onClick(View v) {
		vv=v;
		// TODO Auto-generated method stub
		Log.w("***Click ", "on click ");
		if(vv.getId() == R.id.editText_St)
		{
			Log.w("***ON Click  ", "st");
			//onCreateDialog(0);

			((Activity)MyAdapter.this.mContext).showDialog(0);


		}
		if(vv.getId() == R.id.editText_Ed)
		{
			Log.w("***ON Click  ", "ED");
			((Activity)MyAdapter.this.mContext).showDialog(1);

		}
	}




}


