package wcm.towolf.hearthstonewr;

import wcm.towolf.hearthstonewr.view.worlddata.WorldDataDetailPerClassView;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.analytics.tracking.android.EasyTracker;

public class WorldDataDetailPerClassActivity extends Activity {
	
	WorldDataDetailPerClassView mView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				
		int roletype = getIntent().getIntExtra("roletype", -1);
//		Log.d("TAG", "roletype = " + roletype);
		
		int[] win = getIntent().getIntArrayExtra("win");
//		for (int i : win) {
//			Log.d("TAG", "win = " + i);
//		}
		
		int[] total = getIntent().getIntArrayExtra("total");
//		for (int i : total) {
//			Log.d("TAG", "total = " + i);
//		}

		
		mView = new WorldDataDetailPerClassView(this, roletype, win, total);
		mView.setToContentView(this);
	}
	
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
//	    overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		EasyTracker.getInstance(this).activityStart(this);
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this); 
	}
}
