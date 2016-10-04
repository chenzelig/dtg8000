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

public class GetLanguage extends Activity implements OnClickListener {
	private SmsReceiver smsReceiver;
	private Button set_enable = null;
	private Button get_disable = null;
	private ImageView mainmenu_return_but = null;
	
	private String destnumber = null;
	private String password = null;
	private String lang_status = null;
	
	private TextView get_lang_name = null;
	private Button btnBack;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		super.setContentView(R.layout.getlanguage);
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(this);
		//��̬ע����ն�����Ϣ
		IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		//����������ȼ�
        localIntentFilter.setPriority(Integer.MAX_VALUE);
        smsReceiver = new SmsReceiver();
        registerReceiver(smsReceiver, localIntentFilter);
		
 	    //ʵ��
		this.set_enable = (Button)super.findViewById(R.id.set_enable);
		this.set_enable.setOnClickListener(new OnClickListenersosnumbersetenble());
		this.get_disable = (Button)super.findViewById(R.id.get_disable);
		this.get_disable.setOnClickListener(new OnClickListenersosnumbergetdisable());
		this.set_enable.setBackgroundResource(R.drawable.buttonform2);
		this.get_disable.setBackgroundResource(R.drawable.btn_red2);
		this.get_lang_name = (TextView)super.findViewById(R.id.get_language_state);
		
		this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
			
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
 	    lang_status = uiState.getString("language","");

 	    if(lang_status.equals("")){
 	    	this.get_lang_name.setText(getResources().getString(R.string.Unset));
 	    } else if (lang_status.equals("0")){
 	    	this.get_lang_name.setText(getResources().getString(R.string.English));
 	    } else if (lang_status.equals("1")){
 	    	this.get_lang_name.setText(getResources().getString(R.string.Dutch));
 	    } else if (lang_status.equals("2")){
 	    	this.get_lang_name.setText(getResources().getString(R.string.French));
 	    } else if (lang_status.equals("3")){
 	    	this.get_lang_name.setText(getResources().getString(R.string.German));
 	    } else if (lang_status.equals("4")){
 	    	this.get_lang_name.setText(getResources().getString(R.string.Portuguese));
 	    } else if (lang_status.equals("5")){
 	    	this.get_lang_name.setText(getResources().getString(R.string.Spanish));
 	    }
 	   else if (lang_status.equals("6")){
	    	this.get_lang_name.setText(getResources().getString(R.string.Turkish));
	    }
 	    
		//����LinearLayout
		LinearLayout ll = (LinearLayout) findViewById(R.id.menu_get_language);
		ll.setOnLongClickListener(new OnLongClickListenergetlanguage());
		
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(GetLanguage.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			GetLanguage.this.startActivity(it);
			GetLanguage.this.finish();
		}
		
	}
	
	private class OnLongClickListenergetlanguage implements OnLongClickListener
	{
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(GetLanguage.this, "Long click linearlayout.", Toast.LENGTH_SHORT).show();
			
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
		Intent it = new Intent(GetLanguage.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		GetLanguage.this.startActivity(it);
		GetLanguage.this.finish();
    }
	
	//set enable
	private class OnClickListenersosnumbersetenble implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(GetLanguage.this,Languageset.class);//ʵ��Intent
			it.putExtra("myinfo", "entry set sosnumber");//Key
			GetLanguage.this.startActivity(it);
			GetLanguage.this.finish();
		}
		
	}
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			GetLanguage.this.set_enable.setBackgroundResource(R.drawable.buttonform2);
			GetLanguage.this.get_disable.setBackgroundResource(R.drawable.btn_red2);
			
			String smstel = destnumber;//����
			String smsnote1 = "GET"+password+"#LANG#";//���
			
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
	                    if (msgtemp.contains("+LANG=")) {  
	                        //TODO
	                    	String lang_status = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	                    	//Toast.makeText(GetSosNumber.this, "Received:"+msgtemp+" from:"+numbertemp, Toast.LENGTH_SHORT).show();
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("language", lang_status);
	        		  		et.commit();
	        		  		
	        		  		if(lang_status.equals("")){
	        		  			GetLanguage.this.get_lang_name.setText(getResources().getString(R.string.Unset));
	        		  		} else if(lang_status.equals("0")){
	        		  			GetLanguage.this.get_lang_name.setText(getResources().getString(R.string.English));
	        		  		} else if(lang_status.equals("1")){
	        		  			GetLanguage.this.get_lang_name.setText(getResources().getString(R.string.Dutch));
	        		  		} else if(lang_status.equals("2")){
	        		  			GetLanguage.this.get_lang_name.setText(getResources().getString(R.string.French));
	        		  		} else if(lang_status.equals("3")){
	        		  			GetLanguage.this.get_lang_name.setText(getResources().getString(R.string.German));
	        		  		} else if(lang_status.equals("4")){
	        		  			GetLanguage.this.get_lang_name.setText(getResources().getString(R.string.Portuguese));
	        		  		} else if(lang_status.equals("5")){
	        		  			GetLanguage.this.get_lang_name.setText(getResources().getString(R.string.Spanish));
	        		  		}
	        		  		else if(lang_status.equals("6")){
	        		  			GetLanguage.this.get_lang_name.setText(getResources().getString(R.string.Turkish));
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
