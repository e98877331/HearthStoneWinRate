package wcmlab.towolf.heartstonewr.detail;

import wcmlab.towolf.hearthstonewr.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DialogActivity extends Activity {

//	MainView mView;
//	TextView mTextView;
//	
//	DetailView dView;
	
	EditText mEditText;
	Button mButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);
	//	mView = new MainView(this);
		
		mEditText = (EditText) findViewById(R.id.editText1);
		
		mButton = (Button) findViewById(R.id.button1);
		mButton.setText("done");
				
	//	mView.setToContentView(this);
		
//		dView = new DetailView(this);
//		dView.setToContentView(this);
		
		mButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				Bundle b = new Bundle();
				b.putString("name", mEditText.getText().toString());
				i.putExtras(b);
				setResult(RESULT_OK, i);
				finish();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
