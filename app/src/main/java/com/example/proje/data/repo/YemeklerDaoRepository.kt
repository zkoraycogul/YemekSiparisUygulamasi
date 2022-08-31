package com.example.proje.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.proje.data.entity.*
import com.example.proje.retrofit.YemeklerDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class YemeklerDaoRepository(var ymkdao:YemeklerDao) {
    var yemeklerListesi: MutableLiveData<List<SepetYemekler>>
    var yemek_liste: MutableLiveData<List<Yemekler>>
    var bosListe = ArrayList<SepetYemekler>()

    init {
        yemeklerListesi = MutableLiveData()
        yemek_liste = MutableLiveData()
    }

    fun yemekleriAl(): MutableLiveData<List<Yemekler>> {
        return yemek_liste
    }

    fun sepetYemekleriGetir(): MutableLiveData<List<SepetYemekler>> {
        return yemeklerListesi
    }

    fun yemekAra(yemekAra:String){
        Log.e("Aranılan yemek", yemekAra)
    }

    fun yemekKayit(yemek_adi: String,yemek_resim_adi: String,yemek_fiyat: Int,yemek_siparis_adet: Int,kullanici_adi: String) {
        ymkdao.sepetYemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
            .enqueue(object:Callback<CRUDCevap>{
                override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {
                }
                override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
            })
    }


    fun tumYemekleriListele() {
        ymkdao.tumYemekler().enqueue(object : Callback<YemeklerCevap> {
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler
                yemek_liste.value = liste
            }
            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })
    }

    fun tumSepetYemekleriAl(kullanici_adi: String) {
        ymkdao.sepetGoster(kullanici_adi).enqueue(object :Callback<SepetYemeklerCevap>{
            override fun onResponse(call: Call<SepetYemeklerCevap>?, response: Response<SepetYemeklerCevap>) {
                val yemekListesiSepet = response.body().sepet_yemekler
                    yemeklerListesi.value = yemekListesiSepet
                }
            override fun onFailure(call: Call<SepetYemeklerCevap>?, t: Throwable?) {}
        })
    }

    fun yemekSil(yemekId: Int,kullanici_adi: String) {
        ymkdao.sepettenSil(yemekId,kullanici_adi).enqueue(object : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {
                val basari = response.body().success
                val mesaj = response.body().message
                Log.e("Sepet Yemek sil","$basari - $mesaj- $yemekId")
                tumSepetYemekleriAl(kullanici_adi)

            }

            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {

            }
        })
    }
}
