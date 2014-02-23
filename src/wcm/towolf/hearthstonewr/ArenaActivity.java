package wcm.towolf.hearthstonewr;

import java.util.ArrayList;

import wcm.towolf.hearthstonewr.gahelper.GAHelper;
import wcm.towolf.hearthstonewr.model.ArenaEventDataProvider;
import wcm.towolf.hearthstonewr.model.datatype.RoleType;
import wcm.towolf.hearthstonewr.model.datatype.arena.ArenaEventData;
import wcm.towolf.hearthstonewr.view.HeroChooseDialog;
import wcm.towolf.hearthstonewr.view.HeroChooseView.ClickCallBack;
import wcm.towolf.hearthstonewr.view.TopPanel;
import wcm.towolf.hearthstonewr.view.arena.ArenaView;
import wcm.towolf.hearthstonewr.view.arena.ArenaViewListItem;
import wcm.towolf.hearthstonewr.view.arena.NewEventPanel;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

public class ArenaActivity extends Activity {
	ArenaView mView;

	TopPanel mTopPanel;
	NewEventPanel mNewEventPanel;
	ListView mListView;
	MainAdapter mAdapter;

	ArrayList<ArenaEventData> mData;

	ArenaEventDataProvider mProvider;

	HeroChooseDialog mHCD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		/*
		 * final ArenaEventDataProvider provider = new ArenaEventDataProvider();
		 * 
		 * final Random rd = new Random();
		 * 
		 * LinearLayout rl = new LinearLayout(this);
		 * 
		 * Button testBtn = new Button(this); testBtn.setText("testbun");
		 * testBtn.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View arg0) { // TODO Auto-generated
		 * method stub
		 * 
		 * provider.addEvent(rd.nextInt(7)); provider.addEvent(rd.nextInt(7));
		 * provider.addEvent(rd.nextInt(7)); provider.addEvent(rd.nextInt(7));
		 * 
		 * } });
		 * 
		 * Button testBtn2 = new Button(this); testBtn2.setText("testbtn2");
		 * testBtn2.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub provider.addGame(rd.nextInt(4), rd.nextInt(7),
		 * rd.nextBoolean()); } });
		 * 
		 * Button testBtn3 = new Button(this); testBtn3.setText("testbtn3");
		 * testBtn3.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub ArrayList<ArenaEventData> data = provider
		 * .getAllArenaEventData(); int a = 5; a = a + 1; } });
		 * 
		 * rl.addView(testBtn); rl.addView(testBtn2); rl.addView(testBtn3); //
		 */

		mProvider = new ArenaEventDataProvider();

		mView = new ArenaView(this);

