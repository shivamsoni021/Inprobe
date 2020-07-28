package com.example.notes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class SecondYearActivity extends AppCompatActivity {

    private ImageView dcImage;
    private ImageView discreteImage;
    private ImageView pplImage;
    private ImageView adaImage;
    private ImageView cjImage;
    private ImageView phpImage;
    private ImageView cnImage;
    private ImageView ajImage;
    private ImageView dbmsImage;
    private ImageView osImage;
    private ImageView eesImage;
    private ImageView androidImage;
    private Toolbar stoolbar;
    private Button ssignoutButton;

    String[] dcNotes = {};
    String[] discreteNotes = {};
    String[] pplNotes = {};
    String[] adaNotes = {};
    String[] cjNotes = {};
    String[] phpNotes = { };
    String[] cnNotes = {};
    String[] ajNotes = {  };
    String[] dbmsNotes = {  };
    String[] osNotes = {};
    String[] eesNotes = {};
    String[] androidNotes = { };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_year);


        dcImage= (ImageView)findViewById(R.id.dcImage);
        discreteImage = (ImageView)findViewById(R.id.discreteImage);
        pplImage = (ImageView)findViewById(R.id.pplImage);
        adaImage = (ImageView)findViewById(R.id.adaImage);
        cjImage = (ImageView)findViewById(R.id.cjImage);
        phpImage = (ImageView)findViewById(R.id.phpImage);
        cnImage = (ImageView)findViewById(R.id.cnImage);
        ajImage = (ImageView)findViewById(R.id.ajImage);
        dbmsImage = (ImageView)findViewById(R.id.dbmsImage);
        osImage = (ImageView)findViewById(R.id.osImage);
        eesImage = (ImageView)findViewById(R.id.eesImage);
        androidImage = (ImageView)findViewById(R.id.androidImage);
        stoolbar = (Toolbar)findViewById(R.id.stoolbar);
        ssignoutButton=(Button)findViewById(R.id.ssignOutButton);


        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_bar);
        setSupportActionBar(stoolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dcImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SecondYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","secondyear/dc/");
                intent.putExtra("subject", dcNotes);
                startActivity(intent);
            }
        });

        discreteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SecondYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","secondyear/discrete/");
                intent.putExtra("subject", discreteNotes);
                startActivity(intent);
            }
        });

        pplImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SecondYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","secondyear/ppl/");
                intent.putExtra("subject", pplNotes);
                startActivity(intent);
            }
        });

        adaImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SecondYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","secondyear/ada/");
                intent.putExtra("subject", adaNotes);
                startActivity(intent);
            }
        });

        cjImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SecondYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","secondyear/cj/");
                intent.putExtra("subject", cjNotes);
                startActivity(intent);
            }
        });

        phpImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SecondYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","secondyear/php/");
                intent.putExtra("subject", phpNotes);
                startActivity(intent);
            }
        });

        cnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SecondYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","secondyear/cn/");
                intent.putExtra("subject", cnNotes);
                startActivity(intent);
            }
        });

        ajImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SecondYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","secondyear/aj/");
                intent.putExtra("subject", ajNotes);
                startActivity(intent);
            }
        });

        dbmsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SecondYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","secondyear/dbms/");
                intent.putExtra("subject", dbmsNotes);
                startActivity(intent);
            }
        });

        osImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SecondYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","secondyear/os/");
                intent.putExtra("subject", osNotes);
                startActivity(intent);
            }
        });

        eesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SecondYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","secondyear/ees/");
                intent.putExtra("subject", eesNotes);
                startActivity(intent);
            }
        });

        androidImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SecondYearActivity.this , ListsActivity.class);
                intent.putExtra("ref","secondyear/cso/");
                intent.putExtra("subject", androidNotes);
                startActivity(intent);
            }
        });

        ssignoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                Intent intent = new Intent(SecondYearActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home :
                Intent intent = new Intent(SecondYearActivity.this,CsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
}
    }
