package wcm.towolf.hearthstonewr;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import wcm.towolf.hearthstonewr.model.bigdatalogger.ArenaBigDataLogger;
import wcm.towolf.hearthstonewr.view.MyAlertDialog;
import wcm.towolf.hearthstonewr.view.open.AcknowledgementDialog;
import wcm.towolf.hearthstonewr.view.open.DonateDialog;
import wcm.towolf.hearthstonewr.view.open.OpenView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.analytics.tracking.android.EasyTracker;

public class OpenActivity extends Activity {

	OpenView mView;
	
	
	Button mAckBtn;
	Button mNormalBtn;
	Button mArenaBtn;
	Button mExportBtn;
	Button mWorldDataBtn;
	Button mShowPatchNoteBtn;
	Button mDonateBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       
		
		mView = new OpenView(this);
		//using global ratio fixer
		RatioFixer.setGlobalRatioFixer(mView.getRatioFixer());
		
		mAckBtn = mView.ackBtn;
		mAckBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AcknowledgementDialog.show(OpenActivity.this);
			}
		});
		
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
				ArenaBigDataLogger.sharedInstance().dequeueAllToServer();
				Intent i = new Intent(OpenActivity.this, ExportActivity.class);
				// i.putExtra("RoleData",mAdapter.getItem(arg2));
				startActivity(i);
				
	//			ArenaTest.addTestEvents();
			}
		});
		
		mWorldDataBtn = mView.worldDataBtn;
		mWorldDataBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ArenaBigDataLogger.sharedInstance().dequeueAllToServer();
				Intent i = new Intent(OpenActivity.this, ExportActivity.class);
				// i.putExtra("RoleData",mAdapter.getItem(arg2));
				startActivity(i);
				
	//			ArenaTest.addTestEvents();
			}
		});
		
		mShowPatchNoteBtn = mView.showPatchNoteBtn;
		mShowPatchNoteBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MyAlertDialog.show(OpenActivity.this, R.string.update_note_title,R.string.update_note_content);
			}
		});
		
		mDonateBtn = mView.donateBtn;
		mDonateBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//			   DonateDialog dialog = new DonateDialog(OpenActivity.this);
//			   dialog.show();
				DonateDialog.show(OpenActivity.this);
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
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		EasyTracker.getInstance(this).activityStart(this);
		mView.startAnimation();
		
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
		mView.stopAnimation();
		
		
		
		
	}
	
	public void showPatchNoteAtFirstTime()
	{
		SharedPreferences settings = getSharedPreferences("Preference", 0);
		boolean b = settings.getBoolean("isFirstTimeV11",true);
	    if(b)
	    {
	    	settings.edit().putBoolean("isFirstTimeV11", false).commit();
	    	MyAlertDialog.show(this, R.string.update_note_title,R.string.update_note_content);
	    	
	    }
	    
	}
	
}
