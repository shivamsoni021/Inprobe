package com.example.notes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class DownloadActivity extends AppCompatActivity {

    FirebaseStorage storage;
    StorageReference storageReference;
    StorageReference pathReference;
    PDFView pdfView;
    private Toolbar dtoolbar;
    private Button dsignOutButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        pdfView= (PDFView) findViewById(R.id.pdfView);
        dtoolbar=(Toolbar)findViewById(R.id.dtoolbar);
        dsignOutButton=(Button)findViewById(R.id.dsignOutButton);

        progressDialog = new ProgressDialog(DownloadActivity.this);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Get Ready to Learn");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);


        setSupportActionBar(dtoolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

        Intent intent = getIntent();
        String downloadFile = intent.getStringExtra("downloadRequest");
        String initial = intent.getStringExtra("reference");

        pathReference = storageReference.child(initial + downloadFile + ".pdf");
        setTitle(downloadFile);
        try {

            final File file = File.createTempFile("tempFile" , "pdf");
            pathReference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    pdfView.fromFile(file).load();
                    Toast.makeText(DownloadActivity.this, "Zoom in for proper reading", Toast.LENGTH_LONG).show();
     }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        dsignOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new androidx.appcompat.app.AlertDialog.Builder(DownloadActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Sign Out")
                        .setMessage("Do you want to sign out?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                FirebaseAuth auth = FirebaseAuth.getInstance();
                                auth.signOut();
                                Intent intent = new Intent(DownloadActivity.this,MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("No",null)
                        .show();

            }
        });

    }


    }
