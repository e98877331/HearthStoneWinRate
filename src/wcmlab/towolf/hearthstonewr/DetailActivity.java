package wcmlab.towolf.hearthstonewr;

import wcmlab.towolf.heartstonewr.detail.DetailView;
import wcmlab.towolf.heartstonewr.detail.DialogActivity;
import wcmlab.towolf.heartstonewr.main.MainView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class DetailActivity extends Activity {

	MainView mView;
	TextView mTextView;
	
	DetailView dView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	//	mView = new MainView(this);
		
		
	//	mView.setToContentView(this);
		
		dView = new DetailView(this);
		dView.setToContentView(this);
		
		dView.titleTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(DetailActivity.this, DialogActivity.class);
				startActivityForResult(i, 50);
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
			dView.titleTextView.setText(data.getStringExtra("name"));
		}
	}

}
