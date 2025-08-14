package com.emine.techopedia.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.emine.techopedia.databinding.FragmentTechDetailBinding
import com.emine.techopedia.util.gorselIndir
import com.emine.techopedia.util.placeholderYap
import com.emine.techopedia.viewmodel.TechDetailViewModel


class TechDetailFragment : Fragment() {

    private var _binding: FragmentTechDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TechDetailViewModel
    var techId=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTechDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this)[TechDetailViewModel::class.java]
        arguments?.let {
            techId= TechDetailFragmentArgs.fromBundle(it).techId
        }
        viewModel.roomVerisiniAl(techId)

        observeLiveData ()
    }
    private fun observeLiveData (){
        viewModel.techLiveData.observe(viewLifecycleOwner){
            binding.techIsim.text=it.techIsim
            binding.techTur.text=it.techTur
            binding.techPopulerlik.text=it.techPopulerlik
            binding.techAciklama.text=it.techAciklama
            binding.techCikisYili.text=it.techCikisYili
            binding.techImage.gorselIndir(it.techGorsel, placeholderYap(requireContext()))
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}