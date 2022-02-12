package com.example.guiadeviajem.database.hotspot;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HotspotDAO {
    @Query("select * from hotspotentity where position_id = (:posId)")
    List<HotspotEntity> findAll(int posId);

    @Query("select * from hotspotentity where id = (:hotId) and position_id = (:posId)")
    List<HotspotEntity> findOne(int posId, int hotId);

    @Insert
    void insertAll(HotspotEntity position);

    @Update()
    void update(HotspotEntity position);

    @Delete
    void delete(HotspotEntity position);

}
