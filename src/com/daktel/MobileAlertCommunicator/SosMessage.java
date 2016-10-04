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
import android.widget.CheckBox;
//import android.widget.CheckBox;
import android.widget.EditText;
//import android.widget.ImageButton;
import android.widget.ImageView;
//import android.widget.TextView;
import android.widget.Toast;

public class SosMessage extends Activity implements OnClickListener {
	private SmsReceiver smsReceiver;
	private String destnumber = null;
	private String password = null;
	
	private Button set_enable = null;
	private Button get_disable = null;
	
	private EditText sosnumber1_edit = null;
	private EditText sosnumber2_edit = null;
	private EditText sosnumber3_edit = null;
	private EditText sosnumber4_edit = null;
	private EditText sosnumber5_edit = null;
	
	private CheckBox sos_message1_ck1 = null;
	private CheckBox sos_message1_ck2 = null;
	private CheckBox sos_message1_ck3 = null;
	private CheckBox sos_message1_ck4 = null;
	private CheckBox sos_message1_ck5 = null;
	private CheckBox sos_message1_ck6 = null;
	private CheckBox sos_message1_ck7 = null;
	private CheckBox sos_message1_ck8 = null;
	
	private CheckBox sos_message2_ck1 = null;
	private CheckBox sos_message2_ck2 = null;
	private CheckBox sos_message2_ck3 = null;
	private CheckBox sos_message2_ck4 = null;
	private CheckBox sos_message2_ck5 = null;
	private CheckBox sos_message2_ck6 = null;
	private CheckBox sos_message2_ck7 = null;
	private CheckBox sos_message2_ck8 = null;
	
	private CheckBox sos_message3_ck1 = null;
	private CheckBox sos_message3_ck2 = null;
	private CheckBox sos_message3_ck3 = null;
	private CheckBox sos_message3_ck4 = null;
	private CheckBox sos_message3_ck5 = null;
	private CheckBox sos_message3_ck6 = null;
	private CheckBox sos_message3_ck7 = null;
	private CheckBox sos_message3_ck8 = null;
	
	private CheckBox sos_message4_ck1 = null;
	private CheckBox sos_message4_ck2 = null;
	private CheckBox sos_message4_ck3 = null;
	private CheckBox sos_message4_ck4 = null;
	private CheckBox sos_message4_ck5 = null;
	private CheckBox sos_message4_ck6 = null;
	private CheckBox sos_message4_ck7 = null;
	private CheckBox sos_message4_ck8 = null;
	
	private CheckBox sos_message5_ck1 = null;
	private CheckBox sos_message5_ck2 = null;
	private CheckBox sos_message5_ck3 = null;
	private CheckBox sos_message5_ck4 = null;
	private CheckBox sos_message5_ck5 = null;
	private CheckBox sos_message5_ck6 = null;
	private CheckBox sos_message5_ck7 = null;
	private CheckBox sos_message5_ck8 = null;
	
	private Button mm_sos_message1_set = null;
	private Button mm_sos_message2_set = null;
	private Button mm_sos_message3_set = null;
	private Button mm_sos_message4_set = null;
	private Button mm_sos_message5_set = null;
	
	private ImageView mainmenu_return_but = null;
	
