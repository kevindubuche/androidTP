package com.example.neighbors.adapters

import android.widget.Button
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.neighbors.R
import com.example.neighbors.models.Neighbor

interface ListNeighborHandler {
    fun onDeleteNeibor(neighbor: Neighbor)

}
