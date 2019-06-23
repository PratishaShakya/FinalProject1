package com.example.finalproject.Activity;


import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;


import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.finalproject.Application.App;
import com.example.finalproject.Fragment.CategoriesFragment;
import com.example.finalproject.Fragment.FavoriteFragment;
import com.example.finalproject.Fragment.HomeFragment;
import com.example.finalproject.Adapter.ListViewAdapter;
import com.example.finalproject.Adapter.MainAdapter;
import com.example.finalproject.Fragment.NearMeFragment;
import com.example.finalproject.Generic.Keys;
import com.example.finalproject.R;
import com.example.finalproject.SingleHorizontal;

import java.security.Key;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    ViewFlipper v_flipper;
    ListView listView;
    ListViewAdapter adapter;
    //String[] title;
    //String[] description;
    //int[] icon;
    private ArrayList<Object> objects=new ArrayList<>();

   // ArrayList<Models> arrayList = new ArrayList<Models>();


    //String[] localguide={"Roshan","Pratisha","Prashant"};
    //int[] guide={R.drawable.ic_login,R.drawable.ic_login,R.drawable.ic_login};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//horizontalview
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        MainAdapter adapter = new MainAdapter(this, getObject());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //drawer navigation
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.inflateHeaderView(R.layout.nav_header);

        TextView userName = headerView.findViewById(R.id.userName);
        TextView userEmail = headerView.findViewById(R.id.userEmail);

        if (App.db().getBoolean(Keys.USER_LOGGED_IN)) {
            userName.setText(App.db().getString(Keys.USER_NAME));
            userEmail.setText(App.db().getString(Keys.USER_EMAIL));
        } else {
            userName.setText("Please Log In");
            userEmail.setText("Please Log In");
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


//getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
//navigationView.setCheckedItem(R.id.nav_profile);
        //header


//bottom
        BottomNavigationView bottomNav = findViewById(R.id.button_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();



        int images[] = {R.drawable.arbit, R.drawable.locus, R.drawable.holi};

        v_flipper = findViewById(R.id.v_flipper);

        for (int image : images) {
            flipperImages(image);
        }


      /*  Toolbar toolbar = findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/
//changing action bar of activities

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Evento");


        //list items
      /* title = new String[]{"Sports", "Workshop", "art", "festival"};
        description = new String[]{"sports details...", "Workshop dtails...", "art details...", "festival details..."};
        icon = new int[]{R.drawable.evento, R.drawable.event, R.drawable.b, R.drawable.c};

        listView = findViewById(R.id.listView);

        for (int i = 0 ; i < title.length; i++) {
            Models model = new Models(title[i], description[i], icon[i]);
            //bind all strings in an array
            arrayList.add(model);
        }
        */
//pass rsults to itemviewadapter class
     //  adapter = new ListViewAdapter(this, arrayList);

//bind the adapter to the listview
    //   listView.setAdapter(adapter);

       /* CustomerAdapter customerAdapter=new CustomerAdapter();
        listView.setAdapter(customerAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),Listdataactivtiy.class);
                intent.putExtra("name",localguide);
                intent.putExtra("image",guide);
                startActivity(intent);
            }
        }); */

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_profile:
                // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
              startActivity(new Intent(MainActivity.this, SignInActivity.class));
                break;
            case R.id.nav_notification:
                // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                break;
            case R.id.nav_addevent:
                startActivity(new Intent(MainActivity.this,AddEventActivity.class));
                break;
                case R.id.nav_category:
                startActivity(new Intent(MainActivity.this,CategoryActivity.class));
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private ArrayList<Object> getObject() {

        objects.add(getHorizontalData().get(0));
        objects.add(getHorizontalData1().get(0));
        objects.add(getHorizontalData2().get(0));
        return objects;
    }




    public static ArrayList<SingleHorizontal> getHorizontalData() {
        ArrayList<SingleHorizontal> singleHorizontals = new ArrayList<>();
        singleHorizontals.add(new SingleHorizontal(R.mipmap.locus, "Event", "Grand sale,....", "2019/2/1"));
        singleHorizontals.add(new SingleHorizontal(R.mipmap.timthumb, "Mela", "Mart Opening.", "2019/9/1"));
        singleHorizontals.add(new SingleHorizontal(R.mipmap.stock, "event", "IT WorkShop...", "20109/5/30"));
        return singleHorizontals;
    }
    public static ArrayList<SingleHorizontal> getHorizontalData1() {
        ArrayList<SingleHorizontal> singleHorizontals = new ArrayList<>();
        singleHorizontals.add(new SingleHorizontal(R.mipmap.kathmandu, "Event", "Grand sale,....", "2019/2/1"));
        singleHorizontals.add(new SingleHorizontal(R.mipmap.stock, "Mela", "Mart Opening.", "2019/9/1"));
        singleHorizontals.add(new SingleHorizontal(R.mipmap.kathmandu, "event", "IT WorkShop...", "20109/5/30"));
        return singleHorizontals;
    }
    public static ArrayList<SingleHorizontal> getHorizontalData2() {
        ArrayList<SingleHorizontal> singleHorizontals = new ArrayList<>();
        singleHorizontals.add(new SingleHorizontal(R.mipmap.kathmandu, "Event", "Grand sale,....", "2019/2/1"));
        singleHorizontals.add(new SingleHorizontal(R.mipmap.stock, "Mela", "Mart Opening.", "2019/9/1"));
        singleHorizontals.add(new SingleHorizontal(R.mipmap.kathmandu, "event", "IT WorkShop...", "20109/5/30"));
        return singleHorizontals;
    }




    //status bar
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.commonmenu, menu);

        MenuItem myActionMenu = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) myActionMenu.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    adapter.filter("");
                    listView.clearTextFilter();
                } else {
                    adapter.filter(s);
                }
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mnuNotification) {
            Toast.makeText(this, "Notification menu is clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, NotificationActivity.class));
        } else if (id == R.id.mnuSignIn) {
            Toast.makeText(this, "SignIn menu is clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }



    //listview
    /*private class CustomerAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return localguide.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.row_data,null);
            TextView name=view.findViewById(R.id.events);
            ImageView image=view.findViewById(R.id.imageView);

            name.setText(localguide[i]);
          //  image.setImageResource(guide[i]);

            return view;

        }
    }
    */


    //buttom bar
   private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()) {
                case R.id.mnuHome:
                   // selectedFragment = new HomeFragment();
                 //   HomeFragment homeFragment=new HomeFragment();
                  // getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment).addToBackStack("first frag").commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).addToBackStack("first frag").commit();
                    break;

                case R.id.mnuNearMe:
                   // selectedFragment = new NearMeFragment();
                  //  NearMeFragment nearMeFragmentt=new NearMeFragment();
                   // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,nearMeFragmentt).addToBackStack("second frag").commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NearMeFragment()).commit();

                    break;

                case R.id.mnuFavorite:
                   // selectedFragment = new FavoriteFragment();
                    FavoriteFragment favoriteFragment=new FavoriteFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,favoriteFragment).addToBackStack("third frag").commit();


                    break;
                case R.id.mnucategory:
                   // selectedFragment=new CategoriesFragment();
                 //  CategoriesFragment categoriesFragment=new CategoriesFragment();
                  //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,categoriesFragment).addToBackStack("fourth frag").commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CategoriesFragment()).commit();

            }
           // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

    //

    public void flipperImages(int image)
    {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3000); // 4sec
        v_flipper.setAutoStart(true);

        //animation
        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setInAnimation(this,android.R.anim.slide_out_right);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
        if(getSupportFragmentManager().getBackStackEntryCount() <= 1){
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

}




