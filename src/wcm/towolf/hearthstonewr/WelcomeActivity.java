package wcm.towolf.hearthstonewr;

import wcm.towolf.heartstonewr.welcome.WelcomeView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageSwitcher;

public class WelcomeActivity extends Activity {
	WelcomeView wView;
	
	ImageSwitcher switcher;
	
	private static final int[] IMAGES = { R.drawable.mage, R.drawable.priest,
			R.drawable.warrior, R.drawable.hunter, R.drawable.druid,
			R.drawable.rogue, R.drawable.shaman, R.drawable.warlock,
			R.drawable.paladin };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
	
		wView = new WelcomeView(this);
		wView.setToContentView(this);
		
		switcher = wView.switcher;
		switcher.postDelayed(runnable, 100);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		switcher.removeCallbacks(runnable);
	}
	
	int counter = 0;
	
	Runnable runnable = new Runnable() {

		@Override
		public void run() {
//			if (counter < 5) {
				// TODO Auto-generated method stub
//				Log.d("weichi", "A");
				switcher.setBackgroundResource(getCharID());
//				switcher.setBackgroundResource(IMAGES[counter]);
//				Log.d("weichi", "B");
//				counter++;
//				Log.d("weichi", "C");
//				switcher.postDelayed(this, 200);
//			} else {
				// TODO: start next activity
				Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
				startActivity(i);
				finish();
				switcher.removeCallbacks(this);
//			}
		}
		
	};
	
	int previous_num = 0;
	private int getCharID() {
		int index = (int)(Math.random() * 9);
		Log.d("weichi", "index1=" + index);
		if (index == previous_num) {
			index = (index + 1) % 9;
		}
		previous_num = index;
		Log.d("weichi", "index2=" + index);
		return IMAGES[index];
	}

}
