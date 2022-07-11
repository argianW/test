package com.acera.test

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.acera.test.adapter.MainAdapter
import com.acera.test.databinding.ActivityMainBinding
import com.acera.test.model.DataModelItem
import com.acera.test.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), RecyclerViewClickListener {
    private var mainAdapter = MainAdapter()
    lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        allTodo()
        setupRv()
    }


    private fun allTodo() {
        mainViewModel.todo.observe(this) {
            mainAdapter.setDataTodo(it)
        }
        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        mainViewModel.getDataTodo()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
    }

    private fun setupRv() {
        layoutManager = LinearLayoutManager(this)
        binding.rvTodo.layoutManager = layoutManager
        mainAdapter.listener = this
        binding.rvTodo.adapter = mainAdapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onItemClicked(view: View, item: DataModelItem) {
        val detailUserObject = Intent(this, DetailTodo::class.java)
        detailUserObject.putExtra("id", item.id)
        startActivity(detailUserObject)
    }
}