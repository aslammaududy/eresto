package me.aslammaududy.eresto;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.List;

import me.aslammaududy.eresto.Model.Menu;

public class BarcodeActivity extends AppCompatActivity {

    private List<Menu> menuList;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        sharedPreferences = getSharedPreferences("menu", 0);
        editor = sharedPreferences.edit();
        gson = new Gson();

        String json = sharedPreferences.getString("Menu List", "");
        menuList = gson.fromJson(json, new TypeToken<List<Menu>>() {
        }.getType());

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < menuList.size(); i++) {
            stringBuilder.append(menuList.get(i).getName());
            stringBuilder.append("\n");
            stringBuilder.append("\n");
        }

        Log.i("sb", stringBuilder.toString());


        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(stringBuilder.toString(), BarcodeFormat.QR_CODE, 400, 400);
            ImageView imageViewQrCode = findViewById(R.id.qrCode);
            imageViewQrCode.setImageBitmap(bitmap);
        } catch (Exception e) {

        }
    }
}


