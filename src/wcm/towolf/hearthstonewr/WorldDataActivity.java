package wcm.towolf.hearthstonewr;

import java.util.ArrayList;

import wcm.towolf.hearthstonewr.model.api.ApiHelper;
import wcm.towolf.hearthstonewr.model.bigdatalogger.WorldHeroData;
import wcm.towolf.hearthstonewr.view.worlddata.PicsView;
import wcm.towolf.hearthstonewr.view.worlddata.WorldDataDetailView;
import wcm.towolf.hearthstonewr.view.worlddata.WorldDataDetailView.RowView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
	
	ProgressDialog dialog;
	AlertDialog.Builder alertDialog;

	final static String TAG = "WorldDataDetailAcitvity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle(getResources().getString(R.string.world_data_dialog_title));
		alertDialog.setMessage(getResources().getString(R.string.world_data_dialog_message)).setCancelable(false);
		alertDialog.setNeutralButton(getResources().getString(R.string.world_data_dialog_button), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});
		
		mView = new WorldDataDetailView(this);
		mView.setToContentView(this);
		
		mView.listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
				Intent intent = new Intent(WorldDataActivity.this, WorldDataDetailPerClassActivity.class);
				intent.putExtra("roletype", ((RowView)view).worldHeroData.roleType);
				intent.putExtra("win", ((RowView)view).worldHeroData.winGamesArray);
				intent.putExtra("total", ((RowView)view).worldHeroData.totalGamesArray);
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
				    	
						dialog = ProgressDialog.show(WorldDataActivity.this, null, "please wait");
					    new GetWorldDataTask("type").execute();
					} else {
						finish();
					}
				}
			});
			
			return;
	    }
	    
	    // API call
		
	    new GetWorldDataTask("type").execute();
	}
	
	public class GetWorldDataTask extends AsyncTask<Void, Void, ArrayList<WorldHeroData>> {

		String type;

		public GetWorldDataTask(String type) {
			this.type = type;
		}

		@Override
		protected ArrayList<WorldHeroData> doInBackground(Void... params) {			
			ArrayList<WorldHeroData> arrayList = ApiHelper.getWorldArenaData();
			return arrayList;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			dialog = ProgressDialog.show(WorldDataActivity.this, null, getResources().getString(R.string.world_data_dialog_progress_message));
		}
		protected void onPostExecute(ArrayList<WorldHeroData> result) {
			dialog.dismiss();
			
			if (result == null) {
//				Log.e(TAG, "result is null");
				alertDialog.show();
			} else {
				mView.setData(result);
			}
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
		
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}
	
}
