package com.example.lugares.ui.lugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.lugares.databinding.FragmentAddLugarBinding
import com.example.lugares.viewmodel.LugarViewModel


class addLugarFragment : Fragment() {
  private var _binding: FragmentAddLugarBinding?= null
    private val binding get()= _binding!!
    private lateinit var lugarViewModel:LugarViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lugarViewModel = ViewModelProvider(this)[LugarViewModel::class.java]
        _binding = FragmentAddLugarBinding.inflate(inflater, container,false)
        return binding.root
    }
}