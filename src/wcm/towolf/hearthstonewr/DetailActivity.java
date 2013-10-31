package wcm.towolf.hearthstonewr;

import java.text.DecimalFormat;

import wcm.towolf.hearthstonewr.model.RoleDataProvider;
import wcm.towolf.hearthstonewr.model.datatype.RoleData;
import wcm.towolf.hearthstonewr.model.datatype.RoleType;
import wcm.towolf.heartstonewr.detail.DetailView;
import wcm.towolf.heartstonewr.detail.DialogActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class DetailActivity extends Activity {
	DetailView dView;
	
	RoleData mRole;
	
	RoleDataProvider mProvider;
	
	int roleID;
	int roleType;
	String roleName;
	float winRate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		mRole = RoleData.getPassingData();
		roleID = mRole.roleID;
		roleType = mRole.roleType;
		roleName = mRole.roleName;
		winRate = mRole.winRate;
		
		mProvider = new RoleDataProvider();
		
		initUI();
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
	
	
	private void initUI() {
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
				// TODO: next patch
//				Intent i = new Intent(DetailActivity.this, DetailListActivity.class);
//				startActivity(i);
			}
		});
		
		dView.winButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mRole.addGame(RoleType.HUNTER, true);
				
				updateView();
			}
		});
		
		dView.loseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mRole.addGame(RoleType.HUNTER, false);
				
				updateView();
			}
		});
		
		dView.winDecreaseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mProvider.deleteLastGame(mRole.roleID);
				
				updateView();
			}
		});
		
		updateView();
	}
	
	private void updateView() {
		DecimalFormat fnum = new DecimalFormat("##0.00");
		String dd = fnum.format(mRole.getWinRate() * 100);
		dView.winRateTextView.setText(dd + "%");
		dView.winCounterTextView.setText("W: " + Integer.toString(mRole.getWin()));
		dView.loseCounterTextView.setText("L: " + Integer.toString(mRole.getLose()));
		dView.totalCounterTextView.setText("Total: " + Integer.toString(mRole.getTotalGame()));
	}
}
