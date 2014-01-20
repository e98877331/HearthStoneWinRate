package wcm.towolf.hearthstonewr;

import wcm.towolf.hearthstonewr.view.HeroChooseDialog;
import wcm.towolf.hearthstonewr.view.MyAlertDialog;
import wcm.towolf.hearthstonewr.view.open.OpenView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ShareActionProvider;

public class OpenActivity extends Activity {

	OpenView mView;
	
	Button mNormalBtn;
	Button mArenaBtn;
	Button mExportBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
 
		
		mView = new OpenView(this);
		mNormalBtn = mView.normalBtn;
		mNormalBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(OpenActivity.this, MainActivity.class);
				// i.putExtra("RoleData",mAdapter.getItem(arg2));
				startActivity(i);	
			}
		});
		
		mArenaBtn = mView.arenaBtn;
		mArenaBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(OpenActivity.this, ArenaActivity.class);
				// i.putExtra("RoleData",mAdapter.getItem(arg2));
				startActivity(i);	
			}
		});
		
		
		mExportBtn = mView.exportBtn;
		mExportBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(OpenActivity.this, ArenaDetailActivity.class);
				// i.putExtra("RoleData",mAdapter.getItem(arg2));
				startActivity(i);
			}
		});
		mView.setToContentView(this);
		mView.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				showPatchNoteAtFirstTime();				
			}
		});

		
	}
	

	
	public void showPatchNoteAtFirstTime()
	{
		SharedPreferences settings = getSharedPreferences("Preference", 0);
		boolean b = settings.getBoolean("isFirstTime",true);
	    if(b)
	    {
	    	settings.edit().putBoolean("isFirstTime", false).commit();
	    	MyAlertDialog.show(this, R.string.update_note_title,R.string.update_note_content);
	    	
	    }
	    
	}
	
}
