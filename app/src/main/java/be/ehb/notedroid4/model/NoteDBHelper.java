package be.ehb.notedroid4.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MVH on 29-4-2017.
 */

public class NoteDBHelper extends SQLiteOpenHelper {

    public NoteDBHelper(Context context) {
        super(context, NoteDBContract.DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createStatement = "CREATE table " + NoteDBContract.TABLE_NOTES + " ( "
                + NoteDBContract._ID + " integer primary key autoincrement, "
                + NoteDBContract.NOTE_TITLE + " text not null, "
                + NoteDBContract.NOTE_PUBLISHDATE + " text not null, "
                + NoteDBContract.NOTE_LASTMODIFIEDDATE + " text not null, "
                + NoteDBContract.NOTE_COMMENT + " text not null "
                + ")";

        db.execSQL(createStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
