package wcm.towolf.heartstonewr.main;

import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;
import android.content.Context;
import android.widget.Button;
import android.widget.ListView;

public class MainView extends RatioRelativeLayout {

	// ArrayList<RoleData> mData;
	Context mContext;
	public ListView mListView;

	public Button mTestBtn, mTestBtn1;

	public MainView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		mListView = new ListView(context);
		this.addView(mListView);

		mTestBtn = new Button(context);
		this.addView(mTestBtn, 150, 150, 0, 0);
		
		mTestBtn1 = new Button(context);
		this.addView(mTestBtn1, 150, 150, 200, 0);

	}

}
