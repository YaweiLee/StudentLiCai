package com.bn.financial_assistant;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.bn.account.AccountActivity;
import com.bn.financial_assistant.R;

public class SeettsActivity extends Activity {	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags
        (
        		WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_seetts);
    	//�����Ŀ
        Button incomebutton=(Button)findViewById(R.id.Button01);
        incomebutton.setOnClickListener(new View.OnClickListener() {
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				Intent intent=new Intent();
    		        intent.setClass(SeettsActivity.this, AccountShouruActivity .class);
    		        startActivity(intent);
    			}
    		});
        
    	//֧����Ŀ
    	Button btspend=(Button)this.findViewById(R.id.Button02);
    	btspend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
		        intent.setClass(SeettsActivity.this, AccountZhichuActivity.class);
		        startActivity(intent);
			}
		});
    	//�˻�
    	Button btzhanghu=(Button)this.findViewById(R.id.Button03);
    	btzhanghu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
		        intent.setClass(SeettsActivity.this, AccountItemActivity.class);
		        startActivity(intent);
			}
		});

    	
    	//����
    	Button returnbutton=(Button)this.findViewById(R.id.settingfanhui);
    	returnbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SeettsActivity.this.finish();
			}
		});
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getRepeatCount() == 0) {

			SeettsActivity.this.finish();

			return false;

		}
		return false;
	}
	// =====================================��ҳ���ʼ��
	// end==============================================
}
