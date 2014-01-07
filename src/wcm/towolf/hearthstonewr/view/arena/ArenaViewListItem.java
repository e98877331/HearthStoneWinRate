package wcm.towolf.hearthstonewr.view.arena;

import itri.u9lab.towolf.ratiofixer.RatioFixer;

import java.text.DecimalFormat;

import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.model.datatype.RoleType;
import wcm.towolf.hearthstonewr.model.datatype.arena.ArenaEventData;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils.TruncateAt;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ArenaViewListItem extends RelativeLayout{

	RatioFixer mRF;
//ImageView mIconBG;
	ImageView mIcon;
	TextView mRoleName;
	TextView mWinNumber;
	
	//ImageView mIcon 
	public ArenaViewListItem(Context context,RatioFixer rf) {
		super(context);
		// TODO Auto-generated constructor stub
		mRF = rf;
		
		RelativeLayout rl = new RelativeLayout(context);
		
		rl.setBackgroundResource(R.drawable.main_list_item_bg);
		//not use yet
		
		mIcon = new ImageView(context);
		rl.addView(mIcon,mRF.getLayoutParam(160, 230, 33, 5));
		
//		mRoleName = new TextView(context);
//		mRoleName.setGravity(Gravity.CENTER);
//		mRoleName.setBackgroundResource(R.drawable.rect_label);
//		mRoleName.setText("roleName");
//		mRoleName.setTextColor(Color.parseColor("#F3E5AB"));
//		mRoleName.setTextSize(TypedValue.COMPLEX_UNIT_PX,mRF.getRealValue(30));
//		mRoleName.setEllipsize(TruncateAt.MARQUEE);
//        mRoleName.setSelected(true);
//        mRoleName.setSingleLine(true);
//        int wpadding = mRF.getRealValue(15);
//        mRoleName.setPadding(wpadding, 0, wpadding, 0);
//		rl.addView(mRoleName,mRF.getLayoutParam(328,120,230,60));
		
		mWinNumber = new TextView(context);
		mWinNumber.setTextColor(Color.parseColor("#F3E5AB"));
		mWinNumber.setBackgroundResource(R.drawable.circle_label);
		mWinNumber.setGravity(Gravity.CENTER);
		mWinNumber.setText("50%");
		mWinNumber.setTextSize(TypedValue.COMPLEX_UNIT_PX,mRF.getRealValue(40));
		rl.addView(mWinNumber,mRF.getLayoutParam(150,150,585,42));
		
		this.addView(rl,mRF.getLayoutParam(768, 240, 0, 0));
		
		
	}
	
	public void setData(ArenaEventData data)
	{
		mIcon.setBackgroundResource(RoleType.getRoleRes(data.roleType));
		//mRoleName.setText(roleName);
		
	
			mWinNumber.setBackgroundResource(R.drawable.circle_label);

		mWinNumber.setText(Integer.toString(data.win));
		

			//mWinRate.setText("X");
	}
	
}
