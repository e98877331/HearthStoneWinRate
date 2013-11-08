package wcm.towolf.heartstonewr.main;

import wcm.towolf.hearthstonewr.R;
import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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
		

		mTestBtn = new Button(context);
		mTestBtn.setBackgroundResource(R.drawable.rect_btn);
		mTestBtn.setTextColor(Color.parseColor("#F3E5AB"));
		mTestBtn.setText(R.string.main_create_btn);
		mTestBtn.setTypeface(Typeface.SERIF);
		
		mTestBtn.setTextSize(getRatioFixer().getRealValue(30));
		this.addView(mTestBtn, 768, 200, 0, 0);
		
		
		mTestBtn1 = new Button(context);
		//this.addView(mTestBtn1, 150, 150, 200, 0);
		
		mListView = new ListView(context);
		mListView.setDivider(null);
		this.addView(mListView,768,1030,0,210);


	}

}
