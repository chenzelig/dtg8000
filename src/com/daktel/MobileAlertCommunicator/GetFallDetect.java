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

public class GetFallDetect extends Activity implements OnClickListener {
	private Button set_enable = null;
	private Button get_disable = null;
	private ImageView mainmenu_return_but = null;
	private SmsReceiver smsReceiver;
	
	private String destnumber = null;
	private String password = null;
	
	private TextView fall_detect_name = null;
	
	private Button btnBack;
	
	private TextView falldetectnumber = null;
	private TextView tvSensitvitystep = null;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		super.setContentView(R.layout.getfalldetect);
		
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(this);
		
		falldetectnumber=(TextView)findViewById(R.id.falldetectnumber);
		tvSensitvitystep=(TextView)findViewById(R.id.tvSensitvitystep);		
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
		
		this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
		
		this.fall_detect_name = (TextView)super.findViewById(R.id.fall_detect_name);
		
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
		if(FallDetectset.falldetectstate == 0)
		{
	 		 this.fall_detect_name.setTextSize(18);
	 		 this.fall_detect_name.setText(getResources().getString(R.string.Disable));
		}
		else if(FallDetectset.falldetectstate == 1)
		{
	 		 this.fall_detect_name.setTextSize(18);
	 		 this.fall_detect_name.setText(getResources().getString(R.string.Enable));
		}
		
		
		falldetectnumber.setText(uiState.getString("falldetectnumber", "none"));
		
		String sensitivitystep = uiState.getString("sensitivitystep", "none");
		if(sensitivitystep.equals("0"))
		{
			tvSensitvitystep.setText(R.string._low);
		}
		if(sensitivitystep.equals("1"))
		{
			tvSensitvitystep.setText(R.string._normal);
		}
		if(sensitivitystep.equals("2"))
		{
			tvSensitvitystep.setText(R.string._high);
		}
		
			
		//����LinearLayout
		LinearLayout ll = (LinearLayout) findViewById(R.id.menu_get_fall_detect);
		ll.setOnLongClickListener(new OnLongClickListenergetfalldetect());
		
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(GetFallDetect.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			GetFallDetect.this.startActivity(it);
			GetFallDetect.this.finish();
		}
		
	}
	
	private class OnLongClickListenergetfalldetect implements OnLongClickListener
	{
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(GetFallDetect.this, "Long click linearlayout.", Toast.LENGTH_SHORT).show();
			
			return false;
		}
		
	}
	
	@Override
	protected void onRestart(){
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
		if(FallDetectset.falldetectstate == 0)
		{
	 		 this.fall_detect_name.setTextSize(18);
	 		 this.fall_detect_name.setText(getResources().getString(R.string.Disable));
		}
		else if(FallDetectset.falldetectstate == 1)
		{
	 		 this.fall_detect_name.setTextSize(18);
	 		 this.fall_detect_name.setText(getResources().getString(R.string.Enable));
		}
		
		super.onRestart();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(smsReceiver);//����ͷţ���Ȼ����פ�ڴ�
	}	

	public void onBackPressed() { 
		Intent it = new Intent(GetFallDetect.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		GetFallDetect.this.startActivity(it);
		GetFallDetect.this.finish();
    }

	//set enable
	private class OnClickListenersosnumbersetenble implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(GetFallDetect.this,FallDetectset.class);//ʵ��Intent
			it.putExtra("myinfo", "entry fall detect set");//Key
			GetFallDetect.this.startActivity(it);
			GetFallDetect.this.finish();
		}
		
	}
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			GetFallDetect.this.set_enable.setBackgroundResource(R.drawable.buttonform2);
			GetFallDetect.this.get_disable.setBackgroundResource(R.drawable.btn_red2);
			String smstel = destnumber;//����

			String smsnote1 = "GET"+password+"#SWFALL#";//���
			
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
	                    if (msgtemp.contains("+SWFALL=")) {  
	                        //TODO
	                    	String fall_status = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	                    	//Toast.makeText(GetSosNumber.this, "Received:"+msgtemp+" from:"+numbertemp, Toast.LENGTH_SHORT).show();
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putInt("fallstate", Integer.parseInt(fall_status));
	        		  		et.commit();
	        		  		if(Integer.parseInt(fall_status) == 0){
	        		  			GetFallDetect.this.fall_detect_name.setText(getResources().getString(R.string.Disable));
	        		  		} else if(Integer.parseInt(fall_status) == 1){
	        		  			GetFallDetect.this.fall_detect_name.setText(getResources().getString(R.string.Enable));
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
