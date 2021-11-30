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
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.neighbors.NavigationListener
import com.example.neighbors.R
import com.example.neighbors.di.DI.repository
import com.example.neighbors.repositories.NeighborRepository
import com.example.neighbors.models.Neighbor
import com.example.neighbors.ui.MainActivity
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso

class AddNeighborFragment: Fragment() {
    private lateinit var imagePreview: ImageView
    private lateinit var imageURI: TextInputLayout
    private lateinit var name: TextInputLayout
    private lateinit var phone: TextInputLayout
    private lateinit var website: TextInputLayout
    private lateinit var address: TextInputLayout
    private lateinit var about: TextInputLayout
    private lateinit var saveBotton: Button
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
        val view = inflater.inflate(R.layout.add_neighbor, container, false)

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.add_neighbors_title)
        }
//        Récupérer les valeurs de champs
        imagePreview = view.findViewById(R.id.image)
        imageURI = view.findViewById(R.id.imageURI)
        name = view.findViewById(R.id.name)
        phone = view.findViewById(R.id.phone)
        website = view.findViewById(R.id.website)
        address = view.findViewById(R.id.address)
        about = view.findViewById(R.id.about)
        saveBotton = view.findViewById(R.id.saveBotton)

//        saveBotton.isClickable = false
        saveBotton.isEnabled = false

        this.validationCheck()

//        backToNeighbors.setOnClickListener {
//            (activity as? NavigationListener)?.showFragment(ListNeighborsFragment())
//        }
//
//        (activity as? NavigationListener)?.updateTitle(R.string.newNeighborTitle)

        saveBotton.setOnClickListener {
//            Créer un nouvel objet Neighbor
            val newNeighbor = Neighbor(
                id = 0,
                name = name.editText?.text.toString(),
                avatarUrl = imageURI.editText?.text.toString(),
                address = address.editText?.text.toString(),
                phoneNumber = phone.editText?.text.toString(),
                aboutMe = about.editText?.text.toString(),
                favorite = false,
                webSite = website.editText?.text.toString()
            )
            validationCheck()
            addNeighbor(newNeighbor)

        }
        return view
    }

    private fun addNeighbor(newNeighbor: Neighbor) {
        repository.add(newNeighbor)
        Toast.makeText(context, "Voisin ajoute", Toast.LENGTH_LONG).show()
        (activity as? NavigationListener)?.showFragment(ListNeighborsFragment())
    }

    private fun validationCheck() {
        this.validationImageURI()
        this.validationName()
        this.validationPhone()
        this.validationWebsite()
        this.validationAddress()
        this.validationAbout()
    }

    private fun validationImageURI() {
        imageURI.editText?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @SuppressLint("StringFormatInvalid")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()) {
                    imageURI.isErrorEnabled = true
                    imageURI.error = getString(R.string.error_message_for_required_field)
                } else if (!Patterns.WEB_URL.matcher(p0).matches()) {
                    imageURI.isErrorEnabled = true
                    imageURI.error = getString(R.string.error_message_for_invalid_URI)
                } else {
                    imageURI.isErrorEnabled = false
                    Picasso.get().load(imageURI.editText?.text.toString())
                        .error(R.drawable.ic_baseline_person_outline_24)
                        .into(imagePreview)
                }
                enableSaveButton()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun validationName() {
        name.editText?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @SuppressLint("StringFormatInvalid")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()) {
                    name.isErrorEnabled = true
                    name.error = getString(R.string.error_message_for_required_field)
                } else if (p0.length < 3 || p0.length > 30) {
                    name.isErrorEnabled = true
                    name.error = getString(R.string.error_message_for_invalid_name)
                } else {
                    name.isErrorEnabled = false
                }
                enableSaveButton()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun validationPhone() {
        phone.editText?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @SuppressLint("StringFormatInvalid")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()) {
                    phone.isErrorEnabled = true
                    phone.error = getString(R.string.error_message_for_required_field)
                } else if (p0.length != 10 || ( (p0[0].toString()+p0[1].toString() != "06" && p0[0].toString()+p0[1].toString() != "07")  ) ) {
                    phone.isErrorEnabled = true
                    phone.error = getString(R.string.error_message_for_invalid_phone)
                } else {
                    phone.isErrorEnabled = false

                }

                enableSaveButton()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun validationWebsite() {
        website.editText?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @SuppressLint("StringFormatInvalid")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()) {
                    website.isErrorEnabled = true
                    website.error = getString(R.string.error_message_for_required_field)
                } else if (!Patterns.WEB_URL.matcher(p0).matches()) {
                    website.isErrorEnabled = true
                    website.error = getString(R.string.error_message_for_invalid_website)
                } else {
                    website.isErrorEnabled = false
                }

                enableSaveButton()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun validationAddress() {
        address.editText?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @SuppressLint("StringFormatInvalid")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()) {
                    address.isErrorEnabled = true
                    address.error = getString(R.string.error_message_for_required_field)
                } else if (p0.length < 3 || p0.length > 30) {
                    address.isErrorEnabled = true
                    address.error = getString(R.string.error_message_for_invalid_address)
                } else {
                    address.isErrorEnabled = false
                }

                enableSaveButton()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun validationAbout() {
        about.editText?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @SuppressLint("StringFormatInvalid")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()) {
                    about.isErrorEnabled = true
                    about.error = getString(R.string.error_message_for_required_field)
                } else if (p0.length < 5 || p0.length > 30) {
                    about.isErrorEnabled = true
                    about.error = getString(R.string.error_message_for_invalid_about)
                } else {
                    about.isErrorEnabled = false
                }

                enableSaveButton()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun enableSaveButton() {
        saveBotton.setEnabled(
            !imageURI.isErrorEnabled &&
            !name.isErrorEnabled &&
            !phone.isErrorEnabled &&
            !website.isErrorEnabled &&
            !address.isErrorEnabled &&
            !about.isErrorEnabled)
    }

}