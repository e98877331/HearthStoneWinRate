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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
//		dView.mainImageView.setImageBitmap(applySaturationFilter(BitmapFactory.decodeResource(getResources(), mRole.getRoleRes()),1));
		
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
			
			hunter = new RoleImageView(context, RoleType.HUNTER);
			hunter.setBackgroundResource(R.drawable.hunter);
			this.addView(hunter, rf.getLayoutParam(256, 410, 0, 0));
			
			warrior = new RoleImageView(context, RoleType.WARRIOR);
			warrior.setBackgroundResource(R.drawable.warrior);
			this.addView(warrior, rf.getLayoutParam(256, 410, 0, 410));
			
			paladin = new RoleImageView(context, RoleType.PALADIN);
			paladin.setBackgroundResource(R.drawable.paladin);
			this.addView(paladin, rf.getLayoutParam(256, 410, 0, 820));
			
			shaman = new RoleImageView(context, RoleType.SHAMAN);
			shaman.setBackgroundResource(R.drawable.shaman);
			this.addView(shaman, rf.getLayoutParam(256, 410, 256, 0));
			
			rogue = new RoleImageView(context, RoleType.ROGUE);
			rogue.setBackgroundResource(R.drawable.rogue);
			this.addView(rogue, rf.getLayoutParam(256, 410, 256, 410));
			
			druid = new RoleImageView(context, RoleType.DRUID);
			druid.setBackgroundResource(R.drawable.druid);
			this.addView(druid, rf.getLayoutParam(256, 410, 256, 820));
			
			warlock = new RoleImageView(context, RoleType.WARLOCK);
			warlock.setBackgroundResource(R.drawable.warlock);
			this.addView(warlock, rf.getLayoutParam(256, 410, 512, 0));
			
			priest = new RoleImageView(context, RoleType.PRIEST);
			priest.setBackgroundResource(R.drawable.priest);
			this.addView(priest, rf.getLayoutParam(256, 410, 512, 410));
			
			mage = new RoleImageView(context, RoleType.MAGE);
			mage.setBackgroundResource(R.drawable.mage);
			this.addView(mage, rf.getLayoutParam(256, 410, 512, 820));
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
			
			hunter = new RoleImageView2(context, RoleType.HUNTER);
			hunter.setBackgroundResource(R.drawable.hunter);
			this.addView(hunter, rf.getLayoutParam(256, 410, 0, 0));
			
			warrior = new RoleImageView2(context, RoleType.WARRIOR);
			warrior.setBackgroundResource(R.drawable.warrior);
			this.addView(warrior, rf.getLayoutParam(256, 410, 0, 410));
			
			paladin = new RoleImageView2(context, RoleType.PALADIN);
			paladin.setBackgroundResource(R.drawable.paladin);
			this.addView(paladin, rf.getLayoutParam(256, 410, 0, 820));
			
			shaman = new RoleImageView2(context, RoleType.SHAMAN);
			shaman.setBackgroundResource(R.drawable.shaman);
			this.addView(shaman, rf.getLayoutParam(256, 410, 256, 0));
			
			rogue = new RoleImageView2(context, RoleType.ROGUE);
			rogue.setBackgroundResource(R.drawable.rogue);
			this.addView(rogue, rf.getLayoutParam(256, 410, 256, 410));
			
			druid = new RoleImageView2(context, RoleType.DRUID);
			druid.setBackgroundResource(R.drawable.druid);
			this.addView(druid, rf.getLayoutParam(256, 410, 256, 820));
			
			warlock = new RoleImageView2(context, RoleType.WARLOCK);
			warlock.setBackgroundResource(R.drawable.warlock);
			this.addView(warlock, rf.getLayoutParam(256, 410, 512, 0));
			
			priest = new RoleImageView2(context, RoleType.PRIEST);
			priest.setBackgroundResource(R.drawable.priest);
			this.addView(priest, rf.getLayoutParam(256, 410, 512, 410));
			
			mage = new RoleImageView2(context, RoleType.MAGE);
			mage.setBackgroundResource(R.drawable.mage);
			this.addView(mage, rf.getLayoutParam(256, 410, 512, 820));
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
	
	 public Bitmap applySaturationFilter(Bitmap source, int level) {
	     // get original image size
	     int width = source.getWidth();
	     int height = source.getHeight();
	     int[] pixels = new int[width * height];
	     float[] HSV = new float[3];
	     // get pixel array from source image
	     source.getPixels(pixels, 0, width, 0, 0, width, height);
	  
	     int index = 0;
	     // iteration through all pixels
	     for(int y = 0; y < height; ++y) {
	         for(int x = 0; x < width; ++x) {
	             // get current index in 2D-matrix
	             index = y * width + x;
	             // convert to HSV
	             Color.colorToHSV(pixels[index], HSV);
	             // increase Saturation level
	             HSV[1] *= level;
	             HSV[1] = (float) Math.max(0.0, Math.min(HSV[1], 1.0));
	             // take color back
	             pixels[index] = Color.HSVToColor(HSV);
	         }
	     }
	     // output bitmap
	     Bitmap bmOut = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
	     bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
	     return bmOut;
	 }
	
	private void printDebugInfo() {
		Log.d("debug", "total:" + mRole.getTotalGame());
		Log.d("debug", "win:" + mRole.getWin());
		Log.d("debug", "lose:" + mRole.getLose());
		Log.d("debug", "win rate:" + mRole.getWinRate());
		Log.d("debug", "role res:" + mRole.getRoleRes());
	}
}
