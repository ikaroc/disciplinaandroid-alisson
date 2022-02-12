package com.example.guiadeviajem.activities;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.guiadeviajem.R;
import com.example.guiadeviajem.database.hotspot.HotspotDatabase;
import com.example.guiadeviajem.database.hotspot.HotspotEntity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.guiadeviajem.databinding.ActivityHotspotListBinding;

import java.util.List;

public class HotspotListActivity extends FragmentActivity implements OnMapReadyCallback {
    private ActivityHotspotListBinding binding;
    private Button button;
    private List<HotspotEntity> response;
    private HotspotDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHotspotListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.hotspot_map);
        mapFragment.getMapAsync(this);
        button = findViewById(R.id.hotspot_new_hotspot_button);

        Intent getInt = getIntent();
        String id = getInt.getStringExtra("id");
        String name = getInt.getStringExtra("name");
        String latitude = getInt.getStringExtra("latitude");
        String longitude = getInt.getStringExtra("longitude");

        database = HotspotDatabase.getDbInstance(HotspotListActivity.this);
        response = database.positionDAO().findAll(Integer.parseInt(id));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigation = new Intent(HotspotListActivity.this, NewHotspotActivity.class);
                navigation.putExtra("id", id);
                startActivity(navigation);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (response != null) {
            for (HotspotEntity hotspot:response) {
                googleMap.addMarker(
                        new MarkerOptions().position(new LatLng(
                                Double.parseDouble(hotspot.latitude),
                                Double.parseDouble(hotspot.longitude)
                        )).title(
                                hotspot.name
                        )
                );
            }
        }
    }
}