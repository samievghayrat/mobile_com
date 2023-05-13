package com.ghayrat_samiev.mobile_com;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Scanned_Aps extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private ListView wifiListView;
    private WifiManager wifiManager;
    private List<String> wifiNetworks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanned_aps_page);

//         LayoutInflater inflater = LayoutInflater.from(this); // or use your activity context
//         View _wifiListView = inflater.inflate(R.layout.scanned_aps_page, null);

        wifiListView = findViewById(R.id.listView);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, request for it
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_CODE);
        } else {
            scanWifiNetworks();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                scanWifiNetworks();
            } else {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void scanWifiNetworks() {
        //wifiNetworks.clear();
        List<ScanResult> wifiScanResults = wifiManager.getScanResults();

        for (ScanResult wifiScanResult : wifiScanResults) {
            Toast.makeText(this, wifiScanResults.size() + " WiFi networks found", Toast.LENGTH_SHORT).show();
            Log.d("Wifi Scan", "Number of WiFi Networks Found: " + wifiScanResults.size());

            wifiNetworks.add( wifiScanResult.SSID + "; " + wifiScanResult.BSSID+ wifiScanResult.capabilities+"; "+wifiScanResult.frequency+" MHz; "+wifiScanResult.level+" dBm\n\n");
            Log.d("Wifi Scan", "SSID: " + wifiScanResult.SSID + "; BSSID: " + wifiScanResult.BSSID+ wifiScanResult.capabilities+"; "+wifiScanResult.frequency+" MHz; "+wifiScanResult.level+" dBm\n\n");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, wifiNetworks);
        wifiListView.setAdapter(adapter);

        Toast.makeText(this, wifiScanResults.size() + " WiFi networks found", Toast.LENGTH_SHORT).show();
    }
}
