package com.example.neighbors.ui.fragments

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.neighbors.NavigationListener
import com.example.neighbors.R
import com.example.neighbors.models.Neighbor
import com.example.neighbors.repositories.NeighborRepository
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso
import android.widget.CompoundButton




class DetailsNeighborFragment(neighbor : Neighbor): Fragment() {
    val neighbor = neighbor
    private lateinit var imagePreview: ImageView
//    private lateinit var imageURI: TextInputLayout
    private lateinit var name: TextView
    private lateinit var phone: TextView
    private lateinit var website: TextView
    private lateinit var address: TextView
    private lateinit var about: TextView
    private lateinit var favoris: Switch
    private lateinit var repository: NeighborRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application: Application = activity?.application ?: return
        repository = NeighborRepository.getInstance(application)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_neighbor, container, false)


        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.details_neighbor_title)
        }

        imagePreview = view.findViewById(R.id.image)
        name = view.findViewById(R.id.name)
        phone = view.findViewById(R.id.phone)
        website = view.findViewById(R.id.website)
        address = view.findViewById(R.id.address)
        about = view.findViewById(R.id.about)
        favoris = view.findViewById(R.id.favorisSwitch)

        name.setText(neighbor.name);
        phone.setText(neighbor.phoneNumber);
        website.setText(neighbor.webSite);
        address.setText(neighbor.address);
        about.setText(neighbor.aboutMe);
        Picasso.get().load(neighbor.avatarUrl)
            .error(R.drawable.ic_baseline_person_outline_24)
            .into(imagePreview)
        favoris.setChecked(neighbor.favorite);

//        favoris.setOnClickListener {
//            println("change sttus of voisin")
//            repository.updateFavoriteStatus(neighbor)
//
//        }

        favoris.setOnCheckedChangeListener({ buttonView, isChecked ->
            // do something, the isChecked will be
            // true if the switch is in the On position
            neighbor.favorite = !neighbor.favorite
            repository.updateFavoriteStatus(neighbor)
            println("change sattus of voisin")
        })


        return view
    }




}