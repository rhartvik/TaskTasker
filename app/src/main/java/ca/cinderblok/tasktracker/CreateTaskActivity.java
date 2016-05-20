package ca.cinderblok.tasktracker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ca.cinderblok.tasktracker.DAL.TaskDbContract;
import ca.cinderblok.tasktracker.DAL.TaskDbHelper;

public class CreateTaskActivity extends AppCompatActivity {

    private TaskDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new TaskDbHelper(this, TaskDbContract.DATABASE_NAME, null, TaskDbContract.DATABASE_VERSION);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();




                // dbHelper.InsertNewTask();

            }
        });
    }

}
