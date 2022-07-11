package com.acera.test

import android.view.View
import com.acera.test.model.DataModel
import com.acera.test.model.DataModelItem

interface RecyclerViewClickListener {
    fun onItemClicked(view: View, item: DataModelItem)
}