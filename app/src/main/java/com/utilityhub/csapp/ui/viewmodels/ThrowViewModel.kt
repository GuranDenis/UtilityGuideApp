package com.utilityhub.csapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.utilityhub.csapp.domain.use_case.utility.UtilityUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ThrowViewModel @Inject constructor(
    private val useCases: UtilityUseCases
) : ViewModel() {

    fun getThrowingSpots(map: String, utility: String, landingSpot: String) = liveData(Dispatchers.IO) {
        useCases.getThrowSpots(map, utility, landingSpot).collect { response ->
            emit(response)
        }
    }
}