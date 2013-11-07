package wcm.towolf.hearthstonewr.view;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.model.datatype.RoleType;
import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class HeroChooseView extends RelativeLayout{

	RatioFixer mRF;
	
	IconButton hunter;
	IconButton warrior;
	IconButton paladin;
	IconButton shaman;
	IconButton rogue;
	IconButton druid;
	IconButton warlock;
	IconButton priest;
	IconButton mage;
	
	Context mContext;
	
	
    ClickCallBack mCallback;

	public HeroChooseView(Context context,RatioFixer rf,ClickCallBack callBack) {
		super(context);
		// TODO Auto-generated constructor stub
		mCallback = callBack;
		mContext = context;
		mRF = rf;
		this.setBackgroundColor(Color.GRAY);
		this.setClickable(true);
		
		int w = 160;
		int h = 240;
//		
//		hunter = new IconButton(context, RoleType.HUNTER);
//		this.addView(hunter, rf.getLayoutParam(w, h, 0, 0));
//		
//		warrior = new IconButton(context, RoleType.WARRIOR);
//		this.addView(warrior, rf.getLayoutParam(w,h, 0, h));
//		
//		paladin = new IconButton(context, RoleType.PALADIN);
//		this.addView(paladin, rf.getLayoutParam(w,h, 0, 2*h));
//		
//		shaman = new IconButton(context, RoleType.SHAMAN);
//		this.addView(shaman, rf.getLayoutParam(w,h, w, 0));
//		
//		rogue = new IconButton(context, RoleType.ROGUE);
//		this.addView(rogue, rf.getLayoutParam(w,h, w, h));
//		
//		druid = new IconButton(context, RoleType.DRUID);
//		this.addView(druid, rf.getLayoutParam(w,h, w, 2*h));
//		
//		warlock = new IconButton(context, RoleType.WARLOCK);
//		this.addView(warlock, rf.getLayoutParam(w,h, 2*w, 0));
//		
//		priest = new IconButton(context, RoleType.PRIEST);
//		this.addView(priest, rf.getLayoutParam(w,h, 2*w, h));
//		
//		mage = new IconButton(context, RoleType.MAGE);
//		this.addView(mage, rf.getLayoutParam(w,h, 2*w, 2*h));
		hunter = new IconButton(mContext, RoleType.HUNTER);
		this.addView(hunter, mRF.getLayoutParam(w, h, 0, 0));
		
		warrior = new IconButton(mContext, RoleType.WARRIOR);
		this.addView(warrior, mRF.getLayoutParam(w,h, 0, h));
		
		paladin = new IconButton(mContext, RoleType.PALADIN);
		this.addView(paladin, mRF.getLayoutParam(w,h, 0, 2*h));
		
		shaman = new IconButton(mContext, RoleType.SHAMAN);
		this.addView(shaman, mRF.getLayoutParam(w,h, w, 0));
		
		rogue = new IconButton(mContext, RoleType.ROGUE);
		this.addView(rogue, mRF.getLayoutParam(w,h, w, h));
		
		druid = new IconButton(mContext, RoleType.DRUID);
		this.addView(druid, mRF.getLayoutParam(w,h, w, 2*h));
		
		warlock = new IconButton(mContext, RoleType.WARLOCK);
		this.addView(warlock, mRF.getLayoutParam(w,h, 2*w, 0));
		
		priest = new IconButton(mContext, RoleType.PRIEST);
		this.addView(priest, mRF.getLayoutParam(w,h, 2*w, h));
		
		mage = new IconButton(mContext, RoleType.MAGE);
		this.addView(mage, mRF.getLayoutParam(w,h, 2*w, 2*h));	
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
	    int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
	    int w = parentWidth /3;
	    int h = parentHeight /3;
//
//	    hunter.setLayoutParams(mRF.getLayoutParam(w, h, 0, 0));
//		
//	    warrior.setLayoutParams(mRF.getLayoutParam(w,h, 0, h));
//		
//		paladin.setLayoutParams(mRF.getLayoutParam(w,h, 0, 2*h));
//		
//		shaman.setLayoutParams(mRF.getLayoutParam(w,h, w, 0));
//		
//		rogue.setLayoutParams(mRF.getLayoutParam(w,h, w, h));
//		
//		druid.setLayoutParams(mRF.getLayoutParam(w,h, w, 2*h));
//		
//		warlock.setLayoutParams(mRF.getLayoutParam(w,h, 2*w, 0));
//
//		priest.setLayoutParams(mRF.getLayoutParam(w,h, 2*w, h));
//		
//		mage.setLayoutParams(mRF.getLayoutParam(w,h, 2*w, 2*h));
		
	}
	

	private class IconButton extends Button
	{

		int mRoleType;
		public IconButton(Context context, int roleType) {
			super(context);
			// TODO Auto-generated constructor stub
			mRoleType = roleType;
			this.setBackgroundResource(RoleType.getRoleRes(roleType));
			this.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if(event.getAction() ==MotionEvent.ACTION_DOWN)
						  v.getBackground().setColorFilter(new LightingColorFilter(0xFF999999, 0xFF000000));
					else if(event.getAction() ==MotionEvent.ACTION_UP)
						v.getBackground().clearColorFilter();
					return false;
				}
			});
			
			this.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				HeroChooseView.this.mCallback.run(mRoleType);	
				}
			});

		}
		
		
	}
	
   abstract public static class ClickCallBack
   {
	   abstract public void run(int roleType);
	   
   }
	
}
