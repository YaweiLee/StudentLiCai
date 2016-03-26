package com.bn.financial_assistant;

import static com.bn.assets.Constant.DEL_DIALOG;
import java.util.ArrayList;
import java.util.List;

import com.bn.assets.DBUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class AccountZhichuActivity extends Activity {
	
	
	private String[] zhichukm={"饮食支出","交通支出","日常用品","通讯支出","教育支出"};
	private List<String> al=new ArrayList<String>();
	Dialog dialogDelConfirm;
	private String tname;
	private int index;
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
    setContentView(R.layout.activity_account_zhichu);
	goSZView("spendsubject",zhichukm);
	Button returnbutton=(Button)findViewById(R.id.fanhui);
	returnbutton.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			AccountZhichuActivity.this.finish();
		}
	});
	}
	//收入科目、支出科目添加监听器
    public void goSZView(final String tablename,String[] kemu){
    	tname=tablename;
    	//添加按钮
    	 Button addbutton=(Button)this.findViewById(R.id.add); 
    	 addbutton.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			EditText xkm=(EditText)findViewById(R.id.EditText01);
    	    	String xkmStr=xkm.getText().toString().trim();
    			al=DBUtil.getSubjects(tablename);
    			if(al.contains(xkmStr+"")){
    				Toast.makeText(AccountZhichuActivity.this, "不能重复插入！！", Toast.LENGTH_SHORT).show();
    			}
    			else if(xkmStr.length()==0){
    				Toast.makeText(AccountZhichuActivity.this, "输入框不能为空！！", Toast.LENGTH_SHORT).show();
    			}
    			else {
    				DBUtil.insertSubjects(xkmStr,tablename);
        			al.add(xkmStr);
        			ListView lv=(ListView)findViewById(R.id.ListView01);
        			setAdapter(lv,al,tablename); 
    			} 
    		}
    	});	
    	al=DBUtil.getSubjects(tablename);
    	//初始化
    		if(al.size()==0){
             	for(int i=0;i<kemu.length;i++){
                 	DBUtil.insertSubjects(kemu[i], tablename);
                 	      }
             	}
    	al=DBUtil.getSubjects(tablename);
    	ListView lv=(ListView)findViewById(R.id.ListView01);
    	setAdapter(lv,al,tablename);
    }
    public void setAdapter(final ListView lv,final List<String> al, final String tablename){
    	final BaseAdapter ba=new BaseAdapter(){
    		@Override
    		public int getCount() {
    			// TODO Auto-generated method stub
    			return al.size();
    		}
    		@Override
    		public Object getItem(int position) {
    			// TODO Auto-generated method stub
    			return al.get(position);
    		}
    		@Override
    		public long getItemId(int position) {
    			// TODO Auto-generated method stub
    			return position;
    		}
    		@Override
    		public View getView(final int position,View convertView, ViewGroup parent) {
    			LinearLayout ll=new LinearLayout(AccountZhichuActivity.this);
    			ll.setOrientation(LinearLayout.HORIZONTAL);
    			ll.setPadding(5,5,5,5);
    			LinearLayout ll1=new LinearLayout(AccountZhichuActivity.this);
    			ll1.setLayoutParams(new LayoutParams(380,android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
    			TextView tv=new TextView(AccountZhichuActivity.this);
    			tv.setText((CharSequence) al.get(position));
    			tv.setTextSize(18);
    			tv.setTextColor(AccountZhichuActivity.this.getResources().getColor(R.color.black));
    			tv.setGravity(Gravity.LEFT);
    			//删除按钮
    			LinearLayout ll2=new LinearLayout(AccountZhichuActivity.this);
    			Button bt=new Button(AccountZhichuActivity.this);
    		    bt.setText("删除");
    		    bt.setTextSize(15);
    	        bt.setLayoutParams(new LayoutParams(120,80));
    		    ll1.addView(tv);
    		    ll2.addView(bt);
    		    ll.addView(ll1);
    		    ll.addView(ll2);
    		    bt.setOnClickListener(new View.OnClickListener() {
        			@Override
        			public void onClick(View v) {
        				index=position;
        				showDialog(DEL_DIALOG);
        				}
        		});
    			return ll;
    		}   	
        };
        lv.setAdapter(ba);  
        ba.notifyDataSetChanged();
    }
  //对话框
    @Override
    public Dialog onCreateDialog(int id)
    {
       Dialog dialog=null;
    	
    	switch(id)
    	{
    	case DEL_DIALOG:
        	AlertDialog.Builder abDelConfirm=new AlertDialog.Builder(AccountZhichuActivity.this); 
    		abDelConfirm.setItems(null,null);
    		dialogDelConfirm=abDelConfirm.create();
    		dialog=dialogDelConfirm;	
        	break;
    	}
	return dialog;
}
    @Override
	public void onPrepareDialog(int id,Dialog dialog) 
	{
		super.onPrepareDialog(id, dialog);
    	switch(id){
    	case DEL_DIALOG:
    		dialog.setContentView(R.layout.dialole);
    		Button bDelOk=(Button)dialog.findViewById(R.id.bdialogdelconfirmOk);
			Button bDelCancel=(Button)dialog.findViewById(R.id.bdialogdelconfirmCancel);
			bDelOk.setOnClickListener
			(
					new OnClickListener()
					{
						@Override
						public void onClick(View v) 
						{ListView lv=(ListView)findViewById(R.id.ListView01);
						 
						    al=DBUtil.getSubjects(tname);
							DBUtil.deleteFromTable(al.get(index).toString(),tname);
	        				al.remove(index);
	        				setAdapter(lv,al,tname);
							dialogDelConfirm.cancel();
						}
					}
			);
			bDelCancel.setOnClickListener
			(
					new OnClickListener()
					{
						@Override
						public void onClick(View v) 
						{
							dialogDelConfirm.cancel();
						}
					}
			);
			break;
    	}
	}
	
}
