package wcm.towolf.hearthstonewr;

import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class ArenaDetailActivity extends Activity {

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
		
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.CENTER_IN_PARENT);
		rl.addView(tv,lp);
		
		ImageView iv = new ImageView(this);
		iv.setImageResource(R.drawable.common_infinity);
			
		
		rl.setToContentView(this);
		
		LightingColorFilter lcf = new LightingColorFilter( 0, 0xFFFFFFFF); 
		iv.setColorFilter(lcf);
		rl.addView(iv,170,170,tv.getLeft()+260,tv.getTop()+595);
		
		
		ObjectAnimator animCR1 = ObjectAnimator.ofFloat(iv, "rotation", 360.0f);
		animCR1.setDuration(2000);
		animCR1.setRepeatCount(ObjectAnimator.INFINITE);
		animCR1.setInterpolator(null);
		animCR1.start();
		
	}
}
