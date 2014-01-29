package wcm.towolf.heartstonewr.detail;


import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.model.datatype.RoleData;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class DialogActivity extends Activity {

	EditText mEditText;
	Button mButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);
	//	mView = new MainView(this);
		
		mEditText = (EditText) findViewById(R.id.editText1);
		
		mButton = (Button) findViewById(R.id.button1);
		mButton.setText(getResources().getString(R.string.detail_dialog_button));
		
		mEditText.setText(RoleData.getPassingData().getName());
		mEditText.setFilters( new InputFilter[] {
				   new InputFilter.LengthFilter(RoleData.NAME_LENGTH)});
		mEditText.selectAll();
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		
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
}
