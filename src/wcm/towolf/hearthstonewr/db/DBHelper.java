package wcm.towolf.hearthstonewr.db;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

// TODO: Auto-generated Javadoc
/**
 * The Class MySQLiteOpenHelper.
 * 繼承SQLiteOpenHelper，定義database的開啟、更新及資料操作(查詢、新增、刪除及修改)
 */
public class DBHelper extends SQLiteOpenHelper {
    public String TableNames[];
    public String FieldNames[][];
    public String FieldTypes[][];
    public static String NO_CREATE_TABLES = "no tables";
    private String message = "";


    
    private static DBHelper mInstance;
    
    public static void init(Context context)
    {
    	
    	DBSchema schema= new DBSchema();
    	//create folder in external storage for database
    	File dir = new File(schema.dbLocation);
    	boolean b = dir.mkdirs();
    	context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ schema.dbLocation)));
    	
    	
    	if(mInstance == null)
    	mInstance = new DBHelper(context, schema);
    }
    
    public static DBHelper sharedInstance()
    {
       return mInstance;	
    }
    
    public static void closeDB()
    {
    	mInstance.close();
    }
    
    
    private DBHelper(Context context,DBSchema schema)
    {

    	
    	   this(context, schema.dbName, null, schema.version,
        		schema.tables, schema.fieldNames, schema.fieldTypes);
    }
    /**
     * Instantiates a new MySQLiteOpenHelper.
     * 
     * @param context
     *            the context
     * @param dbname
     *            the database name
     * @param factory
     *            the factory
     * @param version
     *            the version
     * @param tableNames
     *            the table names
     * @param fieldNames
     *            the field names
     * @param fieldTypes
     *            the field types
     */
    private DBHelper(Context context, String dbname,
	    CursorFactory factory, int version, String tableNames[],
	    String fieldNames[][], String fieldTypes[][]) {
	super(context, dbname, factory, version);
	TableNames = tableNames;
	FieldNames = fieldNames;
	FieldTypes = fieldTypes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
     * .SQLiteDatabase)
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
	if (TableNames == null) {
	    message = NO_CREATE_TABLES;
	    return;
	}
	/* 建立table */
	for (int i = 0; i < TableNames.length; i++) {
	    String sql = "CREATE TABLE " + TableNames[i] + " (";
	    for (int j = 0; j < FieldNames[i].length; j++) {
		sql += FieldNames[i][j] + " " + FieldTypes[i][j] + ",";
	    }
	    sql = sql.substring(0, sql.length() - 1);
	    sql += ")";
	    db.execSQL(sql);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
     * .SQLiteDatabase, int, int)
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
	for (int i = 0; i < TableNames.length; i++) {
	    String sql = "DROP TABLE IF EXISTS " + TableNames[i];
	    db.execSQL(sql);
	}
	onCreate(db);
    }

    /**
     * Runs the provided SQL and returns a Cursor over the result set.
     * 
     * @param sql
     *            the SQL
     * @throws SQLException
     *             the SQL exception
     */
    public void execSQL(String sql) throws java.sql.SQLException {
	SQLiteDatabase db = this.getWritableDatabase();
	db.execSQL(sql);
    }

    /**
     * Raw query.
     * 
     * @param sql
     *            the SQL query. The SQL string must not be ; terminated
     * @param selectionArgs
     *            You may include ?s in where clause in the query, which will be
     *            replaced by the values from selectionArgs. The values will be
     *            bound as Strings
     * @return A Cursor object, which is positioned before the first entry. Note
     *         that Cursors are not synchronized, see the documentation for more
     *         details.
     */
    public Cursor rawQuery(String sql, String[] selectionArgs) {
	SQLiteDatabase db = this.getReadableDatabase();
	Cursor cursor = db.rawQuery(sql, selectionArgs);
	return cursor;
    }

    /**
     * 查詢資料.
     * 
     * @param table
     *            查詢的table name
     * @param columns
     *            查詢的資料的欄位名稱
     * @param selection
     *            查詢條件字串 如：field1 = ? and field2 = ?
     * @param selectionArgs
     *            查詢條件的值 如：["a","b"]
     * @param groupBy
     *            groupBy後面的字串 如：field1,field2
     * @param having
     *            having後面的字串
     * @param orderBy
     *            orderBy後面的字串
     * @return Cursor 包含了取得的資料集
     */
    public Cursor select(String table, String[] columns, String selection,
	    String[] selectionArgs, String groupBy, String having,
	    String orderBy) {
	SQLiteDatabase db = this.getReadableDatabase();
	Cursor cursor = db.query(table, columns, selection, selectionArgs,
		groupBy, having, orderBy);
	return cursor;
    }

    /**
     * Select.
     * 
     * @param distinct
     *            the distinct
     * @param table
     *            the table
     * @param columns
     *            the columns
     * @param selection
     *            the selection
     * @param selectionArgs
     *            the selection args
     * @param groupBy
     *            the group by
     * @param having
     *            the having
     * @param orderBy
     *            the order by
     * @param limit
     *            the limit
     * @return the cursor
     */
    public Cursor select(boolean distinct, String table, String[] columns,
	    String selection, String[] selectionArgs, String groupBy,
	    String having, String orderBy, String limit) {
	SQLiteDatabase db = this.getReadableDatabase();
	Cursor cursor = db.query(distinct, table, columns, selection,
		selectionArgs, groupBy, having, orderBy, limit);
	return cursor;
    }

    /**
     * 新增資料.
     * 
     * @param table
     *            新增資料的table name
     * @param fields
     *            新增資料的欄位名稱
     * @param values
     *            新增資料的欄位值
     * @return long row id
     */
    public long insert(String table, String fields[], String values[]) {
	SQLiteDatabase db = this.getWritableDatabase();
	/* 將新增的值放入ContentValues */
	ContentValues cv = new ContentValues();
	for (int i = 0; i < fields.length; i++) {
	    cv.put(fields[i], values[i]);
	}
	return db.insert(table, null, cv);
    }

    /**
     * 刪除資料.
     * 
     * @param table
     *            刪除資料的table name
     * @param where
     *            刪除資料的條件
     * @param whereValue
     *            刪除資料的條件值
     * @return int 刪除的筆數
     */
    public int delete(String table, String where, String[] whereValue) {
	SQLiteDatabase db = this.getWritableDatabase();

	return db.delete(table, where, whereValue);
    }

    /**
     * 更新資料.
     * 
     * @param table
     *            更新資料的table name
     * @param updateFields
     *            the update fields
     * @param updateValues
     *            the update values
     * @param where
     *            更新除資料的條件
     * @param whereValue
     *            更新資料的條件值
     * @return int 更新的筆數
     */
    public int update(String table, String updateFields[],
	    String updateValues[], String where, String[] whereValue) {
	SQLiteDatabase db = this.getWritableDatabase();

	/* 將修改的值放入ContentValues */
	ContentValues cv = new ContentValues();
	for (int i = 0; i < updateFields.length; i++) {
	    cv.put(updateFields[i], updateValues[i]);
	}
	return db.update(table, cv, where, whereValue);
    }

    /**
     * Gets the message.
     * 
     * @return the message
     */
    public String getMessage() {
	return message;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.database.sqlite.SQLiteOpenHelper#close()
     */
    @Override
    public synchronized void close() {
	super.close();
    }
}
