package com.example.neighbors.data

import com.example.neighbors.models.Neighbor
import com.example.neighbors.service.DummyNeighborApiService
import com.example.neighbors.service.NeighborApiService

class NeighborRepository {
    private val apiService: NeighborApiService

    init {
        apiService = DummyNeighborApiService()
    }

    fun getNeighbours(): List<Neighbor> = apiService.neighbours

    companion object {
        private var instance: NeighborRepository? = null
        fun getInstance(): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository()
            }
            return instance!!
        }
    }
}
