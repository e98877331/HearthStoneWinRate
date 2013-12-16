package wcm.towolf.hearthstonewr.db;

import java.util.ArrayList;

import wcm.towolf.hearthstonewr.model.datatype.RoleData;
import wcm.towolf.hearthstonewr.model.datatype.arena.ArenaEventData;
import android.database.Cursor;

public class DBTBArenaEventList extends DBTableBase{
	public static String TABLE = "Arena_Event_List";
	public static String fieldNames[] = {"Event_ID","Role_Type_Num"};
	public static String fieldTypes[] ={"INTEGER PRIMARY KEY ASC AUTOINCREMENT","INTEGER"};
	
	
	public DBTBArenaEventList()
	{
		super();
	}
	
	
	public void addEvent(int roleType)
	{
		String fields[] = {"Role_Type_Num"};
		String values[] = {Integer.toString(roleType)};
		mDBHelper.insert(TABLE, fields, values);
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
		mDBHelper.delete(TABLE, "Role_ID = ?", value);
	}
	
}