	private Button btnBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sos_message);
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
		
		this.sosnumber1_edit = (EditText)super.findViewById(R.id.sosnumber1_edit);
		this.sosnumber2_edit = (EditText)super.findViewById(R.id.sosnumber2_edit);
		this.sosnumber3_edit = (EditText)super.findViewById(R.id.sosnumber3_edit);
		this.sosnumber4_edit = (EditText)super.findViewById(R.id.sosnumber4_edit);
		this.sosnumber5_edit = (EditText)super.findViewById(R.id.sosnumber5_edit);
		
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(this);
		
		this.sos_message1_ck1 = (CheckBox)super.findViewById(R.id.sos_message1_ck1);
		this.sos_message1_ck2 = (CheckBox)super.findViewById(R.id.sos_message1_ck2);
		this.sos_message1_ck3 = (CheckBox)super.findViewById(R.id.sos_message1_ck3);
		this.sos_message1_ck4 = (CheckBox)super.findViewById(R.id.sos_message1_ck4);
		this.sos_message1_ck5 = (CheckBox)super.findViewById(R.id.sos_message1_ck5);
		this.sos_message1_ck6 = (CheckBox)super.findViewById(R.id.sos_message1_ck6);
		this.sos_message1_ck7 = (CheckBox)super.findViewById(R.id.sos_message1_ck7);
		this.sos_message1_ck8 = (CheckBox)super.findViewById(R.id.sos_message1_ck8);
		
		
		String[] initValue = Utility.getShareValue(this, "sosmessage_message1").split(",");
		if(initValue.length>1)
		{
			sosnumber1_edit.setText(initValue[0]);
		}
		
		for(int i=1;i<initValue.length;i++)
		{
			if(initValue[i].length()>0 && initValue[i].equals("1"))
			{
				sos_message1_ck1.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("2"))
			{
				sos_message1_ck2.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("3"))
			{
				sos_message1_ck3.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("4"))
			{
				sos_message1_ck4.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("5"))
			{
				sos_message1_ck5.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("6"))
			{
				sos_message1_ck6.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("7"))
			{
				sos_message1_ck7.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("8"))
			{
				sos_message1_ck8.setChecked(initValue[i].length()>0);
			}
		}
		
		
		
		
		this.sos_message2_ck1 = (CheckBox)super.findViewById(R.id.sos_message2_ck1);
		this.sos_message2_ck2 = (CheckBox)super.findViewById(R.id.sos_message2_ck2);
		this.sos_message2_ck3 = (CheckBox)super.findViewById(R.id.sos_message2_ck3);
		this.sos_message2_ck4 = (CheckBox)super.findViewById(R.id.sos_message2_ck4);
		this.sos_message2_ck5 = (CheckBox)super.findViewById(R.id.sos_message2_ck5);
		this.sos_message2_ck6 = (CheckBox)super.findViewById(R.id.sos_message2_ck6);
		this.sos_message2_ck7 = (CheckBox)super.findViewById(R.id.sos_message2_ck7);
		this.sos_message2_ck8 = (CheckBox)super.findViewById(R.id.sos_message2_ck8);
		
		initValue = Utility.getShareValue(this, "sosmessage_message2").split(",");
		if(initValue.length>1)
		{
			sosnumber2_edit.setText(initValue[0]);
		}
		for(int i=1;i<initValue.length;i++)
		{
			if(initValue[i].length()>0 && initValue[i].equals("1"))
			{
				sos_message2_ck1.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("2"))
			{
				sos_message2_ck2.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("3"))
			{
				sos_message2_ck3.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("4"))
			{
				sos_message2_ck4.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("5"))
			{
				sos_message2_ck5.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("6"))
			{
				sos_message2_ck6.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("7"))
			{
				sos_message2_ck7.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("8"))
			{
				sos_message2_ck8.setChecked(initValue[i].length()>0);
			}
		}
		
		
		this.sos_message3_ck1 = (CheckBox)super.findViewById(R.id.sos_message3_ck1);
		this.sos_message3_ck2 = (CheckBox)super.findViewById(R.id.sos_message3_ck2);
		this.sos_message3_ck3 = (CheckBox)super.findViewById(R.id.sos_message3_ck3);
		this.sos_message3_ck4 = (CheckBox)super.findViewById(R.id.sos_message3_ck4);
		this.sos_message3_ck5 = (CheckBox)super.findViewById(R.id.sos_message3_ck5);
		this.sos_message3_ck6 = (CheckBox)super.findViewById(R.id.sos_message3_ck6);
		this.sos_message3_ck7 = (CheckBox)super.findViewById(R.id.sos_message3_ck7);
		this.sos_message3_ck8 = (CheckBox)super.findViewById(R.id.sos_message3_ck8);
