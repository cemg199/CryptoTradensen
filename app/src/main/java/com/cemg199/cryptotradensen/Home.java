package com.cemg199.cryptotradensen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private TextView txtWelcome;
    private Button btn_account, btn_create_account,btn_options;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtWelcome          = (TextView) findViewById(R.id.dashboard_welcomeHome);
        btn_account         = (Button)   findViewById(R.id.accounts);
        btn_create_account  = (Button)   findViewById(R.id.createAccount);
        btn_options         = (Button)   findViewById(R.id.options);

        btn_account.setOnClickListener(this);
        btn_create_account.setOnClickListener(this);
        btn_options.setOnClickListener(this);

        //Init firebase
        auth = FirebaseAuth.getInstance();

        //Session Check
        if(auth.getCurrentUser() != null)
        {
            txtWelcome.setText("Welcome, " + auth.getCurrentUser().getEmail() );
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.accounts)
        {
            //changePass(input_new_password.getText().toString());
        }
        else if(v.getId() == R.id.createAccount)
        {
            startActivity(new Intent(this,CreateAccountRegistry.class));
            finish();

        } else if(v.getId() == R.id.options)
        {
           startActivity(new Intent(this,DashBoard.class));
            finish();
        }
    }
}
