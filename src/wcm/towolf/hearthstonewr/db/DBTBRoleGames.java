package wcm.towolf.hearthstonewr.db;

import java.sql.SQLException;
import java.util.ArrayList;

import wcm.towolf.hearthstonewr.model.datatype.RoleGame;
import android.database.Cursor;
import android.util.Log;

public class DBTBRoleGames {

	public static final String TAG =  "DBTBRoleGames";
	
	public static String TABLE = "Role_Games";
	public static String fieldNames[] = {"Game_ID","Role_ID","Role_Type_Num","Is_Win"};
	public static String fieldTypes[] ={"INTEGER PRIMARY KEY ASC AUTOINCREMENT","INTEGER","INTEGER","INTEGER"};
	
	private DBHelper mDBHelper;
	
	public DBTBRoleGames(DBHelper pDBHelper)
	{
		//mDBHelper = pDBHelper;
		
		mDBHelper = DBHelper.sharedInstance();
	}
	
	
	public void addGame(int roleID,int enemyRoleType,boolean isWin)
	{
		
		String fields[] = {"Role_ID","Role_Type_Num","Is_Win"};
		int isWinInt = isWin?1:0;
		String values[] = {Integer.toString(roleID),Integer.toString(enemyRoleType),Integer.toString(isWinInt)};
		mDBHelper.insert(TABLE, fields, values);
		
		
	}
	
	public ArrayList<RoleGame> getAllGames()
	{
		ArrayList<RoleGame> retData = new ArrayList<RoleGame>();
		
		String fields[] = {"Game_ID","Role_ID","Role_Type_Num","Is_Win"};
		
		Cursor c = mDBHelper.select(TABLE, fields, null, null, null, null, null);
		while(c.moveToNext())
		{
			
			//TODO fix win rate;
			RoleGame rg = new RoleGame(c.getInt(0),c.getInt(1), c.getInt(2),c.getInt(3)==1);
			//Log.e(TAG,Integer.toString(c.getInt(3)));
			retData.add(rg);
		}
		
		return retData;
	}
	
	public RoleGame deleteLastGame(int roleID)
	{
		String queryArg[] = {Integer.toString(roleID)};
		Cursor c =mDBHelper.rawQuery("select * from "+TABLE+" where Role_ID = ? order by rowid desc limit 1", queryArg);
		
		RoleGame rg;
		
		while(c.moveToNext())
		{
		 rg = new RoleGame(c.getInt(0),c.getInt(1), c.getInt(2),c.getInt(3)==1);
		mDBHelper.delete(TABLE, "Game_ID = ?", new String[] {Integer.toString(rg.getGameID())});
		
		return rg;
		}
//		try {
//			mDBHelper.execSQL("DELETE FROM "+TABLE+" WHERE Game_ID = (SELECT MAX(Game_ID) FROM "+TABLE+")");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return null;
	}
	
	public void deleteRole(int pRoleID )
	{
		String value[] = {Integer.toString(pRoleID)};
		mDBHelper.delete(TABLE, "Role_ID = ?", value);
	}
	
	

	
}
