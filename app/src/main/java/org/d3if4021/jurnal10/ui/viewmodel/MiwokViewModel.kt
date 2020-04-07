package org.d3if4021.jurnal10.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.d3if4021.jurnal10.data.Miwok
import org.d3if4021.jurnal10.network.MiwokApi

class MiwokViewModel : ViewModel(){

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private val _data = MutableLiveData<List<Miwok>>()
    val data: LiveData<List<Miwok>>
        get() = _data

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    init {
        _response.value = ""
        _loading.value = true
        init()
    }

    private fun init(){
        uiScope.launch {
            try {
                val result= MiwokApi.retrofitService.showList()

                if (result.isNotEmpty()){
                    _data.value = result
                }else{
                    _response.value = "Data Tidak Ada"
                }
            }catch (t: Throwable){
                _response.value = "Koneksi Internet tidak ada"
            }finally {
                _loading.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


}