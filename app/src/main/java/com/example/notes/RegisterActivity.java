package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity{

    private Button signupButton;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private FirebaseUser cuser;
    private DatabaseReference mDatabase;
    private RadioButton radioButton;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText contactEditText;
    private EditText passwordEditText;
    private Spinner branchSpinner;
    private Spinner yearSpinner;
    private RadioGroup permissionChoice;
    private String[] branches = {"Select-Branch","Computer Science","Information Technology"};
    private String[] years = {"Select-Year","1","2","3"};
    private TextView loginLink;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);


        mAuth = FirebaseAuth.getInstance();

        //Getting all the views

        nameEditText = (EditText)findViewById(R.id.nameEditText);
        emailEditText=(EditText)findViewById(R.id.emailEditText2);
        contactEditText =(EditText)findViewById(R.id.contactEditText);
        passwordEditText=(EditText)findViewById(R.id.passwordEditText2);
        branchSpinner=(Spinner)findViewById(R.id.branchSpinner);
        yearSpinner=(Spinner)findViewById(R.id.yearSpinner);
        permissionChoice=(RadioGroup)findViewById(R.id.radioGroup);
        signupButton=(Button)findViewById(R.id.signupButton);
        loginLink=(TextView)findViewById(R.id.loginLink);

        //Setting Intent for Login Page

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        //Setting List to Branch and Year Spinner

        ArrayAdapter branchAdapter = new ArrayAdapter(RegisterActivity.this,android.R.layout.simple_spinner_item,branches);
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchSpinner.setAdapter(branchAdapter);
        ArrayAdapter yearAdapter = new ArrayAdapter(RegisterActivity.this,android.R.layout.simple_spinner_item,years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        //Setting click listener for signup Button

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    // Method for registering the user

    private void registerUser() {
        int selectedId = permissionChoice.getCheckedRadioButtonId();
        radioButton = (RadioButton) permissionChoice.findViewById(selectedId);
        final String name = nameEditText.getText().toString().trim();
        final String email = emailEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();
        final String contact = contactEditText.getText().toString().trim();
        final String branch = branchSpinner.getSelectedItem().toString();
        final String year = yearSpinner.getSelectedItem().toString();
        final String permissionOption = radioButton.getText().toString().trim();

        String validEmail = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@"+
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";

        Pattern pattern = Pattern.compile(validEmail);

        //Validation for the inputs

        if (TextUtils.isEmpty(name)) {
            nameEditText.requestFocus();
            nameEditText.setError("Field cannot be Empty");
            return;
        } else if (!name.matches("[a-zA-Z ]+")) {
            nameEditText.requestFocus();
            nameEditText.setError("Enter only Alphabetical Characters");
        }
          else if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Field cannot be Empty");
            return;
        } else if (!(pattern.matcher(email).matches())) {
            emailEditText.setError("Email Address is not Valid");
        }

        else if (TextUtils.isEmpty(contact)) {
            contactEditText.setError("Field cannot be Empty");
            return;
        } else if (!(contact.length() == 10)) {
            contactEditText.setError("Contact Number is not Valid");
        }
        else if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Field cannot be Empty");
            return;
        } else if (password.length() < 8) {
            passwordEditText.setError("Password length must be 8 chracters");
        }

        else if (branch.equals("Select-Branch")) {
            ((TextView) branchSpinner.getSelectedView()).setError("Please Select Brancg");
        }

        else if (year == "Select-Year") {
            ((TextView) branchSpinner.getSelectedView()).setError("Please Select Year");
        }
else {

        //Authenticating the user in Firebase

            mAuth.createUserWithEmailAndPassword(email , password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {

                        @Override
                        public void onSuccess(AuthResult authResult) {
                            progressDialog.show();

                            mAuth.signInWithEmailAndPassword(email, password)
                                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                        @Override
                                        public void onSuccess(AuthResult authResult) {
                                           FirebaseUser cuser= FirebaseAuth.getInstance().getCurrentUser();
                                           String userId  = cuser.getUid();
                                            database = FirebaseDatabase.getInstance();
                                            mDatabase = database.getReference("users");

                                            User user = new User(name, email, contact, password, branch, year, permissionOption);
                                            mDatabase.child(userId).setValue(user);
                                            progressDialog.dismiss();

                                            Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(),BranchesActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressDialog.dismiss();
                                            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);

                                        }
                                    });

                        }
                    })
                    //Progress Bar
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Registration Failed! Please try Again Later", Toast.LENGTH_SHORT).show();
                        }
                    });




//
//
//            mAuth.createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//
//                            } else {
//
//                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
//                            }
//
//                        }
//                    });


}
    }



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //  updateUI(currentUser);
    }
}
