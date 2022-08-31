package com.example.proje.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.proje.R
import com.example.proje.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment() {
    private lateinit var tasarim:FragmentRegisterBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        tasarim = FragmentRegisterBinding.inflate(inflater, container, false)


        tasarim.buttonRegKayit.setOnClickListener {

        if(tasarim.editTextRegName.text.toString().isNotEmpty() &&
            tasarim.editTextRegPass.text.toString().isNotEmpty()){

            tasarim.buttonRegKayit.visibility = View.INVISIBLE
            tasarim.editTextRegName.visibility = View.INVISIBLE
            tasarim.editTextRegPass.visibility = View.INVISIBLE
            tasarim.textViewKullaniciAdi.visibility = View.INVISIBLE
            tasarim.textViewSifre.visibility = View.INVISIBLE

            Snackbar.make(it, "Kayıt Başarılı", Snackbar.LENGTH_LONG).show()
            tasarim.animation3.setAnimation("register.json")
            tasarim.animation3.repeatCount = 0
            tasarim.animation3.playAnimation()

            val geriSayim = object : CountDownTimer(5000,1500){
                override fun onTick(p0: Long) {}

                override fun onFinish() {
                    Navigation.findNavController(it).navigate(R.id.regToAnm)
                }

            }
            geriSayim.start()
        }else {
            Snackbar.make(it, "Hatalı Bilgi Girdiniz", Snackbar.LENGTH_LONG).show()
        }
    }
        return tasarim.root
}
}