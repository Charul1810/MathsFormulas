package com.incognisyssolutions.mathformulas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    DatabaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        this.expListView = (ExpandableListView) findViewById(R.id.lvExp);



        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();


        // preparing list data

        prepareListData();

//        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
//
//        // setting list adapter
//        expListView.setAdapter(listAdapter);
//
//        // Listview Group click listener
//        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v,
//                                        int groupPosition, long id) {
//                // Toast.makeText(getApplicationContext(),
//                // "Group Clicked " + listDataHeader.get(groupPosition),
//                // Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//
//        // Listview Group expanded listener
//        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // Listview Group collasped listener
//        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });
        //databaseAccess.close();
        listAdapter=new ExpandableListAdapter(this,listDataHeader,listDataChild);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
        this.expListView.setAdapter(listAdapter);
        // Listview on child click listener

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                SharedPreferences.Editor bundle = getSharedPreferences("app_details", Context.MODE_PRIVATE).edit();
                bundle.putString("title",listDataChild.get(listDataHeader.get(groupPosition)).get(
                                childPosition));
                String s=databaseAccess.getFormula(listDataChild.get(
                        listDataHeader.get(groupPosition)).get(
                        childPosition));
                bundle.putString("content",s);
                bundle.apply();
                startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                finish();



                return false;
            }
        });


    }


    /*
     * Preparing the list data
     */
    private void prepareListData() {
        List<String> categories = databaseAccess.getCategories();

        List<String> algebra=databaseAccess.getSubCategories(1);
        List<String> algebraList = new ArrayList<String>();
        List<String> geometry=databaseAccess.getSubCategories(2);
        List<String> geometryList = new ArrayList<String>();
        List<String> trigonometry=databaseAccess.getSubCategories(3);
        List<String> trigonometryList = new ArrayList<String>();
        List<String> derivative=databaseAccess.getSubCategories(4);
        List<String> derivativeList = new ArrayList<String>();
        List<String> integration=databaseAccess.getSubCategories(5);
        List<String> integrationList = new ArrayList<String>();
        List<String> analytic=databaseAccess.getSubCategories(6);
        List<String> analyticList = new ArrayList<String>();
        List<String> probability=databaseAccess.getSubCategories(7);
        List<String> probabilityList = new ArrayList<String>();
        List<String> laplace=databaseAccess.getSubCategories(8);
        List<String> laplaceList = new ArrayList<String>();
        List<String> fourier=databaseAccess.getSubCategories(9);
        List<String> fourierList = new ArrayList<String>();
        List<String> series=databaseAccess.getSubCategories(10);
        List<String> seriesList = new ArrayList<String>();
        List<String> numericalMethods=databaseAccess.getSubCategories(11);
        List<String> numericalMethodsList = new ArrayList<String>();
        List<String> vector=databaseAccess.getSubCategories(12);
        List<String> vectorList = new ArrayList<String>();
        List<String> beta=databaseAccess.getSubCategories(13);
        List<String> betaList = new ArrayList<String>();
        List<String> zTransform=databaseAccess.getSubCategories(14);
        List<String> zTransformList = new ArrayList<String>();
        List<String> others=databaseAccess.getSubCategories(15);
        List<String> othersList = new ArrayList<String>();


        //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

        //Toast.makeText(getApplicationContext(),algebra.get(0),Toast.LENGTH_LONG).show();
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        for(int i=0;i<categories.size();i++)
        {
            listDataHeader.add(categories.get(i));
        }

//        Toast.makeText(getApplicationContext(),algebra.size()+" - " + geometry.size(),Toast.LENGTH_LONG).show();

        for (int i=0;i<algebra.size();i++){
            algebraList.add(algebra.get(i));
        }
        for (int i=0;i<geometry.size();i++){
            geometryList.add(geometry.get(i));
        }
        for (int i=0;i<trigonometry.size();i++){
            trigonometryList.add(trigonometry.get(i));
        }
        for (int i=0;i<derivative.size();i++){
            derivativeList.add(derivative.get(i));
        }
        for (int i=0;i<integration.size();i++){
            integrationList.add(integration.get(i));
        }
        for (int i=0;i<analytic.size();i++){
            analyticList.add(analytic.get(i));;
        }
        for (int i=0;i<probability.size();i++){
            probabilityList.add(probability.get(i));
        }
        for (int i=0;i<laplace.size();i++){
            laplaceList.add(laplace.get(i));
        }
        for (int i=0;i<fourier.size();i++){
            fourierList.add(fourier.get(i));
        }
        for (int i=0;i<series.size();i++){
            seriesList.add(series.get(i));
        }
        for (int i=0;i<numericalMethods.size();i++){
            numericalMethodsList.add(numericalMethods.get(i));
        }
        for (int i=0;i<vector.size();i++){
            vectorList.add(vector.get(i));
        }
        for (int i=0;i<beta.size();i++){
            betaList.add(beta.get(i));
        }
        for (int i=0;i<zTransform.size();i++){
            zTransformList.add(zTransform.get(i));
        }
        for (int i=0;i<others.size();i++){
            othersList.add(others.get(i));
        }

        listDataChild.put(listDataHeader.get(0), algebraList); // Header, Child data
        listDataChild.put(listDataHeader.get(1), geometryList);
        listDataChild.put(listDataHeader.get(2), trigonometryList);
        listDataChild.put(listDataHeader.get(3), derivativeList);
        listDataChild.put(listDataHeader.get(4), integrationList);
        listDataChild.put(listDataHeader.get(5), analyticList);
        listDataChild.put(listDataHeader.get(6), probabilityList);
        listDataChild.put(listDataHeader.get(7), laplaceList);
        listDataChild.put(listDataHeader.get(8), fourierList);
        listDataChild.put(listDataHeader.get(9), seriesList);
        listDataChild.put(listDataHeader.get(10), numericalMethodsList);
        listDataChild.put(listDataHeader.get(11), vectorList);
        listDataChild.put(listDataHeader.get(12), betaList);
        listDataChild.put(listDataHeader.get(13), zTransformList);
        listDataChild.put(listDataHeader.get(14), othersList);









    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        prepareListData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_search){
            startActivity(new Intent(getApplicationContext(),SearchActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
