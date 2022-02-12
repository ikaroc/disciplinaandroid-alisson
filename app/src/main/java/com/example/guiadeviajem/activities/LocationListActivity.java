package com.example.guiadeviajem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guiadeviajem.utils.CustomAdapter;
import com.example.guiadeviajem.R;
import com.example.guiadeviajem.database.position.PositionDatabase;
import com.example.guiadeviajem.database.position.PositionEntity;

import java.util.ArrayList;
import java.util.List;

public class LocationListActivity extends AppCompatActivity {
    private Button newLocationButton;
    private PositionDatabase database;
    private List<PositionEntity> responseData;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        newLocationButton = findViewById(R.id.location_new_location_button);
        database = PositionDatabase.getDbInstance(this.getApplicationContext());
        responseData = (List<PositionEntity>) database.positionDAO().findAll();
        List<PositionEntity> list = new ArrayList<>();
        list.addAll(responseData);

        listView = (ListView) findViewById(R.id.location_listview_view);

        CustomAdapter newAdapter = new CustomAdapter(this, R.layout.fragment_listview, list);
        listView.setAdapter(newAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PositionEntity position = responseData.get(i);
                Intent navigation = new Intent(LocationListActivity.this, HotspotListActivity.class);
                navigation.putExtra("name", position.name);
                Log.v("name", position.name);
                navigation.putExtra("id", String.valueOf(position.id));
                navigation.putExtra("latitude", position.latitude);
                navigation.putExtra("longitude", position.longitude);
                startActivity(navigation);
            }
        });

        newLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigation = new Intent(LocationListActivity.this, NewLocationActivity.class);
                startActivity(navigation);
            }
        });
    }
}