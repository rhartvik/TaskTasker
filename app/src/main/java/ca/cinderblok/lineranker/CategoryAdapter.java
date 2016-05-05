package ca.cinderblok.lineranker;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ca.cinderblok.lineranker.DAL.LineDbContract;

/**
 * Created by rachelhartviksen on 2016-05-03.
 */
public class CategoryAdapter extends CursorAdapter {
    public CategoryAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.category, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView categoryNameTextView = (TextView) view.findViewById(R.id.categoryName);
        String categoryName = cursor.getString(cursor.getColumnIndexOrThrow(LineDbContract.CategoryTable.COLUMN_NAME_NAME));
        categoryNameTextView.setText(categoryName);
    }
}