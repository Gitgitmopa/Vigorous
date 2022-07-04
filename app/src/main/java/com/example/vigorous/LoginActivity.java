package com.example.vigorous;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    //Variables
    Button btnLogin, btnRegister;
    TextInputLayout userName, passWord;
    TextInputEditText edit_user, edit_pass;
    CheckBox rememberMe;
    ImageView back;
    ProgressDialog progressDialog;
    private static final int LOADING = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Find the Id in xml
        progressDialog = new ProgressDialog(this);
        userName = findViewById(R.id.input_log_Username);
        rememberMe = findViewById(R.id.remember_me);
        back = findViewById(R.id.back);
        passWord = findViewById(R.id.input_log_Password);
        edit_user = findViewById(R.id.text_input_user);
        edit_pass = findViewById(R.id.text_input_pass);
        btnLogin = findViewById(R.id.button_Login);
        btnRegister = findViewById(R.id.btn_reg);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignAndRegActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left_animation,R.anim.slide_out_right);
                finish();
            }
        });

        //CheckBox auto login
        SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);
        String checkBox = preferences.getString("remember","");//To get stored data


        if (checkBox.equals("true")){
            Intent intent = new Intent(LoginActivity.this,LoadingActivity.class);
            startActivity(intent);
        }else if (checkBox.equals("false")){
            Toast.makeText(this, "Please Sign In First", Toast.LENGTH_SHORT).show();
        }


        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Checked", Toast.LENGTH_SHORT).show();

                }else if (!buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "UnChecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Checkbox save username and password
        SharedPref sharedPref = new SharedPref(LoginActivity.this,SharedPref.SHARED_NAME);
        if (sharedPref.checkRememberMe()){
            HashMap<String, String> rememberMeDetails = sharedPref.getDetailsFromRememberMe();
            edit_user.setText(rememberMeDetails.get(SharedPref.KEY_USERNAME));
            edit_pass.setText(rememberMeDetails.get(SharedPref.KEY_PASSWORD));
        }
    }


    //VALIDATION
    //individual function for each TextView/EditText
    private Boolean validateUsernameLog(){
        String val  =  userName.getEditText().getText().toString();

        if(val.isEmpty()){
            userName.setError("Field cannot be empty");
            return false;
        } else {
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = passWord.getEditText().getText().toString();
        if (val.isEmpty()) {
            passWord.setError("Field cannot be empty");
            return false;
        } else {
            passWord.setError(null);
            passWord.setErrorEnabled(false);
            return true;
        }
    }


    public void btn_Login(View view) {
        if(!validateUsernameLog() || !validatePassword()) {
            return;

        } else {
            isUser();
        }
    }

    private void isUser() {
        String userEnteredUsername = userName.getEditText().getText().toString().trim();
        String userEnteredPassword = passWord.getEditText().getText().toString().trim();
        if (rememberMe.isChecked()){
            SharedPref sharedPref = new SharedPref(LoginActivity.this,SharedPref.SHARED_NAME);
            sharedPref.createRememberToStored(userEnteredUsername,userEnteredPassword);
        }

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);


        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    userName.setError(null);
                    userName.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userEnteredPassword)){
                        progressDialog.setTitle("Please wait!!");
                        progressDialog.setMessage("Process!");
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(),LoadingActivity.class);
                                startActivity(intent);
                            }
                        },LOADING);

                    } else {
                        passWord.setError("Wrong Password");
                        passWord.requestFocus();
                    }

                } else {
                    userName.setError("No such user exist");
                    userName.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}