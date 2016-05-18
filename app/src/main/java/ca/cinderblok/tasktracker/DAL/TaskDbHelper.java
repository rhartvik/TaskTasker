package ca.cinderblok.tasktracker.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TaskDbHelper extends SQLiteOpenHelper{

    static final String TAG = TaskDbHelper.class.getSimpleName();

    public TaskDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(TaskDbContract.ProjectTable.SQL_CREATE);
        Log.d(TAG, "onCreated sql: " + TaskDbContract.ProjectTable.SQL_CREATE);

        sqLiteDatabase.execSQL(TaskDbContract.TaskTable.SQL_CREATE);
        Log.d(TAG, "onCreated sql: " + TaskDbContract.TaskTable.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(TaskDbContract.ProjectTable.SQL_DELETE);
        Log.d(TAG, "onCreated sql: " + TaskDbContract.ProjectTable.SQL_DELETE);

        sqLiteDatabase.execSQL(TaskDbContract.TaskTable.SQL_DELETE);
        Log.d(TAG, "onCreated sql: " + TaskDbContract.TaskTable.SQL_DELETE);

        onCreate(sqLiteDatabase);
    }

    public long InsertNewProject(String str) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskDbContract.ProjectTable.COLUMN_NAME_NAME, str);
        return db.insert(TaskDbContract.ProjectTable.TABLE_NAME, null, values);
    }

    public long InsertNewTask(
            long projectId
            , String title
            , String description
            , long reporterId
            , long assigneeId
            , long statusId
            , long priorityId
            , long sprintId){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues lineValues = new ContentValues();
        lineValues.put(TaskDbContract.TaskTable.COLUMN_NAME_TITLE, title);
        lineValues.put(TaskDbContract.TaskTable.COLUMN_NAME_DESCRIPTION, description);
        lineValues.put(TaskDbContract.TaskTable.COLUMN_NAME_REPORTER_ID, reporterId);
        lineValues.put(TaskDbContract.TaskTable.COLUMN_NAME_ASSIGNEE_ID, assigneeId);
        lineValues.put(TaskDbContract.TaskTable.COLUMN_NAME_STATUS_ID, statusId);
        lineValues.put(TaskDbContract.TaskTable.COLUMN_NAME_PRIORITY_ID, priorityId);
        lineValues.put(TaskDbContract.TaskTable.COLUMN_NAME_SPRINT_ID, sprintId);
        lineValues.put(TaskDbContract.TaskTable.COLUMN_NAME_PROJECT_ID, projectId);
        return db.insert(TaskDbContract.TaskTable.TABLE_NAME, null, lineValues);
    }

    public Cursor GetProjects() {
        SQLiteDatabase db = getWritableDatabase();
        return db.query(
                TaskDbContract.ProjectTable.TABLE_NAME
                , TaskDbContract.ProjectTable.FULL_PROJECTION
                , null, null, null, null, null);
    }

    public Cursor GetTasks(long projectId) {
        SQLiteDatabase db = getWritableDatabase();
        return db.query(
                TaskDbContract.TaskTable.TABLE_NAME
                , TaskDbContract.TaskTable.FULL_PROJECTION
                , TaskDbContract.TaskTable.COLUMN_NAME_PROJECT_ID + "=?"
                , new String[] { Long.toString(projectId) }
                , null
                , null
                , TaskDbContract.TaskTable.COLUMN_NAME_DEADLINE + " DESC");
    }
}
