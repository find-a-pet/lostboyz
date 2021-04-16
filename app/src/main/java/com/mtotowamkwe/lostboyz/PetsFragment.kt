package com.mtotowamkwe.lostboyz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mtotowamkwe.lostboyz.model.Pet
import com.mtotowamkwe.lostboyz.model.PetViewModel
import de.hdodenhof.circleimageview.CircleImageView

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
                petsRecyclerView.adapter = PetAdapter(cutePets)
            }
        )
    }

    private class PetHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private inner class PetAdapter(private val allPets: List<Pet>) :
        RecyclerView.Adapter<PetHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetHolder {
            return PetHolder(
                layoutInflater.inflate(R.layout.pet_list_item, parent, false))
        }

        override fun onBindViewHolder(holder: PetHolder, position: Int) {
            val aPet = allPets[position]
            val petName: TextView = holder.itemView.findViewById(R.id.petName)
            val petImage: CircleImageView = holder.itemView.findViewById(R.id.petImage)

            holder.itemView.apply {
                petName.text = aPet.name
                Glide.with(this).load(aPet.url).into(petImage)
            }

            holder.itemView
        }

        override fun getItemCount(): Int = allPets.size

    }

    companion object {
        fun newInstance() = PetsFragment()
    }
}
