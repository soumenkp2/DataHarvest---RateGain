package com.example.dataharvest_rategain.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dataharvest_rategain.data.repository.DataFirebaseRepository
import com.example.dataharvest_rategain.data.repository.DataFirebaseRepositoryImplementation
import com.example.dataharvest_rategain.viewmodels.dataviewmodel

class DataViewModelFactory(private val repository: DataFirebaseRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when(modelClass){
        dataviewmodel::class.java -> dataviewmodel(repository)
        else -> throw IllegalArgumentException("Unknown ViewModel class")
    } as T
}