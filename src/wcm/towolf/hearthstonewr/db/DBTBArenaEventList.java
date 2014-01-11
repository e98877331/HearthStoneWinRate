package wcm.towolf.hearthstonewr.db;

import java.util.ArrayList;

import wcm.towolf.hearthstonewr.model.datatype.RoleData;
import wcm.towolf.hearthstonewr.model.datatype.RoleGame;
import wcm.towolf.hearthstonewr.model.datatype.arena.ArenaEventData;
import android.database.Cursor;

public class DBTBArenaEventList extends DBTableBase{
	public static String TABLE = "Arena_Event_List";
	public static String fieldNames[] = {"Event_ID","Role_Type_Num","Add_Date"};
	public static String fieldTypes[] ={"INTEGER PRIMARY KEY ASC AUTOINCREMENT","INTEGER","timestamp DATE DEFAULT (datetime('now','localtime'))"};
	
	
	public DBTBArenaEventList()
	{
		super();
	}
	
	
	public long addEvent(int roleType)
	{
		String fields[] = {"Role_Type_Num"};
		String values[] = {Integer.toString(roleType)};
		return mDBHelper.insert(TABLE, fields, values);
	}
	
    public ArenaEventData getEvent(int eventId)
    {
		String queryArg[] = {Integer.toString(eventId)};
		Cursor c =mDBHelper.rawQuery("select * from "+TABLE+" where Event_ID = ?", queryArg);
		
		ArenaEventData rg;
		
		c.moveToNext();
		rg = new ArenaEventData(c.getInt(0),c.getInt(1));
		return rg;
		
    }
	
	public ArrayList<ArenaEventData> getAllEvents()
	{
		ArrayList<ArenaEventData> retData = new ArrayList<ArenaEventData>();
		
		String fields[] = {"Event_ID","Role_Type_Num"};
		
		Cursor c = mDBHelper.select(TABLE, fields, null, null, null, null, null);
		while(c.moveToNext())
		{
			
			
			ArenaEventData rd = new ArenaEventData(c.getInt(0),c.getInt(1));
			retData.add(rd);
		}
		
		return retData;
	}
	
	public void changeRoleType(int pEventID,int pRoleType)
	{
		String fields[] = {"Role_Type_Num"};
		String values[] = {Integer.toString(pRoleType)};
		String whereValue[] = {Integer.toString(pEventID)}; 
		mDBHelper.update(TABLE, fields, values, "Role_ID = ?", whereValue);
		//mDBHelper.update(TABLE, {"Role_Name"}, {pName}, "Role_ID = ?", {pRoleID});
	}
	
	public void deleteEvent(int pEventID )
	{
		String value[] = {Integer.toString(pEventID)};
		mDBHelper.delete(TABLE, "Event_ID = ?", value);
	}
	
}
