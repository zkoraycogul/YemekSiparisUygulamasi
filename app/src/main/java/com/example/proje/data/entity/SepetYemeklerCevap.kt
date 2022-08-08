package com.example.proje.data.entity

import com.google.gson.annotations.SerializedName

class SepetYemeklerCevap(@SerializedName("sepet_yemekler") var sepet_yemekler:List<SepetYemekler>,
                         @SerializedName("success") var success:Int) {
}