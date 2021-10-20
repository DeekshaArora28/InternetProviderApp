package com.example.internetproviderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;

import model.ClientOrder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher{

    TextView tvSubTotal;
    TextView tvTPS;
    TextView tvTVQ;
    TextView tvTotal;
    EditText edClNumber, edNbmonths;
    RadioGroup rgInternet;
    Button btnSave, btnShowAll, btnQuit;
    ArrayList<ClientOrder> ListOfOrders;
    RadioButton rbBell, rbVideotron, rbaCanac;

    String internetType = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {

        tvSubTotal = (TextView) findViewById(R.id.tvSubTotal);
        tvTPS = (TextView) findViewById(R.id.tvTPS);
        tvTVQ = (TextView) findViewById(R.id.tvTVQ);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        edClNumber = findViewById(R.id.edClNumber);
        edNbmonths = findViewById(R.id.edNbmonths);
        rgInternet = findViewById(R.id.rgInternet);

        btnQuit = findViewById(R.id.btnQuit);
        btnSave = findViewById(R.id.btnSave);
        btnShowAll = findViewById(R.id.btnShowAll);

        btnSave.setOnClickListener(this);
        btnShowAll.setOnClickListener(this);
        btnQuit.setOnClickListener(this);

        ListOfOrders = new ArrayList<ClientOrder>();
        rbBell = findViewById(R.id.rbBell);
        rbVideotron = findViewById(R.id.rbVideotron);
        rbaCanac = findViewById(R.id.rbaCanac);

        rbBell.setOnClickListener(this);
        rbVideotron.setOnClickListener(this);
        rbaCanac.setOnClickListener(this);

        edNbmonths.addTextChangedListener(this);

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.btnSave:
                saveOrder(view);
                break;
            case R.id.btnShowAll:
                ShowallOrders();
                break;
            case R.id.btnQuit:
                quitApp();

                break;

            case R.id.rbBell:
                internetType = rbBell.getText().toString();

                ShowAmont();
                break;
            case R.id.rbVideotron:
                internetType = rbVideotron.getText().toString();
                ShowAmont();
                break;
            case R.id.rbaCanac:
                internetType = rbaCanac.getText().toString();
                ShowAmont();
                break;
        }
    }

    private void quitApp() {
        System.exit(0);
    }

    private void ShowallOrders() {
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra("Results",ListOfOrders);
        startActivity(intent);
    }

    private void saveOrder(View view) {
        try {

            int ClNumber = Integer.valueOf(edClNumber.getText().toString());
            int nbmonths = Integer.valueOf(edNbmonths.getText().toString());
            ClientOrder oneOrder = new ClientOrder(ClNumber, internetType, nbmonths);
            ListOfOrders.add(oneOrder);
            Snackbar.make(view, "The order of the Client number:" + ClNumber + "\nhas been saved successfully", Snackbar.LENGTH_LONG).show();

            clearWidgets();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void clearWidgets() {
        edClNumber.setText(null);
        edNbmonths.setText(null);
        rgInternet.clearCheck();
        tvSubTotal.setText(null);
    }

    private void ShowAmont() {
        try {
            float price = getPrice(internetType);
            int nbmonths = Integer.valueOf(edNbmonths.getText().toString());
            float amount = price*nbmonths;
            tvSubTotal.setText(amount+"");
        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private float getPrice(String internetType) throws Exception {
        float price;
        if(internetType.equals("Bell"))
            price=60.0f;
        else
        if(internetType.equals("Videotron"))
            price=70.0f;
        else
        if(internetType.equals("aCanac"))
            price=45.0f;
        else
            throw new Exception("Please select the Internet Provider");
        return price;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        ShowAmont();

    }
}