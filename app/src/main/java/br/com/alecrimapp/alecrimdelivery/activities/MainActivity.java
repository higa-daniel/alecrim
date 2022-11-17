package br.com.alecrimapp.alecrimdelivery.activities;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.alecrimapp.alecrimdelivery.BuildConfig;
import br.com.alecrimapp.alecrimdelivery.R;
import br.com.alecrimapp.alecrimdelivery.fragments.OrderInfoFragment;
import br.com.alecrimapp.alecrimdelivery.fragments.OrdersFragment;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        OrdersFragment.OnFragmentInteractionListener,
        OrderInfoFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Local variables
        OrdersFragment ordersFragment = null;
        FragmentTransaction fragmentTransaction = null;
        Toolbar toolbar = null;
        DrawerLayout drawer = null;
        ActionBarDrawerToggle toggle = null;
        NavigationView navigationView = null;
        SharedPreferences sp = null;
        TextView txtUserEmail = null;

        sp = getSharedPreferences(BuildConfig.PREFS_NAME, MODE_PRIVATE);

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        txtUserEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.txtUser);

        txtUserEmail.setText(sp.getString("txtUserEmail", "usuario"));

        if (savedInstanceState == null) {
            ordersFragment = new OrdersFragment();

            addFragment(ordersFragment);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            // TODO: logout or close app
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void addFragment(@NonNull Fragment fragment) {
        super.addFragment(R.id.vwFragment, fragment);
    }


    public void replaceFragment(@NonNull Fragment fragment, @Nullable String backStackStateName) {
        super.replaceFragment(R.id.vwFragment, fragment, backStackStateName);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
