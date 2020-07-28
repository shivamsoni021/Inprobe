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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.initialization.InitializationStatus;
//import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirstYearActivity extends AppCompatActivity {

    private ImageView cImage;
    private ImageView cppImage;
    private ImageView moneImage;
    private ImageView mtwoImage;
    private ImageView dlcdImage;
    private ImageView cpiImage;
    private ImageView physicsImage;
    private ImageView icseImage;
    private ImageView cmsImage;
    private ImageView htmlImage;
    private ImageView dsaImage;
    private ImageView csoImage;
    private Toolbar ftoolbar;
    private Button fsignoutButton;


    String[] cNotes = { "Complete C (Recommended)" ,"Introduction to Programming Language","Flow Charts and Algorithms",
                        "Computer System" , "Unit 1" , "Unit 2" , "Unit 3" , "Unit 4" , "Unit 5" , "Viva Questions"  };

    String[] cppNotes = {"Unit 1(Handwritten)","Unit 2(Handwritten)","Unit 3(Handwritten)","Unit 4(Handwritten)",
                          "Unit 5(Handwritten)","Oops Complete"};

    String[] moneNotes = {"Unit 1","Unit 2","Unit 3" , "Unit 4"};
    String[] mtwoNotes = {};
    String[] dlcdNotes = {};
    String[] cpiNotes = { "Unit 1", "Unit 2","Unit 3","Unit 4" , "Unit 5"};
    String[] physicsNotes = {};
    String[] icseNotes = { "Unit 1" , "Unit 2" , "Unit 3" , "Unit 4" , "Unit 5" };

    String[] cmsNotes = {   "Unit 1(HandWritten)","Active and Passive Voice Rules","Articles","Communication and Types",
                            "Debate","Direct Indirect Speech","Email","Essentials of Business Letters", "Listening Skills",
                             "Phrases and Clauses" ,"Presentation and Role Play","Reading","Report" , "Sentence", "Tenses",
                              "Previous Year Paper"  };

    String[] htmlNotes = {};
    String[] dsaNotes = {};
    String[] csoNotes = {"Unit 1","Unit 2" , "Unit 3","Unit 4","Unit 5" , "Questions","Handwritten Notes","Previous Year Paper",
                        "Assignment"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_year);
        setTitle("First Year");

        cImage= (ImageView)findViewById(R.id.cImage);
        cppImage = (ImageView)findViewById(R.id.cppImage);
        moneImage = (ImageView)findViewById(R.id.moneImage);
        mtwoImage = (ImageView)findViewById(R.id.mtwoImage);
        dlcdImage = (ImageView)findViewById(R.id.dlcdImage);
        cpiImage = (ImageView)findViewById(R.id.cpiImage);
        physicsImage = (ImageView)findViewById(R.id.physicsImage);
        icseImage = (ImageView)findViewById(R.id.icseImage);
        cmsImage = (ImageView)findViewById(R.id.cmsImage);
        htmlImage = (ImageView)findViewById(R.id.htmlImage);
        dsaImage = (ImageView)findViewById(R.id.dsaImage);
        csoImage = (ImageView)findViewById(R.id.csoImage);
        ftoolbar = (Toolbar)findViewById(R.id.ftoolbar);
        fsignoutButton=(Button)findViewById(R.id.fsignOutButton);
//        mAdView = (AdView) findViewById(R.id.ffAdView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);


        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_bar);
        setSupportActionBar(ftoolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        cImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FirstYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","firstyear/c/");
                intent.putExtra("subject", cNotes);
                startActivity(intent);
            }
        });

        cppImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FirstYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","firstyear/cpp/");
                intent.putExtra("subject", cppNotes);
                startActivity(intent);
            }
        });

        icseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FirstYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","firstyear/icse/");
                intent.putExtra("subject", icseNotes);
                startActivity(intent);
            }
        });

        cpiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FirstYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","firstyear/cpi/");
                intent.putExtra("subject", cpiNotes);
                startActivity(intent);
            }
        });

        physicsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FirstYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","firstyear/physics/");
                intent.putExtra("subject", physicsNotes);
                startActivity(intent);
            }
        });

        moneImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FirstYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","firstyear/mone/");
                intent.putExtra("subject", moneNotes);
                startActivity(intent);
            }
        });

        mtwoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FirstYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","firstyear/mtwo/");
                intent.putExtra("subject", mtwoNotes);
                startActivity(intent);
            }
        });

        dlcdImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FirstYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","firstyear/dlcd/");
                intent.putExtra("subject", dlcdNotes);
                startActivity(intent);
            }
        });

        htmlImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FirstYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","firstyear/html/");
                intent.putExtra("subject", htmlNotes);
                startActivity(intent);
            }
        });

        cmsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FirstYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","firstyear/cms/");
                intent.putExtra("subject", cmsNotes);
                startActivity(intent);
            }
        });

        dsaImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FirstYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","firstyear/dsa/");
                intent.putExtra("subject", dsaNotes);
                startActivity(intent);
            }
        });

        csoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FirstYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","firstyear/cso/");
                intent.putExtra("subject", csoNotes);
                startActivity(intent);
            }
        });

        fsignoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new androidx.appcompat.app.AlertDialog.Builder(FirstYearActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Sign Out")
                        .setMessage("Do you want to sign out?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                FirebaseAuth auth = FirebaseAuth.getInstance();
                                auth.signOut();
                                Intent intent = new Intent(FirstYearActivity.this,MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("No",null)
                        .show();

            }
        });


//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home :
                Intent intent = new Intent(FirstYearActivity.this,CsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
