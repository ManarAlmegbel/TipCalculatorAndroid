package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {

    EditText username,password;
    Button signin,signup2,back3;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=(EditText) findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        signin=(Button) findViewById(R.id.signin);
        signup2=(Button) findViewById(R.id.signup2);
        DB=new DatabaseHelper(this);
        back3 = findViewById(R.id.back3);
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signin.setOnClickListener(view -> {
            String user=username.getText().toString();
            String pass=password.getText().toString();

            if(user.equals("")||pass.equals("")){
                Toast.makeText(loginActivity.this, "All fields Required",Toast.LENGTH_SHORT).show();

            } else{
                Boolean checkuserpass=DB.checkusernamepassword(user,pass);
                if(checkuserpass==true) {
                    Toast.makeText(loginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();


                    //بدال homeActivity تحطون اسم الاكتفيتي الي تبغون يروحلها
                    Intent intent = new Intent(loginActivity.this, HomeActivity.class);
                    intent.putExtra("username",user);
                    startActivity(intent);
                }else{
                    Toast.makeText(loginActivity.this, "Login Failed, username or password is wrong!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        signup2.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent2);

        });
    }
}