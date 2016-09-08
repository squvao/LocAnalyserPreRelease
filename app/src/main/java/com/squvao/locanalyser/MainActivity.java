package com.squvao.locanalyser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private Button buttonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        initToolbar();
        initFloatingActionButton();
        initNavigationView();

        buttonPlay = (Button) findViewById(R.id.content_main_button_play);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initFloatingActionButton() {
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.action_bar_drawer_toggle_open,R.string.action_bar_drawer_toggle_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState(); // синхронизация с NavigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        /*
        Установка обработчика нажатия на эллемент NavigationView
         */
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) { //обрабатывающий метод (выполняется тогда, когда пользователь выбрал эллемент меню)
                drawerLayout.closeDrawers(); // закрывает полку
                int id = item.getItemId();
                switch (id){
                    case R.id.sub_menu_navigation_view_about_us:
                        Intent intent = new Intent(MainActivity.this,AboutUsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.menu_navigation_view_help:
                        Intent intent2 = new Intent(MainActivity.this,HelpActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.menu_navigation_view_feedback:
                        ShareCompat.IntentBuilder.from(MainActivity.this).setType("message/rfc822")
                                .addEmailTo(getString(R.string.main_activity_navigation_item_feedback_email))
                                .setSubject(getString(R.string.app_name))
                                .setText(getString(R.string.main_activity_navigation_item_feedback_message))
                                .setChooserTitle(getString(R.string.main_activity_navigation_item_feedback_title))
                                .startChooser();
                        break;
                    case R.id.menu_navigation_view_settings:
                        Intent intent1 = new Intent(MainActivity.this,SettingsActivity.class);
                        startActivity(intent1);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
