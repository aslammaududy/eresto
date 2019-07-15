package me.aslammaududy.eresto;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class ScannerActivity extends CaptureActivity {
    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.activity_scanner);
        return findViewById(R.id.qrcode_scanner);
    }
}
