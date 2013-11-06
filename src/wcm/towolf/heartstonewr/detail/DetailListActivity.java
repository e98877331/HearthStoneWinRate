package wcm.towolf.heartstonewr.detail;

import java.util.ArrayList;
import java.util.HashMap;

import wcm.towolf.hearthstonewr.model.datatype.RoleData;
import wcm.towolf.hearthstonewr.model.datatype.RoleEnemyData;
import wcm.towolf.hearthstonewr.model.datatype.RoleType;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleAdapter;

public class DetailListActivity extends ListActivity {
	ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private SimpleAdapter adapter;
	
	RoleData mRole;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		for (int i = 0; i < mPlaces.length; i++) {
//			HashMap<String, String> item = new HashMap<String, String>();
//			item.put("food", mFoods[i]);
//			item.put("place", mPlaces[i]);
//			list.add(item);
//		}
		
		mRole = RoleData.getPassingData();
		for (int i = 0; i < 9; i++) {
			HashMap<String, String> item = new HashMap<String, String>();
			mRole.getRoleEnemyData(i);
			item.put("type", RoleType.getRoleTypeString(i));
			item.put("result", "win rate: " + mRole.getRoleEnemyData(i).getWinRate());
			list.add(item);
		}

		adapter = new SimpleAdapter(this, list,
				android.R.layout.simple_list_item_2, new String[] { "type",
						"result" }, new int[] { android.R.id.text1,
						android.R.id.text2 });

		setListAdapter(adapter);

		getListView().setTextFilterEnabled(true);
		
	}
}
