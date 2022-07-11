package com.acera.test.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acera.test.api.ApiConfig
import com.acera.test.model.DataModelItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _todo = MutableLiveData<List<DataModelItem>>()
    val todo: LiveData<List<DataModelItem>> = _todo

    private val _detailTodo = MutableLiveData<DataModelItem>()
    val detailTodo: LiveData<DataModelItem> = _detailTodo

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getDataTodo() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getTodo()
        client.enqueue(object : Callback<List<DataModelItem>> {
            override fun onResponse(
                call: Call<List<DataModelItem>>,
                response: Response<List<DataModelItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _todo.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<DataModelItem>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDetailTodo(id: String?) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailTodo(id!!)
        client.enqueue(object : Callback<DataModelItem> {
            override fun onResponse(
                call: Call<DataModelItem>,
                response: Response<DataModelItem>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detailTodo.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DataModelItem>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object {
        private const val TAG = "ViewModel"
    }

}