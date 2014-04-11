package wcm.towolf.hearthstonewr.view.worlddata;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;

import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.model.datatype.RoleType;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils.TruncateAt;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class WorldDataDetailPerClassView extends RatioRelativeLayout{
	public ListView listView;
	
	RatioFixer mRf;
	Context mContext;
	
	public WorldDataDetailPerClassView(Context context, int roletype, int[] win, int[] total) {
		super(context);
		mRf  = this.getRatioFixer();
		mContext = context;
		
		RelativeLayout relativeLayout = new RelativeLayout(context);
		relativeLayout.setBackgroundResource(R.drawable.rect_label);
		this.addView(relativeLayout, 768, 200, 0, 0);
		
		TextView titleTextView = new TextView(context);
		titleTextView.setText(getResources().getString(R.string.arena_vs_each_hero));
		titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 20.0f * mRf.getRatio());
		titleTextView.setTextColor(Color.parseColor("#F3E5AB"));
//		titleTextView.setBackgroundResource(R.drawable.rect_label);
//		titleTextView.setBackgroundColor(Color.BLUE);
		titleTextView.setGravity(Gravity.CENTER);
		RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(mRf.getRealValue(568), mRf.getRealValue(200));
		titleParams.setMargins(mRf.getRealValue(140), 0, 0, 0);
		relativeLayout.addView(titleTextView, titleParams);
		titleTextView.setEllipsize(TruncateAt.MARQUEE);
		titleTextView.setSelected(true);
		titleTextView.setSingleLine(true);
        int wpadding = mRf.getRealValue(25);
        titleTextView.setPadding(wpadding, 0, wpadding, 0);
		
		listView = new ListView(context);
		listView.setBackgroundResource(R.drawable.main_bg);
		this.addView(listView,768,1030,0,200);
		
		ArrayList<RowView> list = new ArrayList<RowView>();
		list.add(new RowView(context));
		
		if (win.length == total.length) {
			for (int i = 0; i < 9; i++) {
				list.add(new RowView(context, i, win[i], total[i]));
			}
		}
		listView.setAdapter(new ArenaDetailListAdapter(list));

		ImageView classImageView = new ImageView(context);
		classImageView.setBackgroundResource(RoleType.getRoleRes(roletype));
		RelativeLayout.LayoutParams classParams = new RelativeLayout.LayoutParams(mRf.getRealValue(120), mRf.getRealValue(180));
		classParams.setMargins(mRf.getRealValue(10), mRf.getRealValue(10), 0, 0);
		relativeLayout.addView(classImageView, classParams);
	}
	
	class ArenaDetailListAdapter extends BaseAdapter {
		
		ArrayList<?> list;
		
		public ArenaDetailListAdapter(ArrayList<?> list) {
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
			RowView view;
			if (convertView == null) {
				view = (RowView) list.get(position);
			} else {
				view = (RowView) convertView;
			}
			
			return view;
		}

		@Override
		public boolean isEnabled(int position) {
			if (position == 0)
				return false;
			
			return super.isEnabled(position);
		}
		
	}

	class RowView extends LinearLayout {
		
		RelativeLayout relativeLayout;
		
		ImageView classImageView;
		TextView winTimes;
		TextView winRounds;
		TextView winRate;

		public RowView(Context context) {
			super(context);
			
			classImageView = new ImageView(context);
//			classImageView.setImageResource(imageID);
			classImageView.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, mRf.getRealValue(101), 1.0f));
			this.addView(classImageView);
			
			winTimes = new TextView(context);
			winTimes.setText(getResources().getString(R.string.arena_text_win));
			winTimes.setGravity(Gravity.CENTER);
			winTimes.setPadding(0, mRf.getRealValue(30), 0, 0);
			winTimes.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f));
			this.addView(winTimes);
			
			winRounds = new TextView(context);
			winRounds.setText(getResources().getString(R.string.arena_text_total));
			winRounds.setGravity(Gravity.CENTER);
			winRounds.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f));
			this.addView(winRounds);
			
			winRate = new TextView(context);
			winRate.setText(getResources().getString(R.string.arena_text_rate));
			winRate.setGravity(Gravity.CENTER);
			winRate.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f));
			this.addView(winRate);
		}
		
		public RowView(Context context, int i, int win, int total) {
			super(context);
						
			int imageID = RoleType.getRoleRes(i);
			int times = win;
			int rounds = total;
	    	String rate = "";
	    	if (total == 0) {
	    		rate = "--";
	    	} else {
				float f = (float) win / total;
				DecimalFormat fnum = new DecimalFormat("##0.00");
				rate = fnum.format(f * 100) + "%";
	    	}
			
			classImageView = new ImageView(context);
			classImageView.setImageResource(imageID);
			classImageView.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, mRf.getRealValue(101), 1.0f));
			this.addView(classImageView);
			
			winTimes = new TextView(context);
			winTimes.setText(Integer.toString(times));
			winTimes.setGravity(Gravity.CENTER);
			winTimes.setPadding(0, mRf.getRealValue(30), 0, 0);
			winTimes.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f));
			this.addView(winTimes);
			
			winRounds = new TextView(context);
			winRounds.setText(Integer.toString(rounds));
			winRounds.setGravity(Gravity.CENTER);
			winRounds.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f));
			this.addView(winRounds);
			
			winRate = new TextView(context);
			winRate.setText(rate);
			winRate.setGravity(Gravity.CENTER);
			winRate.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f));
			this.addView(winRate);
		}
		
	}
	
}
