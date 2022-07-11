package com.acera.test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.acera.test.databinding.ActivityDetailTodoBinding
import com.acera.test.model.DataModelItem
import com.acera.test.viewmodel.MainViewModel
import kotlin.properties.Delegates

class DetailTodo : AppCompatActivity() {

    private var id by Delegates.notNull<Int>()

    private lateinit var detailTodoViewModel: MainViewModel
    private lateinit var detailBinding: ActivityDetailTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailTodoBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        id = intent.getIntExtra("id", 0)

        setupViewModel()
        getDetailTodo(id.toString())
    }

    private fun setupViewModel() {
        detailTodoViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
    }

    private fun getDetailTodo(id: String) {
        detailTodoViewModel.detailTodo.observe(this) { detail ->
            setDetailTodo(detail)
        }
        detailTodoViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        detailTodoViewModel.getDetailTodo(id)
    }

    private fun setDetailTodo(detail: DataModelItem) {
        detailBinding.apply {
            dtvId.text = "Id : " + detail.userId
            dtvTitle.text = "Title : " + detail.title
            dtvComplete.text = "Complete : " + detail.completed
        }
    }

    private fun showLoading(isLoading: Boolean) {
        detailBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}