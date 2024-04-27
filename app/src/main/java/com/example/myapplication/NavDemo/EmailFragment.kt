package com.example.myapplication.NavDemo

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentEmailBinding
import com.example.myapplication.databinding.FragmentTermsBinding


class EmailFragment : Fragment() {


    private lateinit var binding: FragmentEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_email, container, false)

        binding.btnSubmit.setOnClickListener {

            val name = requireArguments().getString("user_name")
            if (!TextUtils.isEmpty(binding.edtEmail.text.toString())){
                val bundle = bundleOf("user_email" to binding.edtEmail.text.toString(),
                    "user_name" to name)

                it.findNavController().navigate(R.id.action_emailFragment_to_welcomeFragment,bundle)
            }else{
                Toast.makeText(context,"Enter your Email",Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }


}