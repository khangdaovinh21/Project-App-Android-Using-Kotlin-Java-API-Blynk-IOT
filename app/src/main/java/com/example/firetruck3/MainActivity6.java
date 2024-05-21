package com.example.firetruck3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity6 extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> contributionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        listView = findViewById(R.id.listView);
        contributionList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contributionList);
        listView.setAdapter(adapter);

        loadContributions();
    }

    private void loadContributions() {
        ContributionDAO contributionDAO = new ContributionDAO(this);

        List<ContributionItem> items = contributionDAO.getAllContributions();

        for (ContributionItem item : items) {
            String name = item.getName();
            String phone = item.getPhone();
            double amount = item.getAmount();
            double shares = item.getShares();

            String itemString = "Name: " + name + ", Phone: " + phone + ", Amount: " + amount + ", Shares: " + shares;
            contributionList.add(itemString);
        }

        adapter.notifyDataSetChanged();
    }
}
