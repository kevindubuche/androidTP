package com.example.neighbors.dal.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.neighbors.dal.room.entities.NeighborEntity

@Dao
interface NeighborDao {
    @Query("SELECT * from neighbors")
    fun getNeighbors(): LiveData<List<NeighborEntity>>

    @Delete
    fun deleteNeighbour(toEntity: NeighborEntity)

    @Insert
    fun add(toEntity: NeighborEntity)

//    @Query("UPDATE neighbors SET favorite=:favorite WHERE id = :id")
//    fun updateFavoriteStatus(favorite: Boolean?, id: Long)

    @Update
    fun updateFavoriteStatus(toEntity: NeighborEntity)
}
