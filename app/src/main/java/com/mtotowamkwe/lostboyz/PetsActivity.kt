package com.mtotowamkwe.lostboyz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PetsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pets)

        val isFragmentContainerEmpty = savedInstanceState == null

        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, PetsFragment.newInstance())
                .commit()
        }
    }
}