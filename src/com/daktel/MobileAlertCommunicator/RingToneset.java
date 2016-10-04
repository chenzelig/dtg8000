package com.daktel.MobileAlertCommunicator;

//import java.text.SimpleDateFormat;
//import java.util.Date;

import java.io.FileDescriptor;
import java.io.IOException;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
//import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CompoundButton;
//import android.widget.EditText;
//import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
//import android.widget.TextView;
import android.widget.Toast;

public class RingToneset extends Activity implements OnClickListener  {
	private SmsReceiver smsReceiver;
	private String destnumber = null;
	private String password = null;

	private Button set_enable = null;
	private Button get_disable = null;
	private ImageView mainmenu_return_but = null;
	
	private RadioGroup group1 = null;
	private RadioGroup group2 = null;
	private Boolean changeedGroup = false;
	
	private RadioButton ring_only = null;
	private RadioButton vibration = null;
	private RadioButton ring_vibration = null;
	private RadioButton mute = null;
	
	private RadioButton ring_type1 = null;
	private RadioButton ring_type2 = null;
	private RadioButton ring_type3 = null;
	private RadioButton ring_type4 = null;
	private RadioButton ring_type5 = null;
	private RadioButton ring_type6 = null;
	private RadioButton ring_type7 = null;
	private RadioButton ring_type8 = null;
	
	private SeekBar volume_seek = null;
	private String ringscene = null;
	private String ringscenebak = null;
	private String ringvolume = null;
	private String ringvolumebak = null;
	
