package com.example.proje.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.proje.R
import com.example.proje.data.entity.SepetYemekler
import com.example.proje.databinding.FragmentSepetGoruntulemeBinding
import com.example.proje.ui.adapter.SepetAdapter
import com.example.proje.ui.viewmodel.SepetGoruntulemeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetGoruntulemeFragment : Fragment() {
    private lateinit var tasarim:FragmentSepetGoruntulemeBinding
    private lateinit var viewModel: SepetGoruntulemeViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_sepet_goruntuleme, container, false)
        tasarim.sepetFragment=this
        tasarim.sepetToolbarBaslik = "Sepetim"

        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarSepet)

//        val bundle:SepetGoruntulemeFragmentArgs by navArgs()
//        val sepetYemek = bundle.sepet
//        tasarim.yemekNesnesi=sepetYemek

        viewModel.yemeklerListesi.observe(viewLifecycleOwner) {
            val adapter = SepetAdapter(requireContext(),it,viewModel,)
            tasarim.sepetAdapter= adapter
        }





        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : SepetGoruntulemeViewModel by viewModels()
        viewModel = tempViewModel
    }
}