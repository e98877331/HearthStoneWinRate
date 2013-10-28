package wcmlab.towolf.heartstonewr.main;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import android.content.Context;
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
		
		mIconBG = new ImageView(context);
		this.addView(mIconBG,mRF.getLayoutParam(220, 220, 0, 0));
		
		mIcon = new ImageView(context);
		this.addView(mIcon,mRF.getLayoutParam(200, 200, 10, 10));
		
		mRoleName = new TextView(context);
		mRoleName.setGravity(Gravity.CENTER);
		mRoleName.setText("roleName");
		mRoleName.setTextSize(mRF.getRealValue(10));
		this.addView(mRoleName,mRF.getLayoutParam(328,220,220,0));
		
		mWinRate = new TextView(context);
		mWinRate.setGravity(Gravity.CENTER);
		mWinRate.setText("50%");
		mWinRate.setTextSize(mRF.getRealValue(20));
		this.addView(mWinRate,mRF.getLayoutParam(220,220,548,0));
		
		
		
		
	}
	
	public void setData(int resourceID,String roleName,float winRate)
	{
		mIcon.setBackgroundResource(resourceID);
		mRoleName.setText(roleName);
		mWinRate.setText(Float.toString(winRate));
	}

}
