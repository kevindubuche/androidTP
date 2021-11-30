package com.example.neighbors.dal.memory


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.neighbors.dal.NeighborDatasource
import com.example.neighbors.models.Neighbor

class DummyNeighborApiService private constructor() : NeighborDatasource {

    private val DUMMY_NeighborS = InMemory_NeighborS
    private val observable: MutableLiveData<List<Neighbor>> = MutableLiveData(DUMMY_NeighborS)

    override val neighbours: LiveData<List<Neighbor>>
        get() = observable

    override fun deleteNeighbour(neighbor: Neighbor) {
        DUMMY_NeighborS.remove(neighbor)
        observable.postValue(DUMMY_NeighborS)
    }

    override fun createNeighbour(neighbor: Neighbor) {
        DUMMY_NeighborS.add(neighbor)
        observable.postValue(DUMMY_NeighborS)
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        DUMMY_NeighborS?.find { it.id == neighbor.id }?.favorite = !neighbor.favorite
    }

    override fun updateDataNeighbour(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }


    override fun getGeneratedId(): Long {
        TODO("Not yet implemented")
    }


    companion object {
        private var instance: DummyNeighborApiService? = null
        fun getInstance(): DummyNeighborApiService {
            if (instance == null) {
                instance = DummyNeighborApiService()
            }
            return instance!!
        }
    }

}