	private Button btnBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.ringtone);

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
	    this.set_enable.setBackgroundResource(R.drawable.btn_red2);
		this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		
		this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
		
		this.group1 = (RadioGroup)super.findViewById(R.id.group1);
		//this.group1.setOnCheckedChangeListener(new MyRadioGroupOnCheckedChangedListener());
		//this.group2 = (RadioGroup)super.findViewById(R.id.group2);
		//this.group2.setOnCheckedChangeListener(new MyRadioGroupOnCheckedChangedListener());
		
		this.ring_only = (RadioButton)super.findViewById(R.id.ring_only);
		ring_only.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	if(isChecked)
            	{
	            	ring_only.setChecked(true);
	     	    	ring_vibration.setChecked(false);
	     	    	vibration.setChecked(false);
	     	    	mute.setChecked(false); 
            	}
            }  
        }); 
		
		this.vibration = (RadioButton)super.findViewById(R.id.vibration);
		vibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	if(isChecked)
            	{
	            	ring_only.setChecked(false);
	     	    	ring_vibration.setChecked(false);
	     	    	vibration.setChecked(isChecked);
	     	    	mute.setChecked(false); 
            	}
            }  
        }); 
		this.ring_vibration = (RadioButton)super.findViewById(R.id.ring_vibration);
		ring_vibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	if(isChecked)
            	{
	            	ring_only.setChecked(false);
	     	    	ring_vibration.setChecked(isChecked);
	     	    	vibration.setChecked(false);
	     	    	mute.setChecked(false); 
            	}
            }  
        }); 
		this.mute = (RadioButton)super.findViewById(R.id.mute);
		mute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	if(isChecked)
            	{
	            	ring_only.setChecked(false);
	     	    	ring_vibration.setChecked(false);
	     	    	vibration.setChecked(false);
	     	    	mute.setChecked(true); 
            	}
            }  
        }); 
		
		this.volume_seek = (SeekBar)super.findViewById(R.id.volume_seek);
		//this.volume_seek.setOnSeekBarChangeListener(new OnSeekBarChangeListenervolume());
		
		this.ring_type1 = (RadioButton)findViewById(R.id.ring_type1);
		ring_type1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	if(isChecked)
            	{
            		AssetManager am = getAssets();//获得该应用的AssetManage
            		try{  
                        AssetFileDescriptor afd = am.openFd("ring1.wav");  
                        FileDescriptor fd = afd.getFileDescriptor();
                        MediaPlayer m2 = new MediaPlayer();  
                        //m2.setDataSource(afd.getFileDescriptor());
                        m2.setDataSource(fd, afd.getStartOffset(), afd.getLength());
                        m2.prepare(); //准备  
                        
                        m2.start();
                    }  
                    catch(IOException e){  
                        e.printStackTrace();  
                    }  
            		
            	}
            }  
        }); 
		this.ring_type2 = (RadioButton)findViewById(R.id.ring_type2);
		ring_type2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	if(isChecked)
            	{
            		AssetManager am = getAssets();//获得该应用的AssetManage
            		try{  
                        AssetFileDescriptor afd = am.openFd("ring2.wav");  
                        FileDescriptor fd = afd.getFileDescriptor();
                        MediaPlayer m2 = new MediaPlayer();  
                        //m2.setDataSource(afd.getFileDescriptor());
                        m2.setDataSource(fd, afd.getStartOffset(), afd.getLength());
                        m2.prepare(); //准备  
                        
                        m2.start();
                    }  
                    catch(IOException e){  
                        e.printStackTrace();  
                    }  
            		
            	}
            }  
        }); 
		this.ring_type3 = (RadioButton)findViewById(R.id.ring_type3);
		ring_type3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	if(isChecked)
            	{
            		AssetManager am = getAssets();//获得该应用的AssetManage
            		try{  
                        AssetFileDescriptor afd = am.openFd("ring3.wav");
                        FileDescriptor fd = afd.getFileDescriptor();
                        MediaPlayer m2 = new MediaPlayer();  
                        //m2.setDataSource(afd.getFileDescriptor());
                        m2.setDataSource(fd, afd.getStartOffset(), afd.getLength());
                        m2.prepare(); //准备  
                        
                        m2.start();
                    }  
                    catch(IOException e){  
                        e.printStackTrace();  
                    }  
            		
            	}
            }  
        }); 
		this.ring_type4 = (RadioButton)findViewById(R.id.ring_type4);
		ring_type4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	if(isChecked)
            	{
            		AssetManager am = getAssets();//获得该应用的AssetManage
            		try{  
                        AssetFileDescriptor afd = am.openFd("ring4.wav");  
                        FileDescriptor fd = afd.getFileDescriptor();
                        MediaPlayer m2 = new MediaPlayer();  
                        //m2.setDataSource(afd.getFileDescriptor());
                        m2.setDataSource(fd, afd.getStartOffset(), afd.getLength());
                        m2.prepare(); //准备  
                        
                        m2.start();
                    }  
                    catch(IOException e){  
                        e.printStackTrace();  
                    }  
            		
            	}
            }  
        }); 
		this.ring_type5 = (RadioButton)findViewById(R.id.ring_type5);
		ring_type5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	if(isChecked)
            	{
            		AssetManager am = getAssets();//获得该应用的AssetManage
            		try{  
                        AssetFileDescriptor afd = am.openFd("ring5.wav");  
                        FileDescriptor fd = afd.getFileDescriptor();
                        MediaPlayer m2 = new MediaPlayer();  
                        //m2.setDataSource(afd.getFileDescriptor());
                        m2.setDataSource(fd, afd.getStartOffset(), afd.getLength());
                        m2.prepare(); //准备  
                        
                        m2.start();
                    }  
                    catch(IOException e){  
                        e.printStackTrace();  
                    }  
            		
            	}
            }  
        }); 
		this.ring_type6 = (RadioButton)findViewById(R.id.ring_type6);
		ring_type6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	if(isChecked)
            	{
            		AssetManager am = getAssets();//获得该应用的AssetManage
            		try{  
                        AssetFileDescriptor afd = am.openFd("ring6.wav");  
                        FileDescriptor fd = afd.getFileDescriptor();
                        MediaPlayer m2 = new MediaPlayer();  
                        //m2.setDataSource(afd.getFileDescriptor());
                        m2.setDataSource(fd, afd.getStartOffset(), afd.getLength());
                        m2.prepare(); //准备  
                        
                        m2.start();
                    }  
                    catch(IOException e){  
                        e.printStackTrace();  
                    }  
            		
            	}
            }  
        }); 
		this.ring_type7 = (RadioButton)findViewById(R.id.ring_type7);
		ring_type7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	if(isChecked)
            	{
            		AssetManager am = getAssets();//获得该应用的AssetManage
            		try{  
                        AssetFileDescriptor afd = am.openFd("ring7.wav");  
                        FileDescriptor fd = afd.getFileDescriptor();
                        MediaPlayer m2 = new MediaPlayer();  
                        //m2.setDataSource(afd.getFileDescriptor());
                        m2.setDataSource(fd, afd.getStartOffset(), afd.getLength());
                        m2.prepare(); //准备  
                        
                        m2.start();
                    }  
                    catch(IOException e){  
                        e.printStackTrace();  
                    }  
            		
            	}
            }  
        }); 
		this.ring_type8 = (RadioButton)findViewById(R.id.ring_type8);
		ring_type8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	if(isChecked)
            	{
            		AssetManager am = getAssets();//获得该应用的AssetManage
            		try{  
                        AssetFileDescriptor afd = am.openFd("ring8.wav");  
                        FileDescriptor fd = afd.getFileDescriptor();
                        MediaPlayer m2 = new MediaPlayer();  
                        //m2.setDataSource(afd.getFileDescriptor());
                        m2.setDataSource(fd, afd.getStartOffset(), afd.getLength());
                        m2.prepare(); //准备  
                        
                        m2.start();
                    }  
                    catch(IOException e){  
                        e.printStackTrace();  
                    }  
            		
            	}
            }  
        }); 
		
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
 	    
 	    
 	    
 	    //ring scene
 	    ringscene = uiState.getString("ringscene", "");
 	    if(ringscene.equals("")){
 	    	ringscene = "0";
 	    }
 	   ringscenebak = ringscene;
 	    if(Integer.parseInt(ringscene) == 0)
 	    {
 	    	this.ring_only.setChecked(true);
 	    	this.ring_vibration.setChecked(false);
 	    	this.vibration.setChecked(false);
 	    	this.mute.setChecked(false);
 	    	
 	    }
 	    else if(Integer.parseInt(ringscene) == 1)
 	    {
 	    	this.ring_only.setChecked(false);
 	    	this.ring_vibration.setChecked(true);
 	    	this.vibration.setChecked(false);
 	    	this.mute.setChecked(false);
 	    }
 	    else if(Integer.parseInt(ringscene) == 2)
 	    {
 	    	this.ring_only.setChecked(false);
 	    	this.ring_vibration.setChecked(false);
 	    	this.vibration.setChecked(true);
 	    	this.mute.setChecked(false);
 	    }
 	    else if(Integer.parseInt(ringscene) == 3)
 	    {
 	    	this.ring_only.setChecked(false);
 	    	this.ring_vibration.setChecked(false);
 	    	this.vibration.setChecked(false);
 	    	this.mute.setChecked(true);
 	    }
 	    
 	    //ring volume
 	    ringvolume = uiState.getString("ringvolume", "");
 	    if(ringvolume.equals("")){
 	    	ringvolume = "1";
 	    }
 	   ringvolumebak = ringvolume;
 	    if(ringvolume.equals("0") || ringvolume.equals(""))
 	    	this.volume_seek.setProgress(0);
 	    else
 	    	this.volume_seek.setProgress(Integer.parseInt(ringvolume)-1);
 	    
 	    
 	   String ringtype = uiState.getString("ringtype", "");
 	    if(ringtype.equals("")){
 	    	ringtype = "0";
 	    } 	    
 	    switch(Integer.parseInt(ringtype))
 	    {
 	    	case 0:
 	    		ring_type1.setChecked(true);
 	    		break;
 	    	case 1:
 	    		ring_type2.setChecked(true);
 	    		break;
 	    	case 2:
 	    		ring_type3.setChecked(true);
 	    		break;
 	    	case 3:
 	    		ring_type4.setChecked(true);
 	    		break;
 	    	case 4:
 	    		ring_type5.setChecked(true);
 	    		break;
 	    	case 5:
 	    		ring_type6.setChecked(true);
 	    		break;
 	    	case 6:
 	    		ring_type7.setChecked(true);
 	    		break;
 	    	case 7:
 	    		ring_type8.setChecked(true);
 	    		break;
 	    }
 	    
 	    
 	    
 	    
	}
	
	//seekbar for volume
	private class OnSeekBarChangeListenervolume implements SeekBar.OnSeekBarChangeListener
	{
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			// TODO Auto-generated method stub
//			if(seekBar.getProgress() < 1)
//				seekBar.setProgress(1);
			
			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		SmsManager smsmanager = SmsManager.getDefault();
			switch(seekBar.getProgress())
			{
				case 0:
			  		et.putString("ringvolume", "1");
			  		et.commit();
			  		
			  		ringvolume = "1";
			  		if(ringvolumebak != ringvolume)
			  		{
			  			ringvolumebak = ringvolume;
						String smstel1 = destnumber;//����
						String smsnote1 = "SET"+password+"#VOL#1#";//���
						smsmanager.sendTextMessage(smstel1,null,smsnote1,null,null);
						Toast.makeText(RingToneset.this,R.string.Success, Toast.LENGTH_SHORT).show();
			  		}
					
					break;
					
				case 1:
			  		et.putString("ringvolume", "2");
			  		et.commit();
			  		
			  		ringvolume = "2";
			  		if(ringvolumebak != ringvolume)
			  		{
			  			ringvolumebak = ringvolume;
						String smstel2 = destnumber;//����
						String smsnote2 = "SET"+password+"#VOL#2#";//���
						smsmanager.sendTextMessage(smstel2,null,smsnote2,null,null);
						Toast.makeText(RingToneset.this,R.string.Success, Toast.LENGTH_SHORT).show();
			  		}
					
					break;
					
				case 2:
			  		et.putString("ringvolume", "3");
			  		et.commit();
			  		
			  		ringvolume = "3";
			  		if(ringvolumebak != ringvolume)
			  		{
			  			ringvolumebak = ringvolume;
						String smstel3 = destnumber;//����
						String smsnote3 = "SET"+password+"#VOL#3#";//���
						smsmanager.sendTextMessage(smstel3,null,smsnote3,null,null);
						Toast.makeText(RingToneset.this, R.string.Success, Toast.LENGTH_SHORT).show();
			  		}
					
					break;
					
				case 3:
			  		et.putString("ringvolume", "4");
			  		et.commit();
			  		
			  		ringvolume = "4";
			  		if(ringvolumebak != ringvolume)
			  		{
			  			ringvolumebak = ringvolume;
						String smstel4 = destnumber;//����
						String smsnote4 = "SET"+password+"#VOL#4#";//���
						smsmanager.sendTextMessage(smstel4,null,smsnote4,null,null);
						Toast.makeText(RingToneset.this, R.string.Success, Toast.LENGTH_SHORT).show();
			  		}
					
					break;
					
				case 4:

			  		et.putString("ringvolume", "5");
			  		et.commit();
			  		
			  		ringvolume = "5";
			  		if(ringvolumebak != ringvolume)
			  		{
			  			ringvolumebak = ringvolume;
						String smstel = destnumber;//����
						String smsnote = "SET"+password+"#VOL#5#";//���
						smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
						Toast.makeText(RingToneset.this, R.string.Success, Toast.LENGTH_SHORT).show();
			  		}
					
					break;
				
				default:
					break;
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			String sms = "SET"+password+"#";
			if(ring_only.isChecked())
			{
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("ringscene", "0");
		  		et.commit();
		  		sms += "SCENE#0#";		  		
			}
			if(ring_vibration.isChecked())
			{
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("ringscene", "1");
		  		et.commit();
		  		sms += "SCENE#1#";		  		
			}
			if(vibration.isChecked())
			{
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("ringscene", "2");
		  		et.commit();
		  		sms += "SCENE#2#";		  		
			}
			if(mute.isChecked())
			{
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("ringscene", "3");
		  		et.commit();
		  		sms += "SCENE#3#";		  		
			}
			
			if(ring_type1.isChecked())
			{
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("ringtype", "0");
		  		et.commit();
		  		sms += "RING#0#";
		  		
			}
			if(ring_type2.isChecked())
			{
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("ringtype", "1");
		  		et.commit();
		  		sms += "RING#1#";
		  		
			}
			
			if(ring_type3.isChecked())
			{
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("ringtype", "2");
		  		et.commit();
		  		sms += "RING#2#";
		  		
			}
			
			if(ring_type4.isChecked())
			{
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("ringtype", "3");
		  		et.commit();
		  		sms += "RING#3#";
		  		
			}
			
			if(ring_type5.isChecked())
			{
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("ringtype", "4");
		  		et.commit();
		  		sms += "RING#4#";
		  		
			}
			
			if(ring_type6.isChecked())
			{
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("ringtype", "5");
		  		et.commit();
		  		sms += "RING#5#";
		  		
			}
			
			if(ring_type7.isChecked())
			{
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("ringtype", "6");
		  		et.commit();
		  		sms += "RING#6#";
		  		
			}
			
			if(ring_type8.isChecked())
			{
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("ringtype", "7");
		  		et.commit();
		  		sms += "RING#7#";
		  		
			}
			
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();

	  		et.putString("ringvolume", String.valueOf(volume_seek.getProgress()+1));
			et.commit();
	  		
			sms += "VOL#"+String.valueOf(volume_seek.getProgress()+1)+"#";
	  		
	  		String smstel = destnumber;
	  		SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,sms,null,null);
			Toast.makeText(RingToneset.this, R.string.Success , Toast.LENGTH_SHORT).show();
		
			Intent it = new Intent(RingToneset.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			RingToneset.this.startActivity(it);
			RingToneset.this.finish();
		}
		
	}
	
	//����Ϣ���ղ������ж�
	private class SmsReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
	        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){  
	        	//Log.v("huahua", "MainActivity�յ����ŷ�������Ϣ");  
	        	
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
	                    if (msgtemp.contains("VOL")||msgtemp.contains("RING")||msgtemp.contains("SCENE")) 
	                    {  
	                        //TODO  
	                    	Toast.makeText(RingToneset.this, R.string.Success, Toast.LENGTH_SHORT).show();
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
 	    
 	    //ring scene
 	    ringscene = uiState.getString("ringscene", "");
 	    ringscenebak = ringscene;
 	    if(Integer.parseInt(ringscene) == 1)
 	    {
 	    	this.ring_only.setChecked(true);
 	    }
 	    else if(Integer.parseInt(ringscene) == 2)
 	    {
 	    	this.ring_vibration.setChecked(true);
 	    }
 	    else if(Integer.parseInt(ringscene) == 3)
 	    {
 	    	this.vibration.setChecked(true);
 	    }
 	    else if(Integer.parseInt(ringscene) == 4)
 	    {
 	    	this.mute.setChecked(true);
 	    }
 	    
 	    //ring volume
 	    ringvolume = uiState.getString("ringvolume", "");
 	    ringvolumebak = ringvolume;
 	    if((ringvolume == "0")||(ringvolume == ""))
 	    	this.volume_seek.setProgress(0);
 	    else
 	    	this.volume_seek.setProgress(Integer.parseInt(ringvolume)-1);
 	    
		super.onRestart();
	}
	
	public void onBackPressed() { 
		Intent it = new Intent(RingToneset.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		RingToneset.this.startActivity(it);
		RingToneset.this.finish();
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
			RingToneset.this.set_enable.setBackgroundResource(R.drawable.btn_red2);
			RingToneset.this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		}
	}
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(RingToneset.this,GetRingTone.class);//ʵ��Intent
			it.putExtra("myinfo", "entry get sosnumber");//Key
			RingToneset.this.startActivity(it);
			RingToneset.this.finish();
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
