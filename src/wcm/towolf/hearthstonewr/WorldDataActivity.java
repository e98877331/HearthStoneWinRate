package wcm.towolf.hearthstonewr;

import java.util.ArrayList;

import wcm.towolf.hearthstonewr.view.worlddata.PicsView;
import wcm.towolf.hearthstonewr.view.worlddata.WorldDataDetailView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
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
			mView.addView(pView, 720, 1030, 0, 200);
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
			
			return;
	    }
	    
	    // API call
//	    new GetWorldDataTask("type").execute();
	}
	
	public class GetWorldDataTask extends AsyncTask<Void, Void, ArrayList<?>> {

		String type;

		public GetWorldDataTask(String type) {
			this.type = type;
		}

		@Override
		protected ArrayList<?> doInBackground(Void... params) {
//			RObject<ADInfo> ro = RA.getADInfo(mContext, "APP001", ad_id);
//			if (ro.raExp == null && ro.obj != null) {
//				Log.e(TAG, ro.obj.toString());
//			} else {
//				Log.e(TAG, ro.raExp.toString());
//			}

			return null;
		}

		protected void onPostExecute(ArrayList<?> result) {
//			if (result != null) {
//				if (DBHelper.getInstance(mContext).readADInfo(ad_id) == null) {
//					mLayout.adN.setVisibility(View.VISIBLE);
//					XApplication.isAdNShowing = true;
//				}
//				DBHelper.getInstance(mContext).writeADInfo(result);
//				
//				setADInfoListener();
//			}
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
