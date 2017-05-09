package be.ehb.notedroid4.model;

import android.provider.BaseColumns;

/**
 * Created by MVH on 29-4-2017.
 */

public class NoteDBContract implements BaseColumns {

    public static final String DB_NAME = "dbnotes";
    public static final String TABLE_NOTES = "note";
    public static final String NOTE_TITLE = "title";
    public static final String NOTE_PUBLISHDATE = "publishdate";
    public static final String NOTE_LASTMODIFIEDDATE = "lastmodifieddate";
    public static final String NOTE_COMMENT = "comment";

    public static final String[] TABLE_COMMENTS_COLUMNS = {_ID, NOTE_TITLE, NOTE_PUBLISHDATE, NOTE_LASTMODIFIEDDATE, NOTE_COMMENT};
}
