package be.ehb.notedroid4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import be.ehb.notedroid4.R;
import be.ehb.notedroid4.model.Note;
import be.ehb.notedroid4.model.NoteDataSource;

/**
 * Created by MVH on 21-4-2017.
 */

public class NoteDetailsActivity extends AppCompatActivity {

    private EditText title, content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_screen);

        title = (EditText)findViewById(R.id.title_et);
        content = (EditText)findViewById(R.id.content_et);

        if(!getIntent().getExtras().getBoolean("isNew"))
        {
            Note n = getIntent().getParcelableExtra("selectedNote");

            title.setText(n.getTitle());
            content.setText(n.getContent());
        }
    }

    private String currentDateAsString()
    {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.FRANCE);

        return sdf.format(now);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.activity_details, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        if(item.getItemId() == R.id.save_item)
        {
            //new
            if(getIntent().getExtras().getBoolean("isNew"))
            {
                Note n = new Note(title.getText().toString(), content.getText().toString(), currentDateAsString());
                NoteDataSource.INSTANCE.insertNote(n);
            }

            //update
            if(!getIntent().getExtras().getBoolean("isNew"))
            {
                Note oldNote = getIntent().getParcelableExtra("selectedNote");

                Note newN = new Note();
                newN.setId(oldNote.getId());
                newN.setPublishDate(oldNote.getPublishDate());
                newN.setLastModifiedDate(currentDateAsString());
                newN.setTitle(title.getText().toString());
                newN.setContent(content.getText().toString());

                NoteDataSource.INSTANCE.updateNote(oldNote);
            }

            //sowieso terug naar lijst gaan, onCreate() zal lijst opnieuw maken
            Intent i = new Intent(this.getApplicationContext(), be.ehb.notedroid4.activities.MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }



}