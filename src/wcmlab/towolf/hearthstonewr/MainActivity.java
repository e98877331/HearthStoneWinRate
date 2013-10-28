package wcmlab.towolf.hearthstonewr;

import java.util.ArrayList;
import java.util.Random;

import wcm.towolf.hearthstonewr.db.DBHelper;
import wcm.towolf.hearthstonewr.db.DBSchema;
import wcm.towolf.hearthstonewr.db.RoleList;
import wcm.towolf.hearthstonewr.model.RoleDataProvider;
import wcmlab.towolf.hearthstonewr.model.datatype.RoleData;
import wcmlab.towolf.hearthstonewr.model.datatype.RoleType;
import wcmlab.towolf.heartstonewr.main.MainView;
import wcmlab.towolf.heartstonewr.main.MainViewListItem;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

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
		
		mDataProvider = new RoleDataProvider();
		//mDBSchema = new DBSchema(this.getApplicationContext());
		//mRoleList =  new RoleList(DBHelper.sharedInstance());
		
		//ArrayList<RoleData> data = new ArrayList<RoleData>();
		
//		data.add(new RoleData(RoleType.WARRIOR,"chang david",55));
//		data.add(new RoleData(RoleType.WARRIOR,"chang david",55));
//		data.add(new RoleData(RoleType.WARRIOR,"chang david",55));
//		data.add(new RoleData(RoleType.WARRIOR,"chang david",55));
//		data.add(new RoleData(RoleType.WARRIOR,"chang david",55));
//		data.add(new RoleData(RoleType.WARRIOR,"chang david",55));
//		data.add(new RoleData(RoleType.WARRIOR,"chang david",55));
		
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
		
       reloadListData();		
		
		//setting test button
		mTestBtn = mView.mTestBtn;
		mTestBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//		     Intent intent = new Intent(MainActivity.this,DetailActivity.class);
//		     startActivity(intent);
				Random r=new Random();
				int i1=r.nextInt(4);
				String name;
				switch(i1%4)
				{
				
				case 0:
					name = "first" + i1%4;
					break;
				case 1:
					name = "second" + i1%4;
					break;
				case 2:
					name ="third" + i1%4;
					break;
				case 3:
					name="fourth" + i1%4;
					break;
					default:
						name ="default" + i1%4;
				}
					
				//mRoleList.addRole(new RoleData(RoleType.WARRIOR,name));
		
				mDataProvider.addRole(name,RoleType.WARRIOR); 
				
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
				mDataProvider.addGame(mAdapter.getItem(rd.nextInt(5)).roleID, RoleType.HUNTER, (rd.nextInt(2)>0));
				
				//delete role
//				mDataProvider.deleteRole(mAdapter.getItem(0).roleID);
				
				//get all role data info
			
				
				reloadListData();
			}
		});
		
		mView.setToContentView(this);
		
		
		
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		
			    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
			    
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
			
			((MainViewListItem)convertView).setData(rd.getRoleRes(RoleType.HUNTER), rd.roleName, rd.winRate);
			
			
			return convertView;
		}
		
	}
}
