package com.annaalfiani.gmcapps.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Studio(
    @SerializedName("id") var id : Int? = null,
    @SerializedName("nama_studio") var nama : String? = null,
    @SerializedName("jumlah_kursi") var jumlahKursi : Int? = null
) : Parcelable