//		
		initValue = Utility.getShareValue(this, "sosmessage_message3").split(",");
		if(initValue.length>1)
		{
			sosnumber3_edit.setText(initValue[0]);
		}
		for(int i=1;i<initValue.length;i++)
		{
			if(initValue[i].length()>0 && initValue[i].equals("1"))
			{
				sos_message3_ck1.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("2"))
			{
				sos_message3_ck2.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("3"))
			{
				sos_message3_ck3.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("4"))
			{
				sos_message3_ck4.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("5"))
			{
				sos_message3_ck5.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("6"))
			{
				sos_message3_ck6.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("7"))
			{
				sos_message3_ck7.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("8"))
			{
				sos_message3_ck8.setChecked(initValue[i].length()>0);
			}
		}
		
		this.sos_message4_ck1 = (CheckBox)super.findViewById(R.id.sos_message4_ck1);
		this.sos_message4_ck2 = (CheckBox)super.findViewById(R.id.sos_message4_ck2);
		this.sos_message4_ck3 = (CheckBox)super.findViewById(R.id.sos_message4_ck3);
		this.sos_message4_ck4 = (CheckBox)super.findViewById(R.id.sos_message4_ck4);
		this.sos_message4_ck5 = (CheckBox)super.findViewById(R.id.sos_message4_ck5);
		this.sos_message4_ck6 = (CheckBox)super.findViewById(R.id.sos_message4_ck6);
		this.sos_message4_ck7 = (CheckBox)super.findViewById(R.id.sos_message4_ck7);
		this.sos_message4_ck8 = (CheckBox)super.findViewById(R.id.sos_message4_ck8);
		
		initValue = Utility.getShareValue(this, "sosmessage_message4").split(",");
		if(initValue.length>1)
		{
			sosnumber4_edit.setText(initValue[0]);
		}
		for(int i=1;i<initValue.length;i++)
		{
			if(initValue[i].length()>0 && initValue[i].equals("1"))
			{
				sos_message4_ck1.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("2"))
			{
				sos_message4_ck2.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("3"))
			{
				sos_message4_ck3.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("4"))
			{
				sos_message4_ck4.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("5"))
			{
				sos_message4_ck5.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("6"))
			{
				sos_message4_ck6.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("7"))
			{
				sos_message4_ck7.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("8"))
			{
				sos_message4_ck8.setChecked(initValue[i].length()>0);
			}
		}
		
		this.sos_message5_ck1 = (CheckBox)super.findViewById(R.id.sos_message5_ck1);
		this.sos_message5_ck2 = (CheckBox)super.findViewById(R.id.sos_message5_ck2);
		this.sos_message5_ck3 = (CheckBox)super.findViewById(R.id.sos_message5_ck3);
		this.sos_message5_ck4 = (CheckBox)super.findViewById(R.id.sos_message5_ck4);
		this.sos_message5_ck5 = (CheckBox)super.findViewById(R.id.sos_message5_ck5);
		this.sos_message5_ck6 = (CheckBox)super.findViewById(R.id.sos_message5_ck6);
		this.sos_message5_ck7 = (CheckBox)super.findViewById(R.id.sos_message5_ck7);
		this.sos_message5_ck8 = (CheckBox)super.findViewById(R.id.sos_message5_ck8);
		
		initValue = Utility.getShareValue(this, "sosmessage_message5").split(",");
		if(initValue.length>1)
		{
			sosnumber5_edit.setText(initValue[0]);
		}
		for(int i=1;i<initValue.length;i++)
		{
			if(initValue[i].length()>0 && initValue[i].equals("1"))
			{
				sos_message5_ck1.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("2"))
			{
				sos_message5_ck2.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("3"))
			{
				sos_message5_ck3.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("4"))
			{
				sos_message5_ck4.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("5"))
			{
				sos_message5_ck5.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("6"))
			{
				sos_message5_ck6.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("7"))
			{
				sos_message5_ck7.setChecked(initValue[i].length()>0);
			}
			if(initValue[i].length()>0 && initValue[i].equals("8"))
			{
				sos_message5_ck8.setChecked(initValue[i].length()>0);
			}
		}
		
		this.mm_sos_message1_set = (Button)super.findViewById(R.id.mm_sos_message1_set);
		this.mm_sos_message1_set.setOnClickListener(new OnClickListenersosmessage1());
		this.mm_sos_message2_set = (Button)super.findViewById(R.id.mm_sos_message2_set);
		this.mm_sos_message2_set.setOnClickListener(new OnClickListenersosmessage2());
		this.mm_sos_message3_set = (Button)super.findViewById(R.id.mm_sos_message3_set);
		this.mm_sos_message3_set.setOnClickListener(new OnClickListenersosmessage3());
		this.mm_sos_message4_set = (Button)super.findViewById(R.id.mm_sos_message4_set);
		this.mm_sos_message4_set.setOnClickListener(new OnClickListenersosmessage4());
		this.mm_sos_message5_set = (Button)super.findViewById(R.id.mm_sos_message5_set);
		this.mm_sos_message5_set.setOnClickListener(new OnClickListenersosmessage5());
		
		this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());

		
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			
			// TODO Auto-generated method stub
			
			if(destnumber.equals("00000000000"))
			{	
				if(destnumber.equals("00000000000"))
					Toast.makeText(SosMessage.this, R.string.Please_Add_one_device_first, Toast.LENGTH_SHORT).show();
			}
			else
			{
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#";//���
				//拼msg
				if(sosnumber1_edit.getText().toString().length()>0)
				{
					smsnote+="SOSMSG1#"+sosnumber1_edit.getText().toString()+"#";
				}
				if(sosnumber2_edit.getText().toString().length()>0)
				{
					smsnote+="SOSMSG2#"+sosnumber2_edit.getText().toString()+"#";
				}
				if(sosnumber3_edit.getText().toString().length()>0)
				{
					smsnote+="SOSMSG3#"+sosnumber3_edit.getText().toString()+"#";
				}
				if(sosnumber4_edit.getText().toString().length()>0)
				{
					smsnote+="SOSMSG4#"+sosnumber4_edit.getText().toString()+"#";
				}
				if(sosnumber5_edit.getText().toString().length()>0)
				{
					smsnote+="SOSMSG5#"+sosnumber5_edit.getText().toString()+"#";
				}
				
				
				String ysms = "";
				
				if(sos_message1_ck1.isChecked())
				{
					ysms+="1,";
				}
				if(sos_message1_ck2.isChecked())
				{
					ysms+="2,";
				}
				if(sos_message1_ck3.isChecked())
				{
					ysms+="3,";
				}
				if(sos_message1_ck4.isChecked())
				{
					ysms+="4,";
				}
				if(sos_message1_ck5.isChecked())
				{
					ysms+="5,";
				}
				if(sos_message1_ck6.isChecked())
				{
					ysms+="6,";
				}
				if(sos_message1_ck7.isChecked())
				{
					ysms+="7,";
				}
				if(sos_message1_ck8.isChecked())
				{
					ysms+="8,";
				}
				if(ysms.length()>0)
				{
					ysms = ysms.substring(0,ysms.length()-1);
				}
				if(ysms.length()>0)
				{
					smsnote += "CONFIGSMS1#"+ysms+"#";//���
					
					
					//save data
					SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
			  		Editor et=uiState.edit();
			  		et.putString("sosmessage1", smsnote);
			  		et.commit();
			  		
			  		
			  		Utility.setShareValue(SosMessage.this, "sosmessage_message1", sosnumber1_edit.getText().toString()+","+ysms);
			  		
				}
				
				
				ysms="";
				if(sos_message2_ck1.isChecked())
				{
					ysms+="1,";
				}
				if(sos_message2_ck2.isChecked())
				{
					ysms+="2,";
				}
				if(sos_message2_ck3.isChecked())
				{
					ysms+="3,";
				}
				if(sos_message2_ck4.isChecked())
				{
					ysms+="4,";
				}
				if(sos_message2_ck5.isChecked())
				{
					ysms+="5,";
				}
				if(sos_message2_ck6.isChecked())
				{
					ysms+="6,";
				}
				if(sos_message2_ck7.isChecked())
				{
					ysms+="7,";
				}
				if(sos_message2_ck8.isChecked())
				{
					ysms+="8,";
				}
				if(ysms.length()>0)
				{
					ysms = ysms.substring(0,ysms.length()-1);
				}
				if(ysms.length()>0)
				{
					smsnote += "CONFIGSMS2#"+ysms+"#";//���
					
					
					//save data
					SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
			  		Editor et=uiState.edit();
			  		et.putString("sosmessage2", smsnote);
			  		et.commit();
			  		
			  		Utility.setShareValue(SosMessage.this, "sosmessage_message2", sosnumber2_edit.getText().toString()+","+ysms);
				}
				
				
				ysms="";
				if(sos_message3_ck1.isChecked())
				{
					ysms+="1,";
				}
				if(sos_message3_ck2.isChecked())
				{
					ysms+="2,";
				}
				if(sos_message3_ck3.isChecked())
				{
					ysms+="3,";
				}
				if(sos_message3_ck4.isChecked())
				{
					ysms+="4,";
				}
				if(sos_message3_ck5.isChecked())
				{
					ysms+="5,";
				}
				if(sos_message3_ck6.isChecked())
				{
					ysms+="6,";
				}
				if(sos_message3_ck7.isChecked())
				{
					ysms+="7,";
				}
				if(sos_message3_ck8.isChecked())
				{
					ysms+="8,";
				}
				if(ysms.length()>0)
				{
					ysms = ysms.substring(0,ysms.length()-1);
				}
				if(ysms.length()>0)
				{
					smsnote += "CONFIGSMS3#"+ysms+"#";//���
					
					
					//save data
					SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
			  		Editor et=uiState.edit();
			  		et.putString("sosmessage3", smsnote);
			  		et.commit();
			  		
			  		Utility.setShareValue(SosMessage.this, "sosmessage_message3", sosnumber3_edit.getText().toString()+","+ysms);
				}
				
				ysms="";
				if(sos_message4_ck1.isChecked())
				{
					ysms+="1,";
				}
				if(sos_message4_ck2.isChecked())
				{
					ysms+="2,";
				}
				if(sos_message4_ck3.isChecked())
				{
					ysms+="3,";
				}
				if(sos_message4_ck4.isChecked())
				{
					ysms+="4,";
				}
				if(sos_message4_ck5.isChecked())
				{
					ysms+="5,";
				}
				if(sos_message4_ck6.isChecked())
				{
					ysms+="6,";
				}
				if(sos_message4_ck7.isChecked())
				{
					ysms+="7,";
				}
				if(sos_message4_ck8.isChecked())
				{
					ysms+="8,";
				}
				if(ysms.length()>0)
				{
					ysms = ysms.substring(0,ysms.length()-1);
				}
				if(ysms.length()>0)
				{
					smsnote += "CONFIGSMS4#"+ysms+"#";//���
					
					
					//save data
					SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
			  		Editor et=uiState.edit();
			  		et.putString("sosmessage4", smsnote);
			  		et.commit();
			  		
			  		Utility.setShareValue(SosMessage.this, "sosmessage_message4", sosnumber4_edit.getText().toString()+","+ysms);
				}
				
				
				ysms = "";
				if(sos_message5_ck1.isChecked())
				{
					ysms+="1,";
				}
				if(sos_message5_ck2.isChecked())
				{
					ysms+="2,";
				}
				if(sos_message5_ck3.isChecked())
				{
					ysms+="3,";
				}
				if(sos_message5_ck4.isChecked())
				{
					ysms+="4,";
				}
				if(sos_message5_ck5.isChecked())
				{
					ysms+="5,";
				}
				if(sos_message5_ck6.isChecked())
				{
					ysms+="6,";
				}
				if(sos_message5_ck7.isChecked())
				{
					ysms+="7,";
				}
				if(sos_message5_ck8.isChecked())
				{
					ysms+="8,";
				}
				if(ysms.length()>0)
				{
					ysms = ysms.substring(0,ysms.length()-1);
				}
				if(ysms.length()>0)
				{
					smsnote += "CONFIGSMS5#"+ysms+"#";//���
					
					
					//save data
					SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
			  		Editor et=uiState.edit();
			  		et.putString("sosmessage5", smsnote);
			  		et.commit();
			  		
			  		Utility.setShareValue(SosMessage.this, "sosmessage_message5", sosnumber5_edit.getText().toString()+","+ysms);
				}
				
				
				
				
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(SosMessage.this, R.string.Success, Toast.LENGTH_SHORT).show();
				
				
			}
			
			
			Intent it = new Intent(SosMessage.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			SosMessage.this.startActivity(it);
			SosMessage.this.finish();
		}
		
	}
	
	//sos message1
	private class OnClickListenersosmessage1 implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editmessage_temp = SosMessage.this.sosnumber1_edit.getText().toString().trim();//���
			if(editmessage_temp.equals("")||destnumber.equals("00000000000"))
			{	
				if(editmessage_temp.equals(""))
					Toast.makeText(SosMessage.this, R.string.Please_input_message, Toast.LENGTH_SHORT).show();
				else if(destnumber.equals("00000000000"))
					Toast.makeText(SosMessage.this, R.string.Please_set_destination_number_first, Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SOSMSG1#"+editmessage_temp+"#";//���
				
				String ysms = "";
				if(sos_message1_ck1.isChecked())
				{
					ysms+="1,";
				}
				if(sos_message1_ck2.isChecked())
				{
					ysms+="2,";
				}
				if(sos_message1_ck3.isChecked())
				{
					ysms+="3,";
				}
				if(sos_message1_ck4.isChecked())
				{
					ysms+="4,";
				}
				if(sos_message1_ck5.isChecked())
				{
					ysms+="5,";
				}
				if(sos_message1_ck6.isChecked())
				{
					ysms+="6,";
				}
				if(sos_message1_ck7.isChecked())
				{
					ysms+="7,";
				}
				if(sos_message1_ck8.isChecked())
				{
					ysms+="8,";
				}
				if(ysms.length()>0)
				{
					ysms = ysms.substring(0,ysms.length()-1);
				}
				if(ysms.length()>0)
				{
					smsnote += "CONFIGSMS1#"+ysms+"#";//���
					
					SmsManager smsmanager = SmsManager.getDefault();
					smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
					Toast.makeText(SosMessage.this, R.string.Success, Toast.LENGTH_SHORT).show();
					
					//save data
					SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
			  		Editor et=uiState.edit();
			  		et.putString("sosmessage1", editmessage_temp);
			  		et.commit();
			  		
			  		Utility.setShareValue(SosMessage.this, "sosmessage_message1", sosnumber1_edit.getText().toString()+","+ysms);
				}
				
				
				
			}
		}
		
	}
	
	//sos message2
	private class OnClickListenersosmessage2 implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editmessage_temp = SosMessage.this.sosnumber2_edit.getText().toString().trim();//���
			if(editmessage_temp.equals("")||destnumber.equals("00000000000"))
			{	
				if(editmessage_temp.equals(""))
					Toast.makeText(SosMessage.this, R.string.Please_input_message, Toast.LENGTH_SHORT).show();
				else if(destnumber.equals("00000000000"))
					Toast.makeText(SosMessage.this, R.string.Please_input_phone_number, Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SOSMSG2#"+editmessage_temp+"#";//���
				
				String ysms = "";
				if(sos_message2_ck1.isChecked())
				{
					ysms+="1,";
				}
				if(sos_message2_ck2.isChecked())
				{
					ysms+="2,";
				}
				if(sos_message2_ck3.isChecked())
				{
					ysms+="3,";
				}
				if(sos_message2_ck4.isChecked())
				{
					ysms+="4,";
				}
				if(sos_message2_ck5.isChecked())
				{
					ysms+="5,";
				}
				if(sos_message2_ck6.isChecked())
				{
					ysms+="6,";
				}
				if(sos_message2_ck7.isChecked())
				{
					ysms+="7,";
				}
				if(sos_message2_ck8.isChecked())
				{
					ysms+="8,";
				}
				if(ysms.length()>0)
				{
					ysms = ysms.substring(0,ysms.length()-1);
				}
				if(ysms.length()>0)
				{
					smsnote += "CONFIGSMS2#"+ysms+"#";//���
					SmsManager smsmanager = SmsManager.getDefault();
					smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
					Toast.makeText(SosMessage.this, R.string.Success, Toast.LENGTH_SHORT).show();
					
					//save data
					SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
			  		Editor et=uiState.edit();
			  		et.putString("sosmessage2", editmessage_temp);
			  		et.commit();
			  		
			  		Utility.setShareValue(SosMessage.this, "sosmessage_message2", sosnumber2_edit.getText().toString()+","+ysms);
				}
			}
		}
		
	}
	
	//sos message3
	private class OnClickListenersosmessage3 implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editmessage_temp = SosMessage.this.sosnumber3_edit.getText().toString().trim();//���
			if(editmessage_temp.equals("")||destnumber.equals("00000000000"))
			{	
				if(editmessage_temp.equals(""))
					Toast.makeText(SosMessage.this, R.string.Please_input_message, Toast.LENGTH_SHORT).show();
				else if(destnumber.equals("00000000000"))
					Toast.makeText(SosMessage.this, R.string.Please_input_phone_number, Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SOSMSG3#"+editmessage_temp+"#";//���
				String ysms = "";
				if(sos_message3_ck1.isChecked())
				{
					ysms+="1,";
				}
				if(sos_message3_ck2.isChecked())
				{
					ysms+="2,";
				}
				if(sos_message3_ck3.isChecked())
				{
					ysms+="3,";
				}
				if(sos_message3_ck4.isChecked())
				{
					ysms+="4,";
				}
				if(sos_message3_ck5.isChecked())
				{
					ysms+="5,";
				}
				if(sos_message3_ck6.isChecked())
				{
					ysms+="6,";
				}
				if(sos_message3_ck7.isChecked())
				{
					ysms+="7,";
				}
				if(sos_message3_ck8.isChecked())
				{
					ysms+="8,";
				}
				if(ysms.length()>0)
				{
					ysms = ysms.substring(0,ysms.length()-1);
				}
				if(ysms.length()>0)
				{
					smsnote += "CONFIGSMS3#"+ysms+"#";//���
					SmsManager smsmanager = SmsManager.getDefault();
					smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
					Toast.makeText(SosMessage.this, R.string.Success, Toast.LENGTH_SHORT).show();
					
					//save data
					SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
			  		Editor et=uiState.edit();
			  		et.putString("sosmessage3", editmessage_temp);
			  		et.commit();
			  		
			  		Utility.setShareValue(SosMessage.this, "sosmessage_message3", sosnumber3_edit.getText().toString()+","+ysms);
				}
			}
		}
		
	}
	
	//sos message4
	private class OnClickListenersosmessage4 implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editmessage_temp = SosMessage.this.sosnumber4_edit.getText().toString().trim();//���
			if(editmessage_temp.equals("")||destnumber.equals("00000000000"))
			{	
				if(editmessage_temp.equals(""))
					Toast.makeText(SosMessage.this, R.string.Please_input_message, Toast.LENGTH_SHORT).show();
				else if(destnumber.equals("00000000000"))
					Toast.makeText(SosMessage.this, R.string.Please_choose_a_device_first, Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SOSMSG4#"+editmessage_temp+"#";//���
				
				String ysms = "";
				if(sos_message4_ck1.isChecked())
				{
					ysms+="1,";
				}
				if(sos_message4_ck2.isChecked())
				{
					ysms+="2,";
				}
				if(sos_message4_ck3.isChecked())
				{
					ysms+="3,";
				}
				if(sos_message4_ck4.isChecked())
				{
					ysms+="4,";
				}
				if(sos_message4_ck5.isChecked())
				{
					ysms+="5,";
				}
				if(sos_message4_ck6.isChecked())
				{
					ysms+="6,";
				}
				if(sos_message4_ck7.isChecked())
				{
					ysms+="7,";
				}
				if(sos_message4_ck8.isChecked())
				{
					ysms+="8,";
				}
				if(ysms.length()>0)
				{
					ysms = ysms.substring(0,ysms.length()-1);
				}
				if(ysms.length()>0)
				{
					smsnote += "CONFIGSMS4#"+ysms+"#";//���
					SmsManager smsmanager = SmsManager.getDefault();
					smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
					Toast.makeText(SosMessage.this, R.string.Success, Toast.LENGTH_SHORT).show();
					
					//save data
					SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
			  		Editor et=uiState.edit();
			  		et.putString("sosmessage4", editmessage_temp);
			  		et.commit();
			  		
			  		Utility.setShareValue(SosMessage.this, "sosmessage_message4", sosnumber4_edit.getText().toString()+","+ysms);
				}
			}
		}
		
	}
	
	//sos message5
	private class OnClickListenersosmessage5 implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editmessage_temp = SosMessage.this.sosnumber5_edit.getText().toString().trim();//���
			if(editmessage_temp.equals("")||destnumber.equals("00000000000"))
			{	
				if(editmessage_temp.equals(""))
					Toast.makeText(SosMessage.this, R.string.Please_input_message, Toast.LENGTH_SHORT).show();
				else if(destnumber.equals("00000000000"))
					Toast.makeText(SosMessage.this, R.string.Please_choose_a_device_first, Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SOSMSG5#"+editmessage_temp+"#";//���
				String ysms = "";
				if(sos_message5_ck1.isChecked())
				{
					ysms+="1,";
				}
				if(sos_message5_ck2.isChecked())
				{
					ysms+="2,";
				}
				if(sos_message5_ck3.isChecked())
				{
					ysms+="3,";
				}
				if(sos_message5_ck4.isChecked())
				{
					ysms+="4,";
				}
				if(sos_message5_ck5.isChecked())
				{
					ysms+="5,";
				}
				if(sos_message5_ck6.isChecked())
				{
					ysms+="6,";
				}
				if(sos_message5_ck7.isChecked())
				{
					ysms+="7,";
				}
				if(sos_message5_ck8.isChecked())
				{
					ysms+="8,";
				}
				if(ysms.length()>0)
				{
					ysms = ysms.substring(0,ysms.length()-1);
				}
				if(ysms.length()>0)
				{
					smsnote += "CONFIGSMS5#"+ysms+"#";//���
					SmsManager smsmanager = SmsManager.getDefault();
					smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
					Toast.makeText(SosMessage.this, R.string.Success, Toast.LENGTH_SHORT).show();
					
					//save data
					SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
			  		Editor et=uiState.edit();
			  		et.putString("sosmessage5", editmessage_temp);
			  		et.commit();
			  		
			  		Utility.setShareValue(SosMessage.this, "sosmessage_message5", sosnumber5_edit.getText().toString()+","+ysms);
				}
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
	                    if (msgtemp.contains("M1")||msgtemp.contains("M2")) 
	                    {  
	                        //TODO  
	                    	Toast.makeText(SosMessage.this, R.string.Success, Toast.LENGTH_SHORT).show();
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
		Intent it = new Intent(SosMessage.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		SosMessage.this.startActivity(it);
		SosMessage.this.finish();
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
			SosMessage.this.set_enable.setBackgroundResource(R.drawable.btn_red2);
			SosMessage.this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		}
		
	}
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(SosMessage.this,GetSosMessage.class);//ʵ��Intent
			it.putExtra("myinfo", "entry get sosnumber");//Key
			SosMessage.this.startActivity(it);
			SosMessage.this.finish();
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
