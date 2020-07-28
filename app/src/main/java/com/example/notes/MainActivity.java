package com.example.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signupButton;
    private static final String TAG = "MainActivity";
    private static final int RC_SIGN_IN = 1;
    private SignInButton googleSignInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private Button signOutButton;
    private TextView signUpLink;
    private Button loginButton;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        //Getting all the views

        emailEditText = (EditText) findViewById(R.id.emailText);
        passwordEditText = (EditText) findViewById(R.id.passwordText);
        signUpLink = (TextView) findViewById(R.id.signupLink);
        loginButton = (Button) findViewById(R.id.loginButton);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        //signin button

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUser();
            }
        });

        //Google Sign IN
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.web_client_id))
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

//        Navigating to Registration Activity

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

//
//        googleSignInButton.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        signIn();
//                    }
//                });
//
//    }
//
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null ){

        }
        else{
            Intent intent = new Intent(MainActivity.this,BranchesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        //  updateUI(currentUser);
    }

    private void isUser() {

        String email = emailEditText.getText().toString().trim();
        String password= passwordEditText.getText().toString().trim();

        String validEmail = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@"+
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pattern = Pattern.compile(validEmail);

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Field cannot be Empty");
            return;
        } else if (!(pattern.matcher(email).matches())) {
            emailEditText.setError("Email Address is not Valid");
        }else if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Field cannot be Empty");
            return;
        } else{

            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                redirectBranch();
                            } else {
                                progressDialog.dismiss();
                                loginButton.setError("Combination does not matches! Try Again");
                                Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
    }

}

    private void redirectBranch() {
        Intent intent = new Intent(getApplicationContext(),BranchesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}

////  GoogleSignIn Code Starts
//
//    private void signIn() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
////            handleSignInResult(task);
//        }
//    }
//
//    private void handleSignInResult(Task<GoogleSignInAccount> completeTask) {
//        try {
//            GoogleSignInAccount acc = completeTask.getResult(ApiException.class);
//          //  Toast.makeText(MainActivity.this, "SignIN", Toast.LENGTH_SHORT).show();
////            redirectBranch();
//            FirebaseGoogleAuth(acc);
//        } catch (ApiException e) {
//            Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//            FirebaseGoogleAuth(null);
//        }
//    }
//
//    private void FirebaseGoogleAuth(GoogleSignInAccount account) {
//        AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
//        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                   // Toast.makeText(MainActivity.this, "SkignIN", Toast.LENGTH_SHORT).show();
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    updateUI(user);
//                } else {
//
//                    updateUI(null);
//                }
//            }
//        });
//    }
//
//    private void updateUI(FirebaseUser firebaseUser) {
//         GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
//        if (account != null) {
//            String personName = account.getDisplayName();
//            Toast.makeText(this, "Jao" + personName, Toast.LENGTH_SHORT).show();
////            redirectBranch();
//        }


//    GoogleSignIn Code End Here

