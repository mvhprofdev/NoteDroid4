package be.ehb.notedroid4.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import be.ehb.notedroid4.R;
import be.ehb.notedroid4.activities.NoteDetailsActivity;
import be.ehb.notedroid4.model.Note;
import be.ehb.notedroid4.model.NoteDataSource;
import be.ehb.notedroid4.util.NotesAdapter;


public class MainFragment extends Fragment implements AdapterView.OnItemClickListener {


    private NotesAdapter notesAdapter;
    private SharedPreferences sharedPreferences;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        NoteDataSource.INSTANCE.openConnection(getActivity().getApplicationContext());

        ListView lv = (ListView) rootView.findViewById(R.id.notes_lv);

        notesAdapter = new NotesAdapter(getActivity(), NoteDataSource.INSTANCE.getNotes());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        lv.setAdapter(notesAdapter);
        lv.setOnItemClickListener(this);
        registerForContextMenu(lv);

        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        notesAdapter.notifyDataSetChanged();
        notesAdapter.sortNotesList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mi = getActivity().getMenuInflater();
        mi.inflate(R.menu.context_main, menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater mi = getActivity().getMenuInflater();
        mi.inflate(R.menu.activity_main, menu);

        MenuItem item = menu.findItem(R.id.mi_search_view);
        SearchView sv = (SearchView) item.getActionView();

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                notesAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                notesAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.new_item)
        {
            Intent i = new Intent(getActivity().getApplicationContext(), NoteDetailsActivity.class);
            i.putExtra("isNew", true);
            startActivity(i);
/*
        }else if(item.getItemId() == R.id.mi_pref_str){
            Intent j = new Intent(getActivity().getApplicationContext(), SettingsActivity.class);
            j.putExtra("isNew", true);
            startActivity(j);
*/
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId())
        {
            case R.id.delete_item :
                Note n = (Note) notesAdapter.getItem(info.position);
                //verwijder uit datasource
                NoteDataSource.INSTANCE.deleteNote(n);
                //verwijder uit listview
                notesAdapter.remove(n);
                notesAdapter.notifyDataSetChanged();

                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Note n = (Note)arg0.getItemAtPosition(arg2);

        Intent i = new Intent(getActivity().getApplicationContext(), NoteDetailsActivity.class);
        i.putExtra("isNew", false);
        i.putExtra("selectedNote", n);
        startActivity(i);

    }

}
