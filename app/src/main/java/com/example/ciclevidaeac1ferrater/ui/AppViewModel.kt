package com.example.ciclevidaeac1ferrater.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()


    private fun updateList(newItem: String) {
        _uiState.value =
            uiState.value.copy(listOfLiveCicles = uiState.value.listOfLiveCicles + newItem)
    }

    fun clearList() {
        _uiState.value =
            uiState.value.copy(listOfLiveCicles = emptyList())
    }

    fun gestionaLog(etapa: String) {
        Log.d("MainActivity", etapa)
        updateList(etapa)
    }
}