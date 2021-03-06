package ca.cinderblok.tasktracker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import ca.cinderblok.tasktracker.DAL.TaskDbContract;
import ca.cinderblok.tasktracker.DAL.TaskDbHelper;

/**
 * Created by rachelhartviksen on 2016-05-05.
 */
public class TaskListActivity extends AppCompatActivity implements NewEntryFragment.NewEntryDialogListener {

    TaskDbHelper mDbHelper;
    long categoryId;
    String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        categoryId = intent.getLongExtra(MainActivity.PROJECT_ID_EXTRA, -1L);
        categoryName = intent.getStringExtra(MainActivity.PROJECT_NAME_EXTRA);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(categoryName);
        mDbHelper = new TaskDbHelper(this, TaskDbContract.DATABASE_NAME, null, TaskDbContract.DATABASE_VERSION);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                DialogFragment newFragment = new NewTaskFragment();
//                Bundle bundle = new Bundle();
//                bundle.putLong("category", categoryId);
//                newFragment.setArguments(bundle);
//                newFragment.show(getSupportFragmentManager(), "addline");

                Intent intentToGoToCreateTaskActivity = new Intent();
                // Google 'how to change activities android'

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        RefreshList();
    }

    private void RefreshList(){
        ListView lineListView = (ListView) findViewById(R.id.listView);
        Cursor lineCursor = mDbHelper.GetTasks(categoryId);
        TaskAdapter lineCursorAdapter = new TaskAdapter(this.getBaseContext(), lineCursor, CursorAdapter.NO_SELECTION);
        lineListView.setAdapter(lineCursorAdapter);
    }

    @Override
    public void onNewEntryCreation() {
        RefreshList();
    }
}
