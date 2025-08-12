package com.emine.techopedia.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.emine.techopedia.model.Tech
import com.emine.techopedia.roomdb.TechDatabase
import com.emine.techopedia.service.TechAPIServis
import com.emine.techopedia.util.OzelSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TechListViewModel(application: Application): AndroidViewModel(application) {
    val teknolojiler= MutableLiveData<List<Tech>>()
    val techHataMesaji= MutableLiveData<Boolean>()
    val techYukleniyor= MutableLiveData<Boolean>()

    private val techAPIService= TechAPIServis()

    private val ozelSharedPreferences= OzelSharedPreferences(getApplication())

    private fun teknolojileriGoster(techList: List<Tech>){
        teknolojiler.value=techList
        techHataMesaji.value=false
        techYukleniyor.value=false
    }

    private fun verilerInternettenAl(){
        techYukleniyor.value=true
        viewModelScope.launch { Dispatchers.IO
        val techList=techAPIService.getData()
            withContext(Dispatchers.Main){
                techYukleniyor.value=false
                //teknolojiler.value=techList
                //rooma kaydedeceğiz
                roomaKaydet(techList)
                Toast.makeText(getApplication(),"Teknolojileri İnternetten Aldık", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun roomaKaydet(techList: List<Tech>){
        viewModelScope.launch {
            val dao= TechDatabase(getApplication()).techDAO()
            dao.deleteAllTech()
            val uuidListesi= dao.insertAll(*techList.toTypedArray())
            var i=0
            while (i < techList.size){
                techList[i].uuid=uuidListesi[i].toInt()
                i=i+1
            }
            teknolojileriGoster(techList)
        }
        ozelSharedPreferences.zamaniKaydet(System.nanoTime())
    }
}