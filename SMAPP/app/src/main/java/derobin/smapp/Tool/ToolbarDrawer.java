package derobin.smapp.Tool;

import android.content.res.Configuration;
import android.os.*;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import derobin.smapp.R;

/**
 * Created by Robin on 4/16/2016.
 */
public class ToolbarDrawer extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private DrawerLayout drawLay;
    private ListView drawerList;
    private ActionBarDrawerToggle adt;
    private DrawerMenu menuConfig;
    private Toolbar toolbar;

    protected void onCreate(Bundle sis) {
        super.onCreate(sis);
    }

    protected void setupToolBar() {
        //setContentView(R.layout.toolbar_drawer_base);
        menuConfig = new DrawerMenu();
        drawLay = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.drawer_menu);
        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.menu_list_item_cust, menuConfig.getMenuItems()));


        adt = new ActionBarDrawerToggle(this, drawLay, null, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view)
            {
                getSupportActionBar().setTitle(R.string.app_name);
            }

            public void onDrawerOpened(View drawerView)
            {
                getSupportActionBar().setTitle(R.string.app_name);
            }
        };
        drawLay.addDrawerListener(adt);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerList.setOnItemClickListener(this);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (adt.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        adt.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        adt.onConfigurationChanged(newConfig);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        menuConfig.OpenMenu(this, position);
    }
}
