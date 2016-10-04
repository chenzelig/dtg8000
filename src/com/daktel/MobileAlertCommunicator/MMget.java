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

public class MMget extends Activity implements OnClickListener {
	private TextView get_mm_number1 = null;
	private TextView get_mm_number2 = null;
	private Button set_enable = null;
	private Button get_disable = null;
	private ImageView mainmenu_return_but = null;
	
	private SmsReceiver smsReceiver;
	private String destnumber = null;
	private String password = null;

	private String mmnumber1,mmnumber2;
	
	private Button btnBack;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		super.setContentView(R.layout.mm_get);
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
		
		this.get_mm_number1 = (TextView)super.findViewById(R.id.get_mm_number1);
		this.get_mm_number2 = (TextView)super.findViewById(R.id.get_mm_number2);
		
		this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
		
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
		
 	    mmnumber1 = uiState.getString("mmnumber1","" );
 	    mmnumber2 = uiState.getString("mmnumber2","" );
 	    
 	   

 	    if(!mmnumber1.equals(""))
 	    	this.get_mm_number1.setText(mmnumber1);
 	    
 	    if(!mmnumber2.equals(""))
 	    	this.get_mm_number2.setText(mmnumber2);
		
		//����LinearLayout
		LinearLayout ll = (LinearLayout) findViewById(R.id.menu_get_mm_number);
		ll.setOnLongClickListener(new OnLongClickListenergentmmnumber());
		
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(MMget.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			MMget.this.startActivity(it);
			MMget.this.finish();
		}
		
	}
	
	private class OnLongClickListenergentmmnumber implements OnLongClickListener
	{
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(MMget.this, "Long click linearlayout.", Toast.LENGTH_SHORT).show();
			
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
	
	public void onBackPressed() { 
		Intent it = new Intent(MMget.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		MMget.this.startActivity(it);
		MMget.this.finish();
    }
	
	//set enable
	private class OnClickListenersosnumbersetenble implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(MMget.this,MMset.class);//ʵ��Intent
			it.putExtra("myinfo", "entry set sosnumber");//Key
			MMget.this.startActivity(it);
			MMget.this.finish();
		}
		
	}
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			MMget.this.set_enable.setBackgroundResource(R.drawable.buttonform2);
			MMget.this.get_disable.setBackgroundResource(R.drawable.btn_red2);
			String smstel = destnumber;//����
			String smsnote1 = "GET"+password+"#M1#M2#";//���
			//String smsnote2 = "GET"+password+"#M2#";//���
			
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote1,null,null);
			//smsmanager.sendTextMessage(smstel,null,smsnote2,null,null);
		}	
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(smsReceiver);//����ͷţ���Ȼ����פ�ڴ�
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
	                    if (msgtemp.contains("+M1=")) {  
	                        //TODO
	                    	String mm_num = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	                    	//Toast.makeText(GetSosNumber.this, "Received:"+msgtemp+" from:"+numbertemp, Toast.LENGTH_SHORT).show();
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("mmnumber1", mm_num);
	        		  		et.commit();
	        		  		MMget.this.get_mm_number1.setText(mm_num);
	                    } else if(msgtemp.contains("+M2=")) {
	                    	String mm_num = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("mmnumber2", mm_num);
	        		  		et.commit();
	        		  		MMget.this.get_mm_number2.setText(mm_num);
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
