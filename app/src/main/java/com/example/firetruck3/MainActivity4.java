package com.example.firetruck3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText amountEditText;
    private EditText sharesEditText;
    private Button confirmButton;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        amountEditText = findViewById(R.id.amountEditText);
        sharesEditText = findViewById(R.id.sharesEditText);
        confirmButton = findViewById(R.id.confirmButton);
        imageView = findViewById(R.id.imageView);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String amountStr = amountEditText.getText().toString();

                if (!name.isEmpty() && !phone.isEmpty() && !amountStr.isEmpty()) {
                    double amount = Double.parseDouble(amountStr);

                    double shares = amount * 0.002;

                    sharesEditText.setText(String.valueOf(shares));

                    imageView.setVisibility(View.VISIBLE);
                    ContributionItem newItem = new ContributionItem(name, phone, amount, shares);
                    ContributionDAO contributionDAO = new ContributionDAO(MainActivity4.this);
                    contributionDAO.addContribution(newItem);
                } else {
                    Toast.makeText(MainActivity4.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
