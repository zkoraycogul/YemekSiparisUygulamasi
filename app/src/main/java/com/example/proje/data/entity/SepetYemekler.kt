package com.example.proje.data.entity

import java.io.Serializable

data class SepetYemekler (var sepetYemekId:Int,
                          var sepetYemekAdi:String,
                          var sepetYemekResimAdi:String,
                          var sepetFiyat:Double,
                          var sepetSiparisAdet:Int) : Serializable{
}