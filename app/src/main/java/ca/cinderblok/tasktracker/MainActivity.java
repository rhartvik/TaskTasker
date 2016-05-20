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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import ca.cinderblok.tasktracker.DAL.TaskDbContract;
import ca.cinderblok.tasktracker.DAL.TaskDbHelper;

public class MainActivity extends AppCompatActivity implements NewEntryFragment.NewEntryDialogListener {

    TaskDbHelper mDbHelper;
    public static String PROJECT_ID_EXTRA = "cinderblok.ca.TASK_PROJECT_ID";
    public static String PROJECT_NAME_EXTRA = "cinderblok.ca.TASK_PROJECT_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment newFragment = new NewCategoryFragment();
                newFragment.show(getSupportFragmentManager(), "addproject");

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mDbHelper = new TaskDbHelper(this, TaskDbContract.DATABASE_NAME, null, TaskDbContract.DATABASE_VERSION);

        mDbHelper.SeedData();

        ListView list = (ListView) findViewById(R.id.listView);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = (Cursor)parent.getItemAtPosition(position);
                long projectID = c.getLong(c.getColumnIndexOrThrow(TaskDbContract.ProjectTable._ID));
                Intent intent = new Intent(MainActivity.this, TaskListActivity.class);
                intent.putExtra(PROJECT_ID_EXTRA, projectID);
                String projectName = ((TextView) view.findViewById(R.id.listItemName)).getText().toString();
                intent.putExtra(PROJECT_NAME_EXTRA, projectName);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void RefreshList(){
        ListView categoryListView = (ListView) findViewById(R.id.listView);
        Cursor categoryCursor = mDbHelper.GetProjects();
        // Setup cursor adapter using cursor from last step
        SimpleEnumerationAdapter projectCursorAdapter = new SimpleEnumerationAdapter(this.getBaseContext(), categoryCursor, CursorAdapter.NO_SELECTION);
        // Attach cursor adapter to the ListView
        categoryListView.setAdapter(projectCursorAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        RefreshList();
    }

    @Override
    public void onStop() {
        super.onStop();
        mDbHelper.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onNewEntryCreation() {
        RefreshList();
    }
}
