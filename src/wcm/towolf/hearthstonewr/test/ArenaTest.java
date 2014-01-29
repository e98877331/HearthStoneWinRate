package wcm.towolf.hearthstonewr.test;

import wcm.towolf.hearthstonewr.db.DBHelper;
import wcm.towolf.hearthstonewr.model.ArenaEventDataProvider;

public class ArenaTest {

	public static void addTestEvents() {

		ArenaEventDataProvider provider = new ArenaEventDataProvider();

		DBHelper.sharedInstance().getWritableDatabase().beginTransaction();
		for (int i = 0; i < 100; i++) {
			long eid = provider.addEvent(4);
			provider.addGame((int) eid, 5, true);
			provider.addGame((int) eid, 5, true);
			provider.addGame((int) eid, 5, true);
			provider.addGame((int) eid, 5, true);
			provider.addGame((int) eid, 5, true);
			provider.addGame((int) eid, 5, true);
			provider.addGame((int) eid, 5, true);
			provider.addGame((int) eid, 5, true);
			provider.addGame((int) eid, 5, true);
			provider.addGame((int) eid, 5, false);
			provider.addGame((int) eid, 5, false);
			provider.addGame((int) eid, 5, false);
		}
		DBHelper.sharedInstance().getWritableDatabase().setTransactionSuccessful();
		DBHelper.sharedInstance().getWritableDatabase().endTransaction();
	}

}
