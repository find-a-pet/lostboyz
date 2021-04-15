package com.mtotowamkwe.lostboyz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtotowamkwe.lostboyz.api.LostboyzApi
import com.mtotowamkwe.lostboyz.api.PetsFetcher
import com.mtotowamkwe.lostboyz.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "PetsFragment"

class PetsFragment : Fragment() {

    private lateinit var petsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val petsLiveData: LiveData<String> = PetsFetcher().fetchPets()

        petsLiveData.observe(
            this,
            Observer { responseString ->
                Log.d(TAG, "Response received: $responseString")
            })
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

    companion object {
        fun newInstance() = PetsFragment()
    }
}
