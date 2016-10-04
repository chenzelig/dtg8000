package com.daktel.MobileAlertCommunicator;

//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.BroadcastReceiver;
import android.os.Bundle;
//import android.os.PatternMatcher;
//import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
//import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
//import android.view.View.OnTouchListener;
import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class GetSosNumber extends Activity implements OnClickListener  {
	//private TextView device_name = null;
	private SmsReceiver smsReceiver;
	private TextView get_sos_number1 = null;
	private TextView get_sos_number2 = null;
	private TextView get_sos_number3 = null;
	private TextView get_sos_number4 = null;
	private TextView get_sos_number5 = null;
	private TextView get_sos_number6 = null;
	private TextView get_sos_number7 = null;
	private TextView get_sos_number8 = null;
	
	//private Button add_device = null;
	//private Button switch_device = null;
	private Button set_enable = null;
	private Button get_disable = null;
	private ImageView mainmenu_return_but = null;
	
	private String destnumber = null;
	//private String desname = null;
	private String password = null;
	
	private String sosnumber1,sosnumber2,sosnumber3,sosnumber4,sosnumber5,sosnumber6,sosnumber7,sosnumber8;
	
	private Button btnBack;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.getsosnumber);
		
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
		
        btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(this);
 	    //ʵ��
		this.set_enable = (Button)super.findViewById(R.id.set_enable);
		this.set_enable.setOnClickListener(new OnClickListenersosnumbersetenble());
		this.get_disable = (Button)super.findViewById(R.id.get_disable);
		this.get_disable.setOnClickListener(new OnClickListenersosnumbergetdisable());
		this.set_enable.setBackgroundResource(R.drawable.buttonform2);
		this.get_disable.setBackgroundResource(R.drawable.btn_red2);
		
		this.get_sos_number1 = (TextView)super.findViewById(R.id.get_sos_number1);
		this.get_sos_number2 = (TextView)super.findViewById(R.id.get_sos_number2);
		this.get_sos_number3 = (TextView)super.findViewById(R.id.get_sos_number3);
		this.get_sos_number4 = (TextView)super.findViewById(R.id.get_sos_number4);
		this.get_sos_number5 = (TextView)super.findViewById(R.id.get_sos_number5);
		this.get_sos_number6 = (TextView)super.findViewById(R.id.get_sos_number6);
		this.get_sos_number7 = (TextView)super.findViewById(R.id.get_sos_number7);
		this.get_sos_number8 = (TextView)super.findViewById(R.id.get_sos_number8);
		
		this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
		
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    //desname = uiState.getString("destname","" );
 	    password = "";
 	    //this.device_name.setText(desname);
 	    
		sosnumber1 = uiState.getString("sosnumber1","" );
		sosnumber2 = uiState.getString("sosnumber2","" );
		sosnumber3 = uiState.getString("sosnumber3","" );
		sosnumber4 = uiState.getString("sosnumber4","" );
		sosnumber5 = uiState.getString("sosnumber5","" );
		sosnumber6 = uiState.getString("sosnumber6","" );
		sosnumber7 = uiState.getString("sosnumber7","" );
		sosnumber8 = uiState.getString("sosnumber8","" );
		
		if(!sosnumber1.equals(""))
			this.get_sos_number1.setText(sosnumber1);
		
		if(!sosnumber2.equals(""))
			this.get_sos_number2.setText(sosnumber2);
		
		if(!sosnumber3.equals(""))
			this.get_sos_number3.setText(sosnumber3);
		
		if(!sosnumber4.equals(""))
			this.get_sos_number4.setText(sosnumber4);
		
		if(!sosnumber5.equals(""))
			this.get_sos_number5.setText(sosnumber5);
		
		if(!sosnumber6.equals(""))
			this.get_sos_number6.setText(sosnumber6);
		
		if(!sosnumber7.equals(""))
			this.get_sos_number7.setText(sosnumber7);
		
		if(!sosnumber8.equals(""))
			this.get_sos_number8.setText(sosnumber8);
		
		//����LinearLayout
		LinearLayout ll = (LinearLayout) findViewById(R.id.menu_get_sos_number);
		ll.setOnLongClickListener(new OnLongClickListenergentsosnumber());
		
