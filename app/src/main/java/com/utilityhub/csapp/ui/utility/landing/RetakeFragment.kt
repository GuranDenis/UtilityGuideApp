package com.utilityhub.csapp.ui.utility.landing

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.utilityhub.csapp.R
import com.utilityhub.csapp.core.Global
import com.utilityhub.csapp.databinding.FragmentRetakeBinding
import com.utilityhub.csapp.ui.core.BaseFragment
import com.utilityhub.csapp.ui.home.maps.MapsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RetakeFragment : BaseFragment<FragmentRetakeBinding>(FragmentRetakeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        onBackPressedGoToMaps()
    }

    private fun onBackPressedGoToMaps() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                    findNavController().setGraph(R.navigation.nav_graph)
                    val navHome = MapsFragmentDirections.actionGlobalHome()
                    findNavController().navigate(navHome)
                }
            })
    }

    private fun initData() {

        val retakeLayout = binding.retakeLayout

        if (Global.maps["mirage"] == true) {

            retakeLayout.setBackgroundResource(R.drawable.mirage_background_blur)
        }

        if (Global.maps["inferno"] == true) {

            retakeLayout.setBackgroundResource(R.drawable.inferno_background_blur)
        }

        if (Global.maps["dust2"] == true) {
            retakeLayout.setBackgroundResource(R.drawable.dust2_background_blur)
        }

        if (Global.maps["overpass"] == true) {
            retakeLayout.setBackgroundResource(R.drawable.overpass_background_blur)
        }

        if (Global.maps["nuke"] == true) {
            retakeLayout.setBackgroundResource(R.drawable.nuke_background_blur)
        }

        if (Global.maps["vertigo"] == true) {
            retakeLayout.setBackgroundResource(R.drawable.vertigo_background_blur)
        }

        if (Global.maps["ancient"] == true) {
            retakeLayout.setBackgroundResource(R.drawable.ancient_background_blur)
        }

    }

}