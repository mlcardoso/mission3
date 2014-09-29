package Dom;

/**
 * Created by Marcos Cardoso on 26/09/2014.
 */

        import java.util.ArrayList;
        import java.util.List;

        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.database.sqlite.SQLiteStatement;

public class MySQLite {

    // Database Name
    private static final String DATABASE_NAME = "Tasker.db";

    /*TALBES NAMES*/
    private static final String TABLE_NAME = "MyList";

    private static final int DATABASE_VERSION = 1;

    /*Common Column name*/
    private static final String name = "name";

    /*Name time column name*/
    private static final String time = "time";
    private static final String date = "date";

    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;

    /*INSERT*/
    private static final String INSERT = "insert into "
            + TABLE_NAME + " (" + name + "," + date + "," + time + ") values (?,?,?)";



    public MySQLite(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);
        // TODO Auto-generated constructor stub
    }

    public long insert(String name,String date, String time) {
        this.insertStmt.bindString(1, name);
        this.insertStmt.bindString(2, date);
        this.insertStmt.bindString(3, time);
        return this.insertStmt.executeInsert();
    }
    public void deleteAll() {
        this.db.delete(TABLE_NAME, null, null);
    }

    public List<String> selectAllNames() {
        List<String> list = new ArrayList<String>();
        Cursor cursor = this.db.rawQuery("SELECT DISTINCT "+ name+" from "+TABLE_NAME, null);
        if(cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }

    public List<String> SelectAllDate(String name){
        List<String> list = new ArrayList<String>();
        Cursor cursor = this.db.rawQuery("SELECT "+ date+" from "+TABLE_NAME+" where name="+"'"+name+"'", null);
        if(cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }
    public List<String> SelectAllTime(String name){
        List<String> list = new ArrayList<String>();
        Cursor cursor = this.db.rawQuery("SELECT "+ time+" from "+TABLE_NAME+" where name="+"'"+name+"'", null);
        if(cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }
    public List<String> SelectAll(){
        int i =0;
        List<String> list = new ArrayList<String>();
        Cursor cursor = this.db.rawQuery("SELECT name,date,time from "+TABLE_NAME, null);
        if(cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0)+cursor.getString(1));
                i++;
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }

    private static class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY,name TEXT, date TEXT, time TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        }

    }

}
