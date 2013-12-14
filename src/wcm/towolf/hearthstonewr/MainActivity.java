package wcm.towolf.hearthstonewr;

import java.util.ArrayList;
import java.util.Random;

import wcm.towolf.hearthstonewr.db.DBSchema;
import wcm.towolf.hearthstonewr.model.RoleDataProvider;
import wcm.towolf.hearthstonewr.model.datatype.RoleData;
import wcm.towolf.hearthstonewr.view.HeroChooseView.ClickCallBack;
import wcm.towolf.heartstonewr.main.CreateDeckView;
import wcm.towolf.heartstonewr.main.MainView;
import wcm.towolf.heartstonewr.main.MainViewListItem;
import wcm.towolf.heartstonewr.main.NewDeckButton;
import wcm.towolf.heartstonewr.main.TopPanel;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	final static String TAG = "MainActivity";

	MainView mView;
	ListView mListView;
	Button mTestBtn, mTestBtn1;
	TopPanel mTopPanel;

	DBSchema mDBSchema;
	// DBHelper mDBHelper;
	// RoleList mRoleList;
	MainAdapter mAdapter;
	ArrayList<RoleData> mData;

	RoleDataProvider mDataProvider;

	/*
	 * Override method
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
						| WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

		mDataProvider = new RoleDataProvider();

		mView = new MainView(this);
		mView.setBackgroundColor(Color.WHITE);
		// mTestBtn = mView.mTestBtn;

		mTopPanel = mView.mTopPanel;

		// setting main list
		mListView = mView.mListView;
		// mListView.setOnItemLongClickListener(listener);

		// for onLongClick delete item purpose
		registerForContextMenu(mListView);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				// TODO Auto-generated method stub

				RoleData.setPassingData(mAdapter.getItem(index));

				Intent i = new Intent(MainActivity.this, DetailActivity.class);
				// i.putExtra("RoleData",mAdapter.getItem(arg2));
				startActivity(i);

			}
		});

		// MainAdapter adapter = new MainAdapter(data);
		// mListView.setAdapter(adapter);

		// reloadListData();

		// setting test button

		mTestBtn1 = mView.mTestBtn1;
		mTestBtn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// delete Role
				// mDataProvider.deleteRole(mAdapter.getItem(0).roleID);

				// add game with random win or lose
				Random rd = new Random();
				RoleData data = mData.get(rd.nextInt(5));
				data.addGame(rd.nextInt(8), (rd.nextInt(2) > 0));
				// data.changeRoleName(data.roleName+"N");

				// delete role
				// mDataProvider.deleteRole(mAdapter.getItem(0).roleID);

				// get all role data info

				// deleteGame
				// Random rd = new Random();
				// RoleData data =mData.get(rd.nextInt(5));
				// data.deleteLastGame();
				//
				reloadListData();
			}
		});

		mView.setBackgroundResource(R.drawable.main_bg);
		mView.setToContentView(this);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		reloadListData();
		mTopPanel.update(mDataProvider.getAllRoleData());
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
			deleteRoleDataFromList(info.position);
			reloadListData();

		}

		// Toast.makeText(getApplicationContext(),String.format("Selected menuItemIndex %d for info %d",
		// menuItemIndex, info.position), Toast.LENGTH_LONG).show();
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * private method
	 */

	private void reloadListData() {
		mData = mDataProvider.getAllRoleData();

		if (mAdapter == null) {
			mAdapter = new MainAdapter();
			mListView.setAdapter(mAdapter);
		}
		mAdapter.notifyDataSetChanged();

	}

	private void deleteRoleDataFromList(int listPosition) {
		mDataProvider.deleteRole(mData.get(listPosition).roleID);

	}

	// to avoid quick click createDeckButton
	private static boolean isCdvOpen = false;

	private void showCreateDeckDialog() {
		if (isCdvOpen)
			return;
		isCdvOpen = true;

		final CreateDeckView cdv = new CreateDeckView(MainActivity.this,
				mView.getRatioFixer());

		final Dialog dialog = new Dialog(MainActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(cdv);
		dialog.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				// TODO Auto-generated method stub
				isCdvOpen = false;
			}
		});

		dialog.show();

		Window window = dialog.getWindow();

		window.setLayout(mView.getRatioFixer().getRealValue(710), mView
				.getRatioFixer().getRealValue(1200));

		cdv.setItemClickListener(new ClickCallBack() {

			@Override
			public void run(int roleType) {
				// TODO Auto-generated method stub
				// Log.e(TAG,Integer.toString(roleType));

				mDataProvider.addRole(cdv.getDeckName(), roleType);

				reloadListData();
				dialog.dismiss();

			}
		});

	}

	/*
	 * MainAdapter
	 */

	public class MainAdapter extends BaseAdapter {
		// ArrayList<RoleData> mData;
		public MainAdapter() {

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size() + 1;
		}

		// @Override
		public RoleData getItem(int arg0) {
			// TODO Auto-generated method stub
			if (arg0 == mData.size())
				return null;
			else
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

			// case of showing createDeckButton
			if (index == mData.size()) {
				// convertView = mView.mTestBtn;
				NewDeckButton btn = new NewDeckButton(MainActivity.this,
						mView.getRatioFixer());
				btn.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						showCreateDeckDialog();
					}
				});
				RelativeLayout rl = new RelativeLayout(MainActivity.this);
				rl.addView(btn,
						mView.getRatioFixer().getLayoutParam(768, 180, 0, 0));
				convertView = rl;

			} else { // case of showing deck item
				if (convertView == null
						|| !(convertView instanceof MainViewListItem)) {
					convertView = new MainViewListItem(MainActivity.this,
							mView.getRatioFixer());
				}

				RoleData rd = mData.get(index);

				((MainViewListItem) convertView).setData(rd.getRoleRes(),
						rd.roleName, rd.getWinRate());

			}
			return convertView;
		}

	}
}
