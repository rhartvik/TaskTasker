package ca.cinderblok.lineranker.DAL;

import android.provider.BaseColumns;

public final class LineDbContract {
    
    public LineDbContract() {}

    static final String TAG = LineDbHelper.class.getSimpleName();

    public static String DATABASE_NAME = "lines.db";
    public static int DATABASE_VERSION = 1;

    public static abstract class CategoryTable implements BaseColumns {

        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME_NAME = "name";

        public static final String[] FULL_PROJECTION = new String[] {
                _ID
                , COLUMN_NAME_NAME
        };

        public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " integer primary key autoincrement not null, "
                + COLUMN_NAME_NAME + " text)";

        public static final String SQL_DELETE = "DROP TABLE IF EXISTS " + CategoryTable.TABLE_NAME;

    }

    public static abstract class LineTable implements BaseColumns {

        public static final String TABLE_NAME = "Line";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_LINE = "linestring";
        public static final String COLUMN_NAME_RATING = "rating";

        public static final String[] FULL_PROJECTION = new String[] {
                _ID
                , COLUMN_NAME_CATEGORY
                , COLUMN_NAME_DATE
                , COLUMN_NAME_LINE
                , COLUMN_NAME_RATING
        };

        public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " integer primary key autoincrement not null, "
                + COLUMN_NAME_CATEGORY + " int, "
                + COLUMN_NAME_DATE + " int, "
                + COLUMN_NAME_LINE + " text, "
                + COLUMN_NAME_RATING + " int, "
                + "FOREIGN KEY(" + COLUMN_NAME_CATEGORY + ") REFERENCES "
                + CategoryTable.TABLE_NAME + "(" + CategoryTable._ID + ")"
                + ")";

        public static final String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

}
