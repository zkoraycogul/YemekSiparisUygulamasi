package com.example.proje.ui.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proje.data.entity.SepetYemekler
import com.example.proje.data.repo.YemeklerDaoRepository
import com.example.proje.ui.adapter.YemeklerAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SepetGoruntulemeViewModel @Inject constructor (var ymkrepo:YemeklerDaoRepository) : ViewModel() {

    var sepetYemeklerListesi = MutableLiveData<List<SepetYemekler>>()

    init {
        yemekleriGoruntule(kullanici_adi = "Koray")
        sepetYemeklerListesi = ymkrepo.sepetYemekleriGetir()
    }

    fun sil(yemekId: Int, kullanici_adi: String) {
        ymkrepo.yemekSil(yemekId,kullanici_adi)
    }

    fun yemekleriGoruntule(kullanici_adi:String){
        ymkrepo.tumSepetYemekleriAl(kullanici_adi)
    }
}

