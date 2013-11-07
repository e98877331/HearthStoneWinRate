package wcm.towolf.heartstonewr.main;

import itri.u9lab.towolf.ratiofixer.RatioFixer;

import java.text.DecimalFormat;

import wcm.towolf.hearthstonewr.R;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainViewListItem extends RelativeLayout{

	RatioFixer mRF;
	ImageView mIconBG;
	ImageView mIcon;
	TextView mRoleName;
	TextView mWinRate;
	
	//ImageView mIcon 
	public MainViewListItem(Context context,RatioFixer rf) {
		super(context);
		// TODO Auto-generated constructor stub
		mRF = rf;
		
		RelativeLayout rl = new RelativeLayout(context);
		
		rl.setBackgroundResource(R.drawable.main_list_item_bg);
		//not use yet
		mIconBG = new ImageView(context);
		rl.addView(mIconBG,mRF.getLayoutParam(220, 240, 0, 0));
		
		mIcon = new ImageView(context);
		rl.addView(mIcon,mRF.getLayoutParam(200, 230, 5, 5));
		
		mRoleName = new TextView(context);
		mRoleName.setGravity(Gravity.CENTER);
		mRoleName.setBackgroundResource(R.drawable.rect_label);
		mRoleName.setText("roleName");
		mRoleName.setTextColor(Color.parseColor("#F3E5AB"));
		mRoleName.setTextSize(mRF.getRealValue(15));
		rl.addView(mRoleName,mRF.getLayoutParam(328,120,230,60));
		
		mWinRate = new TextView(context);
		mWinRate.setTextColor(Color.parseColor("#F3E5AB"));
		mWinRate.setBackgroundResource(R.drawable.circle_label);
		mWinRate.setGravity(Gravity.CENTER);
		mWinRate.setText("50%");
		mWinRate.setTextSize(mRF.getRealValue(20));
		rl.addView(mWinRate,mRF.getLayoutParam(150,150,583,40));
		
		this.addView(rl,mRF.getLayoutParam(768, 240, 0, 0));
		
		
	}
	
	public void setData(int resourceID,String roleName,float winRate)
	{
		mIcon.setBackgroundResource(resourceID);
		mRoleName.setText(roleName);
		
		if(winRate != -1)
		{
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		mWinRate.setText(df.format(winRate*100)+"%");
		}
		else
			mWinRate.setText("X");
	}

}
