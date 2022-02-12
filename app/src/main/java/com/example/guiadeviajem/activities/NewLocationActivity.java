package com.example.guiadeviajem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;

import com.example.guiadeviajem.R;
import com.example.guiadeviajem.database.position.PositionDatabase;
import com.example.guiadeviajem.database.position.PositionEntity;
import com.example.guiadeviajem.databinding.ActivityNewLocationBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NewLocationActivity extends FragmentActivity implements OnMapReadyCallback {
    private PositionDatabase database;
    private GoogleMap mMap;
    private ActivityNewLocationBinding binding;
    private EditText positionName;
    private Button addlocationButton;
    private Double latitude;
    private Double longitude;
    private LatLng markPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.new_location_map);
        mapFragment.getMapAsync(this);
        addlocationButton = findViewById(R.id.new_location_button);
        database = PositionDatabase.getDbInstance(this.getApplicationContext());
        positionName = findViewById(R.id.new_location_input);

        addlocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PositionEntity positionEntity = new PositionEntity(
                positionName.getText().toString(),
                latitude.toString(),
                longitude.toString()
                );
                database.positionDAO().insertAll(positionEntity);

                Intent navigation = new Intent(NewLocationActivity.this, LocationListActivity.class);
                startActivity(navigation);
            }
        });



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                latitude = latLng.latitude;
                longitude = latLng.longitude;
                markPoint = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(markPoint).title("New point"));
            }
        });
    }
}

