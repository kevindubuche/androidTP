package com.example.neighbors.ui

import android.annotation.SuppressLint
import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.neighbors.NavigationListener
import com.example.neighbors.R
import com.example.neighbors.di.DI
import com.example.neighbors.repositories.NeighborRepository
import com.example.neighbors.ui.fragments.ListNeighborsFragment
import com.example.neighbors.viewmodels.NeighborViewModel

class MainActivity : AppCompatActivity() , NavigationListener {
    private lateinit var toolbar: Toolbar
    private lateinit var repository: NeighborRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DI.inject(application)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
//       setSupportActionBar(toolbar)
        showFragment(ListNeighborsFragment())
    }



    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.layout.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.persistent -> {
                repository = NeighborRepository.getInstance(application)
                repository.activePersitantMode()
                showFragment(ListNeighborsFragment())
                Toast.makeText(this,"Mode persistent acitivé",Toast.LENGTH_SHORT).show()
            }
            R.id.nonPersistent -> {
                repository = NeighborRepository.getInstance(application)
                repository.activeMemoryMode()
                showFragment(ListNeighborsFragment())
                Toast.makeText(this,"Mode memoire acitivé",Toast.LENGTH_SHORT).show()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateTitle(title: Int) {
        toolbar.setTitle(title)
    }

}