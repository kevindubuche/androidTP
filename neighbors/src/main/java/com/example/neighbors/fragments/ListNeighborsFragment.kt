package com.example.neighbors.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neighbors.R
import com.example.neighbors.adapters.ListNeighborHandler
import com.example.neighbors.adapters.ListNeighborsAdapter
import com.example.neighbors.data.NeighborRepository
import com.example.neighbors.models.Neighbor
import android.content.DialogInterface
import com.example.neighbors.NavigationListener
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListNeighborsFragment : Fragment(), ListNeighborHandler {
    private lateinit var recyclerView: RecyclerView
    private lateinit var listNeighborHandler: ListNeighborHandler
    private lateinit var addNeighbor: FloatingActionButton

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_neighbors_fragment, container, false)

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.list_neighbors_title)
        }

        recyclerView = view.findViewById(R.id.neighbors_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        addNeighbor = view.findViewById(R.id.neighbors_add)

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        addNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AddNeighborFragment())
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val neighbors = NeighborRepository.getInstance().getNeighbours()
        val adapter = ListNeighborsAdapter(neighbors,this)
        recyclerView.adapter = adapter
    }

    override fun onDeleteNeibor(neighbor: Neighbor) {
        println(neighbor.name)
        showAlertDialog(neighbor)
    }
    private fun showAlertDialog(neighbor: Neighbor) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(recyclerView.context)
        alertDialog.setTitle("Confirmation Message")
        alertDialog.setMessage("Voulez-vous supprimer ce voisin ?")
        alertDialog.setPositiveButton(
            "yes"
        ) { _, _ ->
            Toast.makeText(recyclerView.context, "Voisin suprimmé avec succès !", Toast.LENGTH_LONG).show()
            var index : Int = NeighborRepository.getInstance().getNeighbours().indexOf(neighbor)
//            val neighbors = NeighborRepository.getInstance().deleteNeighbour(neighbor)
            NeighborRepository.getInstance().deleteNeighbour(neighbor)
            recyclerView.adapter?.notifyItemRemoved(index)
            println("You have deleted the neighbor with the name "+neighbor.name )
        }
        alertDialog.setNegativeButton(
            "No"
        ) { _, _ -> }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }




}
