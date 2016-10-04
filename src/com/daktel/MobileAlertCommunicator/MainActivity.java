package com.daktel.MobileAlertCommunicator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText username_edit = null;
	private EditText password_edit = null;
	private Button signin_button = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.username_edit = (EditText)super.findViewById(R.id.username_edit);
		this.password_edit = (EditText)super.findViewById(R.id.password_edit);
		this.signin_button = (Button)super.findViewById(R.id.signin_button);
		this.signin_button.setOnClickListener(new OnClickListenerlogin());
	}
	
	public void onBackPressed() { 
        new AlertDialog.Builder(this).setTitle(R.string.Confirm_Exit) 
            .setIcon(android.R.drawable.ic_dialog_info) 
            .setPositiveButton(getResources().getString(R.string.YES), new DialogInterface.OnClickListener() { 
         
                @Override 
                public void onClick(DialogInterface dialog, int which) { 
	                // �����ȷ�ϡ���Ĳ��� 
	                MainActivity.this.finish(); 
                } 
            }) 
            .setNegativeButton(getResources().getString(R.string.NO), new DialogInterface.OnClickListener() { 
         
                @Override 
                public void onClick(DialogInterface dialog, int which) { 
                	// ��������ء���Ĳ���,���ﲻ����û���κβ��� 
                } 
            }).show(); 
    }
	 
	private class OnClickListenerlogin implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String usernametemp = MainActivity.this.username_edit.getText().toString();//���
			String passwordtemp = MainActivity.this.password_edit.getText().toString();//���
			if(usernametemp.equals("") || passwordtemp.equals(""))
			{
				Toast.makeText(MainActivity.this, R.string.Please_input_user_name_or_password, Toast.LENGTH_SHORT).show();
			}
			else
			{
				Intent it = new Intent(MainActivity.this,MainMenuSetting.class);//ʵ��Intent
				it.putExtra("myinfo", "entry main menu");//Key
				MainActivity.this.startActivity(it);
				MainActivity.this.finish();//��½�ɹ���رյ�½����
			}
		}
		
	}
}

