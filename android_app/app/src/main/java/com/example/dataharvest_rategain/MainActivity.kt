package com.example.dataharvest_rategain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dataharvest_rategain.adapters.DataAdapter
import com.example.dataharvest_rategain.data.repository.DataFirebaseRepositoryImplementation
import com.example.dataharvest_rategain.databinding.ActivityMainBinding
import com.example.dataharvest_rategain.presentation.SearchActivity
import com.example.dataharvest_rategain.utils.DataViewModelFactory
import com.example.dataharvest_rategain.viewmodels.dataviewmodel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private lateinit var viewModel: dataviewmodel
    private lateinit var dataAdapter : DataAdapter

    val viewModel: dataviewmodel by lazy {
        ViewModelProvider(this,DataViewModelFactory(DataFirebaseRepositoryImplementation())).get(dataviewmodel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModel = ViewModelProvider(this, DataViewModelFactory(DataFirebaseRepositoryImplementation())).get(dataviewmodel::class.java)

        viewModel.fetchData()

        // Observe the data changes
        viewModel.dataList.observe(this, { dataList ->
            Log.d("soumen main",dataList.size.toString())
            //Toast.makeText(this@MainActivity," " + dataList.size.toString(),Toast.LENGTH_SHORT).show()
            dataAdapter = DataAdapter(this, dataList)
            binding.rcvDataList.adapter = dataAdapter
            binding.rcvDataList.layoutManager = LinearLayoutManager(this)
            dataAdapter.notifyDataSetChanged()
        })

        binding.sortByDate.setOnClickListener {
            viewModel.fetchSortedDataByDates()
            Toast.makeText(this@MainActivity,"Data Sorted By Date",Toast.LENGTH_SHORT).show()
        }

        binding.tvSortByLikes.setOnClickListener {
            viewModel.fetchSortedDataByLikes()
            Toast.makeText(this@MainActivity,"Data Sorted By Likes",Toast.LENGTH_SHORT).show()
        }

        binding.llTop.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

    }
}