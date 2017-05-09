package be.ehb.notedroid4.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.ehb.notedroid4.R;
import be.ehb.notedroid4.model.Note;

/**
 * Created by MVH on 21-4-2017.
 */

public class NotesAdapter extends BaseAdapter implements Filterable {

    //hulpklasse om layouts om bij te houden
    private static class ViewHolder {
        TextView dateTV;
        TextView titleTV;
    }

    private LayoutInflater inflater;
    private ArrayList<Note> shownNotes;
    private ArrayList<Note> allNotes;
    SharedPreferences prefs;

    public NotesAdapter(Activity context, List<Note> objects) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.inflater = context.getLayoutInflater();
        this.shownNotes = new ArrayList<Note>(objects);
        this.allNotes = new ArrayList<Note>(objects);
    }

    public void sortNotesList(){

        switch (prefs.getString("pref_order", "Ascending on name")){
            case "Ascending on name":
                Collections.sort(shownNotes, Note.AscendingTitleComparator);
                break;
            case "Descending on name":
                Collections.sort(shownNotes, Note.DescendingTitleComparator);
                break;
            case "Ascending on date":
                Collections.sort(shownNotes, Note.AscendingDateComparator);
                break;
            case "Descending on date":
                Collections.sort(shownNotes, Note.DescendingDateComparator);
                break;
        }
    }


    public void remove(Note n) {
        shownNotes.remove(n);
    }

    @Override
    public int getCount() {
        return shownNotes.size();
    }

    @Override
    public Object getItem(int position) {
        return shownNotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return shownNotes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        //is er al een view aangemaakt?
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.note_row, null);
            holder.dateTV = (TextView) convertView.findViewById(R.id.date_tv);
            holder.titleTV = (TextView) convertView.findViewById(R.id.title_tv);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        Note temp = shownNotes.get(position);

        holder.dateTV.setText(temp.getLastModifiedDate());
        holder.titleTV.setText(temp.getTitle());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                shownNotes = (ArrayList<Note>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {


                FilterResults results = new FilterResults();
                ArrayList<Note> filteredArrayNames = new ArrayList<Note>();

                if(constraint.length() == 0)
                {
                    filteredArrayNames = allNotes;
                }
                else {
                    for (Note n : allNotes) {
                        if (n.getTitle().contains(constraint))
                            filteredArrayNames.add(n);
                    }
                }
                results.count = filteredArrayNames.size();
                results.values = filteredArrayNames;

                return results;
            }
        };

        return filter;
    }
}