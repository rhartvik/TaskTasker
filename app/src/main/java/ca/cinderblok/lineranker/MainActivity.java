package ca.cinderblok.lineranker;

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
import android.widget.ListView;

import ca.cinderblok.lineranker.DAL.LineDbContract;
import ca.cinderblok.lineranker.DAL.LineDbHelper;

public class MainActivity extends AppCompatActivity {

    LineDbHelper mDbHelper;

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
                newFragment.show(getSupportFragmentManager(), "addcategory");

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mDbHelper = new LineDbHelper(this, LineDbContract.DATABASE_NAME, null, LineDbContract.DATABASE_VERSION);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        ListView categoryListView = (ListView) findViewById(R.id.categoryListView);
        Cursor categoryCursor = mDbHelper.GetCategories();
        // Setup cursor adapter using cursor from last step
        CategoryAdapter categoryCursorAdapter = new CategoryAdapter(this.getBaseContext(), categoryCursor, CursorAdapter.NO_SELECTION);
        // Attach cursor adapter to the ListView
        categoryListView.setAdapter(categoryCursorAdapter);
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
}
