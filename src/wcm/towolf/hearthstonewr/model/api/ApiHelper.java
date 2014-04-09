package wcm.towolf.hearthstonewr.model.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;

import wcm.towolf.hearthstonewr.model.bigdatalogger.ArenaBigDataLogger;

public class ApiHelper {

	public static void sendArenaLog(ArenaBigDataLogger.Game game)
	{
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		//建立post request
		HttpPost httppost = new HttpPost("http://hearthstonewr.appspot.com/logArena");

		List nameValuePairs = new ArrayList(3);
		nameValuePairs.add(new BasicNameValuePair("roleType", Integer.toString(game.roleType)));
		nameValuePairs.add(new BasicNameValuePair("vsRoleType", Integer.toString(game.vsRoleType)));
		nameValuePairs.add(new BasicNameValuePair("isWin", game.isWin? "true":"false"));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//執行
	}
	
}
