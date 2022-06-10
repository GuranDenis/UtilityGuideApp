package com.utilityhub.csapp.domain.use_case.utility

import com.utilityhub.csapp.domain.repository.UtilityRepository

class DeleteFavorite(
    private val repository: UtilityRepository
) {
    operator fun invoke(map: String, utility: String, landingSpot: String, throwingSpot: String) =
        repository.deleteFavorite(map, utility, landingSpot, throwingSpot)
}