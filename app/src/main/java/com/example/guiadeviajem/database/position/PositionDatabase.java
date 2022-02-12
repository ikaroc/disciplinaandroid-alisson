package com.example.guiadeviajem.database.position;


import android.content.Context;

import androidx.room.*;

@Database(entities = PositionEntity.class, version = 1)
public abstract class PositionDatabase extends RoomDatabase {
    public abstract PositionDAO positionDAO();

    public static PositionDatabase INSTANCE;

    public static PositionDatabase getDbInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PositionDatabase.class, "Position")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