		mTopPanel = mView.topPanel;
		mTopPanel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ArenaActivity.this,
						ArenaDetailActivity.class);
				// i.putExtra("RoleData",mAdapter.getItem(arg2));
				startActivity(i);

			}
		});

		mListView = mView.listView;
		// mListView.setAdapter(new MainAdapter());
		reloadListData();

		setNewEventPanel();

		// for onLongClick delete item purpose
		registerForContextMenu(mListView);

		mView.setToContentView(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		EasyTracker.getInstance(this).activityStart(this);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);

		// AdapterView.AdapterContextMenuInfo info =
		// (AdapterView.AdapterContextMenuInfo)menuInfo;

		menu.setHeaderTitle(R.string.main_context_menu_title);
		// for (int i = 0; i<5; i++) {
		menu.add(Menu.NONE, 0, 0, R.string.main_context_menu_delete_btn);
		// }
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		int menuItemIndex = item.getItemId();
		// String[] menuItems = getResources().getStringArray(R.array.menu);
		// String menuItemName = menuItems[menuItemIndex];
		// String listItemName = "listitem Name";//Countries[info.position];
		if (menuItemIndex == 0) {

			mData.get(mData.size() - info.position - 1).delete();
			// deleteRoleDataFromList(info.position);
			reloadListData();

		}

		// Toast.makeText(getApplicationContext(),String.format("Selected menuItemIndex %d for info %d",
		// menuItemIndex, info.position), Toast.LENGTH_LONG).show();
		return true;
	}

	private void setNewEventPanel() {

		mHCD = new HeroChooseDialog(ArenaActivity.this, mView.getRatioFixer());
		mHCD.setInnerTitle(R.string.arena_which_hero);
		mHCD.setItemClickListener(new ClickCallBack() {

			@Override
			public void run(int roleType) {
				// TODO Auto-generated method stub
				//Log an Arena event starts
				GAHelper.event(ArenaActivity.this, "ui_action", "event_start_button_press",RoleType.getDebugString(roleType, ArenaActivity.this), (long) roleType);
				
				
				long eventID = mProvider.addEvent(roleType);
				ArenaEventData aed = mProvider.getEvent((int) eventID);
				mNewEventPanel.setData(aed);
				reloadListData();
				mHCD.dismiss();

				// this only set start event btn to invisible
				mNewEventPanel.startEvent();

			}
		});

		mNewEventPanel = mView.newEventPanel;
		mNewEventPanel.setStartEventOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!mHCD.isShowing()) {
				
					mHCD.showAndAdjustWindow();
				}
			}
		});

		mNewEventPanel.setWinClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//log on arena game win
				
				int roleType = mNewEventPanel.getCurrentEvent().roleType;
				GAHelper.event(ArenaActivity.this, "ui_action", "event_win_button_press",RoleType.getDebugString(roleType, ArenaActivity.this), 0);
				mNewEventPanel.win();
				// reloadListData();
			}
		});

		mNewEventPanel.setLoseClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int roleType = mNewEventPanel.getCurrentEvent().roleType;
				GAHelper.event(ArenaActivity.this, "ui_action", "event_lose_button_press",RoleType.getDebugString(roleType, ArenaActivity.this), 0);
				
				mNewEventPanel.lose();
				// reloadListData();
			}
		});

		initNewEventPanel();

	}

	private void initNewEventPanel() {
		// no any data yet
		if (mData.size() < 1)
			return;

		ArenaEventData lastData = mData.get(mData.size() - 1);

		if (!lastData.isFinished()) {
			mNewEventPanel.startEvent();
			mNewEventPanel.setData(lastData);
		}

	}

	public void reloadListData() {

		// long tt = System.currentTimeMillis();

		mData = mProvider.getAllArenaEventData();
		mTopPanel.update(mData);
		if (mAdapter == null) {
			mAdapter = new MainAdapter();
			mListView.setAdapter(mAdapter);
		}
		mAdapter.notifyDataSetChanged();

		// Log.e("ArenaActivity",
		// "CYYRUNTimeReload: "+Long.toString(System.currentTimeMillis() - tt));
	}

//	private void GALog(int roleType) {
//
//		EasyTracker easyTracker = EasyTracker.getInstance(this);
//
//		// MapBuilder.createEvent().build() returns a Map of event fields and
//		// values
//		// that are set and sent with the hit.
//		easyTracker.send(MapBuilder.createEvent("ui_action", // Event category
//																// (required)
//				"button_press", // Event action (required)
//				"event_start_button", // Event label
//				1l) // Event value
//				.build());
//
//	}

	public class MainAdapter extends BaseAdapter {
		// ArrayList<RoleData> mData;
		public MainAdapter() {

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		// @Override
		public ArenaEventData getItem(int arg0) {
			// TODO Auto-generated method stub

			return mData.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int index, View convertView, ViewGroup arg2) {
			// TODO Auto-generated method stub

			if (convertView == null
					|| !(convertView instanceof ArenaViewListItem)) {
				convertView = new ArenaViewListItem(ArenaActivity.this,
						mView.getRatioFixer());
			}

			ArenaEventData rd = mData.get(mData.size() - index - 1);

			// Log.e("ArenaActivity","Event ID = "+Integer.toString(rd.eventID));

			((ArenaViewListItem) convertView).setData(rd);
			if (rd.isFinished())
				convertView.setAlpha(1f);
			else {
				// not to show the view
				View view = new View(ArenaActivity.this);
				return view;
			}
			// convertView.setAlpha(0.5f);
			// convertView.setVisibility(View.GONE);

			return convertView;
		}

	}

}
