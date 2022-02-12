package com.example.guiadeviajem.database.position;


import androidx.room.*;
import java.util.List;

@Dao
public interface PositionDAO {
    @Query("select * from positionentity")
    List<PositionEntity> findAll();

    @Query("select * from positionentity where id = (:posId)")
    List<PositionEntity> findOne(int posId);

    @Insert
    void insertAll(PositionEntity position);

    @Update()
    void update(PositionEntity position);

    @Delete
    void delete(PositionEntity position);

}
