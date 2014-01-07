package wcm.towolf.hearthstonewr.view.arena;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;
import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.view.TopPanel;
import android.content.Context;
import android.widget.ListView;

public class ArenaView extends RatioRelativeLayout{

	public TopPanel topPanel;
	public NewEventPanel newEventPanel;
	
	public ListView listView;
	
	RatioFixer mRf;
	
	public ArenaView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mRf  = this.getRatioFixer();
		
		topPanel = new TopPanel(context,mRf);
	    topPanel.setBackgroundResource(R.drawable.arena_top_panel_bg);
		
		this.addView(topPanel,768, 190, 0, 0);
		
		newEventPanel = new NewEventPanel(context,mRf);
		this.addView(newEventPanel, 768, 200, 0, 190);
		
		listView = new ListView(context);
		this.addView(listView,768,840,0,390);
		
	}

	
	
}
