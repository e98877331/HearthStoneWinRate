package wcmlab.towolf.hearthstonewr;

import java.util.ArrayList;

import wcmlab.towolf.hearthstonewr.model.datatype.RoleData;
import wcmlab.towolf.hearthstonewr.model.datatype.RoleType;
import wcmlab.towolf.heartstonewr.main.MainView;
import wcmlab.towolf.heartstonewr.main.MainViewListItem;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

	MainView mView;
	ListView mListView;
	Button mTestBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		ArrayList<RoleData> data = new ArrayList<RoleData>();
		data.add(new RoleData(RoleType.worrior,"chang david",55));
		data.add(new RoleData(RoleType.worrior,"chang david",55));
		data.add(new RoleData(RoleType.worrior,"chang david",55));
		data.add(new RoleData(RoleType.worrior,"chang david",55));
		data.add(new RoleData(RoleType.worrior,"chang david",55));
		data.add(new RoleData(RoleType.worrior,"chang david",55));
		data.add(new RoleData(RoleType.worrior,"chang david",55));
		
		mView = new MainView(this);
		mView.setBackgroundColor(Color.WHITE);
		  
		//setting main list
		mListView = mView.mListView;
		MainAdapter adapter = new MainAdapter(data);
		mListView.setAdapter(adapter);
		
		//setting test button
		mTestBtn = mView.mTestBtn;
		mTestBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		     Intent intent = new Intent(MainActivity.this,DetailActivity.class);
		     startActivity(intent);
		     
			}
		});
		
		
		mView.setToContentView(this);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	
	public class MainAdapter extends BaseAdapter
	{
		ArrayList<RoleData> mData;
        public MainAdapter(ArrayList<RoleData> pData)
        {
        	mData = pData;
        	
        }
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		@Override
		public Object getItem(int arg0) {
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
			
			((MainViewListItem)convertView).setData(rd.getRoleRes(RoleType.worrior), rd.roleName, rd.winRate);
			
			
			return convertView;
		}
		
	}
}
