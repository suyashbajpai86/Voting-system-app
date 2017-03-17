package com.example.hp.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AsyncResponse, View.OnClickListener {
    EditText uname,password;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname = (EditText)findViewById(R.id.uname);
        password = (EditText)findViewById(R.id.password);
        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(this);
    }
    @Override
    public void processFinish(String s){
        if(s.equals("success")){
            Toast.makeText(this, s,
                    Toast.LENGTH_LONG).show();
            Intent i = new Intent(this,vlogin.class);
            startActivity(i);
        }
        else{
            Toast.makeText(this, s,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        HashMap postData = new HashMap();
        postData.put("btnLogin", "Login");
        postData.put("mobile", "android");
        postData.put("txtUsername", uname.getText().toString());
        postData.put("txtPassword", password.getText().toString());

        PostResponseAsyncTask loginTask =
                new PostResponseAsyncTask(this, postData);
        loginTask.execute("http://10.0.2.2/client/login.php");

    }
}
