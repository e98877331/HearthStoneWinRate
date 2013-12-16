package wcm.towolf.hearthstonewr.db;

public class DBTableBase {
	
	protected DBHelper mDBHelper;
	public DBTableBase()
	{
		mDBHelper = DBHelper.sharedInstance();
	}

}
