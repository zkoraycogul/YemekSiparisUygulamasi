package com.example.proje.retrofit

import com.example.proje.data.entity.CRUDCevap
import com.example.proje.data.entity.SepetYemeklerCevap
import com.example.proje.data.entity.YemeklerCevap
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerDao {

    // http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php

    // http://kasimadalan.pe.hu/

    @GET("yemekler/tumYemekleriGetir.php")
    fun tumYemekler() : Call<YemeklerCevap>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun sepetYemekEkle(@Field("yemek_adi") yemekAd:String,
                       @Field("yemek_resim_adi") yemekResimAdi:String,
                       @Field("yemek_fiyat") yemek_fiyat:Int,
                       @Field("yemek_siparis_adet") yemek_siparis_adet:Int
                     ) : Call<CRUDCevap>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun sepettenSil(@Field("sepet_yemek_id") sepet_yemek_id: Int
                   ) : Call<CRUDCevap>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun sepetGoster(@Field("kullanici_adi") kullaniciAdi: String) : Call<SepetYemeklerCevap>



}