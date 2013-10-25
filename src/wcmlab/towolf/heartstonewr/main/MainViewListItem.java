package wcmlab.towolf.heartstonewr.main;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import android.content.Context;
import android.widget.RelativeLayout;

public class MainViewListItem extends RelativeLayout{

	RatioFixer mRF;
	
	//ImageView mIcon 
	public MainViewListItem(Context context,RatioFixer rf) {
		super(context);
		// TODO Auto-generated constructor stub
		mRF = rf;
		
	}

}
