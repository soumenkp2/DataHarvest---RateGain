package com.example.dataharvest_rategain.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dataharvest_rategain.data.repository.DataFirebaseRepository
import com.example.dataharvest_rategain.datamodels.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class dataviewmodel(val repository: DataFirebaseRepository) : ViewModel(){
    private val _dataList = MutableLiveData<List<Data>>()
    val dataList : LiveData<List<Data>> get() = _dataList

    fun fetchData() = viewModelScope.launch {
        var dataListFromRepo : List<Data> = mutableListOf()
        dataListFromRepo = repository.getDataList()
        Log.d("soumen viewmodel", dataListFromRepo.size.toString())

        _dataList.postValue(dataListFromRepo)
    }

    fun fetchSortedDataByLikes() = viewModelScope.launch {
        var dataListFromRepo : List<Data> = mutableListOf()
        dataListFromRepo = repository.getSortedDataListAsLikes()
        Log.d("soumen viewmodel", dataListFromRepo.size.toString())

        _dataList.postValue(dataListFromRepo)
    }

    fun fetchSortedDataByDates() = viewModelScope.launch {
        var dataListFromRepo : List<Data> = mutableListOf()
        dataListFromRepo = repository.getSortedDataListAsDates()
        Log.d("soumen viewmodel", dataListFromRepo.size.toString())

        _dataList.postValue(dataListFromRepo)
    }

}