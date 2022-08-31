package com.example.proje.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.proje.R
import com.example.proje.databinding.FragmentYemekDetayBinding
import com.example.proje.ui.adapter.SepetAdapter
//import com.example.proje.ui.fragment.YemekDetayFragmentDirections.Companion.detaydanSepeteGecis
import com.example.proje.ui.viewmodel.SepetGoruntulemeViewModel
import com.example.proje.ui.viewmodel.YemekDetayViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YemekDetayFragment : Fragment() {
    private lateinit var tasarim:FragmentYemekDetayBinding
    private lateinit var viewModel:YemekDetayViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_yemek_detay ,container, false)
        tasarim.yemekDetayFragment = this
        val bundle:YemekDetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek

        tasarim.yemekDetayToolbarBaslik = "Yemek Detay"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarDetay)

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemekResimAdi}"
        Picasso.get().load(url).into(tasarim.imageViewDetayResim)

        tasarim.textViewDetayAd.text = gelenYemek.yemekAdi
        tasarim.textViewDetayResim.text = gelenYemek.yemekResimAdi
        tasarim.textViewDetayFiyat.text = "${gelenYemek.yemekFiyat} ₺"

        tasarim.buttonArttR.setOnClickListener {
            var a = tasarim.textViewDetaySayi.text.toString().toInt()+1
            tasarim.textViewDetaySayi.text = a.toString()
        }

        tasarim.buttonAzalt.setOnClickListener {
            var a = tasarim.textViewDetaySayi.text.toString().toInt()-1
            if(a>=0) {
                tasarim.textViewDetaySayi.text = a.toString()
            } else {
                tasarim.textViewDetaySayi.text = "0"
            }
        }

            tasarim.buttonSepeteEkle.setOnClickListener {
                if(tasarim.textViewDetaySayi.text.toString().toInt()>0){
                    sepeteEkle(gelenYemek.yemekAdi,gelenYemek.yemekResimAdi,gelenYemek.yemekFiyat,tasarim.textViewDetaySayi.text.toString().toInt(),"Koray")
                    tasarim.textViewDetaySayi.text = "0"

                }else {
                    Snackbar.make(it,"Yemek adedi en az 1 olabilir !",Snackbar.LENGTH_LONG).show()
                }
            }



        return tasarim.root
    }



    fun sepeteEkle(yemekAd:String, yemekResim:String,yemekFiyat:Int,yemekAdet:Int,kullanici_adi:String) {
        viewModel.kayit(yemekAd,yemekResim,yemekFiyat,yemekAdet,kullanici_adi)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:YemekDetayViewModel by viewModels()
        viewModel= tempViewModel
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_sepetim -> {
                Navigation.findNavController(requireView()).navigate(R.id.sepetGoruntulemeFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}