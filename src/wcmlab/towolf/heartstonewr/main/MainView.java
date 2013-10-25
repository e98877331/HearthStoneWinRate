package wcmlab.towolf.heartstonewr.main;

import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;
import android.content.Context;
import android.widget.Button;
import android.widget.ListView;



public class MainView extends RatioRelativeLayout{

    //ArrayList<RoleData> mData;
    Context mContext;
	public ListView mListView;
	
	public Button mTestBtn;
	
	
	public MainView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
	    mListView = new ListView(context);
	    this.addView(mListView);
	    
	    mTestBtn = new Button(context);
	    this.addView(mTestBtn,100,100,0,0);
		
	}
	
	
	


}
