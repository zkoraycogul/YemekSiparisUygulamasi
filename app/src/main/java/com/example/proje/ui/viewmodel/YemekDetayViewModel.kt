package com.example.proje.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proje.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class YemekDetayViewModel @Inject constructor (var ymkrepo:YemeklerDaoRepository) : ViewModel() {

    fun kayit(yemekId:Int,yemekAdi:String,yemekResim:String,yemekFiyat:String,yemekAdet:String) {
        ymkrepo.yemekKayit(yemekId,yemekAdi,yemekResim,yemekFiyat,yemekAdet)
    }
}