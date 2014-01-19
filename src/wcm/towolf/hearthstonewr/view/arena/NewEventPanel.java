package wcm.towolf.hearthstonewr.view.arena;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import wcm.towolf.hearthstonewr.ArenaActivity;
import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.model.datatype.RoleType;
import wcm.towolf.hearthstonewr.model.datatype.arena.ArenaEventData;
import wcm.towolf.hearthstonewr.util.BasicClickEffect;
import wcm.towolf.hearthstonewr.view.HeroChooseDialog;
import wcm.towolf.hearthstonewr.view.HeroChooseView.ClickCallBack;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewEventPanel extends RelativeLayout{

	//768*220
	RatioFixer mRf;
	Context mContext;
	
	ArenaEventData currentEvent;
	
	ImageView roleView;
	
	TextView title;
	
	WinLoseBtn winView, loseView;
	Button undoBtn;
	
	Button startEventBtn;
	
	 HeroChooseDialog mWinHCD,mLoseHCD;
	 
	public NewEventPanel(Context context,RatioFixer rf) {
		super(context);
		// TODO Auto-generated constructor stub
		mRf = rf; 
		mContext = context;
		this.setBackgroundResource(R.drawable.main_list_item_bg);
		
		title = new TextView(context);
		title.setText(R.string.arena_newevent_now_playing);
		title.setTextColor(Color.BLACK);
		title.setBackgroundResource(R.drawable.rect_leather_label);
		title.setGravity(Gravity.CENTER);
		this.addView(title,rf.getLayoutParam(400, 60, 200, 15));
		
		
		roleView = new ImageView(context);
		this.addView(roleView,rf.getLayoutParam(130, 190, 10, 15));
		roleView.setImageResource(R.drawable.mage);
		
		
		winView = new WinLoseBtn(context,true);
		winView.setBackgroundResource(R.drawable.arena_new_win);
		this.addView(winView,mRf.getLayoutParam(250, 120, 150, 80));
		winView.setValue(5);
		
		loseView = new WinLoseBtn(context,false);
		loseView.setBackgroundResource(R.drawable.arena_new_lose);
		this.addView(loseView,mRf.getLayoutParam(250, 120, 420, 80));
		loseView.setValue(3);
		
		
		undoBtn = new Button(context);
		undoBtn.setBackgroundResource(R.drawable.arena_undo);
		undoBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(currentEvent.count == 0)
				{
					currentEvent.delete();
					currentEvent = null;
					endEvent();
					return;
				}
				
				currentEvent.deleteLastGame();
				setData(currentEvent);
				((ArenaActivity)mContext).reloadListData();
				
			}
		});
		
		this.addView(undoBtn,mRf.getLayoutParam(80, 80, 670, 105));
		
		
		startEventBtn = new Button(context);
		startEventBtn.setBackgroundResource(R.drawable.rect_label);
		startEventBtn.setText(R.string.arena_newevent_new_event);
		startEventBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			 startEvent();	
			}
		});
		BasicClickEffect.setClickEffect(startEventBtn);
		
		this.addView(startEventBtn,mRf.getLayoutParam(768,220,0,0));
		
		initWinLoseHCD();
	}
	
	public void startEvent()
	{
		startEventBtn.setVisibility(GONE);
	}
	
	public void endEvent()
	{
		currentEvent = null;
		startEventBtn.setVisibility(VISIBLE);
		
	}
	
	public void win()
	{
		

		if(!mWinHCD.isShowing())
			mWinHCD.showAndAdjustWindow();
	}
	public void lose()
	{
		

		if(!mLoseHCD.isShowing())
			mLoseHCD.showAndAdjustWindow();
	}
	
	public void setStartEventOnClickListener(OnClickListener listener)
	{
		startEventBtn.setOnClickListener(listener);
	}
	
	public void setWinClickListener(OnClickListener l)
	{
		winView.setOnClickListener(l);
	}
	
	public void setLoseClickListener(OnClickListener l)
	{
		loseView.setOnClickListener(l);
	}
	
	
	public void setData(ArenaEventData data)
	{
		currentEvent = data;
		this.setData(data.roleType,data.win,data.count-data.win);
	}
	
	public void setData(int roleType,int win,int lose)
	{
		roleView.setImageResource(RoleType.getRoleRes(roleType));
		winView.setValue(win);
		loseView.setValue(lose);
	}
	
	
/*
 * private method
 */
	public void initWinLoseHCD()
	{
		mWinHCD = new HeroChooseDialog(mContext, mRf);
		mWinHCD.setInnerTitle(R.string.detail_box_win);
		mWinHCD.setItemClickListener(new ClickCallBack() {
			
			@Override
			public void run(int roleType) {
				// TODO Auto-generated method stub
			   currentEvent.addGame(roleType, true);
			   setData(currentEvent);
			   
			   if(currentEvent.win ==12)
			   {
				   endEvent();
			   }
			   
			   ((ArenaActivity)mContext).reloadListData();
			   mWinHCD.dismiss();
			}
		});
		
		mLoseHCD = new HeroChooseDialog(mContext, mRf);
		mLoseHCD.setInnerTitle(R.string.detail_box_lose);
		mLoseHCD.setItemClickListener(new ClickCallBack() {
			
			@Override
			public void run(int roleType) {
				// TODO Auto-generated method stub
			   currentEvent.addGame(roleType, false);
			   setData(currentEvent);
			   
			   if((currentEvent.count-currentEvent.win) == 3)
			   {   endEvent();
			   }
			   
			   ((ArenaActivity)mContext).reloadListData();
			   mLoseHCD.dismiss();
			}
		});
	}
	
	/*
	 * custom button class
	 */
	
	public class WinLoseBtn extends RelativeLayout
	{
        //300*180
        	
		TextView label;
		TextView valueView;
		public WinLoseBtn(Context context,boolean isWinBtn) {
			super(context);
		//	this.setBackgroundColor(Color.BLUE);
			label = new TextView(context);
		    label.setGravity(Gravity.CENTER);
		    label.setTextSize(TypedValue.COMPLEX_UNIT_PX,mRf.getRealValue(25));
			if(isWinBtn)
				label.setText(R.string.common_win);
			else
				label.setText(R.string.common_lose);
			
			this.addView(label,mRf.getLayoutParam(240, 35, 5, 0));
			
			valueView = new TextView(context);
		//valueView.setBackgroundColor(Color.RED);
			valueView.setGravity(Gravity.CENTER);
			valueView.setTextSize(TypedValue.COMPLEX_UNIT_PX,mRf.getRealValue(35));
			
			this.addView(valueView,mRf.getLayoutParam(240, 90, 5, 5));
			
			
			// TODO Auto-generated constructor stub
		}
		
		public void setValue(int i)
		{
			valueView.setText(Integer.toString(i));
		}
		
	}

}
