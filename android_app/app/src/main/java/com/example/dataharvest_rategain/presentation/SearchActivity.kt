package com.example.dataharvest_rategain.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dataharvest_rategain.R
import com.example.dataharvest_rategain.adapters.DataAdapter
import com.example.dataharvest_rategain.data.repository.DataFirebaseRepositoryImplementation
import com.example.dataharvest_rategain.databinding.ActivityMainBinding
import com.example.dataharvest_rategain.databinding.ActivitySearchBinding
import com.example.dataharvest_rategain.datamodels.Data
import com.example.dataharvest_rategain.presentation.search.SearchAdapter
import com.example.dataharvest_rategain.utils.DataViewModelFactory
import com.example.dataharvest_rategain.viewmodels.dataviewmodel
import java.util.Locale

class SearchActivity : AppCompatActivity() {
    val viewModel: dataviewmodel by lazy {
        ViewModelProvider(this, DataViewModelFactory(DataFirebaseRepositoryImplementation())).get(
            dataviewmodel::class.java) }

    private lateinit var binding: ActivitySearchBinding
    //private lateinit var viewModel: dataviewmodel
    private lateinit var dataAdapter : SearchAdapter
    private var list : List<Data> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fetchData()

        // Observe the data changes
        viewModel.dataList.observe(this, { dataList ->
            Log.d("soumen main",dataList.size.toString())
            dataAdapter = SearchAdapter(this, dataList)
            binding.rcvDataList.adapter = dataAdapter
            binding.rcvDataList.layoutManager = LinearLayoutManager(this)
            list = dataList
            dataAdapter.notifyDataSetChanged()
        })

        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText,list)
                return true
            }

        })


    }

    /**
     * Filters the original list of Data objects based on the provided query.
     * @param query: The search query to filter the list.
     * If the query is not null, it iterates through the original list and adds items whose names contain the query (case-insensitive) to a new filtered list.
     * If the filtered list is not empty, it updates the adapter with the filtered data; otherwise, no data is displayed.
     */
    private fun filterList(query: String?, mList : List<Data>) {

        if (query != null) {
            val filteredList = ArrayList<Data>()
            for (i in mList) {
                if (i.blog_title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                //Toast.makeText(context, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                dataAdapter.setFilteredList(filteredList)
            }
        }
    }


}