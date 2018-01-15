package com.incognisyssolutions.mathformulas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


public class DetailsActivity extends AppCompatActivity {
    String post_link,title;
    LinearLayout nointernet;
    Boolean isInternetPresent = false;
    private ProgressBar progress;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences bundle = getSharedPreferences("app_details", Context.MODE_PRIVATE);
        data = bundle.getString("content",null);
        title = bundle.getString("title",null);
        setTitle(bundle.getString("title",null));


        WebView webView = (WebView)this.findViewById(R.id.wb);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL("", data, "text/html", "UTF-8", "");

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
        if (id == R.id.share) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/*");
            //intent.setData(Uri.parse("mailto:"));
            //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Sent from Maths Formulas");
            intent.putExtra(Intent.EXTRA_TEXT,data);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
            finish();
            return true;
        }

//

        return super.onOptionsItemSelected(item);
    }



//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                try {
//
//                        finish();
//
//                } catch (Exception e) {
//                    //Toast.makeText(getApplicationContext(),e.getMessage()+"",Toast.LENGTH_SHORT).show();
//                }
//                break;
//            default:

//        }
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public void onBackPressed() {
        try {

                finish();

        } catch (Exception e) {
            //Toast.makeText(getApplicationContext(),e.getMessage()+"",Toast.LENGTH_SHORT).show();
        }
        super.onBackPressed();

    }
}
