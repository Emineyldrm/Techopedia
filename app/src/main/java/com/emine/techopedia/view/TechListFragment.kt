package com.emine.techopedia.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emine.techopedia.databinding.FragmentTechListBinding
import com.emine.techopedia.model.Tech
import com.emine.techopedia.service.TechAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class TechListFragment : Fragment() {
    private var _binding: FragmentTechListBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTechListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeRefreshLayout.setOnRefreshListener {

        }
        val retrofit= Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TechAPI::class.java)

       CoroutineScope(Dispatchers.IO).launch {
            val teknolojiler=retrofit.getTech()
            teknolojiler.forEach {
                println(it.techIsim)
            }
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}