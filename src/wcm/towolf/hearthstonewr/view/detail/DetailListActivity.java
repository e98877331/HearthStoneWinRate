package wcm.towolf.hearthstonewr.view.detail;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.model.datatype.RoleType;
import wcm.towolf.hearthstonewr.model.datatype.main.RoleData;
import wcm.towolf.hearthstonewr.model.datatype.main.RoleEnemyData;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DetailListActivity extends Activity {
	ArrayList<ListItem> list = new ArrayList<ListItem>();
	
	RoleData mRole;
	
	MyView mView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mView = new MyView(this);
		mView.setToContentView(this);
		
		mRole = RoleData.getPassingData();
		for (int i = 0; i < 9; i++) {
			mRole.getRoleEnemyData(i);
			
			RoleEnemyData red = mRole.getRoleEnemyData(i);
			StringBuilder sb = new StringBuilder();
			if (red.getWinRate() != -1) {
				sb.append(getResources().getString(R.string.detail_list_winrate) + ": " + getRateString(red.getWinRate()))
				.append(", " + getResources().getString(R.string.detail_list_win) + ": " + red.getWin())
				.append(", " + getResources().getString(R.string.detail_list_lose) + ": " + red.getLost());
			} else {
				sb.append(getResources().getString(R.string.detail_no_record));
			}
			
			
			list.add(new ListItem(getResources().getString(RoleType.getRoleTypeString(i)), sb.toString()));
		}


		mView.mListView.setAdapter(new DetailListAdapter(list));
		
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
		RelativeLayout topLayout;
		TextView mTextView;
		ListView mListView;
		
		RatioFixer rf;

		public MyView(Context context) {
			super(context);
			
			rf = this.getRatioFixer();
			
			this.setBackgroundResource(R.drawable.main_bg);
			
			topLayout = new RelativeLayout(context);
			topLayout.setBackgroundResource(R.drawable.main_list_item_bg);
			this.addView(topLayout, 768, 200, 0, 0);
			
			mTextView = new TextView(context);
			mTextView.setText(getResources().getString(R.string.detail_list_title));
			mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 20.0f * rf.getRatio());
			mTextView.setTextColor(Color.parseColor("#FFF8C6"));
			mTextView.setBackgroundResource(R.drawable.rect_label);
			mTextView.setGravity(Gravity.CENTER);
			topLayout.addView(mTextView);
			
			mListView = new ListView(context);
			mListView.setDivider(null);
			this.addView(mListView, 768, 1030, 0, 200);
		}
		
	}
	
	public class DetailListAdapter extends BaseAdapter {
		
		List<ListItem> list = new ArrayList<ListItem>();
		
		public DetailListAdapter(ArrayList<ListItem> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = new ListItemView(DetailListActivity.this);
			}
			
			((ListItemView) convertView).setData(list.get(position).title, list.get(position).rate);
			
			return convertView;
		}
		
	}
	
	public class ListItemView extends RelativeLayout {
		
		TextView classTextView;
		TextView rateTextView;
		
		RatioFixer rf;

		public ListItemView(Context context) {
			super(context);

			rf = mView.getRatioFixer();
			
			RelativeLayout relativeLayout = new RelativeLayout(context);
			relativeLayout.setBackgroundResource(R.drawable.main_list_item_bg);
			this.addView(relativeLayout, rf.getLayoutParam(768, 200, 0, 0));
			
			classTextView = new TextView(context);
			classTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 15.0f * rf.getRatio());
			classTextView.setTextColor(Color.parseColor("#FFF8C6"));
			relativeLayout.addView(classTextView, rf.getLayoutParam(768, 100, 0, 0));
			
			rateTextView = new TextView(context);
			rateTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 10.0f * rf.getRatio());
			rateTextView.setTextColor(Color.parseColor("#FFF8C6"));
			relativeLayout.addView(rateTextView, rf.getLayoutParam(768, 100, 0, 100));
		}
		
		public void setData(String title, String rate) {
			classTextView.setText(title);
			rateTextView.setText(rate);
		}
		
	}
	
	public class ListItem {
		
		String title;
		String rate;
		
		public ListItem(String title, String rate) {
			this.title = title;
			this.rate = rate;
		}
	}
}
