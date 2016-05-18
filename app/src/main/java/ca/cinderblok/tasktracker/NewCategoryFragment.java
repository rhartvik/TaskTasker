package ca.cinderblok.tasktracker;

/**
 * Created by rachelhartviksen on 2016-05-03.
 */
public class NewCategoryFragment extends NewEntryFragment {

    @Override
    protected int getTitleStringId(){
        return R.string.enter_category_name;
    }

    @Override
    protected int getViewId(){
        return R.layout.med_string_field;
    }

    @Override
    protected int getEditTextId(){
        return R.id.mediumEditText;
    }

    @Override
    protected long add(String str){
        return mDbHelper.InsertNewCategory(str);
    }
}


