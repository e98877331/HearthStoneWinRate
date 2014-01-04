package wcm.towolf.hearthstonewr.view.arena;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.model.datatype.RoleType;
import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.opengl.Visibility;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewEventPanel extends RelativeLayout{

	//768*200
	RatioFixer mRf;
	
	ImageView roleView;
	WinLoseBtn winView, loseView;
	Button undoBtn;
	
	Button startEventBtn;
	public NewEventPanel(Context context,RatioFixer rf) {
		super(context);
		// TODO Auto-generated constructor stub
		mRf = rf; 
		
		
		roleView = new ImageView(context);
		this.addView(roleView,rf.getLayoutParam(130, 190, 10, 5));
		roleView.setImageResource(R.drawable.mage);
		
		
		winView = new WinLoseBtn(context);
		this.addView(winView,mRf.getLayoutParam(250, 160, 150, 20));
		winView.setValue(5);
		
		loseView = new WinLoseBtn(context);
		this.addView(loseView,mRf.getLayoutParam(250, 160, 420, 20));
		loseView.setValue(3);
		
		
		undoBtn = new Button(context);
		this.addView(undoBtn,mRf.getLayoutParam(100, 100, 680, 50));
		
		
		startEventBtn = new Button(context);
		startEventBtn.setBackgroundResource(R.drawable.rect_btn);
		startEventBtn.setText("New Event");
		startEventBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			 startEvent();	
			}
		});
		this.addView(startEventBtn,mRf.getLayoutParam(768,200,0,0));
		
	}
	
	public void startEvent()
	{
		startEventBtn.setVisibility(GONE);
	}
	
	public void endEvent()
	{
		startEventBtn.setVisibility(VISIBLE);
	}
	
	public void setStartEventOnClickListener(OnClickListener listener)
	{
		startEventBtn.setOnClickListener(listener);
	}
	
	
	public void setData(int roleType,int win,int lose)
	{
		roleView.setImageResource(RoleType.getRoleRes(roleType));
		winView.setValue(win);
		loseView.setValue(lose);
	}
	
	public class WinLoseBtn extends RelativeLayout
	{
        //300*180
        	
		TextView valueView;
		public WinLoseBtn(Context context) {
			super(context);
			this.setBackgroundColor(Color.BLUE);
			valueView = new TextView(context);
			valueView.setBackgroundColor(Color.RED);
			valueView.setGravity(Gravity.CENTER);
			
			this.addView(valueView,mRf.getLayoutParam(240, 100, 5, 25));
			
			
			// TODO Auto-generated constructor stub
		}
		
		public void setValue(int i)
		{
			valueView.setText(Integer.toString(i));
		}
		
	}

}
