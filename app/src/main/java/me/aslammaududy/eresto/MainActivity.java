package me.aslammaududy.eresto;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import me.aslammaududy.eresto.Adapter.MenuAdapter;
import me.aslammaududy.eresto.Model.Menu;
import me.aslammaududy.eresto.NetworkManager.Connection;
import me.aslammaududy.eresto.NetworkManager.Endpoints;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView menuRecycler;
    private MenuAdapter menuAdapter;
    private List<Menu> menuList, orderedMenu;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private BootstrapButton orderButton;
    private Intent intent;
    private IntentIntegrator integrator;
    private Endpoints endpoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orderButton = findViewById(R.id.menu_order);
        orderButton.setEnabled(false);

        sharedPreferences = getSharedPreferences("menu", 0);
        editor = sharedPreferences.edit();
        gson = new Gson();

        menuList = new ArrayList<>();
        populateMenu(menuList);

        endpoints = Connection.getEndpoints();

        menuRecycler = findViewById(R.id.menu_recycler);

        menuAdapter = new MenuAdapter(menuList);
        menuAdapter.setOnOrderedMenuListener(new MenuAdapter.OnOrderedMenuListener() {
            @Override
            public void onOrderedMenu(List<Menu> orderedMenu) {
                String json = gson.toJson(orderedMenu);
                editor.putString("Menu List", json);
                editor.commit();

                if (orderedMenu.size() > 0) {
                    orderButton.setEnabled(true);
                } else {
                    orderButton.setEnabled(false);
                }
            }
        });

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        menuRecycler.setLayoutManager(layoutManager);

        menuRecycler.setAdapter(menuAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            //if qrcode has nothing in it
            if (intentResult.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
//                intent = new Intent(MainActivity.this, ScanResultActivity.class);
//                intent.putExtra("ordered menu", intentResult.getContents());
//                startActivity(intent);

                new AlertDialog.Builder(this)
                        .setTitle("Daftar Menu")
                        .setMessage(intentResult.getContents())
                        .setNegativeButton("tutup", null)
                        .show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void populateMenu(List<Menu> menuList) {
        menuList.add(new Menu("Mie", "10%", R.drawable.mie));
        menuList.add(new Menu("Ikan Bakar", "5%", R.drawable.ikanbakar));
        menuList.add(new Menu("Nasi Kuning", "20%", R.drawable.nasikuning));
        menuList.add(new Menu("Nasi Lemak", "10%", R.drawable.nasilemak));
        menuList.add(new Menu("Nasi Uduk", "15%", R.drawable.nasiuduk));
        menuList.add(new Menu("Rawon", "5%", R.drawable.rawon));
        menuList.add(new Menu("Capuccino", "5%", R.drawable.capuccino));
        menuList.add(new Menu("Kopi Hitam", "15%", R.drawable.kopihitam));
        menuList.add(new Menu("Squash", "5%", R.drawable.squash));
        menuList.add(new Menu("Teh Manis", "10%", R.drawable.tehmanis));
    }

    public void createQR(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Daftar Menu")
                .setMessage("Apakah anda yakin dengan pesanan anda?")
                .setPositiveButton("Pesan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String json = sharedPreferences.getString("Menu List", "");
                        orderedMenu = gson.fromJson(json, new TypeToken<List<Menu>>() {
                        }.getType());

                        Log.i("json", json);
                        Log.i("menus", orderedMenu.get(0).getName() + "");

                        Call<List<Menu>> call = endpoints.addMenu(orderedMenu);
                        call.enqueue(new Callback<List<Menu>>() {
                            @Override
                            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                                intent = new Intent(getApplicationContext(), BarcodeActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<List<Menu>> call, Throwable t) {

                            }
                        });


                    }
                })
                .setNegativeButton("tutup", null)
                .show();

    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.qrcode_menu:
                intent = new Intent(this, BarcodeActivity.class);
                startActivity(intent);
                break;
            case R.id.qrcode_scanner_menu:
                integrator = new IntentIntegrator(this);
                integrator.setOrientationLocked(false);
                integrator.setCaptureActivity(ScannerActivity.class);
                integrator.initiateScan();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
