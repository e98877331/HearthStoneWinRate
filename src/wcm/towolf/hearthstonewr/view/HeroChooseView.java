package wcm.towolf.hearthstonewr.view;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import wcm.towolf.hearthstonewr.model.datatype.RoleType;
import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
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

	public HeroChooseView(Context context,RatioFixer rf) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		mRF = rf;
		//this.setBackgroundColor(Color.GRAY);
		this.setClickable(true);
		
		int w = 200;
		int h = 300;
		
		int s = 15;
//		
		hunter = new IconButton(mContext, RoleType.HUNTER);
		this.addView(hunter, mRF.getLayoutParam(w, h, 0, 0));
		
		warrior = new IconButton(mContext, RoleType.WARRIOR);
		this.addView(warrior, mRF.getLayoutParam(w,h, 0, h+s));
		
		paladin = new IconButton(mContext, RoleType.PALADIN);
		this.addView(paladin, mRF.getLayoutParam(w,h, 0, 2*(h+s)));
		
		shaman = new IconButton(mContext, RoleType.SHAMAN);
		this.addView(shaman, mRF.getLayoutParam(w,h, w+s, 0));
		
		rogue = new IconButton(mContext, RoleType.ROGUE);
		this.addView(rogue, mRF.getLayoutParam(w,h, w+s, h+s));
		
		druid = new IconButton(mContext, RoleType.DRUID);
		this.addView(druid, mRF.getLayoutParam(w,h, w+s, 2*(h+s)));
		
		warlock = new IconButton(mContext, RoleType.WARLOCK);
		this.addView(warlock, mRF.getLayoutParam(w,h, 2*(w+s), 0));
		
		priest = new IconButton(mContext, RoleType.PRIEST);
		this.addView(priest, mRF.getLayoutParam(w,h, 2*(w+s), h+s));
		
		mage = new IconButton(mContext, RoleType.MAGE);
		this.addView(mage, mRF.getLayoutParam(w,h, 2*(w+s), 2*(h+s)));	
	}



	public HeroChooseView(Context context,RatioFixer rf,ClickCallBack callBack) {
		this(context, rf);
	 setOnClickItemCallBack(callBack);
	
	}
	
	public void setOnClickItemCallBack(ClickCallBack callBack)
	{
		this.mCallback = callBack;
	}
	
//	@Override
//	protected void onSizeChanged(int nw, int nh, int oldw, int oldh) {
//		// TODO Auto-generated method stub
//		super.onSizeChanged(nw, nh, oldw, oldh);
//		
//	    int w = nw /3;
//	    int h = nh /3;
////
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
//		
//		this.post(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//			     requestLayout();
//			}
//		});
//				
//	}
	

//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		// TODO Auto-generated method stub
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//	    int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
//	    int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
//	    int w = parentWidth /3;
//	    int h = parentHeight /3;
//////
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
//		
//		this.post(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//			     requestLayout();
//			}
//		});
//	}
	

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
				if(HeroChooseView.this.mCallback != null)
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
