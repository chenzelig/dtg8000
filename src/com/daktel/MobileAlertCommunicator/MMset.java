package com.daktel.MobileAlertCommunicator;

//import java.text.SimpleDateFormat;
//import java.util.Date;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MMset extends Activity implements OnClickListener {
	//private TextView device_name = null;
	//private Button add_device = null;
	//private Button switch_device = null;
	private SmsReceiver smsReceiver;
	private String destnumber = null;
	//private String desname = null;
	private String password = null;

	private Button set_enable = null;
	private Button get_disable = null;
	
	private EditText mmnumber1_edit = null;
	private Button mm_number1_set = null;
	
	private EditText mmnumber2_edit = null;
	private Button mm_number2_set = null;
	private ImageView mainmenu_return_but = null;
	
	private Button btnBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.mm_set);
		//��̬ע����ն�����Ϣ
		IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		//����������ȼ�
        localIntentFilter.setPriority(Integer.MAX_VALUE);
        smsReceiver = new SmsReceiver();
        registerReceiver(smsReceiver, localIntentFilter);
		
        btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(this);
		
 	    //ʵ��
		this.set_enable = (Button)super.findViewById(R.id.set_enable);
		this.set_enable.setOnClickListener(new OnClickListenersosnumbersetenble());
		this.get_disable = (Button)super.findViewById(R.id.get_disable);
		this.get_disable.setOnClickListener(new OnClickListenersosnumbergetdisable());
	    this.set_enable.setBackgroundResource(R.drawable.btn_red2);
		this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		
		this.mmnumber1_edit = (EditText)super.findViewById(R.id.mmnumber1_edit);
		this.mmnumber1_edit.addTextChangedListener(new TextWatchermmnumber1());
		this.mm_number1_set = (Button)super.findViewById(R.id.mm_number1_set);
		
		this.mmnumber2_edit = (EditText)super.findViewById(R.id.mmnumber2_edit);
		this.mmnumber2_edit.addTextChangedListener(new TextWatchermmnumber2());
		this.mm_number2_set = (Button)super.findViewById(R.id.mm_number2_set);
		
		this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
		
		//�¼�����
		this.mm_number1_set.setOnClickListener(new OnClickListenermmnumber1set());
		this.mm_number2_set.setOnClickListener(new OnClickListenermmnumber2set());
	
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
		
		this.mmnumber1_edit.setText(Utility.getShareValue(this, "mm_number1"));
		this.mm_number1_set.setText(getResources().getString(R.string.Set));
		this.mm_number1_set.setBackgroundResource(R.drawable.btn_red);
		
		this.mmnumber2_edit.setText(Utility.getShareValue(this, "mm_number2"));
		this.mm_number2_set.setText(getResources().getString(R.string.Set));
		this.mm_number2_set.setBackgroundResource(R.drawable.btn_red);
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {


			String smsText = "";
			String editphonenumber_temp = MMset.this.mmnumber1_edit.getText().toString().trim();//���
			if(destnumber.equals("00000000000"))
			{	
				Toast.makeText(MMset.this, getResources().getString(R.string.Please_input_phone_number), Toast.LENGTH_SHORT).show();
					
			}
			else
			{
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET" + password+"#";//���
				
				if(mmnumber1_edit.getText().toString().length()>0)
				{
					smsText += "M1#"+mmnumber1_edit.getText().toString()+"#";
					
					//save data
					SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
			  		Editor et=uiState.edit();
			  		et.putString("mmnumber1", mmnumber1_edit.getText().toString());
			  		et.commit();
			  		
			  		Utility.setShareValue(MMset.this, "mm_number1", mmnumber1_edit.getText().toString());
				}
				if(mmnumber2_edit.getText().toString().length()>0)
				{
					smsText += "M2#"+mmnumber2_edit.getText().toString()+"#";
					
					//save data
					SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
			  		Editor et=uiState.edit();
			  		et.putString("mmnumber2", mmnumber2_edit.getText().toString());
			  		et.commit();
			  		
			  		Utility.setShareValue(MMset.this, "mm_number2", mmnumber2_edit.getText().toString());
				}
				
				if(smsText.length()<=0)
				{
					Toast.makeText(MMset.this, R.string.Please_input_phone_number, Toast.LENGTH_SHORT).show();
				}
				else
				{
					smsnote+=smsText;
					SmsManager smsmanager = SmsManager.getDefault();
					smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
					Toast.makeText(MMset.this, getResources().getString(R.string.Success), Toast.LENGTH_SHORT).show();
				}
				
				
				
			}
			
			
			Intent it = new Intent(MMset.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			MMset.this.startActivity(it);
			MMset.this.finish();
		}
		
	}
	
	private class TextWatchermmnumber1 implements TextWatcher
	{

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			String number1_temp = MMset.this.mmnumber1_edit.getText().toString().trim();
			Utility.setShareValue(MMset.this, "mm_number1", mmnumber1_edit.getText().toString());
			if(number1_temp.equals(""))
			{
				MMset.this.mm_number1_set.setText(getResources().getString(R.string.Set));
				MMset.this.mm_number1_set.setBackgroundResource(R.drawable.btn_red);
			}
			else
			{
				MMset.this.mm_number1_set.setText("Change");
				MMset.this.mm_number1_set.setBackgroundResource(R.drawable.buttonform);
			}
		}
	}
	
	private class TextWatchermmnumber2 implements TextWatcher
	{

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			String number1_temp = MMset.this.mmnumber2_edit.getText().toString().trim();
			Utility.setShareValue(MMset.this, "mm_number2", mmnumber2_edit.getText().toString());
			if(number1_temp.equals(""))
			{
				MMset.this.mm_number2_set.setText(getResources().getString(R.string.Set));
				MMset.this.mm_number2_set.setBackgroundResource(R.drawable.btn_red);
			}
			else
			{
				MMset.this.mm_number2_set.setText("Change");
				MMset.this.mm_number2_set.setBackgroundResource(R.drawable.buttonform);
			}
		}
	}
	
	//mm number1 set
	private class OnClickListenermmnumber1set implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editphonenumber_temp = MMset.this.mmnumber1_edit.getText().toString().trim();//���
			if(editphonenumber_temp.equals("")||destnumber.equals("00000000000"))
			{	
				if(editphonenumber_temp.equals(""))
					Toast.makeText(MMset.this, getResources().getString(R.string.Please_input_phone_number), Toast.LENGTH_SHORT).show();
				else if(destnumber.equals("00000000000"))
					Toast.makeText(MMset.this, R.string.Please_set_destination_number_first, Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET" + password+"#M1#"+editphonenumber_temp+"#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(MMset.this, getResources().getString(R.string.Success), Toast.LENGTH_SHORT).show();
				
				Utility.setShareValue(MMset.this, "mm_number1", mmnumber1_edit.getText().toString());
				//save data
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("mmnumber1", editphonenumber_temp);
		  		et.commit();
			}
		}
		
	}
	
	//mm number2 set
	private class OnClickListenermmnumber2set implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editphonenumber_temp = MMset.this.mmnumber2_edit.getText().toString().trim();//���
			if(editphonenumber_temp.equals("")||destnumber.equals("00000000000"))
			{	
				if(editphonenumber_temp.equals(""))
					Toast.makeText(MMset.this, getResources().getString(R.string.Please_input_phone_number), Toast.LENGTH_SHORT).show();
				else if(destnumber.equals("00000000000"))
					Toast.makeText(MMset.this, R.string.Please_Add_one_device_first, Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET" + password+"#M2#"+editphonenumber_temp+"#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(MMset.this, getResources().getString(R.string.Success), Toast.LENGTH_SHORT).show();
				
				Utility.setShareValue(MMset.this, "mm_number2", mmnumber2_edit.getText().toString());
				//save data
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("mmnumber2", editphonenumber_temp);
		  		et.commit();
			}
		}
		
	}
	
	//����Ϣ���ղ������ж�
	private class SmsReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
	        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){  
	        	Log.v("huahua", "MainActivity�յ����ŷ�������Ϣ");  
	        	
	            Bundle bundle = intent.getExtras();  
	            SmsMessage msg = null;  
	            if (null != bundle) 
	            {  
	                Object[] smsObj = (Object[]) bundle.get("pdus");  
	                for (Object object : smsObj) 
	                {  
	                    msg = SmsMessage.createFromPdu((byte[]) object);  
	                    //Date date = new Date(msg.getTimestampMillis());//ʱ��  
	                    //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	                    //String receiveTime = format.format(date);  
	                    System.out.println("number:" + msg.getOriginatingAddress()  
	                    + "   body:" + msg.getDisplayMessageBody() + "  time:"  
	                            + msg.getTimestampMillis());  
	                      
	                    //String numbertemp = msg.getOriginatingAddress();
	                    String msgtemp = msg.getDisplayMessageBody();
	                    //������д�Լ����߼�  
	                    //if (msg.getOriginatingAddress().equals("+8618003049659")) 
	                    if (msgtemp.contains("M1")||msgtemp.contains("M2")) 
	                    {  
	                        //TODO  
	                    	Toast.makeText(MMset.this, getResources().getString(R.string.Success), Toast.LENGTH_SHORT).show();
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();
	                    }  
	                      
	                }  
	            }
	            
	        }  
		}
		
	}
	
	@Override
	protected void onRestart(){
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
		super.onRestart();
	}
	
	public void onBackPressed() { 
		Intent it = new Intent(MMset.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		MMset.this.startActivity(it);
		MMset.this.finish();
    }
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(smsReceiver);//����ͷţ���Ȼ����פ�ڴ�
	}
	
	//set enable
	private class OnClickListenersosnumbersetenble implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			MMset.this.set_enable.setBackgroundResource(R.drawable.btn_red2);
			MMset.this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		}
		
	}
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(MMset.this,MMget.class);//ʵ��Intent
			it.putExtra("myinfo", "entry get sosnumber");//Key
			MMset.this.startActivity(it);
			MMset.this.finish();
		}
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
			case R.id.btnBack:
				finish();
				break;				
				
		}
		
	}
	
	
}
