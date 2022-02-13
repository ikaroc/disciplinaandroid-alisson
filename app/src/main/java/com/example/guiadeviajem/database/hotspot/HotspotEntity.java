package com.example.guiadeviajem.database.hotspot;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HotspotEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "latitude")
    public String latitude;

    @ColumnInfo(name = "longitude")
    public String longitude;

    @ColumnInfo(name = "weekend")
    public String weekday;

    @ColumnInfo(name = "position_id")
    public int positionId;

    public HotspotEntity( String name, String latitude, String longitude, String weekday, int positionId) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weekday = weekday;
        this.positionId = positionId;
    }

    @NonNull
    @Override
    public String toString() {
        return "HotspotEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", weekend='" + weekday + '\'' +
                '}';
    }
}
