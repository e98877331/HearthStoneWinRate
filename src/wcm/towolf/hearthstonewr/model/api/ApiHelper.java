package wcm.towolf.hearthstonewr.model.api;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONArray;
import org.json.JSONObject;

import wcm.towolf.hearthstonewr.model.bigdatalogger.ArenaBigDataLogger;
import wcm.towolf.hearthstonewr.model.bigdatalogger.WorldHeroData;

public class ApiHelper {

	private static HttpClient mClient = null;
	
	public static void sendArenaLog(ArenaBigDataLogger.Game game)
	{
		

		//建立post request
		HttpPost httppost = new HttpPost("http://hearthstonewr.appspot.com/logArena");

		ArrayList<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("roleType", Integer.toString(game.roleType)));
		nameValuePairs.add(new BasicNameValuePair("vsRoleType", Integer.toString(game.vsRoleType)));
		nameValuePairs.add(new BasicNameValuePair("isWin", game.isWin? "true":"false"));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = ApiHelper.sharedClient().execute(httppost);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//執行
	}
	
	
	public static  ArrayList<WorldHeroData> getWorldArenaData()
	{
		//建立post request

		HttpGet httpget = new HttpGet("http://hearthstonewr.appspot.com/logArena");
		
		HttpResponse response;
		
		ArrayList<WorldHeroData> retList = new ArrayList<WorldHeroData>();
		try {
			response = ApiHelper.sharedClient().execute(httpget);
			
			
			BasicResponseHandler handler = new BasicResponseHandler();
			String responseString = handler.handleResponse(response);
			JSONArray json = new JSONArray(responseString);
			for(int i =0 ;i< 9;i++)
			{
				//WorldHeroData w = new WorldHeroData();
				JSONObject jo = (JSONObject)json.get(i);
				int roleType = jo.getInt("roleType");
				JSONArray totalList = jo.getJSONArray("vsTotalCountList");
				JSONArray winList = jo.getJSONArray("vsWinCountList");
				int [] totalListArr = new int[totalList.length()];
				int [] winListArr = new int[winList.length()];
				for(int j = 0; j< winList.length(); j++)
				{
					totalListArr[j] = totalList.getInt(j);
					winListArr[j] = winList.getInt(j);
				}
				retList.add(new WorldHeroData(roleType, totalListArr, winListArr));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return retList;
	}
	
	public static HttpClient sharedClient()
	{
		if(mClient == null)
		{
			mClient = new DefaultHttpClient();
			mClient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		}
		
		return mClient;
	}
	
}
