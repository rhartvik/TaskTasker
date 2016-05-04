package ca.cinderblok.lineranker;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

import ca.cinderblok.lineranker.DAL.LineDbContract;
import ca.cinderblok.lineranker.DAL.LineDbHelper;

/**
 * Created by rachelhartviksen on 2016-05-03.
 */
public abstract class NewEntryFragment extends DialogFragment {

    protected LineDbHelper mDbHelper;

    protected abstract int getTitleStringId();
    protected abstract int getViewId();
    protected abstract int getEditTextId();


    protected abstract long add(String str);

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final View v = getActivity().getLayoutInflater().inflate(getViewId(), null);

        builder.setView(v)
            .setTitle(getTitleStringId())
            .setView(getViewId())
            .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    EditText field = (EditText) v.findViewById(getEditTextId());
                    add(field.getText().toString());
                }
            })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        mDbHelper = new LineDbHelper(this.getContext(), LineDbContract.DATABASE_NAME, null, LineDbContract.DATABASE_VERSION);
    }

    @Override
    public void onStop() {
        super.onStop();
        mDbHelper.close();
    }
}
