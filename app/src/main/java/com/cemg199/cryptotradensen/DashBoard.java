package com.cemg199.cryptotradensen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashBoard extends AppCompatActivity implements View.OnClickListener {

    private TextView txtWelcome;
    private TextView back;
    private EditText input_new_password;
    private Button   btnChangePass, btnLogout;
    private RelativeLayout activity_dashboard;


    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        //View

        txtWelcome              = (TextView) findViewById(R.id.dashboard_welcome);
        input_new_password      = (EditText) findViewById(R.id.dashboard_new_password);
        btnChangePass           = (Button)   findViewById(R.id.dashboard_btn_change_pass);
        btnLogout               = (Button)   findViewById(R.id.dashboard_btn_logout);
        activity_dashboard      = (RelativeLayout) findViewById(R.id.activity_dashboard);
        back                    = (TextView)       findViewById(R.id.dashboard_btn_back);


        btnChangePass.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        back.setOnClickListener(this);

        //Init firebase
        auth = FirebaseAuth.getInstance();

        //Session Check
        if(auth.getCurrentUser() != null)
        {
            txtWelcome.setText("" + auth.getCurrentUser().getEmail() );
        }




    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.dashboard_btn_change_pass)
        {
            changePass(input_new_password.getText().toString());
        }
        else if(v.getId() == R.id.dashboard_btn_logout)
        {
            logoutUser();
        }else if(v.getId() == R.id.dashboard_btn_back)
        {
            startActivity(new Intent(this,Home.class));
            finish();
        }
    }

    private void logoutUser() {
        auth.signOut();
        if(auth.getCurrentUser() == null)
        {
            startActivity(new Intent(DashBoard.this,MainActivity.class));
            finish();
        }

    }

    private void changePass(String newPassword) {
        FirebaseUser user = auth.getCurrentUser();
        user.updatePassword(newPassword).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Snackbar snackbar = Snackbar.make(activity_dashboard,"Password changed",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });
    }


}
