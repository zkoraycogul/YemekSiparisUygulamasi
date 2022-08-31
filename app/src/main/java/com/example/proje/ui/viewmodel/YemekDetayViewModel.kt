package com.example.proje.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proje.data.entity.SepetYemekler
import com.example.proje.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class YemekDetayViewModel @Inject constructor (var ymkrepo:YemeklerDaoRepository) : ViewModel() {

    fun kayit(yemekAdi:String,yemekResim:String,yemekFiyat:Int,yemekAdet:Int,kullaniciAdi:String) {
        ymkrepo.yemekKayit(yemekAdi,yemekResim,yemekFiyat,yemekAdet,kullaniciAdi)
    }
}