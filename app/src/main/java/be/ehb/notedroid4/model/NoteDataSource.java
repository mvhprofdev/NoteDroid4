package be.ehb.notedroid4.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by MVH on 21-4-2017.
 */

public class NoteDataSource {

    public static final NoteDataSource INSTANCE = new NoteDataSource();
    private ArrayList<Note> notes;

    private NoteDBHelper mNoteDBHelper;
    private SQLiteDatabase mDatabase;

    public void openConnection(Context context){
        mNoteDBHelper = new NoteDBHelper(context);
        mDatabase = mNoteDBHelper.getWritableDatabase();
    }

    private NoteDataSource() {
        notes = new ArrayList<>();
    }

    public ArrayList<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        Cursor mCursor = mDatabase.rawQuery("SELECT * FROM " + NoteDBContract.TABLE_NOTES, null);
        mCursor.moveToFirst();
        while(!mCursor.isAfterLast()){
            Note temp = new Note();

            int index = mCursor.getColumnIndex(NoteDBContract._ID);
            temp.setId(mCursor.getLong(index));

            index = mCursor.getColumnIndex(NoteDBContract.NOTE_TITLE);
            temp.setTitle(mCursor.getString(index));

            index = mCursor.getColumnIndex(NoteDBContract.NOTE_COMMENT);
            temp.setContent(mCursor.getString(index));

            index = mCursor.getColumnIndex(NoteDBContract.NOTE_PUBLISHDATE);
            temp.setPublishDate(mCursor.getString(index));

            index = mCursor.getColumnIndex(NoteDBContract.NOTE_LASTMODIFIEDDATE);
            temp.setLastModifiedDate(mCursor.getString(index));

            notes.add(temp);

            mCursor.moveToNext();
        }

        return notes;
    }

    public boolean insertNote(Note newNote)
    {
        if (mDatabase == null)
            return false;

        ContentValues mValues = new ContentValues();
        mValues.put(NoteDBContract.NOTE_COMMENT, newNote.getContent());
        mValues.put(NoteDBContract.NOTE_TITLE, newNote.getTitle());
        mValues.put(NoteDBContract.NOTE_PUBLISHDATE, newNote.getPublishDate());
        mValues.put(NoteDBContract.NOTE_LASTMODIFIEDDATE, newNote.getLastModifiedDate());


        long resultID = mDatabase.insert(NoteDBContract.TABLE_NOTES, null, mValues);

        if (resultID == -1)
            return false;
        else
            return true;

        //getNotes().add(newNote);
    }

    public  void updateNote(Note oldNote)
    {
        ContentValues mValues = new ContentValues();

        mValues.put(NoteDBContract._ID, oldNote.getId());
        mValues.put(NoteDBContract.NOTE_TITLE, oldNote.getTitle());
        mValues.put(NoteDBContract.NOTE_PUBLISHDATE, oldNote.getPublishDate());
        mValues.put(NoteDBContract.NOTE_COMMENT, oldNote.getContent());

        long idToUpdate = oldNote.getId();

        mDatabase.update(NoteDBContract.TABLE_NOTES,
                mValues,
                NoteDBContract._ID + " = " + idToUpdate,
                null);

        //getNotes().remove(oldNote);
        //getNotes().add(newN);
    }

    public boolean deleteNote(Note oldN){
        if(mDatabase == null)
            return false;

        long idToRemove = oldN.getId();
        int nrOfRemovedRows = mDatabase.delete(NoteDBContract.TABLE_NOTES, NoteDBContract._ID + " = " + idToRemove, null);

        //prevents sql injection, NOT WORKING?
        //String[] arguments = {DBContract._ID, ""+idToRemove};
        //int nrOfRemovedRows = mDatabase.delete(DBContract.TABLE_COMMENTS, "? = ?", arguments);

        if(nrOfRemovedRows == 0)
            return false;
        else
            return true;
    }
}
