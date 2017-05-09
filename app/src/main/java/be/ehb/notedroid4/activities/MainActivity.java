package be.ehb.notedroid4.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import be.ehb.notedroid4.R;
import be.ehb.notedroid4.fragments.AboutFragment;
import be.ehb.notedroid4.fragments.MainFragment;
import be.ehb.notedroid4.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //backstack name, used for adding fragments to back button
    private final String FRAGMENT_BACKSTACK = "fragment_backstack";
    //reference to drawer (navigation)
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navigationHeader = navigationView.getHeaderView(0);
        TextView tvUsername = (TextView) navigationHeader.findViewById(R.id.tv_nav_username);
        tvUsername.setText("9Gag");
        TextView tvEmail = (TextView) navigationHeader.findViewById(R.id.tv_nav_email);
        tvEmail.setText("9Gag@hotmail.com");

        MainFragment mainFragment =  MainFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mainFragment)
                .addToBackStack(FRAGMENT_BACKSTACK)
                .commit();

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.nav_main:
                //MainActivity.this.getSupportFragmentManager().beginTransaction()
                // .replace(R.id.container, MainFragment.newInstance()).commit();

                MainFragment mainFragment =  MainFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, mainFragment)
                        .addToBackStack(FRAGMENT_BACKSTACK)
                        .commit();

                break;
            case R.id.nav_pref:
                SettingsFragment settingsFragment =  SettingsFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, settingsFragment)
                        .addToBackStack(FRAGMENT_BACKSTACK)
                        .commit();
                break;
            case R.id.nav_about:
                AboutFragment aboutFragment = AboutFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, aboutFragment)
                        .addToBackStack(FRAGMENT_BACKSTACK)
                        .commit();
                break;
        }

        drawer.closeDrawer(Gravity.START);

        return true;
    }
}
