package wcm.towolf.hearthstonewr;

import java.util.ArrayList;
import java.util.Random;

import wcm.towolf.hearthstonewr.db.DBSchema;
import wcm.towolf.hearthstonewr.model.RoleDataProvider;
import wcm.towolf.hearthstonewr.model.datatype.RoleData;
import wcm.towolf.hearthstonewr.view.HeroChooseView;
import wcm.towolf.hearthstonewr.view.HeroChooseView.ClickCallBack;
import wcm.towolf.heartstonewr.main.MainView;
import wcm.towolf.heartstonewr.main.MainViewListItem;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

	final static String TAG = "MainActivity";
	
	MainView mView;
	ListView mListView;
	Button mTestBtn,mTestBtn1;
	
	DBSchema mDBSchema;
	//DBHelper mDBHelper;
	//RoleList mRoleList;
	MainAdapter mAdapter;
	ArrayList<RoleData> mData;
	
	RoleDataProvider mDataProvider;
	
/*
 * Override method
 * */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		
		mDataProvider = new RoleDataProvider();

		
		mView = new MainView(this);
		mView.setBackgroundColor(Color.WHITE);
		   
		//setting main list
		mListView = mView.mListView;
		//mListView.setOnItemLongClickListener(listener);
		registerForContextMenu(mListView);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				// TODO Auto-generated method stub
				
				RoleData.setPassingData(mAdapter.getItem(index));
				
				Intent i = new Intent(MainActivity.this, DetailActivity.class);
				//i.putExtra("RoleData",mAdapter.getItem(arg2));
				startActivity(i);
				
			}
		});
		
//		MainAdapter adapter = new MainAdapter(data);
//		mListView.setAdapter(adapter);
		
      // reloadListData();		
		
		//setting test button
		mTestBtn = mView.mTestBtn;
		
		mTestBtn.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() ==MotionEvent.ACTION_DOWN)
					  v.getBackground().setColorFilter(new LightingColorFilter(0xFF999999, 0xFF000000));
				else if(event.getAction() ==MotionEvent.ACTION_UP)
					v.getBackground().clearColorFilter();
				return false;
			}
		});
		mTestBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	
//				Random r=new Random();
//				int i1=r.nextInt(4);
//				String name;
//				switch(i1%4)
//				{
//				
//				case 0:
//					name = "first" + i1%4;
//					break;
//				case 1:
//					name = "second" + i1%4;
//					break;
//				case 2:
//					name ="third" + i1%4;
//					break;
//				case 3:
//					name="fourth" + i1%4;
//					break;
//					default:
//						name ="default" + i1%4;
//				}
//					
//				//mRoleList.addRole(new RoleData(RoleType.WARRIOR,name));
//		        Random rd = new Random();
//		        
//				mDataProvider.addRole(name,rd.nextInt(8));

				final HeroChooseView addView = new HeroChooseView(MainActivity.this, mView.getRatioFixer(),new ClickCallBack() {
					
					@Override
					public void run(int roleType) {
						// TODO Auto-generated method stub
						Log.e(TAG,Integer.toString(roleType));
					}
				});
				
				final Dialog dialog = new Dialog(MainActivity.this);
				dialog.setTitle("Create Deck");
				dialog.setContentView(addView);
				dialog.show();

				
				
                reloadListData();
				
			}
		});
		
		mTestBtn1 = mView.mTestBtn1;
		mTestBtn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//delete Role
				//mDataProvider.deleteRole(mAdapter.getItem(0).roleID);
				
				//add game with random win or lose
				Random rd = new Random();
				RoleData data =mData.get(rd.nextInt(5));
				data.addGame(rd.nextInt(8), (rd.nextInt(2)>0));
				//data.changeRoleName(data.roleName+"N");
				
				
				
				//delete role
//				mDataProvider.deleteRole(mAdapter.getItem(0).roleID);
				
				//get all role data info
	
				//deleteGame
//				Random rd = new Random();
//				RoleData data =mData.get(rd.nextInt(5));
//				data.deleteLastGame();
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
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		
			//    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
			    
			    menu.setHeaderTitle(R.string.main_context_menu_title);
			//    for (int i = 0; i<5; i++) {
			      menu.add(Menu.NONE, 0, 0, R.string.main_context_menu_delete_btn);
			 //   }
	}
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	  AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	  int menuItemIndex = item.getItemId();
//	  String[] menuItems = getResources().getStringArray(R.array.menu);
//	  String menuItemName = menuItems[menuItemIndex];
//	  String listItemName = "listitem Name";//Countries[info.position];
	  if(menuItemIndex == 0)
	  {
		  deleteRoleDataFromList(info.position);
		  reloadListData();
		  
	  }

	//  Toast.makeText(getApplicationContext(),String.format("Selected menuItemIndex %d for info %d", menuItemIndex, info.position), Toast.LENGTH_LONG).show();
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
	
	private void reloadListData()
	{
		mData = mDataProvider.getAllRoleData();
		
		if(mAdapter == null)
		{
	    mAdapter = new MainAdapter();
	    mListView.setAdapter(mAdapter);
		}
		mAdapter.notifyDataSetChanged();
			
		
	}
	
	private void deleteRoleDataFromList(int listPosition)
	{
		mDataProvider.deleteRole(mData.get(listPosition).roleID);
		
	}
	
	public class MainAdapter extends BaseAdapter
	{
		//ArrayList<RoleData> mData;
        public MainAdapter()
        {
        	
        	
        }
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		//@Override
		public RoleData getItem(int arg0) {
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
			
			
			if(convertView == null)
			{
				convertView = new MainViewListItem(MainActivity.this, mView.getRatioFixer());
			}
			
			RoleData rd = mData.get(index);
			
			((MainViewListItem)convertView).setData(rd.getRoleRes(), rd.roleName, rd.getWinRate());
			
			
			return convertView;
		}
		
	}
}
