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
import android.util.Log;
//import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
//import android.view.View.OnTouchListener;
import android.widget.Button;
//import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GetSosMessage extends Activity  implements OnClickListener{
	private SmsReceiver smsReceiver;
	private Button set_enable = null;
	private Button get_disable = null;
	
	private String destnumber = null;
	private String password = null;
	
	private TextView get_sos_message1 = null;
	private TextView get_sos_message2 = null;
	private TextView get_sos_message3 = null;
	private TextView get_sos_message4 = null;
	private TextView get_sos_message5 = null;
	
	//private TextView get_sos_message1_check = null;
	//private TextView get_sos_message2_check = null;
	//private TextView get_sos_message3_check = null;
	//private TextView get_sos_message4_check = null;
	//private TextView get_sos_message5_check = null;
	
	private ImageView mainmenu_return_but = null;
	private String sosmessage1,sosmessage2,sosmessage3,sosmessage4,sosmessage5;
	
	private Button btnBack;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		super.setContentView(R.layout.getsosmessage);
		
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
		this.set_enable.setBackgroundResource(R.drawable.buttonform2);
		this.get_disable.setBackgroundResource(R.drawable.btn_red2);
		
		
		this.get_sos_message1 = (TextView)super.findViewById(R.id.get_sos_message1);
		this.get_sos_message2 = (TextView)super.findViewById(R.id.get_sos_message2);
		this.get_sos_message3 = (TextView)super.findViewById(R.id.get_sos_message3);
		this.get_sos_message4 = (TextView)super.findViewById(R.id.get_sos_message4);
		this.get_sos_message5 = (TextView)super.findViewById(R.id.get_sos_message5);
		
		this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());

        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
		
 	   sosmessage1 = uiState.getString("sosmessage1","" );
 	   sosmessage2 = uiState.getString("sosmessage2","" );
 	   sosmessage3 = uiState.getString("sosmessage3","" );
 	   sosmessage4 = uiState.getString("sosmessage4","" );
 	   sosmessage5 = uiState.getString("sosmessage5","" );

 	    if(!sosmessage1.equals(""))
 	    	this.get_sos_message1.setText(sosmessage1);
 	    
 	    if(!sosmessage2.equals(""))
 	    	this.get_sos_message2.setText(sosmessage2);
 	    
 	    if(!sosmessage3.equals(""))
 	    	this.get_sos_message3.setText(sosmessage3);
 	    
 	    if(!sosmessage4.equals(""))
 	    	this.get_sos_message4.setText(sosmessage4);
 	    
 	    if(!sosmessage5.equals(""))
 	    	this.get_sos_message5.setText(sosmessage5);
 	    
		
		//����LinearLayout
		LinearLayout ll = (LinearLayout) findViewById(R.id.menu_get_sos_message);
		ll.setOnLongClickListener(new OnLongClickListenergetsosmessage());
		
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(GetSosMessage.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			GetSosMessage.this.startActivity(it);
			GetSosMessage.this.finish();
		}
		
	}
	
	private class OnLongClickListenergetsosmessage implements OnLongClickListener
	{
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(GetSosMessage.this, "Long click linearlayout.", Toast.LENGTH_SHORT).show();
			
			return false;
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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(smsReceiver);//����ͷţ���Ȼ����פ�ڴ�
	}

	public void onBackPressed() { 
		Intent it = new Intent(GetSosMessage.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		GetSosMessage.this.startActivity(it);
		GetSosMessage.this.finish();
    }

	//set enable
	private class OnClickListenersosnumbersetenble implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(GetSosMessage.this,SosMessage.class);//ʵ��Intent
			it.putExtra("myinfo", "entry set sosnumber");//Key
			GetSosMessage.this.startActivity(it);
			GetSosMessage.this.finish();
		}
		
	}
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			GetSosMessage.this.set_enable.setBackgroundResource(R.drawable.buttonform2);
			GetSosMessage.this.get_disable.setBackgroundResource(R.drawable.btn_red2);
			
			String smstel = destnumber;//����
			String smsnote1 = "GET"+password+"#SOSMSG1#SOSMSG2#SOSMSG3#SOSMSG4#SOSMSG5#CONFIGSMS1#CONFIGSMS2#CONFIGSMS3#CONFIGSMS4#CONFIGSMS5#";//���
//			String smsnote2 = "GET"+password+"#SOSMSG2#";//���
//			String smsnote3 = "GET"+password+"#SOSMSG3#";//���
//			String smsnote4 = "GET"+password+"#SOSMSG4#";//���
//			String smsnote5 = "GET"+password+"#SOSMSG5#";//���
			
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote1,null,null);
//			smsmanager.sendTextMessage(smstel,null,smsnote2,null,null);
//			smsmanager.sendTextMessage(smstel,null,smsnote3,null,null);
//			smsmanager.sendTextMessage(smstel,null,smsnote4,null,null);
//			smsmanager.sendTextMessage(smstel,null,smsnote5,null,null);
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
	                    if (msgtemp.contains("+SOSMSG1=")) {  
	                        //TODO
	                    	String sos_msg = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	                    	//Toast.makeText(GetSosNumber.this, "Received:"+msgtemp+" from:"+numbertemp, Toast.LENGTH_SHORT).show();
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("sosmessage1", sos_msg);
	        		  		et.commit();
	        		  		GetSosMessage.this.get_sos_message1.setText(sos_msg);
	                    } else if(msgtemp.contains("+SOSMSG2=")) {
	                    	String sos_msg = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("sosmessage2", sos_msg);
	        		  		et.commit();
	        		  		GetSosMessage.this.get_sos_message2.setText(sos_msg);
	                    } else if(msgtemp.contains("+SOSMSG3=")) {
	                    	String sos_msg = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("sosmessage3", sos_msg);
	        		  		et.commit();
	        		  		GetSosMessage.this.get_sos_message3.setText(sos_msg);
	                    } else if(msgtemp.contains("+SOSMSG4=")) {
	                    	String sos_msg = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("sosmessage4", sos_msg);
	        		  		et.commit();
	        		  		GetSosMessage.this.get_sos_message4.setText(sos_msg);
	                    } else if(msgtemp.contains("+SOSMSG5=")) {
	                    	String sos_msg = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("sosmessage5", sos_msg);
	        		  		et.commit();
	        		  		GetSosMessage.this.get_sos_message5.setText(sos_msg);
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
