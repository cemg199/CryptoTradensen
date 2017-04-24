package com.cemg199.cryptotradensen;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateAccountRegistry extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{


    private Calendar calendar = Calendar.getInstance();
    private Button btnDatePicker;
    private TextView display;
    private ImageView back;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_registry);

        back = (ImageView) findViewById(R.id.backImage);
        back.setOnClickListener(this);

        //Init firebase
        auth = FirebaseAuth.getInstance();



        // Spinner element
        Spinner spinner  = (Spinner) findViewById(R.id.spinnerBancos);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinnerTipoCuenta);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        spinner1.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        //bancos
        List<String> categories = new ArrayList<String>();
        categories.add(getResources().getString(R.string.spinnerBancos));
        categories.add(getResources().getString(R.string.avVillas));
        categories.add(getResources().getString(R.string.bancolombia));
        categories.add(getResources().getString(R.string.bancoBogota));
        categories.add(getResources().getString(R.string.bbva));
        categories.add(getResources().getString(R.string.bancoPopular));
        categories.add(getResources().getString(R.string.bancoSantander));
        categories.add(getResources().getString(R.string.citibank));
        categories.add(getResources().getString(R.string.davivienda));
        categories.add(getResources().getString(R.string.serviBanca));

        //tipo cuenta
        List<String> categories2 = new ArrayList<String>();
        categories2.add(getResources().getString(R.string.tipoCuenta));
        categories2.add(getResources().getString(R.string.ahorros));
        categories2.add(getResources().getString(R.string.corriente));
        categories2.add(getResources().getString(R.string.btc));
        categories2.add(getResources().getString(R.string.okpay));
        categories2.add(getResources().getString(R.string.payeer));
        categories2.add(getResources().getString(R.string.perfectMoney));


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, categories);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, categories2);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner1.setAdapter(dataAdapter1);



        btnDatePicker = (Button) findViewById(R.id.btnDatePicker);
        display       = (TextView) findViewById(R.id.timeTextView);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateAccountRegistry.this,listener,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            display.setText(dayOfMonth + "/" + (month+1) + "/" + year);

        }
    };




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // On selecting a spinner item

        String item = parent.getItemAtPosition(position).toString();
        /*
        if(item.equals("SELECT BANK") || item.equals("SELECCIONE BANCO"))
        {
            sp1 = item;
        }
        if(item.equals("SELECT ACCOUNT TYPE") || item.equals("TIPO DE CUENTA"))
        {
            sp*/

        // Showing selected spinner item

        if(item.equals("SELECT BANK") || item.equals("SELECCIONE BANCO") || item.equals("SELECT ACCOUNT TYPE") || item.equals("TIPO DE CUENTA")){

        }else {
            Toast.makeText(parent.getContext(), getResources().getString(R.string.spinnerSeleccion) + item, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backImage){
            startActivity(new Intent(this,Home.class));
            finish();
        }
    }


}