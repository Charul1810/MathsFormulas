package com.incognisyssolutions.mathformulas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    ListView lv;
    EditText inputSearch;
    ArrayAdapter<String> search_list;
    DatabaseAccess databaseAccess;
    List<String> searchTopics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.search_topic);

        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        searchTopics = databaseAccess.getTopics();
        //Toast.makeText(getApplicationContext(),searchTopics.get(1),Toast.LENGTH_LONG).show();
        search_list=new ArrayAdapter<String>(this,R.layout.list_item_search,R.id.product_name,searchTopics);
        lv.setAdapter(search_list);





        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public  void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                SearchActivity.this.search_list.getFilter().filter(cs);
//                search_list=new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item_search,R.id.product_name,searchTopics);
//                lv.setAdapter(search_list);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub
//                search_list=new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item_search,R.id.product_name,searchTopics);
//                lv.setAdapter(search_list);

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
//                search_list=new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item_search,R.id.product_name,searchTopics);
//                lv.setAdapter(search_list);

            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor bundle = getSharedPreferences("app_details", Context.MODE_PRIVATE).edit();
                bundle.putString("title",search_list.getItem(position));
                String s=databaseAccess.getFormula(search_list.getItem(position));
                bundle.putString("content",s);
                bundle.apply();
                startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                finish();

            }
        });



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //case android.R.id.home:
        if(id == android.R.id.home)
            try {

                finish();

            } catch (Exception e) {
                //Toast.makeText(getApplicationContext(),e.getMessage()+"",Toast.LENGTH_SHORT).show();
            }
        //break;
        //default:


        //noinspection SimplifiableIfStatement


//

        return super.onOptionsItemSelected(item);
    }

}
