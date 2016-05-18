package ca.cinderblok.tasktracker;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ca.cinderblok.tasktracker.DAL.TaskDbContract;

/**
 * Created by rachelhartviksen on 2016-05-05.
 */
public class TaskAdapter extends CursorAdapter {
    public TaskAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    public static String LINE_ID_EXTRA = "LINE_LINE_ID";

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.task, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titleTextView = (TextView) view.findViewById(R.id.taskTitle);
        TextView descriptionTextView = (TextView) view.findViewById(R.id.taskDescription);
        String title = cursor.getString(cursor.getColumnIndexOrThrow(TaskDbContract.TaskTable.COLUMN_NAME_TITLE));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(TaskDbContract.TaskTable.COLUMN_NAME_DESCRIPTION));
        if (description.length() > 15) {
            description = description.substring(0, 12) + "...";
        }
        titleTextView.setText(title);
        descriptionTextView.setText(description);
    }
}
