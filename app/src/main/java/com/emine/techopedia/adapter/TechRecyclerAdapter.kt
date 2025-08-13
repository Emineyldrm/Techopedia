package com.emine.techopedia.adapter

import android.graphics.Path
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.emine.techopedia.databinding.TechRecyclerRowBinding
import com.emine.techopedia.model.Tech
import com.emine.techopedia.view.TechListFragmentDirections

class TechRecyclerAdapter(val techList: ArrayList<Tech>) : RecyclerView.Adapter<TechRecyclerAdapter.TechViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TechViewHolder {
        val binding= TechRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TechViewHolder(binding)
    }
    fun techListGuncelle(yeniTechList: List<Tech>){
        techList.clear()
        techList.addAll(yeniTechList)
        notifyDataSetChanged()
        //yeni bir liste verdiğimizde güncelleyecek recyclerView'i
    }

    override fun onBindViewHolder(
        holder: TechViewHolder,
        position: Int
    ) {
       holder.binding.isim.text=techList[position].techIsim
        holder.binding.tur.text=techList[position].techTur
        holder.itemView.setOnClickListener{
            val action= TechListFragmentDirections.actionTechListFragmentToTechDetailFragment(techList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
       return techList.size
    }

    class TechViewHolder(val binding: TechRecyclerRowBinding): RecyclerView.ViewHolder(binding.root){}

}