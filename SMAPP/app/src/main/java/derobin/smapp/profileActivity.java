package derobin.smapp;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.facebook.FacebookSdk;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class profileActivity extends AppCompatActivity {

    //file profile;
    User user;
    ActionBarDrawerToggle adt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //TOOLBAR:
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        //String[] menuitems = getResources().getStringArray(R.array.menuItems);
        final DrawerMenu drawerMenu = new DrawerMenu();
        DrawerLayout dlay = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView drawer = (ListView) findViewById(R.id.list_slidermenu);
        drawer.setAdapter(new ArrayAdapter<String>(this, R.layout.menu_list_item_cust, drawerMenu.getMenuItems()));

        adt = new ActionBarDrawerToggle(this, dlay, null, R.string.app_name, R.string.app_name) {

        };
        dlay.addDrawerListener(adt);
        drawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(drawerMenu.getMenuToOpen(getApplicationContext(), position));
            }
        });

        //toolbar.setTitle("SMAPP");

        user = new User(this);
    }

    @Override
    public void onPostCreate(Bundle sis) {
        super.onPostCreate(sis);
        adt.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration c) {
        super.onConfigurationChanged(c);
        adt.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (adt.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void infoPullComplete() {
        Log.d("[FB]", "User object: " + user.toString());
    }
}
