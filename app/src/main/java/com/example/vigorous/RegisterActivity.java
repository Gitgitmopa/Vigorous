package com.example.vigorous;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;

public class RegisterActivity extends AppCompatActivity {

    //Variables
    Button buttonLogin;
    TextInputLayout fullName, userName, passWord;
    TextInputEditText toast;
    ImageView back2;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    Dialog dialog;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private static final int LOADING = 5000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        //Assign variables and Find the ID in xml
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        buttonLogin = findViewById(R.id.login_now_reg);
        back2 = findViewById(R.id.back2);
        fullName =  findViewById(R.id.inputName);
        userName = findViewById(R.id.inputUser);
        passWord = findViewById(R.id.inputPassword);
        toast = findViewById(R.id.toast);

        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FancyToast.makeText(RegisterActivity.this,"Please add special character !",FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();

            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,SignAndRegActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left_animation,R.anim.slide_out_right);
                finish();
            }
        });
    }


    //Validation in registration
    private Boolean validateName() {
        String val = fullName.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            fullName.setError("Field cannot be empty");
            return false;
        } else {
            fullName.setError(null); //remove the error
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = userName.getEditText().getText().toString().trim();
        String noWhiteSpaces = "\\A\\w{1,20}\\z"; //check if have any spaces

        if (val.isEmpty()) {
            userName.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            userName.setError("Username is too long");
            return false;
        } else if (!val.matches(noWhiteSpaces)) {
            userName.setError("White spaces is not allowed");
            return false;
        } else {
            userName.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = passWord.getEditText().getText().toString().trim();
        String passwordVal = "^" +

                // "(?=.*[0-9])"+         // at least 1 digit
                // "(?=.*[a-z])"+         //at least 1 lower case letter
                //  "(?=.*[A-Z])"+         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +       //Any letter
                "(?=.*[@#$%^&+=])" +     //at least 1 spacial character
                "(?=\\S+$)" +            //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            passWord.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            passWord.setError("Password is too weak");
            return false;
        } else {
            passWord.setError(null);
            return true;
        }
    }

    //Onclick button
    public void btnReg(View view) {
        if (!validateName() | !validateUsername() | !validatePassword()) { // if no valid
            return;
        }
        rootNode = FirebaseDatabase.getInstance(); // called the instance to firebase
        reference = rootNode.getReference("users"); // get the reference// to get or stored data to firebase

        progressDialog.setTitle("Please wait!!");
        progressDialog.setMessage("Process!");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String name = fullName.getEditText().getText().toString();
                String username = userName.getEditText().getText().toString();
                String password = passWord.getEditText().getText().toString();
                Users users = new Users(name,username,password); //helper class to store the data from the user
                reference.child(username).setValue(users); //set the value and passed the database
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                intent.putExtra("fullName", name);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                startActivity(intent);
                finish();
            }
        },LOADING);

    }
}