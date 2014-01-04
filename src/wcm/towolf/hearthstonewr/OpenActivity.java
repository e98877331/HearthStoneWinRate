package wcm.towolf.hearthstonewr;

import wcm.towolf.hearthstonewr.view.open.OpenView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OpenActivity extends Activity {

	OpenView mView;
	
	Button mNormalBtn;
	Button mArenaBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mView = new OpenView(this);
		mNormalBtn = mView.normalBtn;
		mNormalBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(OpenActivity.this, MainActivity.class);
				// i.putExtra("RoleData",mAdapter.getItem(arg2));
				startActivity(i);	
			}
		});
		
		mArenaBtn = mView.arenaBtn;
		mArenaBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(OpenActivity.this, ArenaActivity.class);
				// i.putExtra("RoleData",mAdapter.getItem(arg2));
				startActivity(i);	
			}
		});
		
		
		mView.setToContentView(this);
		
	}
	
}
