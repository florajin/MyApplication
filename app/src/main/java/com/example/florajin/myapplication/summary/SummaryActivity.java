package com.example.florajin.myapplication.summary;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.florajin.myapplication.R;
import com.example.florajin.myapplication.modal.Summary;
import com.example.florajin.myapplication.summary.adapter.SummaryItemAdapter;
import com.example.florajin.myapplication.summary.adapter.UserSummaryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.example.florajin.myapplication.login.LoginActivity.prefKey;

public class SummaryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //private static final String TAG = "SummaryActivity";
   // @BindView(R.id.summary_recycler_view)
   // RecyclerView summaryRecyclerView;

    //@BindView(R.id.drawer_layout)
   // DrawerLayout mDrawerLayout;

    //@BindView(R.id.toolbar)
    //Toolbar toolbar;

    //@BindView(R.id.nav_view)
    //NavigationView navView;
    private DrawerLayout mDrawerLayout;
    private TextView loginUsername;
    private List<Summary> summaryList = new ArrayList<>();
    private List<String> itemList = new ArrayList<>();
    private SummaryItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initSummaryData();
        initSummary();
        initDrawerLayer();
    }

    private void initSummaryData(){
        itemList.add("subcategory1");
        itemList.add("subcategory2");
        Summary data1= new Summary("Certificate", itemList);
        summaryList.add(data1);
        Summary data2= new Summary("Tool", itemList);
        summaryList.add(data2);
    }
    private void initSummary(){

        RecyclerView summaryRecyclerView = (RecyclerView)findViewById(R.id.summary_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        summaryRecyclerView.setLayoutManager(layoutManager);

        UserSummaryAdapter summaryAdapter = new UserSummaryAdapter(summaryList, SummaryActivity.this);
        summaryRecyclerView.setAdapter(summaryAdapter);

        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_line));
        summaryRecyclerView.addItemDecoration(divider);
    }

    private void initDrawerLayer(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setCheckedItem( R.id.nav_import);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        View headerLayout = navView.inflateHeaderView(R.layout.nav_header);
        loginUsername = (TextView) headerLayout.findViewById(R.id.login_username);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sp.getString(prefKey,"No username");
        loginUsername.setText("Hi "+ username);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(R.id.nav_view)) {
            mDrawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       /* switch (item.getItemId()){
            case add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
        }*/
        return true;

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_import) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mDrawerLayout.closeDrawers();
        return true;
    }
}
