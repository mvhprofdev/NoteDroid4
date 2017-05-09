package be.ehb.notedroid4.fragments;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import be.ehb.notedroid4.R;

/**
 * Created by davidvansteertegem on 27/04/2017.
 */

public class SettingsFragment extends PreferenceFragmentCompat {

    public static SettingsFragment newInstance(){
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.prefs);
    }
}
