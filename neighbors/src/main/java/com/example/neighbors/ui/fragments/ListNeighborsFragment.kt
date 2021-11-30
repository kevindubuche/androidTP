package com.example.neighbors.ui.fragments

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neighbors.R
import com.example.neighbors.adapters.ListNeighborHandler
import com.example.neighbors.adapters.ListNeighborsAdapter
import com.example.neighbors.repositories.NeighborRepository
import com.example.neighbors.models.Neighbor
import com.example.neighbors.NavigationListener
import com.example.neighbors.ui.MainActivity
import com.example.neighbors.viewmodels.NeighborViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListNeighborsFragment : Fragment(), ListNeighborHandler {
    private lateinit var recyclerView: RecyclerView
    private lateinit var listNeighborHandler: ListNeighborHandler
    private lateinit var addNeighbor: FloatingActionButton
    private lateinit var viewModel: NeighborViewModel
    private lateinit var repository: NeighborRepository

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application: Application = activity?.application ?: return
        repository = NeighborRepository.getInstance(application)
        viewModel = ViewModelProvider(this).get(NeighborViewModel::class.java)
    }

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

    private fun setData() {
        viewModel.neighbors.observe(viewLifecycleOwner) {
            val adapter = ListNeighborsAdapter(it, this)
            recyclerView.adapter = adapter
//            binding.neighborsList.adapter = adapter
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val neighbors = NeighborRepository.getInstance().getNeighbours()
//        val adapter = ListNeighborsAdapter(neighbors,this)
//        recyclerView.adapter = adapter
        setData()
    }


    override fun onDeleteNeibor(neighbor: Neighbor) {
        println("About to delete " + neighbor.name)
        showAlertDialog(neighbor)
    }

    override fun onDisplayDetails(neighbor: Neighbor) {
        println("About to display details " + neighbor.name)
        (activity as? NavigationListener)?.let {
            it.showFragment(DetailsNeighborFragment(neighbor))
        }
    }

    private fun showAlertDialog(neighbor: Neighbor) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(recyclerView.context)
        alertDialog.setTitle("Confirmation Message")
        alertDialog.setMessage("Voulez-vous supprimer ce voisin ?")
        alertDialog.setPositiveButton(
            "yes"
        ) { _, _ ->
            Toast.makeText(recyclerView.context, "Voisin suprimmé avec succès !", Toast.LENGTH_LONG).show()
            repository.delete(neighbor)
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
