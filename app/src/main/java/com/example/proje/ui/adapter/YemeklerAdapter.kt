package com.example.proje.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.proje.R
import com.example.proje.data.entity.Yemekler
import com.example.proje.databinding.CardTasarimBinding
import com.example.proje.ui.fragment.YemekleriListelemeFragmentDirections
import com.example.proje.ui.viewmodel.YemekleriListelemeViewModel
import com.squareup.picasso.Picasso

class YemeklerAdapter(var mContext:Context,
                      var yemekListesi:List<Yemekler>,
                      var viewModel : YemekleriListelemeViewModel)
    : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>(){
    inner class CardTasarimTutucu(tasarim:CardTasarimBinding) :RecyclerView.ViewHolder(tasarim.root) {
        var tasarim:CardTasarimBinding
        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim : CardTasarimBinding = DataBindingUtil
            .inflate(layoutInflater,
                R.layout.card_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemekListesi.get(position)
        val t = holder.tasarim
        t.yemekNesnesi = yemek

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemekResimAdi}"
        Picasso.get().load(url).into(t.imageViewYemekResim)

      //  t.imageViewYemekResim.setImageResource(
         //   mContext.resources.getIdentifier(yemek.yemekResimAdi,"drawable",mContext.packageName))
        t.textViewYemekAd.text = yemek.yemekAdi
        t.textViewYemekFiyat.text = "${yemek.yemekFiyat} ₺"

        t.satirCard.setOnClickListener {
            val gecis= YemekleriListelemeFragmentDirections.detayGecis(yemek=yemek)
            Navigation.findNavController(it).navigate(gecis)
        }


    }

    override fun getItemCount(): Int {
        return yemekListesi.size
    }
}