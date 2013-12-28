package wcm.towolf.hearthstonewr.db;

import android.os.Environment;


/**
 * The Class DBSchema. 定義CutX的資料
 */
public class DBSchema {

	
	String dbLocation =  Environment.getExternalStorageDirectory()+ "/HearthStoneWRT/db/";
    /* 資料庫 */
     String dbName = dbLocation +"MyDatabase";
    /* version必須大於等於1 */
     int version = 2;

    /* Table資料表 */
    String tables[] = { DBTBRoleList.TABLE,DBTBRoleGames.TABLE,DBTBArenaEventList.TABLE,DBTBArenaGames.TABLE };

    /* 欄位名稱 */
    String fieldNames[][] = { DBTBRoleList.fieldNames,DBTBRoleGames.fieldNames,DBTBArenaEventList.fieldNames,DBTBArenaGames.fieldNames};

    /* 欄位型態 */
    String fieldTypes[][] = { DBTBRoleList.fieldTypes,DBTBRoleGames.fieldTypes,DBTBArenaEventList.fieldTypes,DBTBArenaGames.fieldTypes};

    
    //    /* Table資料表 */
//    private String tables[] = { Channel.table, Program.table,
//	    ChannelFavorite.table, Memo.table, MemoImage.table,
//	    MemoVideo.table, MemoTagBase.table, MemoMetadata.table,
//	    Promote.table };
//
//    /* 欄位名稱 */
//    private String fieldNames[][] = { Channel.fieldNames, Program.fieldNames,
//	    ChannelFavorite.fieldNames, Memo.fieldNames, MemoImage.fieldNames,
//	    MemoVideo.fieldNames, MemoTagBase.fieldNames,
//	    MemoMetadata.fieldNames, Promote.fieldNames };
//
//    /* 欄位型態 */
//    private String fieldTypes[][] = { Channel.fieldTypes, Program.fieldTypes,
//	    ChannelFavorite.fieldTypes, Memo.fieldTypes, MemoImage.fieldTypes,
//	    MemoVideo.fieldTypes, MemoTagBase.fieldTypes,
//	    MemoMetadata.fieldTypes, Promote.fieldTypes };

 //   private DBHelper dbHelper;

    
    
    /**
     * Instantiates a new database schema.
     * 
     * @param context
     *            the context
     */
    
    public DBSchema()
    {
    	
    }
//    public DBSchema(Context) {
//	dbHelper = new DBHelper(context, dbName, null, version,
//		tables, fieldNames, fieldTypes);
//    }

    /**
     * Gets the custom SQLiteOpenHelper.
     * 
     * @return the MySQLiteOpenHelper
     */
//    public DBHelper getDBHelper() {
//	return dbHelper;
//    }

}
