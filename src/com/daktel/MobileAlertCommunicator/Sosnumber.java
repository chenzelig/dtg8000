package com.daktel.MobileAlertCommunicator;

//import java.text.SimpleDateFormat;
//import java.util.Date;

import android.app.Activity;
//import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
//import android.content.DialogInterface;
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
//import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.ImageButton;
import android.widget.ImageView;
//import android.widget.TextView;
import android.widget.Toast;

public class Sosnumber extends Activity implements OnClickListener {
	//private TextView device_name = null;
	//private Button add_device = null;
	//private Button switch_device = null;
	private SmsReceiver smsReceiver;
	private String destnumber = null;
	//private String desname = null;
	private String password = null;
	
	private String sosnumber1_read = null;
	private String sosnumber2_read = null;
	private String sosnumber3_read = null;
	private String sosnumber4_read = null;
	private String sosnumber5_read = null;
	private String sosnumber6_read = null;
	private String sosnumber7_read = null;
	private String sosnumber8_read = null;
	
	private Button set_enable = null;
	private Button get_disable = null;
	
	private EditText sosnumber1_edit = null;
	private Button sos_number1_set = null;
	
	private EditText sosnumber2_edit = null;
	private Button sos_number2_set = null;
	
	private EditText sosnumber3_edit = null;
	private Button sos_number3_set = null;
	
	private EditText sosnumber4_edit = null;
	private Button sos_number4_set = null;
	
	private EditText sosnumber5_edit = null;
	private Button sos_number5_set = null;
	
	private EditText sosnumber6_edit = null;
	private Button sos_number6_set = null;
	
	private EditText sosnumber7_edit = null;
	private Button sos_number7_set = null;
	
	private EditText sosnumber8_edit = null;
	private Button sos_number8_set = null;
	
