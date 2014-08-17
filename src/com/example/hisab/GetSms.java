package com.example.hisab;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

public final class GetSms extends AsyncTask<String, Integer, String>  {

	private ProgressDialog progressBar;
	private Activity mainActivity = null;
	private Cursor cur;
	private ContentResolver cR;
	private int i =1;
	private String sms = "";
	private ConfigDate CfgDate;
	private TextView view;
	public GetSms(Activity a, ContentResolver c)
	{
		this.mainActivity = a;
		this.cR = c;
		Log.w("***Pring In cnst**", "afer progress ");
	}



	protected void onPreExecute() {
		// prepare for a progress bar dialog
		progressBar= new ProgressDialog(mainActivity);

		progressBar.setCancelable(true);
		progressBar.setMessage("SMS downloading ...");
		//progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		//progressBar.setProgress(0);
		//progressBar.setMax(i);
		progressBar.show();
		Log.w("***Pring On pre execute**", "afer progress ");
	}

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		String total ;
		String str = new String(arg0[0]);
		total =iciciBank(str);
		/*
		CfgDate = (ConfigDate) mainActivity.getApplication();
		int mnt = CfgDate.getEndMonth();
		int year = CfgDate.getEndYear();
		Uri uriSMSURI = Uri.parse(str);
		cur = cR.query(uriSMSURI, null, null, null,null);
		Log.w("***Pring in MNT**", mnt + "Year " + year + "");
		Calendar cl = Calendar.getInstance();
		while (cur.moveToNext()) {
			long ms= Long.parseLong(cur.getString(4));
			Date dateFromSms = new Date(ms);
			cl.setTimeInMillis(ms);
			Log.w("Month", cl.get(Calendar.MONTH) + "" );
			if(cl.get(Calendar.MONTH)+1==mnt && cl.get(Calendar.YEAR) == year )
			{

				Log.w("*******Print*****", "In IF " +i);
				String tmp1="";
				String tmp2="";
				
				tmp1=cur.getString(12);
				if(tmp1.contains("Tranx of INR"))
				{
					Log.w("*******Print*****", "tmp1  " +tmp1);
					String[] separated = tmp1.split("Tranx of INR");
					tmp2=separated[1];
					Log.w("*******Print*****", "tmp2  " +tmp2);
					String[] separated1 = tmp2.split("using");
					tmp1=separated1[0];
					String[] separated2 = tmp1.split(".");
					tmp2=separated1[0];
					tmp2=tmp2.replaceAll(",", "");
					Log.w("*******Print*****", tmp2);
					total=Float.valueOf((tmp2))+total;
					sms += "Amount" + tmp2 +"\n";
					i++;
				}
				publishProgress((int)i);
				// Escape early if cancel() is called


			}
			if (isCancelled()) break;
		}
		Log.w("***Pring in execute**", sms);
		sms=sms+"Total "+ total +"";*/
		return total + "";
	}
	protected void onProgressUpdate(Integer... progress) {
		Log.w("***Pring progress update**", "update ");
		this.progressBar.setProgress(progress[0]);
	}

	protected void onPostExecute(String result)
	{
		/*Log.w("***Pring On post execute**", "post ");
		view = new TextView(mainActivity);

		view.setMovementMethod(new ScrollingMovementMethod());
		view.setText(sms);
		mainActivity.setContentView(view);*/
		progressBar.dismiss();

	}
	private String iciciBank(String st)
	{
		float total=0 ;
		String str =st;
		CfgDate = (ConfigDate) mainActivity.getApplication();
		int mnt = CfgDate.getEndMonth();
		int year = CfgDate.getEndYear();
		Uri uriSMSURI = Uri.parse("content://sms/inbox");
		Log.w("***Pring Icici**","IN URI" );
		cur = cR.query(uriSMSURI, null, null, null,null);
		Log.w("***Pring in MNT**", mnt + "Year " + year + "");
		Calendar cl = Calendar.getInstance();
		while (cur.moveToNext()) {
			long ms= Long.parseLong(cur.getString(4));
			Date dateFromSms = new Date(ms);
			cl.setTimeInMillis(ms);
			Log.w("Month", cl.get(Calendar.MONTH) + "" );
			if(cl.get(Calendar.MONTH)+1==mnt && cl.get(Calendar.YEAR) == year )
			{

				Log.w("*******Print*****", "In IF " +i);
				String tmp1="";
				String tmp2="";

				tmp1=cur.getString(12);
				if(tmp1.contains("Tranx of INR"))
				{
					Log.w("*******Print*****", "tmp1  " +tmp1);
					String[] separated = tmp1.split("Tranx of INR");
					tmp2=separated[1];
					Log.w("*******Print*****", "tmp2  " +tmp2);
					String[] separated1 = tmp2.split("using");
					tmp1=separated1[0];
					String[] separated2 = tmp1.split(".");
					tmp2=separated1[0];
					tmp2=tmp2.replaceAll(",", "");
					Log.w("*******Print*****", tmp2);
					total=Float.valueOf((tmp2))+total;
					sms += "Amount" + tmp2 +"\n";
					i++;
				}
				
			}

		}
		sms=sms+"Total "+ total +"";
		return total + "";
	}
}
