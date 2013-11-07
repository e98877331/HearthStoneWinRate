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
	
    ClickCallBack mCallback;

	public HeroChooseView(Context context,RatioFixer rf,ClickCallBack callBack) {
		super(context);
		// TODO Auto-generated constructor stub
		mCallback = callBack;
		this.setBackgroundColor(Color.GRAY);
		this.setClickable(true);
		
		hunter = new IconButton(context, RoleType.HUNTER);
		this.addView(hunter, rf.getLayoutParam(256, 410, 0, 0));
		
		warrior = new IconButton(context, RoleType.WARRIOR);
		this.addView(warrior, rf.getLayoutParam(256, 410, 0, 410));
		
		paladin = new IconButton(context, RoleType.PALADIN);
		this.addView(paladin, rf.getLayoutParam(256, 410, 0, 820));
		
		shaman = new IconButton(context, RoleType.SHAMAN);
		this.addView(shaman, rf.getLayoutParam(256, 410, 256, 0));
		
		rogue = new IconButton(context, RoleType.ROGUE);
		this.addView(rogue, rf.getLayoutParam(256, 410, 256, 410));
		
		druid = new IconButton(context, RoleType.DRUID);
		this.addView(druid, rf.getLayoutParam(256, 410, 256, 820));
		
		warlock = new IconButton(context, RoleType.WARLOCK);
		this.addView(warlock, rf.getLayoutParam(256, 410, 512, 0));
		
		priest = new IconButton(context, RoleType.PRIEST);
		this.addView(priest, rf.getLayoutParam(256, 410, 512, 410));
		
		mage = new IconButton(context, RoleType.MAGE);
		this.addView(mage, rf.getLayoutParam(256, 410, 512, 820));	
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
