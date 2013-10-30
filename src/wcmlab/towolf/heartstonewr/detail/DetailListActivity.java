package wcmlab.towolf.heartstonewr.detail;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class DetailListActivity extends ListActivity {
	ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private SimpleAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		for (int i = 0; i < mPlaces.length; i++) {
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("food", mFoods[i]);
			item.put("place", mPlaces[i]);
			list.add(item);
		}

		adapter = new SimpleAdapter(this, list,
				android.R.layout.simple_list_item_2, new String[] { "food",
						"place" }, new int[] { android.R.id.text1,
						android.R.id.text2 });

		setListAdapter(adapter);

		getListView().setTextFilterEnabled(true);
	}

	private static final String[] mPlaces = new String[] { "台北市", "新北市", "台南市",
			"高雄市", "苗粟縣", "台北市", "新北市", "台南市", "高雄市", "苗粟縣", "台北市", "新北市",
			"台南市", "高雄市", "苗粟縣", "台北市", "新北市", "台南市", "高雄市", "苗粟縣", "台北市",
			"新北市", "台南市", "高雄市", "苗粟縣", "台北市", "新北市", "789", "cde", "abc" };

	private static final String[] mFoods = new String[] { "大餅包小餅", "蚵仔煎",
			"東山鴨頭", "臭豆腐", "潤餅", "豆花", "青蛙下蛋", "豬血糕", "大腸包小腸", "鹹水雞", "烤香腸",
			"車輪餅", "珍珠奶茶", "鹹酥雞", "大熱狗", "炸雞排", "山豬肉", "花生冰", "剉冰", "水果冰",
			"包心粉圓", "排骨酥", "沙茶魷魚", "章魚燒", "度小月", "aaa", "abc", "bbb", "bcd",
			"123" };
}
