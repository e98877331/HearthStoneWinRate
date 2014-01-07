package wcm.towolf.hearthstonewr.view;

import itri.u9lab.towolf.ratiofixer.RatioFixer;

import java.text.DecimalFormat;
import java.util.ArrayList;

import wcm.towolf.hearthstonewr.R;
import wcm.towolf.hearthstonewr.model.datatype.IDataEntitiy;
import wcm.towolf.hearthstonewr.model.datatype.RoleData;
import wcm.towolf.hearthstonewr.model.datatype.arena.ArenaEventData;
import android.content.Context;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TopPanel extends RelativeLayout {

	TextView mWin, mLose, mTotal, mWinRate;

	int win = 0, total = 0;
	float winRate;

	public TopPanel(Context context, RatioFixer rf) {
		super(context);
		// TODO Auto-generated constructor stub
		final int lbh = 90, lbw = 192;
		this.addView(new LabelText(context, R.string.detail_text_win),
				rf.getLayoutParam(lbw, lbh, 0, 0));
		this.addView(new LabelText(context, R.string.detail_text_lose),
				rf.getLayoutParam(lbw, lbh, lbw, 0));
		this.addView(new LabelText(context, R.string.detail_text_total),
				rf.getLayoutParam(lbw, lbh, 2 * lbw, 0));
		this.addView(new LabelText(context, R.string.detail_list_winrate),
				rf.getLayoutParam(lbw, lbh, 3 * lbw, 0));

		mWin = new LabelText(context);
		mLose = new LabelText(context);
		mTotal = new LabelText(context);
		mWinRate = new LabelText(context);

		this.addView(mWin, rf.getLayoutParam(lbw, lbh, 0, lbh));
		this.addView(mLose, rf.getLayoutParam(lbw, lbh, lbw, lbh));
		this.addView(mTotal, rf.getLayoutParam(lbw, lbh, 2 * lbw, lbh));
		this.addView(mWinRate, rf.getLayoutParam(lbw, lbh, 3 * lbw, lbh));

		this.setBackgroundResource(R.drawable.main_top_panel_bg);

	}

	public void update(ArrayList<? extends IDataEntitiy> data) {
		int total = 0, win = 0;

		for (int i = 0; i < data.size(); i++) {
			IDataEntitiy rd = data.get(i);
			total += rd.getTotalGame();
			win += rd.getWin();

		}

		mWin.setText(Integer.toString(win));
		mLose.setText(Integer.toString(total - win));
		mTotal.setText(Integer.toString(total));
		float winRate = getWinRate(win, total);
		if (winRate != -1) {
			// mWinRate.setBackgroundResource(R.drawable.circle_label);
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			mWinRate.setText(df.format(winRate * 100) + "%");
		}

	}
//	public void updateArenaData(ArrayList<ArenaEventData> data) {
//		int total = 0, win = 0;
//
//		for (int i = 0; i < data.size(); i++) {
//			ArenaEventData rd = data.get(i);
//			total += rd.getTotalGame();
//			win += rd.getWin();
//
//		}
//
//		mWin.setText(Integer.toString(win));
//		mLose.setText(Integer.toString(total - win));
//		mTotal.setText(Integer.toString(total));
//		float winRate = getWinRate(win, total);
//		if (winRate != -1) {
//			// mWinRate.setBackgroundResource(R.drawable.circle_label);
//			DecimalFormat df = new DecimalFormat();
//			df.setMaximumFractionDigits(2);
//			mWinRate.setText(df.format(winRate * 100) + "%");
//		}
//
//	}
	

	public float getWinRate(int win, int total) {
		return (total == 0) ? -1 : ((float) win / total);
	}

	class LabelText extends TextView {

		public LabelText(Context context) {
			super(context);
			// TODO Auto-generated constructor stub

			this.setGravity(Gravity.CENTER);
			this.setText("0");
			// this.setBackgroundResource(R.drawable.rect_label);
		}

		public LabelText(Context context, int sid) {
			super(context);
			// TODO Auto-generated constructor stub

			this.setGravity(Gravity.CENTER);
			this.setText(sid);
			// this.setBackgroundResource(R.drawable.rect_label);
		}

	}
}
