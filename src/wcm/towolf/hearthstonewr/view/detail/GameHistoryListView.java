package wcm.towolf.hearthstonewr.view.detail;

import itri.u9lab.towolf.ratiofixer.RatioFixer;

import java.util.ArrayList;

import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.db.DBHelper;
import wcm.towolf.hearthstonewr.db.DBTBRoleGames;
import wcm.towolf.hearthstonewr.model.datatype.AddDateRoleGame;
import wcm.towolf.hearthstonewr.model.datatype.RoleGame;
import wcm.towolf.hearthstonewr.model.datatype.RoleType;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameHistoryListView extends ListView {

	Context mContext;
	public GameHistoryListView(Context context,int roleID) {
		super(context);
		mContext = context;
		// TODO Auto-generated constructor stub
		this.setBackgroundResource(R.drawable.detail_bg);
		this.setAdapter(new DetailListAdapter(roleID));
		
	}
	private String ts(int i)
	{
		return Integer.toString(i);
	}
	public class DetailListAdapter extends BaseAdapter {
		ArrayList<AddDateRoleGame> list;
		
		
//		List<ListItem> list = new ArrayList<ListItem>();
		
		public DetailListAdapter(int roleID) {
			DBTBRoleGames dbtable = new DBTBRoleGames(DBHelper.sharedInstance());
			this.list = dbtable.getAllGameForRoleID(roleID);
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
				convertView = new ListItemView(mContext);
			}
			AddDateRoleGame rg = list.get(position);
			((ListItemView) convertView).setData(rg);
			
			return convertView;
		}
		
	}

	
	public class ListItemView extends RelativeLayout {
		
		
		
		
		ImageView heroIcon;
		TextView gameResultView;
		TextView addDateView;
		
		RatioFixer rf;

		public ListItemView(Context context) {
			super(context);

			rf = RatioFixer.getGlobalRatioFixer();
			
			RelativeLayout relativeLayout = new RelativeLayout(context);
			relativeLayout.setBackgroundResource(R.drawable.main_list_item_bg);
			this.addView(relativeLayout, rf.getLayoutParam(768, 200, 0, 0));
			
			heroIcon = new ImageView(context);
			relativeLayout.addView(heroIcon,rf.getLayoutParam(120, 180, 30, 10));

			gameResultView = new TextView(context);
			gameResultView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 10.0f * rf.getRatio());
			gameResultView.setTextColor(Color.parseColor("#FFF8C6"));
			gameResultView.setGravity(Gravity.CENTER);
			relativeLayout.addView(gameResultView, rf.getLayoutParam(120, 120, 190, 40));
			
			addDateView = new TextView(context);
			addDateView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 4.45f * 9.0f * rf.getRatio());
			addDateView.setTextColor(Color.parseColor("#FFF8C6"));
			addDateView.setGravity(Gravity.CENTER);
			relativeLayout.addView(addDateView, rf.getLayoutParam(330, 120, 300, 40));
		}
		
		public void setData(AddDateRoleGame rg) {
			heroIcon.setImageResource(RoleType.getRoleRes(rg.getEnemyRoleType()));
			gameResultView.setText(rg.getIsWin() ? "Win":"Lose");
			addDateView.setText(rg.getAddDate());
			
		}
		
	}

	
}
