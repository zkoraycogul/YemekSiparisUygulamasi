package com.example.proje.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.example.proje.R
import com.example.proje.databinding.FragmentAnimationBinding
import com.google.android.material.snackbar.Snackbar

class AnimationFragment : Fragment() {
    private lateinit var tasarim:FragmentAnimationBinding
    var name = "kk"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
       tasarim = FragmentAnimationBinding.inflate(inflater, container, false)
        tasarim.animation.setAnimation("anm.json")
        tasarim.animation.repeatCount = 5
        tasarim.animation.playAnimation()
        tasarim.editTextName.setOnClickListener {
            tasarim.animation.visibility = View.INVISIBLE
        }
        tasarim.button.setOnClickListener {
            if(tasarim.editTextName.text.toString().isNotEmpty() &&
                tasarim.editTextNumberPassword.text.toString().isNotEmpty()){

                    tasarim.button.visibility = View.INVISIBLE
                    tasarim.editTextName.visibility = View.INVISIBLE
                    tasarim.editTextNumberPassword.visibility = View.INVISIBLE
                    tasarim.animation2.visibility = View.VISIBLE

                Snackbar.make(it, "Giriş Başarılı",Snackbar.LENGTH_LONG).show()
                tasarim.animation2.setAnimation("welcome.json")
                tasarim.animation2.repeatCount = 0
                tasarim.animation2.playAnimation()

                val geriSayim = object : CountDownTimer(5000,1500){
                    override fun onTick(p0: Long) {}

                    override fun onFinish() {
                        Navigation.findNavController(it).navigate(R.id.anmToListe)
                    }

                }
                geriSayim.start()
            }else {
                Snackbar.make(it, "İsim Gİrmediniz",Snackbar.LENGTH_LONG).show()
            }
        }

        tasarim.buttonKayitOl.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.anmToReg)
        }
        return tasarim.root
    }
}