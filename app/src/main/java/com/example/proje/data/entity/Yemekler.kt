package com.example.proje.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Yemekler(@SerializedName("yemek_id") var yemekId:Int,
                    @SerializedName("yemek_adi") var yemekAdi:String,
                    @SerializedName("yemek_resim_adi") var yemekResimAdi:String,
                    @SerializedName("yemek_fiyat") var yemekFiyat:Int) : Serializable {
}