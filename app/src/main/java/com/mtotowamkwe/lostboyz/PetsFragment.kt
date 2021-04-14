package com.mtotowamkwe.lostboyz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtotowamkwe.lostboyz.api.LostboyzApi
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
        
        /*TODO: If the user is not logged in send them to the log in page
            where they'll have to sign up if they don't have an account*/

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.pets)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        
        val lostboyzApi: LostboyzApi = retrofit.create(LostboyzApi::class.java)

        val fetchPets: Call<String> = lostboyzApi.pets()

        fetchPets.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d(TAG, "Response received: ${response.body()}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "Failed to fetch pets", t)
            }
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

    // Check if the user is logged in

    // If not send them to the log in page

    // Fetch the list of lost pets

    // If not empty display the pets

    // If empty display a message accordingly

    companion object {
        fun newInstance() = PetsFragment()
    }
}
