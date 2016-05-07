package ca.cinderblok.lineranker;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Date;

/**
 * Created by rachelhartviksen on 2016-05-03.
 */
public class NewLineFragment extends NewEntryFragment {

    @Override
    protected int getTitleStringId(){
        return R.string.enter_new_line;
    }

    @Override
    protected int getViewId(){
        return R.layout.string_date_fields;
    }

    @Override
    protected int getEditTextId(){
        return R.id.largeEditText;
    }

    @Override
    protected long add(String str){
        long categoryId = getArguments().getLong("category");
        LayoutInflater inflater = LayoutInflater.from(getActivity().getBaseContext());
        View view = inflater.inflate(getViewId(), null);

        DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        datePicker.getDayOfMonth();
        datePicker.getMonth();

        String dateString = String.format(getString(R.string.date_format)
                , datePicker.getYear()
                , datePicker.getMonth()
                , datePicker.getDayOfMonth());

        return mDbHelper.InsertNewLine(categoryId, str, dateString);
    }

}
