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

    var yemeklerListesi = MutableLiveData<List<SepetYemekler>>()

    init {
        yemekleriGoruntule()
        yemeklerListesi = ymkrepo.yemekleriGetir()
    }

    fun sil(yemekId: Int, sepetYemekAdi: String) {
        ymkrepo.yemekSil(yemekId,sepetYemekAdi)
    }

    fun yemekleriGoruntule(){
        ymkrepo.tumYemekleriAl()
    }
}

