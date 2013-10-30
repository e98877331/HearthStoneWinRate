package wcmlab.towolf.hearthstonewr;

import wcmlab.towolf.heartstonewr.detail.DetailListActivity;
import wcmlab.towolf.heartstonewr.detail.DetailView;
import wcmlab.towolf.heartstonewr.detail.DialogActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class DetailActivity extends Activity {
	DetailView dView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		dView = new DetailView(this);
		dView.setToContentView(this);
		
		dView.titleTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(DetailActivity.this, DialogActivity.class);
				startActivityForResult(i, 50);
			}
		});
		
		dView.mainImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(DetailActivity.this, DetailListActivity.class);
				startActivity(i);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == 50) {
			if (data != null)
				dView.titleTextView.setText(data.getStringExtra("name"));
		}
	}

}
