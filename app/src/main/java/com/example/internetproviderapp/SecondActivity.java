package com.example.internetproviderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import model.ClientOrder;

public class SecondActivity extends AppCompatActivity {



    TextView tvClNumber;
    TextView tvProvider;
    TextView tvNbmonths;
    TextView tvSubTotal;
    TextView tvTotal;

    ArrayList<ClientOrder> ListOfOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initialize();
    }

    private void initialize() {
        tvClNumber = findViewById(R.id.tvClNumber);
        tvTotal = findViewById(R.id.tvTotal);
        tvNbmonths = findViewById(R.id.tvNbmonths);
        tvSubTotal = findViewById(R.id.tvSubTotal);
        tvProvider = findViewById(R.id.tvProvider);
        ListOfOrder = (ArrayList<ClientOrder>) getIntent().getExtras().getSerializable("order");
        StringBuilder list = new StringBuilder("");
        float total = 0;

        for(ClientOrder oneOrder : ListOfOrder) {
            list.append(oneOrder+"\n");
            total+=oneOrder.getAmount();
        }
        tvSubTotal.setText(list);
        tvTotal.append(String.valueOf(total));
    }
}