package wcmlab.towolf.hearthstonewr;

import wcmlab.towolf.heartstonewr.welcome.WelcomeView;
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
		
		switcher  = wView.switcher;
		switcher.postDelayed(runnable, 0);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
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
			if (counter < 10) {
				// TODO Auto-generated method stub
				switcher.setBackgroundResource(getCharID());
				counter++;
				switcher.postDelayed(this, 500);
			} else {
				switcher.removeCallbacks(this);
				// TODO: start next activity
				Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
				startActivity(i);
				finish();
			}
		}
		
	};
	
	int previous_num = 0;
	private int getCharID() {
		int index = (int)(Math.random() * 9);
		if (index == previous_num) {
			index = (index + 1) % 9;
		}
		previous_num = index;
		Log.d("weichi", "num=" + index);
		return IMAGES[index];
	}

}
