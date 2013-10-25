package wcmlab.towolf.heartstonewr.main;

import android.content.Context;
import android.widget.ListView;
import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;

public class MainView extends RatioRelativeLayout{

	public ListView mListView;
	public MainView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
	    mListView = new ListView(context);
		
	}

}
