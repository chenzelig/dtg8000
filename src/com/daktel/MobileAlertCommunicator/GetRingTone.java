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

public class GetRingTone extends Activity implements OnClickListener {
	private SmsReceiver smsReceiver;
	private Button set_enable = null;
	private Button get_disable = null;
	private ImageView mainmenu_return_but = null;
	
	private String destnumber = null;
	private String password = null;
	
	private TextView get_ring_scene = null;
	private TextView get_ring_type = null;
	private TextView get_ring_volume = null;
	
	private String ringscene = null;
	private String ringtype = null;
	private String ringvolume = null;
	
	private Button btnBack;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		super.setContentView(R.layout.getringtone);
		
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
		
		this.get_ring_scene = (TextView)super.findViewById(R.id.get_ring_scene);
		this.get_ring_type = (TextView)super.findViewById(R.id.get_ring_type);
		this.get_ring_volume = (TextView)super.findViewById(R.id.get_ring_volume);
		
		this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
		
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
		
 	   ringscene = uiState.getString("ringscene", "");
 	   ringtype = uiState.getString("ringtype", "");
 	   ringvolume = uiState.getString("ringvolume", "");
 	   
 	    if(ringscene == "1")
 	    	this.get_ring_scene.setText(getResources().getString(R.string.Ring_Only));
 	    else if(ringscene == "2")
 	    	this.get_ring_scene.setText(getResources().getString(R.string.Ring_Vibration));
 	    else if(ringscene == "3")
 	    	this.get_ring_scene.setText(getResources().getString(R.string.Vibration));
 	    else if(ringscene == "4")
 	    	this.get_ring_scene.setText(getResources().getString(R.string.Mute));
 	    
 	    this.get_ring_type.setText(ringtype);
 	    this.get_ring_volume.setText(ringvolume);
		
		//����LinearLayout
		LinearLayout ll = (LinearLayout) findViewById(R.id.menu_get_ring_tone);
		ll.setOnLongClickListener(new OnLongClickListenergetringtone());
		
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(GetRingTone.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			GetRingTone.this.startActivity(it);
			GetRingTone.this.finish();
		}
		
	}
	
	private class OnLongClickListenergetringtone implements OnLongClickListener
	{
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(GetRingTone.this, "Long click linearlayout.", Toast.LENGTH_SHORT).show();
			
			return false;
		}
		
	}
	
	@Override
	protected void onRestart(){
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
 	    
  	   ringscene = uiState.getString("ringscene", "");
  	   ringtype = uiState.getString("ringtype", "");
  	   ringvolume = uiState.getString("ringvolume", "");
  	   
  	    if(Integer.parseInt(ringscene) == 1)
  	    	this.get_ring_scene.setText(getResources().getString(R.string.Ring_Only));
  	    else if(Integer.parseInt(ringscene) == 2)
  	    	this.get_ring_scene.setText(getResources().getString(R.string.Ring_Vibration));
  	    else if(Integer.parseInt(ringscene) == 3)
  	    	this.get_ring_scene.setText(getResources().getString(R.string.Vibration));
  	    else if(Integer.parseInt(ringscene) == 4)
  	    	this.get_ring_scene.setText(getResources().getString(R.string.Mute));
  	    
  	    this.get_ring_type.setText(ringtype);
  	    this.get_ring_volume.setText(ringvolume);
  	    
		super.onRestart();
	}
	
	public void onBackPressed() { 
		Intent it = new Intent(GetRingTone.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		GetRingTone.this.startActivity(it);
		GetRingTone.this.finish();
    }
	
	//set enable
	private class OnClickListenersosnumbersetenble implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(GetRingTone.this,RingToneset.class);//ʵ��Intent
			it.putExtra("myinfo", "entry set sosnumber");//Key
			GetRingTone.this.startActivity(it);
			GetRingTone.this.finish();
		}
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(smsReceiver);//����ͷţ���Ȼ����פ�ڴ�
	}

	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			GetRingTone.this.set_enable.setBackgroundResource(R.drawable.buttonform2);
			GetRingTone.this.get_disable.setBackgroundResource(R.drawable.btn_red2);
			String smstel = destnumber;//����
			String smsnote1 = "GET"+password+"#RING#SCENE#VOL#";//���
			//String smsnote2 = "GET"+password+"#SCENE#";//���
			//String smsnote3 = "GET"+password+"#VOL#";//���
			
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote1,null,null);
			//smsmanager.sendTextMessage(smstel,null,smsnote2,null,null);
			//smsmanager.sendTextMessage(smstel,null,smsnote3,null,null);
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
	                    if (msgtemp.contains("+RING=")) {  
	                        //TODO
	                    	String ring_type = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	                    	//Toast.makeText(GetSosNumber.this, "Received:"+msgtemp+" from:"+numbertemp, Toast.LENGTH_SHORT).show();
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("ringtype", ring_type);
	        		  		et.commit();
	        		  		GetRingTone.this.get_ring_type.setText(ring_type);
	                    } else if(msgtemp.contains("+VOL=")) {
	                    	String vol_level = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("ringvolume", vol_level);
	        		  		et.commit();
	        		  		GetRingTone.this.get_ring_volume.setText(vol_level);
	                    } else if(msgtemp.contains("+SCENE=")) {
	                    	String ring_scene = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("ringscene", ring_scene);
	        		  		et.commit();
	        		  		if(Integer.parseInt(ring_scene) == 1){
	        		  			GetRingTone.this.get_ring_scene.setText(getResources().getString(R.string.Ring_Only));
	        		  		} else if (Integer.parseInt(ring_scene) == 2) {
	        		  			GetRingTone.this.get_ring_scene.setText(getResources().getString(R.string.Ring_Vibration));
	        		  		} else if (Integer.parseInt(ring_scene) == 3) {
	        		  			GetRingTone.this.get_ring_scene.setText(getResources().getString(R.string.Vibration));
	        		  		} else if (Integer.parseInt(ring_scene) == 4) {
	        		  			GetRingTone.this.get_ring_scene.setText(getResources().getString(R.string.Mute));
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
