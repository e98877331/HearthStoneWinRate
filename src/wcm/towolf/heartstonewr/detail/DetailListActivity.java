package wcm.towolf.heartstonewr.detail;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.model.datatype.RoleData;
import wcm.towolf.hearthstonewr.model.datatype.RoleEnemyData;
import wcm.towolf.hearthstonewr.model.datatype.RoleType;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class DetailListActivity extends Activity {
	ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private SimpleAdapter adapter;
	
	RoleData mRole;
	
	MyView mView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		for (int i = 0; i < mPlaces.length; i++) {
//			HashMap<String, String> item = new HashMap<String, String>();
//			item.put("food", mFoods[i]);
//			item.put("place", mPlaces[i]);
//			list.add(item);
//		}
		
		//
		mView = new MyView(this);
		mView.setToContentView(this);
		//
		
		mRole = RoleData.getPassingData();
		for (int i = 0; i < 9; i++) {
			HashMap<String, String> item = new HashMap<String, String>();
			mRole.getRoleEnemyData(i);
			item.put("type", RoleType.getRoleTypeString(i));
			
			RoleEnemyData red = mRole.getRoleEnemyData(i);
			StringBuilder sb = new StringBuilder();
			if (red.getWinRate() != -1) {
				sb.append(getResources().getString(R.string.detail_list_winrate) + ": " + getRateString(red.getWinRate()))
				.append(", " + getResources().getString(R.string.detail_list_win) + ": " + red.getWin())
				.append(", " + getResources().getString(R.string.detail_list_lose) + ": " + red.getLost());
			} else {
				sb.append(getResources().getString(R.string.detail_no_record));
			}
			item.put("result", sb.toString());
			
//			item.put("result", "win rate: " + mRole.getRoleEnemyData(i).getWinRate());
			list.add(item);
		}

		adapter = new SimpleAdapter(this, list,
				android.R.layout.simple_list_item_2, new String[] { "type",
						"result" }, new int[] { android.R.id.text1,
						android.R.id.text2 });

		
		mView.mListView.setAdapter(adapter);
//		setListAdapter(adapter);

//		getListView().setTextFilterEnabled(true);
		
	}
	
	private String getRateString(float f) {
		if (f == -1) {
			return getResources().getString(R.string.detail_no_record);
		} else {
			DecimalFormat fnum = new DecimalFormat("##0.00");
			String dd = fnum.format(f * 100);
			return dd + "%";
		}
	}
	
	public class MyView extends RatioRelativeLayout {
		TextView mTextView;
		ListView mListView;
		
		RatioFixer rf;

		public MyView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			
			rf = this.getRatioFixer();
			
			mTextView = new TextView(context);
			mTextView.setText(getResources().getString(R.string.detail_list_title));
			mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 20.0f * rf.getRatio());
			mTextView.setBackgroundColor(Color.parseColor("#D2B48C"));
			mTextView.setGravity(Gravity.CENTER);
			this.addView(mTextView, 768, 200, 0, 0);
			
//			mTextView = new TextView(context);
//			this.addView(mTextView, 768, 200, 0, 0);
			
			mListView = new ListView(context);
			mListView.setBackgroundColor(Color.parseColor("#FFE4B5"));
			this.addView(mListView, 768, 1030, 0, 200);
		}
		
	}
}
