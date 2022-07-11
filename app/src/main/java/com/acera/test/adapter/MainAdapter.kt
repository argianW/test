package com.acera.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acera.test.RecyclerViewClickListener
import com.acera.test.databinding.ItemTodoBinding
import com.acera.test.model.DataModelItem

class MainAdapter : RecyclerView.Adapter<MainAdapter.ListViewHolder>() {
    private var listTodo: List<DataModelItem> = ArrayList()
    var listener: RecyclerViewClickListener? = null

    fun setDataTodo(users: List<DataModelItem>) {
        users.toMutableList().clear()
        this.listTodo = users.toMutableList()
        notifyDataSetChanged()
    }

    class ListViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val todo = listTodo[position]
        holder.binding.tvUserId.text = "Id : " + todo.userId.toString()
        holder.binding.tvTitle.text = "Title : " + todo.title
        holder.binding.tvComplete.text = "Complete : " + todo.completed.toString()

        holder.binding.clCard.setOnClickListener {
            listener?.onItemClicked(it, todo)
        }
    }

    override fun getItemCount(): Int {
        return listTodo.size
    }
}