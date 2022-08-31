package com.example.proje.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.proje.R
import com.example.proje.data.entity.Yemekler
import com.example.proje.databinding.FragmentYemekleriListelemeBinding
import com.example.proje.ui.adapter.YemeklerAdapter
import com.example.proje.ui.viewmodel.SepetGoruntulemeViewModel
import com.example.proje.ui.viewmodel.YemekleriListelemeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YemekleriListelemeFragment : Fragment() {
    private lateinit var tasarim:FragmentYemekleriListelemeBinding
    private lateinit var viewModel:YemekleriListelemeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_yemekleri_listeleme,container, false)
        tasarim.listelemeFragment=this

        tasarim.yemekListelemeToolbarBaslik = "Yemekler"

        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarYemekListe)

        viewModel.yemek_liste.observe(viewLifecycleOwner) {
            val adapter = YemeklerAdapter(requireContext(), it,viewModel )
            tasarim.yemeklerAdapter = adapter
        }


        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel : YemekleriListelemeViewModel by viewModels()
        viewModel = tempViewModel
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