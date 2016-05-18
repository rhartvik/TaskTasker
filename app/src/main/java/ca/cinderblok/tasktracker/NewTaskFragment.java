package ca.cinderblok.tasktracker;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

/**
 * Created by rachelhartviksen on 2016-05-03.
 */
public class NewTaskFragment extends NewEntryFragment {

    @Override
    protected int getTitleStringId(){
        return R.string.enter_task_title;
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
        long projectId = getArguments().getLong("simple_enum_list_item");
        LayoutInflater inflater = LayoutInflater.from(getActivity().getBaseContext());
        View view = inflater.inflate(getViewId(), null);

        DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        datePicker.getDayOfMonth();
        datePicker.getMonth();

        String dateString = String.format(getString(R.string.date_format)
                , datePicker.getYear()
                , datePicker.getMonth()
                , datePicker.getDayOfMonth());

        return mDbHelper.InsertNewTask(projectId, str, str, 0, 0, 0, 0, 0);
    }

}
