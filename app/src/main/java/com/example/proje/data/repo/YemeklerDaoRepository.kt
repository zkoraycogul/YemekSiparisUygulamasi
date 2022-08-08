package com.example.proje.data.repo

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.proje.MainActivity
import com.example.proje.R
import com.example.proje.data.entity.SepetYemekler
import com.example.proje.data.entity.SepetYemeklerCevap
import com.example.proje.data.entity.Yemekler
import com.example.proje.data.entity.YemeklerCevap
import com.example.proje.retrofit.YemeklerDao
import com.example.proje.ui.fragment.SepetGoruntulemeFragment
import com.example.proje.ui.fragment.YemekDetayFragment
import com.example.proje.ui.viewmodel.SepetGoruntulemeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class YemeklerDaoRepository(var ymkdao:YemeklerDao) {
    val liste = ArrayList<SepetYemekler>()
    var yemeklerListesi: MutableLiveData<List<SepetYemekler>>
    var yemek_liste: MutableLiveData<List<Yemekler>>
    val yemekListesi = ArrayList<SepetYemekler>()


    init {
        yemeklerListesi = MutableLiveData()
        yemek_liste = MutableLiveData()
    }

    fun yemekleriAl(): MutableLiveData<List<Yemekler>> {

        return yemek_liste
    }

    fun tumYemekleriAl2() {
        ymkdao.tumYemekler().enqueue(object : Callback<YemeklerCevap> {
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler
                yemek_liste.value = liste
            }

            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })
    }

    fun yemekleriGetir(): MutableLiveData<List<SepetYemekler>> {
        return yemeklerListesi
    }

    fun yemekKayit(yemekId: Int,yemekAdi: String,yemekResim: String,yemekFiyat: String,yemekAdet: String) {
        val y4 = SepetYemekler(yemekId,yemekAdi,yemekResim,yemekFiyat.toString().toDouble(),yemekAdet.toString().toInt())
        liste.add(y4)
        yemeklerListesi.value = liste
        //ymkdao.sepetYemekEkle(yemekAdi,yemekResim,yemekFiyat.toInt(),yemekAdet.toInt())

    }

    fun yemekSil(yemekId: Int,yemekAdi:String) {
        liste.removeAt(0)
        yemeklerListesi.value = liste
    }

    fun tumYemekleriAl() {
        yemeklerListesi.value = liste
    }
}
