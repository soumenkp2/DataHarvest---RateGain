package com.example.dataharvest_rategain.data.repository

import android.util.Log
import com.example.dataharvest_rategain.datamodels.Data
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class DataFirebaseRepositoryImplementation : DataFirebaseRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val dataCollection = firestore.collection("data")


    override suspend fun getDataList(): List<Data> {
        return try {
            val querySnapshot = dataCollection.get().await()
            val dataList = mutableListOf<Data>()

            for (document in querySnapshot.documents) {
                val blogTitle = document.getString("blog_title") ?: ""
                val imageUrl = document.getString("image_url") ?: ""
                val likes = document.get("likes").toString() ?: ""
                val date = document.getDate("date").toString() ?: ""
                Log.d("soumen add",document.getString("blog_title").toString())
                val data = Data(blogTitle, imageUrl, likes, date)
                dataList.add(data)
            }
            Log.d("soumen repo",dataList.size.toString())
            dataList
        } catch (e: Exception) {
            // Handle exceptions
            Log.d("soumen repo error",e.message.toString())
            emptyList()
        }
    }

    override suspend fun getSortedDataListAsLikes(): List<Data> {
        return try {
            val querySnapshot = dataCollection.orderBy("likes", Query.Direction.DESCENDING).get().await()
            val dataList = mutableListOf<Data>()

            for (document in querySnapshot.documents) {
                val blogTitle = document.getString("blog_title") ?: ""
                val imageUrl = document.getString("image_url") ?: ""
                val likes = document.get("likes").toString() ?: ""
                val date = document.getDate("date").toString() ?: ""
                Log.d("soumen add",document.getString("blog_title").toString())
                val data = Data(blogTitle, imageUrl, likes, date)
                dataList.add(data)
            }
            Log.d("soumen repo",dataList.size.toString())
            dataList
        } catch (e: Exception) {
            // Handle exceptions
            Log.d("soumen repo error",e.message.toString())
            emptyList()
        }
    }

    override suspend fun getSortedDataListAsDates(): List<Data> {
        return try {
            val querySnapshot = dataCollection.orderBy("date", Query.Direction.ASCENDING).get().await()
            val dataList = mutableListOf<Data>()

            for (document in querySnapshot.documents) {
                val blogTitle = document.getString("blog_title") ?: ""
                val imageUrl = document.getString("image_url") ?: ""
                val likes = document.get("likes").toString() ?: ""
                val date = document.getDate("date").toString() ?: ""
                Log.d("soumen add",document.getString("blog_title").toString())
                val data = Data(blogTitle, imageUrl, likes, date)
                dataList.add(data)
            }
            Log.d("soumen repo",dataList.size.toString())
            dataList
        } catch (e: Exception) {
            // Handle exceptions
            Log.d("soumen repo error",e.message.toString())
            emptyList()
        }
    }
}