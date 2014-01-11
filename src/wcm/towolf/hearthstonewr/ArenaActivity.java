package wcm.towolf.hearthstonewr;

import java.util.ArrayList;
import java.util.Random;

import wcm.towolf.hearthstonewr.model.ArenaEventDataProvider;
import wcm.towolf.hearthstonewr.model.datatype.arena.ArenaEventData;
import wcm.towolf.hearthstonewr.view.HeroChooseDialog;
import wcm.towolf.hearthstonewr.view.HeroChooseView.ClickCallBack;
import wcm.towolf.hearthstonewr.view.TopPanel;
import wcm.towolf.hearthstonewr.view.arena.ArenaView;
import wcm.towolf.hearthstonewr.view.arena.ArenaViewListItem;
import wcm.towolf.hearthstonewr.view.arena.NewEventPanel;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ArenaActivity extends Activity {
	ArenaView mView;

	TopPanel mTopPanel;
	NewEventPanel mNewEventPanel;
    ListView mListView;
    MainAdapter mAdapter;
    
    ArrayList<ArenaEventData> mData;
	
    
	
	ArenaEventDataProvider mProvider;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		final ArenaEventDataProvider provider = new ArenaEventDataProvider();

		final Random rd = new Random();

		LinearLayout rl = new LinearLayout(this);

		Button testBtn = new Button(this);
		testBtn.setText("testbun");
		testBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				provider.addEvent(rd.nextInt(7));
				provider.addEvent(rd.nextInt(7));
				provider.addEvent(rd.nextInt(7));
				provider.addEvent(rd.nextInt(7));

			}
		});

		Button testBtn2 = new Button(this);
		testBtn2.setText("testbtn2");
		testBtn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				provider.addGame(rd.nextInt(4), rd.nextInt(7), rd.nextBoolean());
			}
		});

		Button testBtn3 = new Button(this);
		testBtn3.setText("testbtn3");
		testBtn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ArrayList<ArenaEventData> data = provider
						.getAllArenaEventData();
				int a = 5;
				a = a + 1;
			}
		});

		rl.addView(testBtn);
		rl.addView(testBtn2);
		rl.addView(testBtn3);
		// /////////////

		mProvider = new ArenaEventDataProvider();
		
		mData = mProvider.getAllArenaEventData();
		
		mView = new ArenaView(this);

		mTopPanel = mView.topPanel;

		setNewEventPanel();
		
		mListView = mView.listView;
        //mListView.setAdapter(new MainAdapter());
		reloadListData();
		
		
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
			
			mData.get(mData.size() - info.position -1).delete();
			//deleteRoleDataFromList(info.position);
			reloadListData();

		}

		// Toast.makeText(getApplicationContext(),String.format("Selected menuItemIndex %d for info %d",
		// menuItemIndex, info.position), Toast.LENGTH_LONG).show();
		return true;
	}
	
	private void setNewEventPanel() {
		mNewEventPanel = mView.newEventPanel;
		mNewEventPanel.setStartEventOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				final HeroChooseDialog hcd = new HeroChooseDialog(ArenaActivity.this,
						mView.getRatioFixer());
				hcd.setInnerTitle("What's your hero");
				hcd.setItemClickListener(new ClickCallBack() {

					@Override
					public void run(int roleType) {
						// TODO Auto-generated method stub
						//this only set start event btn to invisible
						mNewEventPanel.startEvent();
						
                      long eventID =  mProvider.addEvent(roleType);
                      ArenaEventData aed = mProvider.getEvent((int)eventID);
                      mNewEventPanel.setData(aed);                      
                      reloadListData();
                      hcd.dismiss();
                      
					}
				});
				if(!hcd.isShowing());
				hcd.showAndAdjustWindow();
			}
		});
		
		mNewEventPanel.setWinClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mNewEventPanel.win();
				//reloadListData();
			}
		});
		
		mNewEventPanel.setLoseClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mNewEventPanel.lose();
				//reloadListData();
			}
		});
		
		initNewEventPanel(mData.get(mData.size()-1));
	
	}
	
	private void initNewEventPanel(ArenaEventData lastData)
	{
		if(!lastData.isFinished())
		{
			mNewEventPanel.startEvent();
			mNewEventPanel.setData(lastData);
		}
		
	}
	
	public  void reloadListData() {
		mData = mProvider.getAllArenaEventData();
   mTopPanel.update(mData);
		if (mAdapter == null) {
			mAdapter = new MainAdapter();
			mListView.setAdapter(mAdapter);
		}
		mAdapter.notifyDataSetChanged();

	}
	
	
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


				if (convertView == null || !(convertView instanceof ArenaViewListItem)) {
					convertView = new ArenaViewListItem(ArenaActivity.this,
							mView.getRatioFixer());
				}

			     ArenaEventData rd = mData.get(mData.size() - index -1);

			    Log.e("ArenaActivity","Event ID = "+Integer.toString(rd.eventID));
			     
				((ArenaViewListItem) convertView).setData(rd);
				if(rd.isFinished())
					convertView.setAlpha(1f);
				else
				{
					//not to show the view
					View view = new View(ArenaActivity.this);
					return view;
				}
//					convertView.setAlpha(0.5f);
					//convertView.setVisibility(View.GONE);

			
			return convertView;
		}

	}
	
}
