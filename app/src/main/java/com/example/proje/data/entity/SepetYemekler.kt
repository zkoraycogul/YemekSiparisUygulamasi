package com.example.proje.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SepetYemekler (@SerializedName("sepet_yemek_id") var sepetYemekId:Int,
                          @SerializedName("yemek_adi") var sepetYemekAdi:String,
                          @SerializedName("yemek_resim_adi") var sepetYemekResimAdi:String,
                          @SerializedName("yemek_fiyat") var sepetFiyat:Int,
                          @SerializedName("yemek_siparis_adet") var sepetSiparisAdet:Int,
                          @SerializedName("kullanici_adi") var kullanici_adi:String) : Serializable{
}