//package com.example.neighbors.dal.memory
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.example.neighbors.dal.NeighborDatasource
//import com.example.neighbors.models.Neighbor
//
//class InMemoryNeighborDataSource : NeighborDatasource {
//
//    private val observable: MutableLiveData<List<Neighbor>> = MutableLiveData(InMemory_NeighborS)
//
//    override val neighbours: LiveData<List<Neighbor>>
//        get() = observable
//
//
////    override fun getNeighbour(): MutableList<Neighbor> {
////        return DUMMY_NeighborS
////    }
//
//    override fun deleteNeighbour(neighbor: Neighbor) {
////        DUMMY_NeighborS = DUMMY_NeighborS.drop(DUMMY_NeighborS.indexOf(neighbor))
////         DUMMY_NeighborS.toMutableList().apply {
////            removeAt(DUMMY_NeighborS.indexOf(neighbor))
////        }
//
////        DUMMY_NeighborS.remove(neighbor)
////        println(DUMMY_NeighborS)
//        InMemory_NeighborS.remove(neighbor)
//        observable.value = InMemory_NeighborS
//    }
//
//    override fun getGeneratedId(): Long {
//        return (InMemory_NeighborS.size+1).toLong()
//    }
//
//    override fun createNeighbour(neighbor: Neighbor) {
////        DUMMY_NeighborS.add(neighbor)
//        InMemory_NeighborS.add(neighbor)
//    }
//
//    override fun updateFavoriteStatus(neighbor: Neighbor) {
//        println(InMemory_NeighborS)
//        InMemory_NeighborS?.find { it.id == neighbor.id }?.favorite = !neighbor.favorite
//        observable.value = InMemory_NeighborS
//        println("====")
//        println(InMemory_NeighborS)
//    }
//
//    override fun updateDataNeighbour(neighbor: Neighbor) {
//        TODO("Not yet implemented")
//      //  neighbor.favorite = !neighbor.favorite
//    }
//
//
//
//}