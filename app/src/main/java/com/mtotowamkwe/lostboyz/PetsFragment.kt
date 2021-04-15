package com.mtotowamkwe.lostboyz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtotowamkwe.lostboyz.model.PetViewModel

private const val TAG = "PetsFragment"

class PetsFragment : Fragment() {

    private lateinit var petViewModel: PetViewModel
    private lateinit var petsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        petViewModel = ViewModelProvider(this)[PetViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_pets, container, false)

        petsRecyclerView = view.findViewById(R.id.pets_recycler_view)
        petsRecyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        petViewModel.listOfLostPetsLiveData.observe(
            viewLifecycleOwner,
            Observer { cutePets ->
                Log.d(TAG, "Have lost pets from the ViewModel\n $cutePets")

                // TODO: Update the RecyclerView's data
            }
        )
    }

    companion object {
        fun newInstance() = PetsFragment()
    }
}
