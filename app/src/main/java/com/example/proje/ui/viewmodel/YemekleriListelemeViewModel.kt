package com.example.proje.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proje.data.entity.SepetYemekler
import com.example.proje.data.entity.Yemekler
import com.example.proje.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YemekleriListelemeViewModel @Inject constructor (var ymkrepo: YemeklerDaoRepository) : ViewModel() {

    var sepetYemeklerListesi = MutableLiveData<List<SepetYemekler>>()
    var yemek_liste = MutableLiveData<List<Yemekler>>()
    init {
        yemekleriYukle()
        yemek_liste = ymkrepo.yemekleriAl()
    }

    fun yemekleriYukle(){
        ymkrepo.tumYemekleriListele()
    }

    fun ara(kelimeAra:String){
        ymkrepo.yemekAra(kelimeAra)
    }

    fun yemekleriGoruntule(kullanici_adi:String){
        ymkrepo.tumSepetYemekleriAl("Koray")
        sepetYemeklerListesi = ymkrepo.sepetYemekleriGetir()
    }
}