package com.example.csapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.csapp.Global
import com.example.csapp.R
import kotlinx.android.synthetic.main.fragment_molotov.*

class MolotovFragment : Fragment(R.layout.fragment_molotov) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

    }

    private fun initData(){

        if(Global.maps["mirage"] == true) {

            molotov_layout.setBackgroundResource(R.drawable.mirage_background_blur)
        }

        if(Global.maps["inferno"] == true) {

            molotov_layout.setBackgroundResource(R.drawable.inferno_background_blur)
        }

        if(Global.maps["dust2"] == true) {
            molotov_layout.setBackgroundResource(R.drawable.dust2_background_blur)
        }

        if(Global.maps["overpass"] == true) {
            molotov_layout.setBackgroundResource(R.drawable.overpass_background_blur)
        }

        if(Global.maps["nuke"] == true) {
            molotov_layout.setBackgroundResource(R.drawable.nuke_background_blur)
        }

        if(Global.maps["vertigo"] == true) {
            molotov_layout.setBackgroundResource(R.drawable.vertigo_background_blur)
        }

        if(Global.maps["ancient"] == true) {
            molotov_layout.setBackgroundResource(R.drawable.ancient_background_blur)
        }

    }

}