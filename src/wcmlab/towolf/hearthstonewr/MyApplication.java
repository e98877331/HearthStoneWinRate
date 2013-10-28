package wcmlab.towolf.hearthstonewr;

import wcm.towolf.hearthstonewr.db.DBHelper;
import android.app.Application;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		
		DBHelper.init(getApplicationContext());
	}
	
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		DBHelper.closeDB();
		
	}
}
