package com.daktel.MobileAlertCommunicator;

import java.util.Locale;

//import java.text.SimpleDateFormat;
//import java.util.Date;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
//import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
//import android.widget.TextView;
import android.widget.Toast;

public class Languageset extends Activity  implements OnClickListener{
	private SmsReceiver smsReceiver;
	private String destnumber = null;
	private String password = null;
	private String lang_status = null;
	private Button set_enable = null;
	private Button get_disable = null;
	private ImageView mainmenu_return_but = null;
	private ImageButton lang_1 = null;
	private ImageButton lang_2 = null;
	private ImageButton lang_3 = null;
	private ImageButton lang_4 = null;
	private ImageButton lang_5 = null;
	private ImageButton lang_6 = null;
	private ImageButton lang_7 = null;	
	private LinearLayout laylanguage1 = null;
	private LinearLayout laylanguage2 = null;
	private LinearLayout laylanguage3 = null;
	private LinearLayout laylanguage4 = null;
	private LinearLayout laylanguage5 = null;
	private LinearLayout laylanguage6 = null;	
	private LinearLayout laylanguage7 = null;

	
	private Button btnBack;
	Configuration config;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.language);
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(this);
		Locale.setDefault(Locale.ENGLISH);
		config= getResources().getConfiguration();//获取系统的配置
		
		laylanguage1 = (LinearLayout)findViewById(R.id.laylanguage1);
		laylanguage2 = (LinearLayout)findViewById(R.id.laylanguage2);
		laylanguage3 = (LinearLayout)findViewById(R.id.laylanguage3);
		laylanguage4 = (LinearLayout)findViewById(R.id.laylanguage4);
		laylanguage5 = (LinearLayout)findViewById(R.id.laylanguage5);
		laylanguage6 = (LinearLayout)findViewById(R.id.laylanguage6);
		laylanguage7 = (LinearLayout)findViewById(R.id.laylanguage7);		
		this.laylanguage1.setOnClickListener(new OnClickListenerLang1());
	 	this.laylanguage2.setOnClickListener(new OnClickListenerLang2());
	 	this.laylanguage3.setOnClickListener(new OnClickListenerLang3());
	 	this.laylanguage4.setOnClickListener(new OnClickListenerLang4());
	 	this.laylanguage5.setOnClickListener(new OnClickListenerLang5());
	 	this.laylanguage6.setOnClickListener(new OnClickListenerLang6());
	 	this.laylanguage7.setOnClickListener(new OnClickListenerLang7());		
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
	    this.set_enable.setBackgroundResource(R.drawable.btn_red2);
		this.get_disable.setBackgroundResource(R.drawable.buttonform2);
	 	this.lang_1 = (ImageButton)super.findViewById(R.id.language1);
	 	this.lang_2 = (ImageButton)super.findViewById(R.id.language2);
	 	this.lang_3 = (ImageButton)super.findViewById(R.id.language3);
	 	this.lang_4 = (ImageButton)super.findViewById(R.id.language4);
	 	this.lang_5 = (ImageButton)super.findViewById(R.id.language5);
	 	this.lang_6 = (ImageButton)super.findViewById(R.id.language6);
	 	this.lang_7 = (ImageButton)super.findViewById(R.id.language7);	 	
	 	this.lang_1.setOnClickListener(new OnClickListenerLang1());
	 	this.lang_2.setOnClickListener(new OnClickListenerLang2());
	 	this.lang_3.setOnClickListener(new OnClickListenerLang3());
	 	this.lang_4.setOnClickListener(new OnClickListenerLang4());
	 	this.lang_5.setOnClickListener(new OnClickListenerLang5());
	 	this.lang_6.setOnClickListener(new OnClickListenerLang6());
	 	this.lang_7.setOnClickListener(new OnClickListenerLang7());	
	 	
	 	this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
	
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
 	    
 	    lang_status = uiState.getString("language","0");

 	    if(lang_status.equals("0")){
 	    	this.lang_1.setImageResource(R.drawable.add_language);
 	    } else {
 	    	this.lang_1.setImageResource(R.drawable.delete_language);
 	    }
 	    if(lang_status.equals("1")){
 	    	this.lang_2.setImageResource(R.drawable.add_language);
 	    } else {
 	    	this.lang_2.setImageResource(R.drawable.delete_language);
 	    }
 	    if(lang_status.equals("2")){
 	    	this.lang_3.setImageResource(R.drawable.add_language);
 	    } else {
 	    	this.lang_3.setImageResource(R.drawable.delete_language);
 	    }
 	    if(lang_status.equals("3")){
 	    	this.lang_4.setImageResource(R.drawable.add_language);
 	    } else {
 	    	this.lang_4.setImageResource(R.drawable.delete_language);
 	    }
 	    if(lang_status.equals("4")){
 	    	this.lang_5.setImageResource(R.drawable.add_language);
 	    } else {
 	    	this.lang_5.setImageResource(R.drawable.delete_language);
 	    }
 	    if(lang_status.equals("5")){
 	    	this.lang_6.setImageResource(R.drawable.add_language);
 	    } else {
 	    	this.lang_6.setImageResource(R.drawable.delete_language);
 	    }
 	   if(lang_status.equals("6")){
	    	this.lang_7.setImageResource(R.drawable.add_language);
	    } else {
	    	this.lang_7.setImageResource(R.drawable.delete_language);
	    }
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(Languageset.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			Languageset.this.startActivity(it);
			Languageset.this.finish();
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
	                    if (msgtemp.contains("+LANG=")) 
	                    {  
	                        //TODO  
	                    	Toast.makeText(Languageset.this,R.string.Success +msgtemp, Toast.LENGTH_SHORT).show();
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
		Intent it = new Intent(Languageset.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		Languageset.this.startActivity(it);
		Languageset.this.finish();
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
			Languageset.this.set_enable.setBackgroundResource(R.drawable.btn_red2);
			Languageset.this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		}
		
	}
	

	private class OnClickListenerLang1 implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			Languageset.this.lang_1.setImageResource(R.drawable.add_language);
			Languageset.this.lang_2.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_3.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_4.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_5.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_6.setImageResource(R.drawable.delete_language);

			String smstel = destnumber;//����
			String smsnote1 = "SET"+password+"#LANG#0#";//���
			
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote1,null,null);
			
			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		et.putString("language", "0");
	  		et.commit();
	  		
			Toast.makeText(Languageset.this, R.string.Success, Toast.LENGTH_SHORT).show();
			chageLanguage(ENGLISH);
		}		
	}

	private class OnClickListenerLang2 implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			Languageset.this.lang_1.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_2.setImageResource(R.drawable.add_language);
			Languageset.this.lang_3.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_4.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_5.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_6.setImageResource(R.drawable.delete_language);

			String smstel = destnumber;//����
			String smsnote1 = "SET"+password+"#LANG#1#";//���
			
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote1,null,null);
			
			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		et.putString("language", "1");
	  		et.commit();

	  		Toast.makeText(Languageset.this, R.string.Success, Toast.LENGTH_SHORT).show();
	  		chageLanguage(DUTCH);
		}		
	}

	private class OnClickListenerLang3 implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			Languageset.this.lang_1.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_2.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_3.setImageResource(R.drawable.add_language);
			Languageset.this.lang_4.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_5.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_6.setImageResource(R.drawable.delete_language);

			String smstel = destnumber;//����
			String smsnote1 = "SET"+password+"#LANG#2#";//���
			
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote1,null,null);
			
			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		et.putString("language", "2");
	  		et.commit();
	  		
	  		Toast.makeText(Languageset.this, R.string.Success, Toast.LENGTH_SHORT).show();
	  		chageLanguage(FRENCH);
		}		
	}

	private class OnClickListenerLang4 implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			Languageset.this.lang_1.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_2.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_3.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_4.setImageResource(R.drawable.add_language);
			Languageset.this.lang_5.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_6.setImageResource(R.drawable.delete_language);

			String smstel = destnumber;//����
			String smsnote1 = "SET"+password+"#LANG#3#";//���
			
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote1,null,null);
			
			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		et.putString("language", "3");
	  		et.commit();
	  		
	  		Toast.makeText(Languageset.this, R.string.Success, Toast.LENGTH_SHORT).show();
	  		chageLanguage(GERMAN);
		}		
	}

	private class OnClickListenerLang5 implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			Languageset.this.lang_1.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_2.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_3.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_4.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_5.setImageResource(R.drawable.add_language);
			Languageset.this.lang_6.setImageResource(R.drawable.delete_language);
			
			String smstel = destnumber;//����
			String smsnote1 = "SET"+password+"#LANG#4#";//���
			
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote1,null,null);
			
			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		et.putString("language", "4");
	  		et.commit();
	  		
	  		Toast.makeText(Languageset.this, R.string.Success, Toast.LENGTH_SHORT).show();
	  		chageLanguage(PORTUGUESE);
		}
	}

	private class OnClickListenerLang6 implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			Languageset.this.lang_1.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_2.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_3.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_4.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_5.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_6.setImageResource(R.drawable.add_language);
			String smstel = destnumber;//����
			String smsnote1 = "SET"+password+"#LANG#5#";//���
			
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote1,null,null);
			
			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		et.putString("language", "5");
	  		et.commit();
	  		
	  		Toast.makeText(Languageset.this, R.string.Success, Toast.LENGTH_SHORT).show();
	  		chageLanguage(SPANISH);
		}
	}

	
	
	private class OnClickListenerLang7 implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			Languageset.this.lang_1.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_2.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_3.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_4.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_5.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_6.setImageResource(R.drawable.delete_language);
			Languageset.this.lang_7.setImageResource(R.drawable.add_language);
			String smstel = destnumber;//����
			String smsnote1 = "SET"+password+"#LANG#6#";//���
			
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote1,null,null);
			
			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		et.putString("language", "6");
	  		et.commit();
	  		
	  		Toast.makeText(Languageset.this, R.string.Success, Toast.LENGTH_SHORT).show();
	  		chageLanguage(TURKISH);
		}
	}
	
	
	
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(Languageset.this,GetLanguage.class);//ʵ��Intent
			it.putExtra("myinfo", "entry get sosnumber");//Key
			Languageset.this.startActivity(it);
			Languageset.this.finish();
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
	public final static int ENGLISH=0;
	public final static int DUTCH=1;
	public final static int FRENCH=2;
	public final static int GERMAN=3;
	public final static int PORTUGUESE=4;
	public final static int SPANISH=5;
	public final static int TURKISH=6;
	public final static String LANGUAGE_TYPE="language";
	
	public final static String HAS_SET_ENGLISH="has_set_english";
    public final static String HAS_SET_DUTCH="has_set_DUTCH";
    public final static String HAS_SET_FRENCH="has_set_FRENCH";
    public final static String HAS_SET_GERMAN="has_set_GERMAN";
    public final static String HAS_SET_PORTUGUESE="has_set_PORTUGUESE";
    public final static String HAS_SET_SPANISH="has_set_SPANISH";
    public final static String HAS_SET_TURKISH="has_set_TURKISH";
	
	private void chageLanguage(int position){
		switch(position){
		case ENGLISH:
			config.locale = Locale.getDefault();
			break;
		case DUTCH:
			config.locale = Locale.JAPAN;
			break;
		case FRENCH:
			config.locale = Locale.FRENCH;
			break;
		case GERMAN:
			config.locale = Locale.GERMANY;
			break;
		case PORTUGUESE:
			config.locale = Locale.ITALIAN;
			break;
		case SPANISH:
			config.locale = Locale.CHINESE;
			break;
		case TURKISH:
			config.locale = Locale.KOREAN;
			break;
		}
		SharePreferenceUtil.setPreference(Languageset.this, LANGUAGE_TYPE,position+"");
		getResources().updateConfiguration(config, getResources().getDisplayMetrics());//更新配置
        Intent intent=new Intent(Languageset.this,MainMenuSetting.class);
        intent.putExtra("language","language");
        startActivity(intent);
        finish();
	}
	
	
	
	
}
