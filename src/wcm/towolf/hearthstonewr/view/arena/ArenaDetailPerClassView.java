package wcm.towolf.hearthstonewr.view.arena;

import java.util.ArrayList;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;
import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.view.TopPanel;
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

public class ArenaDetailPerClassView extends RatioRelativeLayout{

	public TopPanel topPanel;
	public NewEventPanel newEventPanel;
	
	public ListView listView;
	
	RatioFixer mRf;
	Context mContext;
	
	public ArenaDetailPerClassView(Context context) {
		super(context);
		mRf  = this.getRatioFixer();
		mContext = context;
		
		TextView titleTextView = new TextView(context);
		titleTextView.setText("對各職業勝率總覽");
		titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 20.0f * mRf.getRatio());
		titleTextView.setTextColor(Color.parseColor("#F3E5AB"));
		titleTextView.setBackgroundResource(R.drawable.rect_label);
		titleTextView.setGravity(Gravity.CENTER);
		this.addView(titleTextView, 768, 200, 0, 0);
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
		list.add(new RowView(context, R.drawable.paladin, "1", "2", "3%"));
		list.add(new RowView(context, R.drawable.priest, "4", "5", "6%"));
		list.add(new RowView(context, R.drawable.warrior, "1", "2", "3%"));
		list.add(new RowView(context, R.drawable.warlock, "1", "2", "3%"));
		list.add(new RowView(context, R.drawable.hunter, "1", "2", "3%"));
		list.add(new RowView(context, R.drawable.mage, "1", "2", "3%"));
		list.add(new RowView(context, R.drawable.druid, "1", "2", "3%"));
		list.add(new RowView(context, R.drawable.shaman, "1", "2", "3%"));
		list.add(new RowView(context, R.drawable.rogue, "1", "2", "3%"));
		listView.setAdapter(new ArenaDetailListAdapter(list));
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
			
			// init convertView
			
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
			this(context, 0, "times", "rounds", "rate");
		}
		
		public RowView(Context context, int imageID, String times, String rounds, String rate) {
			super(context);
			
//			this.setLayoutParams(new LinearLayout.LayoutParams(mRf.getRealValue(768), mRf.getRealValue(103)));
//			LinearLayout.LayoutParams lp = (LayoutParams) this.getLayoutParams();
//			lp.height = mRf.getRealValue(103);
			
			classImageView = new ImageView(context);
			classImageView.setImageResource(imageID);
			classImageView.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, mRf.getRealValue(101), 1.0f));
			this.addView(classImageView);
			
			winTimes = new TextView(context);
			winTimes.setText(times);
			winTimes.setGravity(Gravity.CENTER);
			winTimes.setPadding(0, mRf.getRealValue(30), 0, 0);
			winTimes.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f));
			this.addView(winTimes);
			
			winRounds = new TextView(context);
			winRounds.setText(rounds);
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
