package ca.cinderblok.tasktracker.DAL;

import android.provider.BaseColumns;

public final class TaskDbContract {
    
    public TaskDbContract() {}

    static final String TAG = TaskDbHelper.class.getSimpleName();

    public static String DATABASE_NAME = "lines.db";
    public static int DATABASE_VERSION = 1;

    public static abstract class SimpleEnumerationTable implements BaseColumns {

        public static String TABLE_NAME;
        public static final String COLUMN_NAME_NAME = "name";

        public static final String[] FULL_PROJECTION = new String[] {
                _ID
                , COLUMN_NAME_NAME
        };

        public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " integer primary key autoincrement not null, "
                + COLUMN_NAME_NAME + " text)";

        public static final String SQL_DELETE = "DROP TABLE IF EXISTS " + ProjectTable.TABLE_NAME;


    }

    public static abstract class SprintTable extends SimpleEnumerationTable {

        public static final String TABLE_NAME = "sprint";
    }

    public static abstract class ProjectTable extends SimpleEnumerationTable {

        public static final String TABLE_NAME = "project";
    }

    public static abstract class StatusTable extends SimpleEnumerationTable {

        public static final String TABLE_NAME = "status";
    }

    public static abstract class UserTable extends SimpleEnumerationTable {

        public static final String TABLE_NAME = "user";
    }

    public static abstract class PriorityTable extends SimpleEnumerationTable {

        public static final String TABLE_NAME = "priority";
    }

    public static abstract class TaskTable implements BaseColumns {

        public static final String TABLE_NAME = "task";

        // Mandatory Fields
//        •	Title (Text)
//        •	Description (Text)
//        •	Assignee (User)
//        •	Reporter (User)
//        •	Status (Open, Resolved, Closed, Will Not Fix, Deffered)
//        •	Priority ( Critical, High, Medium, Low)
//        •	Sprint (One of Sprint)
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_REPORTER_ID = "reporterid";
        public static final String COLUMN_NAME_ASSIGNEE_ID = "assigneeid";
        public static final String COLUMN_NAME_STATUS_ID = "statusid";
        public static final String COLUMN_NAME_PRIORITY_ID = "priorityid";
        public static final String COLUMN_NAME_SPRINT_ID = "sprintid";

        // Optional Fields
//        •	Project (One of Project)
//        •	Estimate (TimeNumeric)
//        •	Deadline (DateTime)
//        •	Tags (One-Many of Tag)*
//        •	Comments (One-Many of Comment)*
        // * Separate tables

        public static final String COLUMN_NAME_PROJECT_ID = "projectid";
        public static final String COLUMN_NAME_DEADLINE = "deadline";
        public static final String COLUMN_NAME_EST_MINUTES = "estimationminutes";

        public static final String[] FULL_PROJECTION = new String[] {
                _ID
                , COLUMN_NAME_TITLE
                , COLUMN_NAME_DESCRIPTION
                , COLUMN_NAME_REPORTER_ID
                , COLUMN_NAME_ASSIGNEE_ID
                , COLUMN_NAME_STATUS_ID
                , COLUMN_NAME_PRIORITY_ID
                , COLUMN_NAME_SPRINT_ID
                , COLUMN_NAME_PROJECT_ID
                , COLUMN_NAME_DEADLINE
                , COLUMN_NAME_EST_MINUTES
        };

        public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " integer primary key autoincrement not null, "
                + COLUMN_NAME_TITLE + " text not null, "
                + COLUMN_NAME_DESCRIPTION + " text not null, "
                + COLUMN_NAME_REPORTER_ID + " int not null, "
                + COLUMN_NAME_ASSIGNEE_ID + " int not null, "
                + COLUMN_NAME_STATUS_ID + " int not null, "
                + COLUMN_NAME_PRIORITY_ID + " int not null, "
                + COLUMN_NAME_SPRINT_ID + " int not null, "
                + COLUMN_NAME_PROJECT_ID + " int, "
                + COLUMN_NAME_DEADLINE + " text, "
                + COLUMN_NAME_EST_MINUTES + " int, "
                + "FOREIGN KEY(" + COLUMN_NAME_REPORTER_ID + ") REFERENCES "
                + UserTable.TABLE_NAME + "(" + UserTable._ID + "), "
                + "FOREIGN KEY(" + COLUMN_NAME_ASSIGNEE_ID + ") REFERENCES "
                + UserTable.TABLE_NAME + "(" + UserTable._ID + "),"
                + "FOREIGN KEY(" + COLUMN_NAME_STATUS_ID + ") REFERENCES "
                + StatusTable.TABLE_NAME + "(" + StatusTable._ID + "), "
                + "FOREIGN KEY(" + COLUMN_NAME_PRIORITY_ID + ") REFERENCES "
                + PriorityTable.TABLE_NAME + "(" + PriorityTable._ID + "), "
                + "FOREIGN KEY(" + COLUMN_NAME_SPRINT_ID + ") REFERENCES "
                + SprintTable.TABLE_NAME + "(" + SprintTable._ID + "), "
                + "FOREIGN KEY(" + COLUMN_NAME_PROJECT_ID + ") REFERENCES "
                + ProjectTable.TABLE_NAME + "(" + ProjectTable._ID + ") "
                + ")";

        public static final String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

}
