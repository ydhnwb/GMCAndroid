package com.annaalfiani.gmcapps.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kursi(
    @SerializedName("id") var id : Int? = null,
    @SerializedName("total_rows") var totalRow : Int? = null,
    @SerializedName("total_column") var totalColumn : Int? = null,
    @SerializedName("seat") var seats : List<Seat> = mutableListOf()
) : Parcelable

@Parcelize
data class Seat(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("nama_kursi") var nama_kursi: String? = null
) : Parcelable
