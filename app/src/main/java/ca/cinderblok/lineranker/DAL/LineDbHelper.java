package ca.cinderblok.lineranker.DAL;

import android.content.Context;
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
}