//        ll.setOnClickListener(new OnClickListener() {
//            
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Log.e("ActivityMusic", "====bBLayout�������");
//            }
//        });

		
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(GetSosNumber.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			GetSosNumber.this.startActivity(it);
			GetSosNumber.this.finish();
		}
		
	}
	
	private class OnLongClickListenergentsosnumber implements OnLongClickListener
	{
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(GetSosNumber.this, "Long click linearlayout.", Toast.LENGTH_SHORT).show();
			
			return false;
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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(smsReceiver);//����ͷţ���Ȼ����פ�ڴ�
	}

	public void onBackPressed() { 
		Intent it = new Intent(GetSosNumber.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		GetSosNumber.this.startActivity(it);
		GetSosNumber.this.finish();
    }
	
	//set enable
	private class OnClickListenersosnumbersetenble implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(GetSosNumber.this,Sosnumber.class);//ʵ��Intent
			it.putExtra("myinfo", "entry set sosnumber");//Key
			GetSosNumber.this.startActivity(it);
			GetSosNumber.this.finish();
		}
		
	}
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			GetSosNumber.this.set_enable.setBackgroundResource(R.drawable.buttonform2);
			GetSosNumber.this.get_disable.setBackgroundResource(R.drawable.btn_red2);
			String smstel = destnumber;//����
			String smsnote1 = "GET"+password+"#PHONE1#PHONE2#PHONE3#PHONE4#PHONE5#PHONE6#PHONE7#PHONE8#";//���
			
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote1,null,null);
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
	                    if (msgtemp.contains("+PHONE1=")) {  
	                        //TODO
	                    	String sos_num = msgtemp.substring(msgtemp.indexOf("+PHONE1=") + 8, msgtemp.indexOf("#", msgtemp.indexOf("+PHONE1=") + 8));
	                    	//Toast.makeText(GetSosNumber.this, "Received:"+msgtemp+" from:"+numbertemp, Toast.LENGTH_SHORT).show();
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("sosnumber1", sos_num);
	        		  		et.commit();
	        		  		if(sos_num.equals("")){
	        		  			GetSosNumber.this.get_sos_number1.setText(getResources().getString(R.string.Unset));
	        		  		} else {
	        		  			GetSosNumber.this.get_sos_number1.setText(sos_num);
	        		  		}
	        		  	} 

	                    if(msgtemp.contains("+PHONE2=")) {
	                    	String sos_num = msgtemp.substring(msgtemp.indexOf("+PHONE2=") + 8, msgtemp.indexOf("#", msgtemp.indexOf("+PHONE2=") + 8));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("sosnumber2", sos_num);
	        		  		et.commit();
	        		  		if(sos_num.equals("")){
	        		  			GetSosNumber.this.get_sos_number2.setText(getResources().getString(R.string.Unset));
	        		  		} else {
	        		  			GetSosNumber.this.get_sos_number2.setText(sos_num);
	        		  		}
	                    }
	                    
	                    if(msgtemp.contains("+PHONE3=")) {
	                    	String sos_num = msgtemp.substring(msgtemp.indexOf("+PHONE3=") + 8, msgtemp.indexOf("#", msgtemp.indexOf("+PHONE3=") + 8));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("sosnumber3", sos_num);
	        		  		et.commit();
	        		  		if(sos_num.equals("")){
	        		  			GetSosNumber.this.get_sos_number3.setText(getResources().getString(R.string.Unset));
	        		  		} else {
	        		  			GetSosNumber.this.get_sos_number3.setText(sos_num);
	        		  		}

	                    } 
	                    
	                    if(msgtemp.contains("+PHONE4=")) {
	                    	String sos_num = msgtemp.substring(msgtemp.indexOf("+PHONE4=") + 8, msgtemp.indexOf("#", msgtemp.indexOf("+PHONE4=") + 8));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("sosnumber4", sos_num);
	        		  		et.commit();
	        		  		if(sos_num.equals("")){
	        		  			GetSosNumber.this.get_sos_number4.setText(getResources().getString(R.string.Unset));
	        		  		} else {
	        		  			GetSosNumber.this.get_sos_number4.setText(sos_num);
	        		  		}

	                    }
	                    
	                    if(msgtemp.contains("+PHONE5=")) {
	                    	String sos_num = msgtemp.substring(msgtemp.indexOf("+PHONE5=") + 8, msgtemp.indexOf("#", msgtemp.indexOf("+PHONE5=") + 8));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("sosnumber5", sos_num);
	        		  		et.commit();
	        		  		if(sos_num.equals("")){
	        		  			GetSosNumber.this.get_sos_number5.setText(getResources().getString(R.string.Unset));
	        		  		} else {
	        		  			GetSosNumber.this.get_sos_number5.setText(sos_num);
	        		  		}

	                    }
	                    
	                    if(msgtemp.contains("+PHONE6=")) {
	                    	String sos_num = msgtemp.substring(msgtemp.indexOf("+PHONE6=") + 8, msgtemp.indexOf("#", msgtemp.indexOf("+PHONE6=") + 8));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("sosnumber6", sos_num);
	        		  		et.commit();
	        		  		if(sos_num.equals("")){
	        		  			GetSosNumber.this.get_sos_number6.setText(getResources().getString(R.string.Unset));
	        		  		} else {
	        		  			GetSosNumber.this.get_sos_number6.setText(sos_num);
	        		  		}

	                    }
	                    
	                    if(msgtemp.contains("+PHONE7=")) {
	                    	String sos_num = msgtemp.substring(msgtemp.indexOf("+PHONE7=") + 8, msgtemp.indexOf("#", msgtemp.indexOf("+PHONE7=") + 8));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("sosnumber7", sos_num);
	        		  		et.commit();
	        		  		if(sos_num.equals("")){
	        		  			GetSosNumber.this.get_sos_number7.setText(getResources().getString(R.string.Unset));
	        		  		} else {
	        		  			GetSosNumber.this.get_sos_number7.setText(sos_num);
	        		  		}

	                    }
	                    
	                    if(msgtemp.contains("+PHONE8=")) {
	                    	String sos_num = msgtemp.substring(msgtemp.indexOf("+PHONE8=") + 8, msgtemp.indexOf("#", msgtemp.indexOf("+PHONE8=") + 8));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("sosnumber8", sos_num);
	        		  		et.commit();
	        		  		if(sos_num.equals("")){
	        		  			GetSosNumber.this.get_sos_number8.setText(getResources().getString(R.string.Unset));
	        		  		} else {
	        		  			GetSosNumber.this.get_sos_number8.setText(sos_num);
	        		  		}

	                    }
	                } 
	            }
	            
	        }  
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

