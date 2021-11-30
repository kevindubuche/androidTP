package com.example.neighbors.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.neighbors.dal.NeighborDatasource
import com.example.neighbors.dal.memory.DummyNeighborApiService
import com.example.neighbors.dal.room.RoomNeighborDataSource
import com.example.neighbors.models.Neighbor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NeighborRepository private constructor(private val application: Application) {
    var dataSource: NeighborDatasource = RoomNeighborDataSource(application)
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    // Méthode qui retourne la liste des voisins
    fun getNeighbours(): LiveData<List<Neighbor>> = dataSource.neighbours
    fun add(neighbor: Neighbor) = executor.execute{dataSource.createNeighbour(neighbor)}
    fun delete(neighbor: Neighbor) =  executor.execute{dataSource.deleteNeighbour(neighbor)}
    fun updateFavoriteStatus(neighbor: Neighbor) =  executor.execute{dataSource.updateFavoriteStatus(neighbor)}
    fun activePersitantMode() {
        println("PERSISTANT MODE IS ACTIVED")
        dataSource = RoomNeighborDataSource(application)
    }
    fun activeMemoryMode() {
        println("MEMORY MODE IS ACTIVED")
        dataSource = DummyNeighborApiService.getInstance();
    }
    companion object {
        private var instance: NeighborRepository? = null

        // On crée un méthode qui retourne l'instance courante du repository si elle existe ou en crée une nouvelle sinon
        fun getInstance(application: Application): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository(application)
            }
            return instance!!
        }
    }
}

