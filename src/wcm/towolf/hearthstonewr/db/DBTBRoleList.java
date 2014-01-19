package wcm.towolf.hearthstonewr.db;

import java.sql.SQLException;
import java.util.ArrayList;

import wcm.towolf.hearthstonewr.model.datatype.RoleData;
import android.database.Cursor;

public class DBTBRoleList {

	public static String TABLE = "Hero_List";
	//Position is added after db version 4
	public static String fieldNames[] = {"Role_ID","Role_Name","Role_Type_Num","Position"};
	public static String fieldTypes[] ={"INTEGER PRIMARY KEY ASC AUTOINCREMENT","VARCHAR(64)","INTEGER","INTEGER"};
	
	private DBHelper mDBHelper;
	
	public DBTBRoleList(DBHelper pDBHelper)
	{
		//mDBHelper = pDBHelper;
		
		mDBHelper = DBHelper.sharedInstance();
	}
	
	
	public void addRole(String roleName, int roleType)
	{

		
		String sqlString = "SELECT MAX(Position) From " + TABLE;
		Cursor c= mDBHelper.rawQuery(sqlString, null);
		if(c.moveToFirst())
		{
			
			int position = c.getInt(0);

			
			String fields[] = {"Role_Name","Role_Type_Num","Position"};
			String values[] = {roleName,Integer.toString(roleType),Integer.toString(position+1)};
			mDBHelper.insert(TABLE, fields, values);
				
			
		}
			
		
		//String sqlString = "
	}
	
//	public void addRole(RoleData pRoleData)
//	{
//		
//		String sqlString = "SELECT MAX(Position) From " + TABLE;
//		Cursor c= mDBHelper.rawQuery(sqlString, null);
//		if(c.moveToFirst())
//		{
//			String fields[] = {"Role_Name","Role_Type_Num","Position"};
//			String values[] = {pRoleData.roleName,Integer.toString(pRoleData.roleType),c.getString(0)};
//			mDBHelper.insert(TABLE, fields, values);
//				
//			
//		}
//			
//		
//		
//		
//	}
//	
	public ArrayList<RoleData> getAllRole()
	{
		ArrayList<RoleData> retData = new ArrayList<RoleData>();
		
		String fields[] = {"Role_ID","Role_Name","Role_Type_Num","Position"};
		
		Cursor c = mDBHelper.select(TABLE, fields, null, null, null, null, "Position ASC");
		while(c.moveToNext())
		{
			
			
			RoleData rd = new RoleData(c.getInt(0),c.getInt(2), c.getString(1));
			rd.position = c.getInt(3);
			retData.add(rd);
		}
		
		return retData;
	}
	
	public void changeRoleName(int pRoleID,String pName)
	{
		String fields[] = {"Role_Name"};
		String values[] = {pName};
		String whereValue[] = {Integer.toString(pRoleID)}; 
		mDBHelper.update(TABLE, fields, values, "Role_ID = ?", whereValue);
		//mDBHelper.update(TABLE, {"Role_Name"}, {pName}, "Role_ID = ?", {pRoleID});
	}
	
	public void deleteRole(int pRoleID )
	{
		String value[] = {Integer.toString(pRoleID)};
		mDBHelper.delete(TABLE, "Role_ID = ?", value);
	}
	
	public void swapPosition(RoleData a, RoleData b)
	{
	    int temp = a.position;
	    a.position = b.position;
	    b.position = temp;
	    //"UPDATE Hero_List SET Position  = Role_ID"
	    
	    try {	
			mDBHelper.execSQL("UPDATE " + TABLE + " SET Position = "+Integer.toString(a.position) + " WHERE Role_ID = "+Integer.toString(a.roleID));
			mDBHelper.execSQL("UPDATE " + TABLE + " SET Position = "+Integer.toString(b.position) + " WHERE Role_ID = "+Integer.toString(b.roleID));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