	private ImageView mainmenu_return_but = null;
	
	
	private Button btnBack;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sos_number);
		//��̬ע����ն�����Ϣ
		IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		//����������ȼ�
        localIntentFilter.setPriority(Integer.MAX_VALUE);
        smsReceiver = new SmsReceiver();
        registerReceiver(smsReceiver, localIntentFilter);
        
		//this.device_name = (TextView)super.findViewById(R.id.device_name);
		//this.add_device = (Button)super.findViewById(R.id.add_device);
		//this.add_device.setOnTouchListener(new OnTouchListeneradddevice());
		//this.switch_device = (Button)super.findViewById(R.id.switch_device);
		//this.switch_device.setOnTouchListener(new OnTouchListenerswitchdevice());
		
 	    //ʵ��
		this.set_enable = (Button)super.findViewById(R.id.set_enable);
		this.set_enable.setOnClickListener(new OnClickListenersosnumbersetenble());
		this.get_disable = (Button)super.findViewById(R.id.get_disable);
		this.get_disable.setOnClickListener(new OnClickListenersosnumbergetdisable());
	    this.set_enable.setBackgroundResource(R.drawable.btn_red2);
		this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		
		this.sosnumber1_edit = (EditText)super.findViewById(R.id.sosnumber1_edit);
		this.sosnumber1_edit.addTextChangedListener(new TextWatchernumber1());
		this.sos_number1_set = (Button)super.findViewById(R.id.sos_number1_set);
		
		this.sosnumber2_edit = (EditText)super.findViewById(R.id.sosnumber2_edit);
		this.sosnumber2_edit.addTextChangedListener(new TextWatchernumber2());
		this.sos_number2_set = (Button)super.findViewById(R.id.sos_number2_set);
		
		this.sosnumber3_edit = (EditText)super.findViewById(R.id.sosnumber3_edit);
		this.sosnumber3_edit.addTextChangedListener(new TextWatchernumber3());
		this.sos_number3_set = (Button)super.findViewById(R.id.sos_number3_set);
		
		this.sosnumber4_edit = (EditText)super.findViewById(R.id.sosnumber4_edit);
		this.sosnumber4_edit.addTextChangedListener(new TextWatchernumber4());
		this.sos_number4_set = (Button)super.findViewById(R.id.sos_number4_set);
		
		this.sosnumber5_edit = (EditText)super.findViewById(R.id.sosnumber5_edit);
		this.sosnumber5_edit.addTextChangedListener(new TextWatchernumber5());
		this.sos_number5_set = (Button)super.findViewById(R.id.sos_number5_set);
		
		this.sosnumber6_edit = (EditText)super.findViewById(R.id.sosnumber6_edit);
		this.sosnumber6_edit.addTextChangedListener(new TextWatchernumber6());
		this.sos_number6_set = (Button)super.findViewById(R.id.sos_number6_set);
		
		this.sosnumber7_edit = (EditText)super.findViewById(R.id.sosnumber7_edit);
		this.sosnumber7_edit.addTextChangedListener(new TextWatchernumber7());
		this.sos_number7_set = (Button)super.findViewById(R.id.sos_number7_set);
		
		this.sosnumber8_edit = (EditText)super.findViewById(R.id.sosnumber8_edit);
		this.sosnumber8_edit.addTextChangedListener(new TextWatchernumber8());
		this.sos_number8_set = (Button)super.findViewById(R.id.sos_number8_set);
		
		this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
		
		//�¼�����
		this.sos_number1_set.setOnClickListener(new OnClickListenersosnumber1set());
		this.sos_number2_set.setOnClickListener(new OnClickListenersosnumber2set());
		this.sos_number3_set.setOnClickListener(new OnClickListenersosnumber3set());
		this.sos_number4_set.setOnClickListener(new OnClickListenersosnumber4set());
		this.sos_number5_set.setOnClickListener(new OnClickListenersosnumber5set());
		this.sos_number6_set.setOnClickListener(new OnClickListenersosnumber6set());
		this.sos_number7_set.setOnClickListener(new OnClickListenersosnumber7set());
		this.sos_number8_set.setOnClickListener(new OnClickListenersosnumber8set());
	
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    //desname = uiState.getString("destname","" );
 	    password = "";
 	    //this.device_name.setText(desname);
   
		sosnumber1_read = uiState.getString("sosnumber1", ""); 
		sosnumber2_read = uiState.getString("sosnumber2", "");
		sosnumber3_read = uiState.getString("sosnumber3", "");
		sosnumber4_read = uiState.getString("sosnumber4", "");
		sosnumber5_read = uiState.getString("sosnumber5", "");
		sosnumber6_read = uiState.getString("sosnumber6", "");
		sosnumber7_read = uiState.getString("sosnumber7", "");
		sosnumber8_read = uiState.getString("sosnumber8", "");
		
		this.sosnumber1_edit.setText(sosnumber1_read);
		this.sos_number1_set.setText(R.string.Set);
		this.sos_number1_set.setBackgroundResource(R.drawable.btn_red);
		
		this.sosnumber2_edit.setText(sosnumber2_read);
		this.sos_number2_set.setText(R.string.Set);
		this.sos_number2_set.setBackgroundResource(R.drawable.btn_red);
		
		this.sosnumber3_edit.setText(sosnumber3_read);
		this.sos_number3_set.setText(R.string.Set);
		this.sos_number3_set.setBackgroundResource(R.drawable.btn_red);
		
		this.sosnumber4_edit.setText(sosnumber4_read);
		this.sos_number4_set.setText(R.string.Set);
		this.sos_number4_set.setBackgroundResource(R.drawable.btn_red);
		
		this.sosnumber5_edit.setText(sosnumber5_read);
		this.sos_number5_set.setText(R.string.Set);
		this.sos_number5_set.setBackgroundResource(R.drawable.btn_red);
		
		this.sosnumber6_edit.setText(sosnumber6_read);
		this.sos_number6_set.setText(R.string.Set);
		this.sos_number6_set.setBackgroundResource(R.drawable.btn_red);
		
		this.sosnumber7_edit.setText(sosnumber7_read);
		this.sos_number7_set.setText(R.string.Set);
		this.sos_number7_set.setBackgroundResource(R.drawable.btn_red);
		
		this.sosnumber8_edit.setText(sosnumber8_read);
		this.sos_number8_set.setText(R.string.Set);
		this.sos_number8_set.setBackgroundResource(R.drawable.btn_red);
		
		
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(this);
		
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			
			
			//批量下发短信
			//for example:SET9999#PHONE1#911#
			String smstel = destnumber;//����
			String smsnote = "SET";
			if(sosnumber1_edit.getText().toString().length()>0)
			{
				smsnote+="#PHONE1#"+sosnumber1_edit.getText().toString();
				
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("sosnumber1", sosnumber1_edit.getText().toString());
		  		et.commit();
			}
			if(sosnumber2_edit.getText().toString().length()>0)
			{
				smsnote+="#PHONE2#"+sosnumber2_edit.getText().toString();
				
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("sosnumber2", sosnumber2_edit.getText().toString());
		  		et.commit();
			}
			if(sosnumber3_edit.getText().toString().length()>0)
			{
				smsnote+="#PHONE3#"+sosnumber3_edit.getText().toString();
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("sosnumber3", sosnumber3_edit.getText().toString());
		  		et.commit();
			}
			if(sosnumber4_edit.getText().toString().length()>0)
			{
				smsnote+="#PHONE4#"+sosnumber4_edit.getText().toString();
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("sosnumber4", sosnumber4_edit.getText().toString());
		  		et.commit();
			}
			if(sosnumber5_edit.getText().toString().length()>0)
			{
				smsnote+="#PHONE5#"+sosnumber5_edit.getText().toString();
				
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("sosnumber5", sosnumber5_edit.getText().toString());
		  		et.commit();
			}
			if(sosnumber6_edit.getText().toString().length()>0)
			{
				smsnote+="#PHONE6#"+sosnumber6_edit.getText().toString();
				
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("sosnumber6", sosnumber6_edit.getText().toString());
		  		et.commit();
			}
			if(sosnumber7_edit.getText().toString().length()>0)
			{
				smsnote+="#PHONE7#"+sosnumber7_edit.getText().toString();
				
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("sosnumber7", sosnumber7_edit.getText().toString());
		  		et.commit();
			}
			if(sosnumber8_edit.getText().toString().length()>0)
			{
				smsnote+="#PHONE8#"+sosnumber8_edit.getText().toString();
				
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("sosnumber8", sosnumber8_edit.getText().toString());
		  		et.commit();
			}
			smsnote+="#";
					
			Log.d("debug", smsnote);
					
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
			Toast.makeText(Sosnumber.this, R.string.Success, Toast.LENGTH_SHORT).show();
			
			
			
			// TODO Auto-generated method stub
			Intent it = new Intent(Sosnumber.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			Sosnumber.this.startActivity(it);
			Sosnumber.this.finish();
			
			
		}
		
	}
	
	private class TextWatchernumber1 implements TextWatcher
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
			String number1_temp = Sosnumber.this.sosnumber1_edit.getText().toString().trim();
			if(number1_temp.equals(""))
			{
				Sosnumber.this.sos_number1_set.setText(R.string.Set);
				Sosnumber.this.sos_number1_set.setBackgroundResource(R.drawable.btn_red);
			}
			else
			{
				Sosnumber.this.sos_number1_set.setText(R.string.Change);
				Sosnumber.this.sos_number1_set.setBackgroundResource(R.drawable.buttonform);
			}
		}
	}
	
	private class TextWatchernumber2 implements TextWatcher
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
			String number2_temp = Sosnumber.this.sosnumber2_edit.getText().toString().trim();
			if(number2_temp.equals(""))
			{
				Sosnumber.this.sos_number2_set.setText(R.string.Set);
				Sosnumber.this.sos_number2_set.setBackgroundResource(R.drawable.btn_red);
			}
			else
			{
				Sosnumber.this.sos_number2_set.setText(R.string.Change);
				Sosnumber.this.sos_number2_set.setBackgroundResource(R.drawable.buttonform);
			}
		}
	}
	
	private class TextWatchernumber3 implements TextWatcher
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
			String number3_temp = Sosnumber.this.sosnumber3_edit.getText().toString().trim();
			if(number3_temp.equals(""))
			{
				Sosnumber.this.sos_number3_set.setText(R.string.Set);
				Sosnumber.this.sos_number3_set.setBackgroundResource(R.drawable.btn_red);
			}
			else
			{
				Sosnumber.this.sos_number3_set.setText(R.string.Change);
				Sosnumber.this.sos_number3_set.setBackgroundResource(R.drawable.buttonform);
			}
		}
	}
	
	private class TextWatchernumber4 implements TextWatcher
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
			String number4_temp = Sosnumber.this.sosnumber4_edit.getText().toString().trim();
			if(number4_temp.equals(""))
			{
				Sosnumber.this.sos_number4_set.setText(R.string.Set);
				Sosnumber.this.sos_number4_set.setBackgroundResource(R.drawable.btn_red);
			}
			else
			{
				Sosnumber.this.sos_number4_set.setText(R.string.Change);
				Sosnumber.this.sos_number4_set.setBackgroundResource(R.drawable.buttonform);
			}
		}
	}
	
	private class TextWatchernumber5 implements TextWatcher
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
			String number5_temp = Sosnumber.this.sosnumber5_edit.getText().toString().trim();
			if(number5_temp.equals(""))
			{
				Sosnumber.this.sos_number5_set.setText(R.string.Set);
				Sosnumber.this.sos_number5_set.setBackgroundResource(R.drawable.btn_red);
			}
			else
			{
				Sosnumber.this.sos_number5_set.setText(R.string.Change);
				Sosnumber.this.sos_number5_set.setBackgroundResource(R.drawable.buttonform);
			}
		}
	}
	
	private class TextWatchernumber6 implements TextWatcher
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
			String number6_temp = Sosnumber.this.sosnumber6_edit.getText().toString().trim();
			if(number6_temp.equals(""))
			{
				Sosnumber.this.sos_number6_set.setText(R.string.Set);
				Sosnumber.this.sos_number6_set.setBackgroundResource(R.drawable.btn_red);
			}
			else
			{
				Sosnumber.this.sos_number6_set.setText(R.string.Change);
				Sosnumber.this.sos_number6_set.setBackgroundResource(R.drawable.buttonform);
			}
		}
	}
	
	private class TextWatchernumber7 implements TextWatcher
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
			String number1_temp = Sosnumber.this.sosnumber7_edit.getText().toString().trim();
			if(number1_temp.equals(""))
			{
				Sosnumber.this.sos_number7_set.setText(R.string.Set);
				Sosnumber.this.sos_number7_set.setBackgroundResource(R.drawable.btn_red);
			}
			else
			{
				Sosnumber.this.sos_number7_set.setText(R.string.Change);
				Sosnumber.this.sos_number7_set.setBackgroundResource(R.drawable.buttonform);
			}
		}
	}
	
	private class TextWatchernumber8 implements TextWatcher
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
			String number8_temp = Sosnumber.this.sosnumber8_edit.getText().toString().trim();
			if(number8_temp.equals(""))
			{
				Sosnumber.this.sos_number8_set.setText(R.string.Set);
				Sosnumber.this.sos_number8_set.setBackgroundResource(R.drawable.btn_red);
			}
			else
			{
				Sosnumber.this.sos_number8_set.setText(R.string.Change);
				Sosnumber.this.sos_number8_set.setBackgroundResource(R.drawable.buttonform);
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
	                    if (msgtemp.contains("PHONE")) 
	                    {  
	                        //TODO  
	                    	Toast.makeText(Sosnumber.this,R.string.Success, Toast.LENGTH_SHORT).show();
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
 	    //desname = uiState.getString("destname","" );
 	    password = "";
 	    //this.device_name.setText(desname);
 	    
		super.onRestart();
	}
	
	public void onBackPressed() { 
		Intent it = new Intent(Sosnumber.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		Sosnumber.this.startActivity(it);
		Sosnumber.this.finish();
    }
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(smsReceiver);//����ͷţ���Ȼ����פ�ڴ�
	}
	
	//sos number1 set
	private class OnClickListenersosnumber1set implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editphonenumber_temp = Sosnumber.this.sosnumber1_edit.getText().toString().trim();//���
			if(destnumber.equals("00000000000"))
			{	
				Toast.makeText(Sosnumber.this,R.string.Please_choose_a_device_first,Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET#PHONE1#"+editphonenumber_temp+"#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(Sosnumber.this, R.string.Success, Toast.LENGTH_SHORT).show();
				
				//save data
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("sosnumber1", editphonenumber_temp);
		  		et.commit();
			}
		}
		
	}
	
	//sos number2 set
	private class OnClickListenersosnumber2set implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editphonenumber_temp = Sosnumber.this.sosnumber2_edit.getText().toString().trim();//���
			if(destnumber.equals("00000000000"))
			{	
				Toast.makeText(Sosnumber.this, R.string.Please_choose_a_device_first, Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE2#911#
				String smstel = destnumber;//����
				String smsnote = "SET#PHONE2#"+editphonenumber_temp+"#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(Sosnumber.this, R.string.Success, Toast.LENGTH_SHORT).show();
			
				//save data
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("sosnumber2", editphonenumber_temp);
		  		et.commit();
			}
		}
		
	}
	
	//sos number3 set
	private class OnClickListenersosnumber3set implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editphonenumber_temp = Sosnumber.this.sosnumber3_edit.getText().toString().trim();//���
			if(destnumber.equals("00000000000"))
			{	
				Toast.makeText(Sosnumber.this, R.string.Please_set_destination_number_first, Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE3#911#
				String smstel = destnumber;//����
				String smsnote = "SET#PHONE3#"+editphonenumber_temp+"#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(Sosnumber.this, R.string.Success, Toast.LENGTH_SHORT).show();
			
				//save data
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("sosnumber3", editphonenumber_temp);
		  		et.commit();
			}
		}
		
	}
	
	//sos number4 set
	private class OnClickListenersosnumber4set implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editphonenumber_temp = Sosnumber.this.sosnumber4_edit.getText().toString().trim();//���
			if(destnumber.equals("00000000000"))
			{	
				Toast.makeText(Sosnumber.this, R.string.Please_set_destination_number_first, Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE4#911#
				String smstel = destnumber;//����
				String smsnote = "SET#PHONE4#"+editphonenumber_temp+"#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(Sosnumber.this, R.string.Success, Toast.LENGTH_SHORT).show();
			
				//save data
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("sosnumber4", editphonenumber_temp);
		  		et.commit();
			}
		}
		
	}
	
	//sos number5 set
	private class OnClickListenersosnumber5set implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editphonenumber_temp = Sosnumber.this.sosnumber5_edit.getText().toString().trim();//���
			if(destnumber.equals("00000000000"))
			{	
				Toast.makeText(Sosnumber.this, R.string.Please_set_destination_number_first, Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE5#911#
				String smstel = destnumber;//����
				String smsnote = "SET#PHONE5#"+editphonenumber_temp+"#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(Sosnumber.this, R.string.Success, Toast.LENGTH_SHORT).show();
			
				//save data
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("sosnumber5", editphonenumber_temp);
		  		et.commit();
			}
		}
		
	}
	
	//sos number6 set
	private class OnClickListenersosnumber6set implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editphonenumber_temp = Sosnumber.this.sosnumber6_edit.getText().toString().trim();//���
			if(destnumber.equals("00000000000"))
			{	
				Toast.makeText(Sosnumber.this, R.string.Please_set_destination_number_first, Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE6#911#
				String smstel = destnumber;//����
				String smsnote = "SET#PHONE6#"+editphonenumber_temp+"#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(Sosnumber.this, R.string.Success, Toast.LENGTH_SHORT).show();
			
				//save data
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("sosnumber6", editphonenumber_temp);
		  		et.commit();
			}
		}
		
	}
	
	//sos number7 set
	private class OnClickListenersosnumber7set implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editphonenumber_temp = Sosnumber.this.sosnumber7_edit.getText().toString().trim();//���
			if(destnumber.equals("00000000000"))
			{	
				Toast.makeText(Sosnumber.this, R.string.Please_set_destination_number_first, Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE7#911#
				String smstel = destnumber;//����
				String smsnote = "SET#PHONE7#"+editphonenumber_temp+"#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(Sosnumber.this, R.string.Success, Toast.LENGTH_SHORT).show();
			
				//save data
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("sosnumber7", editphonenumber_temp);
		  		et.commit();
			}
		}
		
	}
	
	//sos number8 set
	private class OnClickListenersosnumber8set implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editphonenumber_temp = Sosnumber.this.sosnumber8_edit.getText().toString().trim();//���
			if(destnumber.equals("00000000000"))
			{	
				Toast.makeText(Sosnumber.this, R.string.Please_set_destination_number_first, Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE8#911#
				String smstel = destnumber;//����
				String smsnote = "SET#PHONE8#"+editphonenumber_temp+"#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(Sosnumber.this, R.string.Success, Toast.LENGTH_SHORT).show();
			
				//save data
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("sosnumber8", editphonenumber_temp);
		  		et.commit();
			}
		}
		
	}
	
	//set enable
	private class OnClickListenersosnumbersetenble implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Sosnumber.this.set_enable.setBackgroundResource(R.drawable.btn_red2);
			Sosnumber.this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		}
		
	}
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(Sosnumber.this,GetSosNumber.class);//ʵ��Intent
			it.putExtra("myinfo", "entry get sosnumber");//Key
			Sosnumber.this.startActivity(it);
			Sosnumber.this.finish();
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
