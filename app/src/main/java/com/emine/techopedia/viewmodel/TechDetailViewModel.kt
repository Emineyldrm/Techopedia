package com.emine.techopedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.emine.techopedia.model.Tech
import com.emine.techopedia.roomdb.TechDatabase
import kotlinx.coroutines.launch


class TechDetailViewModel(application: Application) : AndroidViewModel(application){
    val techLiveData = MutableLiveData<Tech>()
    fun roomVerisiniAl(uuid : Int){
        viewModelScope.launch {
            val dao = TechDatabase(getApplication()).techDAO()
            val tech = dao.getTech(uuid)
            techLiveData.value = tech
        }
    }
}