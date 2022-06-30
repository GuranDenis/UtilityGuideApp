package com.utilityhub.csapp.ui.utility.landing

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.utilityhub.csapp.common.Constants
import com.utilityhub.csapp.common.Utils
import com.utilityhub.csapp.databinding.FragmentSmokeBinding
import com.utilityhub.csapp.domain.model.Response
import com.utilityhub.csapp.domain.model.Utility
import com.utilityhub.csapp.ui.core.BaseLandFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SmokeFragment : BaseLandFragment<FragmentSmokeBinding>(
    FragmentSmokeBinding::inflate
) {

    private val args: SmokeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getNavArgs()
        setBackground()
        getLandingSpots()
        setAdapter()
    }

    override fun onStart() {
        super.onStart()
        binding.apply {
            searchView.apply {
                setOnQueryTextListener(object : OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        clearFocus()
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        searchText = newText!!
                        if (searchText.isNotBlank()) {
                            utilityFilters.add(searchText)
                            filterByTagsAndText()
                            utilityFilters.remove(searchText)
                        } else {
                            adapter.filter.filter("")
                            filterByTagsAndText()
                        }

                        return true
                    }
                })
            }
            chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
                if (utilityFilters.isNotEmpty())
                    utilityFilters.clear()
                checkedIds.forEach {
                    when (it) {
                        chipASite.id -> utilityFilters.add(Constants.TAG_A_SITE)
                        chipBSite.id -> utilityFilters.add(Constants.TAG_B_SITE)
                        chipMid.id -> utilityFilters.add(Constants.TAG_MID)
                        chipOneWay.id -> utilityFilters.add(Constants.TAG_ONE_WAY)
                        chipRetake.id -> utilityFilters.add(Constants.TAG_RETAKE)
                    }
                }
                if (searchText.isNotBlank())
                    utilityFilters.add(searchText)
                adapter.filter.filter("")
                filterByTagsAndText()
                utilityFilters.remove(searchText)
            }
        }
    }

    private fun setAdapter() {
        binding.recyclerView.adapter = adapter
    }

    private fun filterByTagsAndText() {
        utilityFilters.forEach {
            adapter.filter.filter(it)
        }
    }

    private fun setBackground() {
        val backgroundBlur = Utils.getMapBackgroundBlurDrawable(
            viewModel.currentMap.value.toString(),
            requireContext()
        )
        binding.smokeLayout.background = backgroundBlur
    }

    private fun getNavArgs() {
        viewModel.setCurrentMap(args.map)
    }

    private fun getLandingSpots() {
        viewModel.getLandingSpots(utilityType = utilityType)
            .observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Response.Success -> {
                        if (landingSpots.isNotEmpty()) {
                            landingSpots.clear()
                        }
                        landingSpots.addAll(response.data)
                        adapter.setData(landingSpots)
                    }
                    is Response.Failure -> Log.w("getLandingSpots", response.errorMessage)
                }
            }
    }

    override fun onUtilityClick(utility: Utility) {
        val navThrow =
            SmokeFragmentDirections.actionSmokeFragmentToThrowFragment(
                map = viewModel.currentMap.value.toString(),
                utilityType = utilityType,
                landingSpot = utility.name!!,
                landId = utility.id!!
            )
        findNavController().navigate(navThrow)
    }

    companion object {
        private const val utilityType = Constants.SMOKES_REF
    }

}