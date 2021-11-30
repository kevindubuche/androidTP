package com.example.neighbors.dal

import androidx.lifecycle.LiveData
import com.example.neighbors.models.Neighbor

interface NeighborDatasource {
    /**
     * Get all my Neighbors
     * @return [MutableList]
     */
    val neighbours: LiveData<List<Neighbor>>
//    fun getNeighbour(): List<Neighbor>

    /**
     * Deletes a neighbor
     * @param neighbor : Neighbor
     */
    fun deleteNeighbour(neighbor: Neighbor)

    /**
     * Create a neighbour
     * @param neighbor: Neighbor
     */
    fun createNeighbour(neighbor: Neighbor)

    fun getGeneratedId():Long


    /**
     * Update "Favorite status" of an existing Neighbour"
     * @param neighbor: Neighbor
     */
    fun updateFavoriteStatus(neighbor: Neighbor)

    /**
     * Update modified fields of an existing Neighbour"
     * @param neighbor: Neighbor
     */
    fun updateDataNeighbour(neighbor: Neighbor)

}