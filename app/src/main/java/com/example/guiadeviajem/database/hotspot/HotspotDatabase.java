package com.example.guiadeviajem.database.hotspot;


import android.content.Context;

import androidx.room.*;

@Database(entities = HotspotEntity.class, version = 1)
public abstract class HotspotDatabase extends RoomDatabase {
    public abstract HotspotDAO positionDAO();

    public static HotspotDatabase INSTANCE;

    public static HotspotDatabase getDbInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), HotspotDatabase.class, "Hotspot")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
