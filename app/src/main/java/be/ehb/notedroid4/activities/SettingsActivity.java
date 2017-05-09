package be.ehb.notedroid4.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import be.ehb.notedroid4.R;
import be.ehb.notedroid4.fragments.SettingsFragment;

/**
 * Created by MVH on 21-4-2017.
 */

public class SettingsActivity extends AppCompatActivity {

    private SettingsFragment mSettingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref);

        mSettingsFragment = SettingsFragment.newInstance();

        //getSupportFragmentManager().beginTransaction().add(R.id.container, new mSettingsFragment()).commit();

    }
}