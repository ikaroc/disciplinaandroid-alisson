package com.example.guiadeviajem.activities;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guiadeviajem.R;
import com.example.guiadeviajem.database.hotspot.HotspotDatabase;
import com.example.guiadeviajem.database.hotspot.HotspotEntity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.guiadeviajem.databinding.ActivityNewHotspotBinding;

public class NewHotspotActivity extends FragmentActivity implements OnMapReadyCallback {
    private ActivityNewHotspotBinding binding;
    private Button button;
    private EditText nameInput, weekdayInput;
    private HotspotDatabase database;
    private Double latitude, longitude;
    private String pLatitude, pLongitude;
    private String positionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewHotspotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.new_hotspot_map);
        mapFragment.getMapAsync(this);

        button = findViewById(R.id.new_hotspot_button);
        nameInput = findViewById(R.id.new_hotspot_input_name);
        weekdayInput = findViewById(R.id.new_hotspot_input_date);

        database = HotspotDatabase.getDbInstance(NewHotspotActivity.this);

        Intent getData = getIntent();
        positionId = getData.getStringExtra("id");
        String pName = getData.getStringExtra("pName");
        pLatitude = getData.getStringExtra("pLatitude");
        pLongitude = getData.getStringExtra("pLongitude");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString();
                String weekend = weekdayInput.getText().toString();

                database.positionDAO().insertAll(new HotspotEntity(
                        name,
                        latitude.toString(),
                        longitude.toString(),
                        weekend,
                        Integer.parseInt(positionId)
                ));
                Intent navigation = new Intent(NewHotspotActivity.this, HotspotListActivity.class);
                navigation.putExtra("id", positionId);
                navigation.putExtra("pName", pName);
                navigation.putExtra("pLatitude", pLatitude);
                navigation.putExtra("pLongitude", pLongitude);
                startActivity(navigation);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(
                        Double.parseDouble(pLatitude),
                        Double.parseDouble(pLongitude)
                ),
                12.0f
        ));
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                googleMap.clear();
                latitude = latLng.latitude;
                longitude = latLng.longitude;
                googleMap.addMarker(
                        new MarkerOptions()
                                .position(new LatLng(latitude, longitude))
                                .title("new hotspot")
                );
            }
        });
    }
}