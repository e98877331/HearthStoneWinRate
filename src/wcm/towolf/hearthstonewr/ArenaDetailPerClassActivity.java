package wcm.towolf.hearthstonewr;

import wcm.towolf.hearthstonewr.view.arena.ArenaDetailPerClassView;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import com.google.analytics.tracking.android.EasyTracker;

public class ArenaDetailPerClassActivity extends Activity {
	
	ArenaDetailPerClassView mView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		int position = getIntent().getIntExtra("position", -1);
		Log.d("TAG", "position = " + position);
		
		mView = new ArenaDetailPerClassView(this,position);
		mView.setToContentView(this);
		

		
//		ArenaEventDataProvider provider = new ArenaEventDataProvider();
//		ArrayList<ArenaHeroDetailData> herodata =  provider.getAllArenaHeroData();
//		ArrayList<ArenaVSHeroData> arenaVSHeroDatas = herodata.get(position - 1).getVsHeroList();
//    	for(ArenaHeroDetailData.ArenaVSHeroData vsd : arenaVSHeroDatas)
//    	{
//    	   Log.e("TAG","vs:" + RoleType.getDebugString(vsd.getVSRoleType(),this)+ " Win: " + vsd.getWins()+" Total:"+ vsd.getTotal());
//    	}
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
