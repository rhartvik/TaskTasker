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
        builder.setView(getViewId())
            .setTitle(getTitleStringId())
            .setView(getViewId())
            .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    EditText field = (EditText) getDialog().findViewById(getEditTextId());
                    String input = field.getText().toString();
                    add(input);
                    mListener.onNewEntryCreation();
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

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NewEntryDialogListener {
        public void onNewEntryCreation();
    }

    // Use this instance of the interface to deliver action events
    NewEntryDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NewEntryDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
