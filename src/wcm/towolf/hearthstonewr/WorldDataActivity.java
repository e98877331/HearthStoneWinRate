package wcm.towolf.hearthstonewr;

import wcm.towolf.hearthstonewr.view.worlddata.PicsView;
import wcm.towolf.hearthstonewr.view.worlddata.WorldDataDetailView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.analytics.tracking.android.EasyTracker;

public class WorldDataActivity extends Activity {
	
	WorldDataDetailView mView;
	PicsView pView;

	final static String TAG = "WorldDataDetailAcitvity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		mView = new WorldDataDetailView(this);
		mView.setToContentView(this);
		
		mView.listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
				Intent intent = new Intent(WorldDataActivity.this, ArenaDetailPerClassActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			}
		});
		
		final SharedPreferences settings = getSharedPreferences("Preference", 0);
		boolean isPicsConfirmed = settings.getBoolean("isPicsConfirmed", false);
	    if (!isPicsConfirmed) {
			pView = new PicsView(this);
			mView.addView(pView, 720, 1230, 0, 0);
			pView.button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (pView.isChecked()) {
						pView.setVisibility(View.GONE);
				    	settings.edit().putBoolean("isPicsConfirmed", true).commit();
					} else {
						finish();
					}
				}
			});
	    }
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
