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
//import android.view.View.OnTouchListener;
import android.widget.Button;
//import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AutoAnswerset extends Activity  implements OnClickListener {
	private SmsReceiver smsReceiver;
	private String destnumber = null;
	private String password = null;

	public static int autoanswerstate = 0;//1:on   0:off
	private TextView auto_answer_name = null;
	private ImageView auto_answer_state = null;
	
	private Button set_enable = null;
	private Button get_disable = null;
	private ImageView mainmenu_return_but = null;
	
	private Button btnBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.autoanswer);
		
		setContentView(R.layout.autoanswer);
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(this);
		
		//��̬ע����ն�����Ϣ
		IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		//����������ȼ�
        localIntentFilter.setPriority(Integer.MAX_VALUE);
        smsReceiver = new SmsReceiver();
        registerReceiver(smsReceiver, localIntentFilter);
		
		this.auto_answer_name = (TextView)super.findViewById(R.id.auto_answer_name);
		this.auto_answer_state = (ImageView)super.findViewById(R.id.auto_answer_state);
		this.auto_answer_state.setOnClickListener(new OnClickListenerautoanswer());
		
 	    //ʵ��
		this.set_enable = (Button)super.findViewById(R.id.set_enable);
		this.set_enable.setOnClickListener(new OnClickListenersosnumbersetenble());
		this.get_disable = (Button)super.findViewById(R.id.get_disable);
		this.get_disable.setOnClickListener(new OnClickListenersosnumbergetdisable());
	    this.set_enable.setBackgroundResource(R.drawable.btn_red2);
		this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		
		this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
	
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
 	   
 	  autoanswerstate = uiState.getInt("autoanswerstate", 0);
 	   if(autoanswerstate == 0)
 	   {
 		  this.auto_answer_state.setImageResource(R.drawable.app_home_disable);
 		 this.auto_answer_name.setTextSize(18);
 		 this.auto_answer_name.setText(getResources().getString(R.string.Disable));
 	   }
 	   else if(autoanswerstate == 1)
 	   {
 		  this.auto_answer_state.setImageResource(R.drawable.app_home_enable);
 		 this.auto_answer_name.setTextSize(18);
 		 this.auto_answer_name.setText(getResources().getString(R.string.Enable));
 	   }

	}
	
	private class OnClickListenerautoanswer implements OnClickListener
	{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		if(autoanswerstate == 0)
	  		{
	  			autoanswerstate = 1;
	  			AutoAnswerset.this.auto_answer_state.setImageResource(R.drawable.app_home_enable);
	  			AutoAnswerset.this.auto_answer_name.setTextSize(18);
	  			AutoAnswerset.this.auto_answer_name.setText(getResources().getString(R.string.Enable));
	  			
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SWECI#1#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(AutoAnswerset.this, R.string.Success, Toast.LENGTH_SHORT).show();
				
	  		}
	  		else if(autoanswerstate == 1)
	  		{
	  			autoanswerstate = 0;
	  			AutoAnswerset.this.auto_answer_state.setImageResource(R.drawable.app_home_disable);
	  			AutoAnswerset.this.auto_answer_name.setTextSize(18);
	  			AutoAnswerset.this.auto_answer_name.setText(getResources().getString(R.string.Disable));
	  			
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SWECI#0#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(AutoAnswerset.this, getResources().getString(R.string.Success), Toast.LENGTH_SHORT).show();
	  		}
	  		
	  		et.putInt("autoanswerstate", autoanswerstate);
	  		et.commit();
		}
		
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(AutoAnswerset.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			AutoAnswerset.this.startActivity(it);
			AutoAnswerset.this.finish();
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
	                    if (msgtemp.contains("+SWECI=")) 
	                    {  
	                        //TODO  
	                    	Toast.makeText(AutoAnswerset.this, getResources().getString(R.string.Success), Toast.LENGTH_SHORT).show();
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
 	    
 	 	  autoanswerstate = uiState.getInt("autoanswerstate", 0);
 	 	   if(autoanswerstate == 0)
 	 	   {
 	 		  this.auto_answer_state.setImageResource(R.drawable.app_home_disable);
 	 		 this.auto_answer_name.setTextSize(18);
 	 		 this.auto_answer_name.setText(R.string.Disable);
 	 	   }
 	 	   else if(autoanswerstate == 1)
 	 	   {
 	 		  this.auto_answer_state.setImageResource(R.drawable.app_home_enable);
 	 		 this.auto_answer_name.setTextSize(18);
 	 		 this.auto_answer_name.setText(R.string.Enable);
 	 	   }
 	 	   
		super.onRestart();
	}
	
	public void onBackPressed() { 
		Intent it = new Intent(AutoAnswerset.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		AutoAnswerset.this.startActivity(it);
		AutoAnswerset.this.finish();
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
			AutoAnswerset.this.set_enable.setBackgroundResource(R.drawable.btn_red2);
			AutoAnswerset.this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		}
	}
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(AutoAnswerset.this,GetAutoAnswer.class);//ʵ��Intent
			it.putExtra("myinfo", "entry get sosnumber");//Key
			AutoAnswerset.this.startActivity(it);
			AutoAnswerset.this.finish();
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
