package com.example.dataharvest_rategain.data.repository

import com.example.dataharvest_rategain.datamodels.Data

interface DataFirebaseRepository {
    suspend fun getDataList() :List<Data>
    suspend fun getSortedDataListAsLikes(): List<Data>
    suspend fun getSortedDataListAsDates(): List<Data>
}