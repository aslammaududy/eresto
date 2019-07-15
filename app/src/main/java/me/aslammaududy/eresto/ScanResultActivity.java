package me.aslammaududy.eresto;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.aslammaududy.eresto.Adapter.OrderMenuAdapter;
import me.aslammaududy.eresto.Model.Menu;

public class ScanResultActivity extends AppCompatActivity {

    private RecyclerView orderedMenuRecycler;
    private List<Menu> orderedMenuList;
    private OrderMenuAdapter orderMenuAdapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        orderedMenuRecycler = findViewById(R.id.ordered_menu_recycler);

        intent = getIntent();

        orderedMenuList = new ArrayList<>();

        orderMenuAdapter = new OrderMenuAdapter(orderedMenuList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        orderedMenuRecycler.setLayoutManager(layoutManager);
        orderedMenuRecycler.setAdapter(orderMenuAdapter);
    }
}
