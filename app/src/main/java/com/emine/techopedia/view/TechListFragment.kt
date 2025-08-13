package com.emine.techopedia.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.emine.techopedia.adapter.TechRecyclerAdapter
import com.emine.techopedia.databinding.FragmentTechListBinding
import com.emine.techopedia.model.Tech
import com.emine.techopedia.service.TechAPI
import com.emine.techopedia.viewmodel.TechListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class TechListFragment : Fragment() {
    private var _binding: FragmentTechListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TechListViewModel
    private val techRecyclerAdapter= TechRecyclerAdapter(arrayListOf())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProvider(this)[TechListViewModel::class.java]

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
        binding.techListRecyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.techListRecyclerView.adapter=techRecyclerAdapter

        viewModel= ViewModelProvider(this)[TechListViewModel::class.java]
        viewModel.refreshData()
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.techHataMesaji.visibility= View.GONE
            binding.techListRecyclerView.visibility= View.GONE
            binding.techYukleniyor.visibility= View.VISIBLE
            viewModel.refreshDataFromInternet()
            binding.swipeRefreshLayout.isRefreshing=false
        }
        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.teknolojiler.observe(viewLifecycleOwner){
            //adapter
            techRecyclerAdapter.techListGuncelle(it)
            binding.techListRecyclerView.visibility= View.VISIBLE
        }
        viewModel.techHataMesaji.observe(viewLifecycleOwner){
            if (it){
                binding.techHataMesaji.visibility= View.VISIBLE
                binding.techListRecyclerView.visibility= View.GONE
            }else{
                binding.techHataMesaji.visibility= View.GONE
            }
        }
        viewModel.techYukleniyor.observe(viewLifecycleOwner){
            if (it){
                binding.techHataMesaji.visibility= View.GONE
                binding.techListRecyclerView.visibility= View.GONE
                binding.techYukleniyor.visibility= View.VISIBLE
            }else{
                binding.techYukleniyor.visibility= View.GONE
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}