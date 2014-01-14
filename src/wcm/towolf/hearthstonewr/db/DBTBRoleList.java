package wcm.towolf.hearthstonewr.db;

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
		String fields[] = {"Role_Name","Role_Type_Num"};
		String values[] = {roleName,Integer.toString(roleType)};
		mDBHelper.insert(TABLE, fields, values);
		
		//String sqlString = "
	}
	
	public void addRole(RoleData pRoleData)
	{
		
		String fields[] = {"Role_Name","Role_Type_Num"};
		String values[] = {pRoleData.roleName,Integer.toString(pRoleData.roleType)};
		mDBHelper.insert(TABLE, fields, values);
		
		
	}
	
	public ArrayList<RoleData> getAllRole()
	{
		ArrayList<RoleData> retData = new ArrayList<RoleData>();
		
		String fields[] = {"Role_ID","Role_Name","Role_Type_Num"};
		
		Cursor c = mDBHelper.select(TABLE, fields, null, null, null, null, null);
		while(c.moveToNext())
		{
			
			
			RoleData rd = new RoleData(c.getInt(0),c.getInt(2), c.getString(1));
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
	
	
	
	
}
