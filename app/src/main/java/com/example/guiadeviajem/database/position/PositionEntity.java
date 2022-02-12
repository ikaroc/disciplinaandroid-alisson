package com.example.guiadeviajem.database.position;

import androidx.room.*;

import java.util.UUID;

@Entity
public class PositionEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "latitude")
    public String latitude;

    @ColumnInfo(name = "longitude")
    public String longitude;

    public PositionEntity(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "PositionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
