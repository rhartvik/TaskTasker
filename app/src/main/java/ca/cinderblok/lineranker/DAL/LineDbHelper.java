package ca.cinderblok.lineranker.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LineDbHelper extends SQLiteOpenHelper{

    static final String TAG = LineDbHelper.class.getSimpleName();

    public LineDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(LineDbContract.CategoryTable.SQL_CREATE);
        Log.d(TAG, "onCreated sql: " + LineDbContract.CategoryTable.SQL_CREATE);

        sqLiteDatabase.execSQL(LineDbContract.LineTable.SQL_CREATE);
        Log.d(TAG, "onCreated sql: " + LineDbContract.LineTable.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(LineDbContract.CategoryTable.SQL_DELETE);
        Log.d(TAG, "onCreated sql: " + LineDbContract.CategoryTable.SQL_DELETE);

        sqLiteDatabase.execSQL(LineDbContract.LineTable.SQL_DELETE);
        Log.d(TAG, "onCreated sql: " + LineDbContract.LineTable.SQL_DELETE);

        onCreate(sqLiteDatabase);
    }

    public long InsertNewCategory(String str) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LineDbContract.CategoryTable.COLUMN_NAME_NAME, str);
        return db.insert(LineDbContract.CategoryTable.TABLE_NAME, null, values);
    }

    public long InsertNewLine (long categoryId, String line, String date){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues lineValues = new ContentValues();
        lineValues.put(LineDbContract.LineTable.COLUMN_NAME_CATEGORY, categoryId);
        lineValues.put(LineDbContract.LineTable.COLUMN_NAME_DATE, date);
        lineValues.put(LineDbContract.LineTable.COLUMN_NAME_LINE, line);
        lineValues.put(LineDbContract.LineTable.COLUMN_NAME_RATING, 0);
        return db.insert(LineDbContract.LineTable.TABLE_NAME, null, lineValues);
    }

    public Cursor GetCategories() {
        SQLiteDatabase db = getWritableDatabase();
        return db.query(LineDbContract.CategoryTable.TABLE_NAME, LineDbContract.CategoryTable.FULL_PROJECTION, null, null, null, null, null);
    }

    public Cursor GetLines(long catagoryId) {
        SQLiteDatabase db = getWritableDatabase();
        // TO DO: Add a sort by rating
        return db.query(
                LineDbContract.LineTable.TABLE_NAME
                , LineDbContract.LineTable.FULL_PROJECTION
                , LineDbContract.LineTable.COLUMN_NAME_CATEGORY + "=?"
                , new String[] { Long.toString(catagoryId) }
                , null
                , null
                , null);
    }
}
