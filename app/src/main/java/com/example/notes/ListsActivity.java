package com.example.notes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.auth.FirebaseAuth;

public class ListsActivity extends AppCompatActivity {

    private ListView list;
    private Toolbar ltoolbar;
    private Button lsignoutButton;
    private AdView mAdView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        setTitle("Notes List");
        mAdView = (AdView) findViewById(R.id.ffAdView);

        list = (ListView)findViewById(R.id.subjectsList);
        ltoolbar = (Toolbar)findViewById(R.id.ltoolbar);
        lsignoutButton=(Button)findViewById(R.id.lsignOutButton);


        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_bar);
        setSupportActionBar(ltoolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        String[] units = intent.getStringArrayExtra("subject");
        final String ref = intent.getStringExtra("ref");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,units);
        list.setAdapter(arrayAdapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String downloadFile = arrayAdapter.getItem(position);
                Intent downloadIntent = new Intent(ListsActivity.this , DownloadActivity.class);
                downloadIntent.putExtra("reference" , ref);
                downloadIntent.putExtra("downloadRequest", downloadFile);
                startActivity(downloadIntent);

            }
        });

        lsignoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new androidx.appcompat.app.AlertDialog.Builder(ListsActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Sign Out")
                        .setMessage("Do you want to sign out?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                FirebaseAuth auth = FirebaseAuth.getInstance();
                                auth.signOut();
                                Intent intent = new Intent(ListsActivity.this,MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("No",null)
                        .show();

            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String year;
        switch(item.getItemId()){
            case android.R.id.home :
                Intent getIntent = getIntent();
                final String ref2 = getIntent.getStringExtra("ref");

                if(ref2.contains("firstyear")){
                    Intent intent = new Intent(ListsActivity.this,FirstYearActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else if(ref2.contains("secondyear")){
                    Intent intent = new Intent(ListsActivity.this,SecondYearActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else if(ref2.contains("thirdyear")){
                    Intent intent = new Intent(ListsActivity.this,ThirdYearActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

//                Intent intent = new Intent(ListsActivity.this,BranchesActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}