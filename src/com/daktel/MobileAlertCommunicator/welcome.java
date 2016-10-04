package com.daktel.MobileAlertCommunicator;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class welcome extends Activity{
	
private static final int REQUEST_CODE_SETTING = 1001;
	
	static int idx = 0;
	Timer tr;
	Configuration config;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CONTEXT_MENU); 
        setContentView(R.layout.welcome);

        
        config = getResources().getConfiguration();//获取系统的配置
        String language=SharePreferenceUtil.getPreference(this, Languageset.LANGUAGE_TYPE);
        Log.e("language", language);
        Locale.setDefault(Locale.ENGLISH);
        if(language.equals("")){
        	setLanguage(Languageset.ENGLISH);
        }else{
        	setLanguage(Integer.parseInt(language));
        }

    }


	void Loading()
	{
		idx = 1;
		// 持续滚动
		tr = new Timer();
		tr.schedule(new TimerTask() {
			@Override
			public void run() {
				idx++;
				if (idx >= 10) {// 这里可以修改为其它的判断条件，比如讲�?��预加载的内容
					this.cancel();
					
					Intent intent = new Intent(welcome.this,MainMenuSetting.class);
					startActivity(intent);
					finish();
				}
			}
		}, 0, 100);
	}
	private void setDefault(){
		SharePreferenceUtil.setPreference(this, Languageset.HAS_SET_ENGLISH, "false");
		SharePreferenceUtil.setPreference(this, Languageset.HAS_SET_DUTCH, "false");
		SharePreferenceUtil.setPreference(this, Languageset.HAS_SET_FRENCH, "false");
		SharePreferenceUtil.setPreference(this, Languageset.HAS_SET_GERMAN, "false");
		SharePreferenceUtil.setPreference(this, Languageset.HAS_SET_PORTUGUESE, "false");
		SharePreferenceUtil.setPreference(this, Languageset.HAS_SET_SPANISH, "false");
		SharePreferenceUtil.setPreference(this, Languageset.HAS_SET_TURKISH, "false");
	}
	
	private void setLanguage(int language){
		switch(language){
		
		case Languageset.ENGLISH:
			if (SharePreferenceUtil.getPreference(this,Languageset.HAS_SET_ENGLISH).equals("true")){
                Loading();
                return;
            }  
			setDefault();
			SharePreferenceUtil.setPreference(this, Languageset.HAS_SET_ENGLISH, "true");
			Locale.setDefault(Locale.ENGLISH);
			config.locale=Locale.getDefault();
			break;
		case Languageset.DUTCH:
			if (SharePreferenceUtil.getPreference(this,Languageset.HAS_SET_DUTCH).equals("true")){
                Loading();
                return;
            }  
			setDefault();
			SharePreferenceUtil.setPreference(this, Languageset.HAS_SET_DUTCH, "true");
			config.locale = Locale.JAPAN;
			break;
		case Languageset.FRENCH:
			if (SharePreferenceUtil.getPreference(this,Languageset.HAS_SET_FRENCH).equals("true")){
                Loading();
                return;
            }  
			setDefault();
			SharePreferenceUtil.setPreference(this, Languageset.HAS_SET_FRENCH, "true");
			config.locale = Locale.FRENCH;
			break;
		case Languageset.GERMAN:
			if (SharePreferenceUtil.getPreference(this,Languageset.HAS_SET_GERMAN).equals("true")){
                Loading();
                return;
            }  
			setDefault();
			SharePreferenceUtil.setPreference(this, Languageset.HAS_SET_GERMAN, "true");
			config.locale = Locale.GERMANY;
			break;
		case Languageset.PORTUGUESE:
			if (SharePreferenceUtil.getPreference(this,Languageset.HAS_SET_PORTUGUESE).equals("true")){
                Loading();
                return;
            }  
			setDefault();
			SharePreferenceUtil.setPreference(this, Languageset.HAS_SET_PORTUGUESE, "true");
			config.locale = Locale.ITALIAN;
			break;
		case Languageset.SPANISH:
			if (SharePreferenceUtil.getPreference(this,Languageset.HAS_SET_SPANISH).equals("true")){
                Loading();
                return;
            }  
			setDefault();
			SharePreferenceUtil.setPreference(this, Languageset.HAS_SET_SPANISH, "true");
			config.locale=Locale.CHINESE;
			break;
		case Languageset.TURKISH:
			if (SharePreferenceUtil.getPreference(this,Languageset.HAS_SET_TURKISH).equals("true")){
                Loading();
                return;
            }  
			setDefault();
			SharePreferenceUtil.setPreference(this, Languageset.HAS_SET_TURKISH, "true");
			config.locale = Locale.KOREAN;
			break;
		}
		getResources().updateConfiguration(config, getResources().getDisplayMetrics());//更新配置
        finish();
        Intent intent=new Intent(welcome.this,welcome.class);
        startActivity(intent);
	}
	
}
