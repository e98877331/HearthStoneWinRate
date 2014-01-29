package wcm.towolf.hearthstonewr;

import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;

import java.io.Serializable;
import java.util.ArrayList;

import wcm.towolf.hearthstonewr.model.ArenaEventDataProvider;
import wcm.towolf.hearthstonewr.model.datatype.RoleType;
import wcm.towolf.hearthstonewr.model.datatype.arena.ArenaHeroDetailData;
import wcm.towolf.hearthstonewr.model.datatype.arena.ArenaHeroDetailData.ArenaVSHeroData;
import wcm.towolf.hearthstonewr.view.arena.ArenaDetailView;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;

public class ExportActivity extends Activity {

	final static String TAG = "CsvAcitvity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		RatioRelativeLayout rl = new RatioRelativeLayout(this);
	
		
		rl.setBackgroundResource(R.drawable.main_bg);
		TextView tv = new TextView(this);
		tv.setText("Coming \nS       n");
		tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,rl.getRatioFixer().getRealValue(100));
		
//		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		lp.addRule(RelativeLayout.CENTER_IN_PARENT);
		rl.addView(tv,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,200,505);
		
		ImageView iv = new ImageView(this);
		iv.setImageResource(R.drawable.common_infinity);
			
		
		rl.setToContentView(this);
		
		LightingColorFilter lcf = new LightingColorFilter( 0, 0xFFFFFFFF); 
		iv.setColorFilter(lcf);
		rl.addView(iv,170,170,tv.getLeft()+265,tv.getTop()+600);
		
		
		ObjectAnimator animCR1 = ObjectAnimator.ofFloat(iv, "rotation", 360.0f);
		animCR1.setDuration(2000);
		animCR1.setRepeatCount(ObjectAnimator.INFINITE);
		animCR1.setInterpolator(null);
		animCR1.start();
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
