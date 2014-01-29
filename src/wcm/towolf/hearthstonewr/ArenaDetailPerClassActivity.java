package wcm.towolf.hearthstonewr;

import wcm.towolf.hearthstonewr.view.arena.ArenaDetailPerClassView;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

public class ArenaDetailPerClassActivity extends Activity {
	
	ArenaDetailPerClassView mView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		mView = new ArenaDetailPerClassView(this);
		mView.setToContentView(this);
		
		int position = getIntent().getIntExtra("position", -1);
		Log.d("TAG", "position = " + position);
	}
	
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
//	    overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
	}
}
