package com.acera.test.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModelItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("completed")
	val completed: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
) : Parcelable

@Parcelize
data class DataModel(

	@field:SerializedName("dataModel")
	val dataModel: List<DataModelItem?>? = null
) : Parcelable
