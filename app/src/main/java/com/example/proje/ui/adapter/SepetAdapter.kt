package com.example.proje.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.proje.R
import com.example.proje.data.entity.SepetYemekler
import com.example.proje.databinding.CardTasarimSepetBinding
import com.example.proje.ui.fragment.YemekDetayFragment
import com.example.proje.ui.viewmodel.SepetGoruntulemeViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


class SepetAdapter(var mContext:Context,
                   var sepetListesi:List<SepetYemekler>,
                   var viewModel: SepetGoruntulemeViewModel
                   )
    : RecyclerView.Adapter<SepetAdapter.CardSepetTasarimTutucu>(){
    inner class CardSepetTasarimTutucu(tasarim: CardTasarimSepetBinding) :RecyclerView.ViewHolder(tasarim.root) {
        var tasarim: CardTasarimSepetBinding

        init {
            this.tasarim = tasarim

        }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardSepetTasarimTutucu {

        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim : CardTasarimSepetBinding = DataBindingUtil
            .inflate(layoutInflater,
            R.layout.card_tasarim_sepet,parent,false)
        return CardSepetTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardSepetTasarimTutucu, position: Int) {
        val sepet = sepetListesi.get(position)
        val t = holder.tasarim
        t.sepetNesnesi=sepet

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepet.sepetYemekResimAdi}"
        Picasso.get().load(url).into(t.imageViewSepetResim)
        t.textViewSepetAd.text = sepet.sepetYemekAdi
        t.textViewSepetFiyat.text = "${(sepet.sepetFiyat)*(sepet.sepetSiparisAdet)} ₺"
        t.textViewAdet.text = "${sepet.sepetSiparisAdet}"
        t.imageView3.setOnClickListener{
            Snackbar.make(it,"${sepet.sepetYemekAdi} silinsin mi ?",Snackbar.LENGTH_LONG)
                .setAction("EVET") {
                   viewModel.sil(sepet.sepetYemekId,sepet.kullanici_adi)
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return sepetListesi.size
    }

}