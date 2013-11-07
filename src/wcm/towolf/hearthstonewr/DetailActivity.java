package wcm.towolf.hearthstonewr;

import itri.u9lab.towolf.ratiofixer.RatioFixer;

import java.text.DecimalFormat;

import wcm.towolf.hearthstonewr.model.RoleDataProvider;
import wcm.towolf.hearthstonewr.model.datatype.RoleData;
import wcm.towolf.hearthstonewr.model.datatype.RoleType;
import wcm.towolf.heartstonewr.detail.DetailListActivity;
import wcm.towolf.heartstonewr.detail.DetailView;
import wcm.towolf.heartstonewr.detail.DialogActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DetailActivity extends Activity {
	DetailView dView;
	
	RoleData mRole;
	
	RoleDataProvider mProvider;
	
	BoxView bView;
	BoxView2 bView2;
	
	int roleID;
	int roleType;
	String roleName;
	float winRate;
	
	Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = this;
		
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
			if (data != null) {
				String name = data.getStringExtra("name");
				dView.titleTextView.setText(name);
				mRole.changeRoleName(name);
			}
		}
	}
	
	private void initUI() {
		dView = new DetailView(this);
		dView.setToContentView(this);
		
		dView.mainImageView.setBackgroundResource(mRole.getRoleRes());
		
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
				Intent i = new Intent(DetailActivity.this, DetailListActivity.class);
				startActivity(i);
			}
		});
		
		dView.winButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				mRole.addGame(RoleType.HUNTER, true);
				bView.setVisibility(View.VISIBLE);
			}
		});
		
		dView.loseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				mRole.addGame(RoleType.HUNTER, false);
				bView2.setVisibility(View.VISIBLE);
			}
		});
		
		dView.winDecreaseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mRole.deleteLastGame();
				
				updateView();
			}
		});
		
		bView = new BoxView(this);
		bView.setVisibility(View.GONE);
		dView.addView(bView, 768, 1230, 0, 0);
		
		bView2 = new BoxView2(this);
		bView2.setVisibility(View.GONE);
		dView.addView(bView2, 768, 1230, 0, 0);
		
		updateView();
	}
	
	private void updateView() {
		dView.titleTextView.setText(mRole.getName());
		
		float f = mRole.getWinRate();
		if (f == -1) {
			dView.winRateTextView.setText(getResources().getString(R.string.detail_no_record));
		} else {
			DecimalFormat fnum = new DecimalFormat("##0.00");
			String dd = fnum.format(f * 100);
			dView.winRateTextView.setText(dd + "%");
		}
		dView.winCounterTextView.setText("W: " + Integer.toString(mRole.getWin()));
		dView.loseCounterTextView.setText("L: " + Integer.toString(mRole.getLose()));
		dView.totalCounterTextView.setText("Total: " + Integer.toString(mRole.getTotalGame()));
		printDebugInfo();
	}
	
	public class BoxView extends RelativeLayout {
		TextView title;
		RoleImageView hunter;
		RoleImageView warrior;
		RoleImageView paladin;
		RoleImageView shaman;
		RoleImageView rogue;
		RoleImageView druid;
		RoleImageView warlock;
		RoleImageView priest;
		RoleImageView mage;

		RatioFixer rf;
		
		public BoxView(Context context) {
			super(context);
			
			rf = dView.getRatioFixer();
			
			this.setBackgroundColor(Color.GRAY);
			this.setClickable(true);
			
			title = new TextView(context);
			title.setText("Who you win?");
			title.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 15.0f * rf.getRatio());
			this.addView(title, rf.getLayoutParam(768, 132, 0, 0));
			
			hunter = new RoleImageView(context, RoleType.HUNTER);
			hunter.setBackgroundResource(R.drawable.hunter);
			this.addView(hunter, rf.getLayoutParam(256, 366, 0, 132));
			
			warrior = new RoleImageView(context, RoleType.WARRIOR);
			warrior.setBackgroundResource(R.drawable.warrior);
			this.addView(warrior, rf.getLayoutParam(256, 366, 0, 498));
			
			paladin = new RoleImageView(context, RoleType.PALADIN);
			paladin.setBackgroundResource(R.drawable.paladin);
			this.addView(paladin, rf.getLayoutParam(256, 366, 0, 864));
			
			shaman = new RoleImageView(context, RoleType.SHAMAN);
			shaman.setBackgroundResource(R.drawable.shaman);
			this.addView(shaman, rf.getLayoutParam(256, 366, 256, 132));
			
			rogue = new RoleImageView(context, RoleType.ROGUE);
			rogue.setBackgroundResource(R.drawable.rogue);
			this.addView(rogue, rf.getLayoutParam(256, 366, 256, 498));
			
			druid = new RoleImageView(context, RoleType.DRUID);
			druid.setBackgroundResource(R.drawable.druid);
			this.addView(druid, rf.getLayoutParam(256, 366, 256, 864));
			
			warlock = new RoleImageView(context, RoleType.WARLOCK);
			warlock.setBackgroundResource(R.drawable.warlock);
			this.addView(warlock, rf.getLayoutParam(256, 366, 512, 132));
			
			priest = new RoleImageView(context, RoleType.PRIEST);
			priest.setBackgroundResource(R.drawable.priest);
			this.addView(priest, rf.getLayoutParam(256, 366, 512, 498));
			
			mage = new RoleImageView(context, RoleType.MAGE);
			mage.setBackgroundResource(R.drawable.mage);
			this.addView(mage, rf.getLayoutParam(256, 366, 512, 864));
		}		
	}
	
	class RoleImageView extends ImageView {
		boolean isWin;

		public RoleImageView(Context context, final int roleType) {
			super(context);
			
			this.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mRole.addGame(roleType, true);
					
					updateView();
					bView.setVisibility(View.GONE);
				}
			});
		}
	}
	
	public class BoxView2 extends RelativeLayout {
		TextView title;
		RoleImageView2 hunter;
		RoleImageView2 warrior;
		RoleImageView2 paladin;
		RoleImageView2 shaman;
		RoleImageView2 rogue;
		RoleImageView2 druid;
		RoleImageView2 warlock;
		RoleImageView2 priest;
		RoleImageView2 mage;

		RatioFixer rf;
		
		public BoxView2(Context context) {
			super(context);
			
			rf = dView.getRatioFixer();
			
			this.setBackgroundColor(Color.GRAY);
			this.setClickable(true);
			
			title = new TextView(context);
			title.setText("Who you lose?");
			title.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 15.0f * rf.getRatio());
			this.addView(title, rf.getLayoutParam(768, 132, 0, 0));
			
			hunter = new RoleImageView2(context, RoleType.HUNTER);
			hunter.setBackgroundResource(R.drawable.hunter);
			this.addView(hunter, rf.getLayoutParam(256, 366, 0, 132));
			
			warrior = new RoleImageView2(context, RoleType.WARRIOR);
			warrior.setBackgroundResource(R.drawable.warrior);
			this.addView(warrior, rf.getLayoutParam(256, 366, 0, 498));
			
			paladin = new RoleImageView2(context, RoleType.PALADIN);
			paladin.setBackgroundResource(R.drawable.paladin);
			this.addView(paladin, rf.getLayoutParam(256, 366, 0, 864));
			
			shaman = new RoleImageView2(context, RoleType.SHAMAN);
			shaman.setBackgroundResource(R.drawable.shaman);
			this.addView(shaman, rf.getLayoutParam(256, 366, 256, 132));
			
			rogue = new RoleImageView2(context, RoleType.ROGUE);
			rogue.setBackgroundResource(R.drawable.rogue);
			this.addView(rogue, rf.getLayoutParam(256, 366, 256, 498));
			
			druid = new RoleImageView2(context, RoleType.DRUID);
			druid.setBackgroundResource(R.drawable.druid);
			this.addView(druid, rf.getLayoutParam(256, 366, 256, 864));
			
			warlock = new RoleImageView2(context, RoleType.WARLOCK);
			warlock.setBackgroundResource(R.drawable.warlock);
			this.addView(warlock, rf.getLayoutParam(256, 366, 512, 132));
			
			priest = new RoleImageView2(context, RoleType.PRIEST);
			priest.setBackgroundResource(R.drawable.priest);
			this.addView(priest, rf.getLayoutParam(256, 366, 512, 498));
			
			mage = new RoleImageView2(context, RoleType.MAGE);
			mage.setBackgroundResource(R.drawable.mage);
			this.addView(mage, rf.getLayoutParam(256, 366, 512, 864));
		}		
	}
	
	class RoleImageView2 extends ImageView {
		boolean isWin;

		public RoleImageView2(Context context, final int roleType) {
			super(context);
			
			this.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mRole.addGame(roleType, false);
					
					updateView();
					bView2.setVisibility(View.GONE);
				}
			});
		}
	}

	private void printDebugInfo() {
		Log.d("debug", "total:" + mRole.getTotalGame());
		Log.d("debug", "win:" + mRole.getWin());
		Log.d("debug", "lose:" + mRole.getLose());
		Log.d("debug", "win rate:" + mRole.getWinRate());
		Log.d("debug", "role res:" + mRole.getRoleRes());
	}

	@Override
	public void onBackPressed() {
		if (bView.getVisibility() == View.VISIBLE) {
			bView.setVisibility(View.GONE);
			return;
		}
		
		if (bView2.getVisibility() == View.VISIBLE) {
			bView2.setVisibility(View.GONE);
			return;
		}
		
		super.onBackPressed();
	}
}
