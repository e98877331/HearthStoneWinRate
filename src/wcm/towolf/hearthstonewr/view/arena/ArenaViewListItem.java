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

public class ArenaViewListItem extends RelativeLayout {

	RatioFixer mRF;
	// ImageView mIconBG;
	TextView mDateView;
    EventDetailView mEventDetail;
	
	ImageView mIcon;
	TextView mWinLabel;
	TextView mWinNumber;

	// ImageView mIcon
	public ArenaViewListItem(Context context, RatioFixer rf) {
		super(context);
		// TODO Auto-generated constructor stub
		mRF = rf;

		RelativeLayout rl = new RelativeLayout(context);

		rl.setBackgroundResource(R.drawable.main_list_item_bg);
		// not use yet

		mIcon = new ImageView(context);
		rl.addView(mIcon, mRF.getLayoutParam(160, 230, 33, 5));

		mDateView = new TextView(context);
		mDateView.setGravity(Gravity.CENTER);
		mDateView.setBackgroundResource(R.drawable.rect_label);
		mDateView.setText("roleName");
		mDateView.setTextColor(Color.parseColor("#F3E5AB"));
		mDateView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mRF.getRealValue(20));
		rl.addView(mDateView, mRF.getLayoutParam(328, 40, 230, 25));
		
		mEventDetail = new EventDetailView(context);
		rl.addView(mEventDetail,mRF.getLayoutParam(321, 200, 240, 40));
		

		mWinNumber = new TextView(context);
		mWinNumber.setTextColor(Color.parseColor("#F3E5AB"));
		mWinNumber.setBackgroundResource(R.drawable.circle_label);
		mWinNumber.setGravity(Gravity.CENTER);
		mWinNumber.setText("50%");
		mWinNumber
				.setTextSize(TypedValue.COMPLEX_UNIT_PX, mRF.getRealValue(40));
		rl.addView(mWinNumber, mRF.getLayoutParam(150, 150, 585, 42));

		mWinLabel = new TextView(context);
		mWinLabel.setText("Win");
		mWinLabel.setTextColor(Color.parseColor("#F3E5AB"));
		mWinLabel.setGravity(Gravity.CENTER);
		mWinLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, mRF.getRealValue(33));
		rl.addView(mWinLabel, mRF.getLayoutParam(150, 100, 585, 20));

		this.addView(rl, mRF.getLayoutParam(768, 240, 0, 0));

	}

	public void setData(ArenaEventData data) {
		mIcon.setBackgroundResource(RoleType.getRoleRes(data.roleType));
		// mRoleName.setText(roleName);

		mDateView.setText(data.startDate);
		
		mWinNumber.setBackgroundResource(R.drawable.circle_label);

		mWinNumber.setText(Integer.toString(data.win));
        
		mEventDetail.setData(data.winLoseArray);
		
		
		// mWinRate.setText("X");
	}
	
	
	public class EventDetailView extends RelativeLayout
	{
        //300*100
		ImageView[] tokens = new ImageView[13];
		
		public EventDetailView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			this.setBackgroundResource(R.drawable.arena_event_detail);
			
			for(int i = 0; i < tokens.length;i++)
			{
			  tokens[i] = new ImageView(context);
			 // tokens[i].setBackgroundResource(R.drawable.arena_event_detail_token_default);
			}
			
			for( int i = 0 ; i< 6; i++)
			{
				this.addView(tokens[i],mRF.getLayoutParam(50, 50, (48) *i, 48));
                this.addView(tokens[12-i],mRF.getLayoutParam(50, 50, (48) *i, 106));	
			}
			
			this.addView(tokens[6],mRF.getLayoutParam(50, 50, 270, 76));
			
		}
		
		public void setData(int[] data)
		{
			for(int i = 0 ; i<13; i++ )
			{
				if(data[i] == -1)
					tokens[i].setBackgroundResource(R.drawable.arena_event_detail_token_default);
				else if(data[i] == 0)
					tokens[i].setBackgroundResource(R.drawable.arena_event_detail_token_lose);
				else if(data[i] == 1)
					tokens[i].setBackgroundResource(R.drawable.arena_event_detail_token_win);
			}
		}
		
	}

}
