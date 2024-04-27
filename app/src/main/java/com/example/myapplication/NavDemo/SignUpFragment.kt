package com.example.myapplication.NavDemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSignUpBinding
import com.example.myapplication.databinding.FragmentTermsBinding


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up, container, false)
        binding.btnTerms.setOnClickListener {
            it.findNavController().navigate(R.id.action_signUpFragment_to_termsFragment)
        }

        binding.btnSignUp.setOnClickListener {
            it.findNavController().navigate(R.id.action_signUpFragment_to_nameFragment2)
        }
        return binding.root
    }


}