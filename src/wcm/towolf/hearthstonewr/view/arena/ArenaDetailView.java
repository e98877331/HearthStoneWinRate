package wcm.towolf.hearthstonewr.view.arena;

import itri.u9lab.towolf.ratiofixer.RatioFixer;
import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;

import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.model.ArenaEventDataProvider;
import wcm.towolf.hearthstonewr.model.datatype.RoleType;
import wcm.towolf.hearthstonewr.model.datatype.arena.ArenaHeroDetailData;
import wcm.towolf.hearthstonewr.model.datatype.arena.ArenaHeroDetailData.ArenaVSHeroData;
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

public class ArenaDetailView extends RatioRelativeLayout{

	public TopPanel topPanel;
	public NewEventPanel newEventPanel;
	
	public ListView listView;
	
	RatioFixer mRf;
	Context mContext;
	
	public ArrayList<RowView> rowViews = new ArrayList<RowView>();
	
	public ArenaDetailView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mRf  = this.getRatioFixer();
		mContext = context;
		
//		topPanel = new TopPanel(context,mRf);
//	    topPanel.setBackgroundResource(R.drawable.rect_label);
//		this.addView(topPanel,768, 200, 0, 0);
		
//		newEventPanel = new NewEventPanel(context,mRf);
//		this.addView(newEventPanel, 768, 220, 0, 190);
		
		TextView titleTextView = new TextView(context);
		titleTextView.setText(getResources().getString(R.string.arena_match_detail));
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
		
//		ArrayList<RowView> list = new ArrayList<RowView>();
		rowViews.add(new RowView(context));
		
		ArenaEventDataProvider provider = new ArenaEventDataProvider();
		ArrayList<ArenaHeroDetailData> herodata =  provider.getAllArenaHeroData();
	    for(ArenaHeroDetailData hero: herodata)
	    {	    	
	    	RowView rowView = new RowView(context, hero);
			rowViews.add(rowView);;
	    	
			// getVsHeroList()
//	    	for(ArenaHeroDetailData.ArenaVSHeroData vsd : hero.getVsHeroList())
//	    	{
//	    	   Log.e(TAG,"vs:" + RoleType.getDebugString(vsd.getVSRoleType(),this)+ " Win: " + vsd.getWins()+" Total:"+ vsd.getTotal());
//	    	}
	    }
//		Log.e(TAG,"Run time:" +Long.toString(System.currentTimeMillis() - tt));
		



//		list.add(new RowView(context, R.drawable.priest, "4", "5", "6%"));
//		list.add(new RowView(context, R.drawable.warrior, "1", "2", "3%"));
//		list.add(new RowView(context, R.drawable.warlock, "1", "2", "3%"));
//		list.add(new RowView(context, R.drawable.hunter, "1", "2", "3%"));
//		list.add(new RowView(context, R.drawable.mage, "1", "2", "3%"));
//		list.add(new RowView(context, R.drawable.druid, "1", "2", "3%"));
//		list.add(new RowView(context, R.drawable.shaman, "1", "2", "3%"));
//		list.add(new RowView(context, R.drawable.rogue, "1", "2", "3%"));
		listView.setAdapter(new ArenaDetailListAdapter(rowViews));
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

	public class RowView extends LinearLayout {
		
		RelativeLayout relativeLayout;
		
		ImageView classImageView;
		TextView winTimes;
		TextView winRounds;
		TextView winRate;
		ImageView arrowImageView;
		
//		ArenaHeroDetailData hero;
		public ArrayList<ArenaVSHeroData> arenaVSHeroDatas;

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
			
			arrowImageView = new ImageView(context);
//			arrowImageView.setImageResource(R.drawable.arena_arrow);
			TableLayout.LayoutParams lp = new TableLayout.LayoutParams(mRf.getRealValue(30), mRf.getRealValue(30), 1.0f);
			lp.topMargin = mRf.getRealValue(40);
			arrowImageView.setLayoutParams(lp);
			this.addView(arrowImageView);
		}
		
		public RowView(Context context, ArenaHeroDetailData hero) {
			super(context);
			
			arenaVSHeroDatas = hero.getVsHeroList();
			
			int imageID = RoleType.getRoleRes(hero.getRoleType());
			int times = hero.getWins();
			int rounds = hero.getTotal();
	    	String rate = "";
	    	if (hero.getTotal() == 0) {
	    		rate = "--";
	    	} else {
				float f = (float) hero.getWins() / hero.getTotal();
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
			
			arrowImageView = new ImageView(context);
			arrowImageView.setImageResource(R.drawable.arena_arrow);
			TableLayout.LayoutParams lp = new TableLayout.LayoutParams(mRf.getRealValue(30), mRf.getRealValue(30), 1.0f);
			lp.topMargin = mRf.getRealValue(40);
			arrowImageView.setLayoutParams(lp);
			this.addView(arrowImageView);
		}
		
	}
	

	
}
