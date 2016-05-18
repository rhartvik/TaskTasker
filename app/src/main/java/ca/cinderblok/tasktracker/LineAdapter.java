package ca.cinderblok.tasktracker;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ca.cinderblok.tasktracker.DAL.LineDbContract;

/**
 * Created by rachelhartviksen on 2016-05-05.
 */
public class LineAdapter extends CursorAdapter {
    public LineAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    public static String LINE_ID_EXTRA = "LINE_LINE_ID";

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.line, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView lineTextView = (TextView) view.findViewById(R.id.lineText);
        TextView ratingTextView = (TextView) view.findViewById(R.id.ratingText);
        String lineText = cursor.getString(cursor.getColumnIndexOrThrow(LineDbContract.LineTable.COLUMN_NAME_LINE));
        Integer rating = cursor.getInt(cursor.getColumnIndexOrThrow(LineDbContract.LineTable.COLUMN_NAME_RATING));
        lineTextView.setText(lineText);
        ratingTextView.setText(rating.toString());
    }
}
