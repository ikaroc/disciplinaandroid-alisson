package com.example.guiadeviajem.activities;

import androidx.fragment.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    private TextView title;
    private List<HotspotEntity> response;
    private HotspotDatabase database;
    private String pLatitude, pLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHotspotListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.hotspot_map);
        mapFragment.getMapAsync(this);
        button = findViewById(R.id.hotspot_new_hotspot_button);
        title = findViewById(R.id.hotspot_title);

        Intent getInt = getIntent();
        String id = getInt.getStringExtra("id");
        String pName = getInt.getStringExtra("pName");
        pLatitude = getInt.getStringExtra("pLatitude");
        pLongitude = getInt.getStringExtra("pLongitude");

        title.setText(pName);

        database = HotspotDatabase.getDbInstance(HotspotListActivity.this);
        response = database.positionDAO().findAll(Integer.parseInt(id));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigation = new Intent(HotspotListActivity.this, NewHotspotActivity.class);
                navigation.putExtra("id", id);
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
        if (response != null) {
            for (HotspotEntity hotspot:response) {
                googleMap.addMarker(
                        new MarkerOptions().position(new LatLng(
                                Double.parseDouble(hotspot.latitude),
                                Double.parseDouble(hotspot.longitude)
                        )).title(
                                hotspot.name + " | " + hotspot.weekday
                        )
                );
            }
        }
    }
}