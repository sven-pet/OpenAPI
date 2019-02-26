package openapi.scomas.se.openapi.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import openapi.scomas.se.openapi.networks.swedishradio.SwedishRadioAsyncTask
import openapi.scomas.se.openapi.networks.swedishradio.SwedishRadioModel

/**
 * Project : OpenAPI
 * Package Name : openapi.scomas.se.openapi.viewmodels
 * Created by svenpettersson on 2019-02-24.
 */
class MasterViewModel : ViewModel() {

    //var programObserver: MutableLiveData<SwedishRadioModel.SwedishRadioResult> = MutableLiveData<SwedishRadioModel.SwedishRadioResult>()
    var programObserver: MutableLiveData<MutableList<SwedishRadioModel.Program>> = MutableLiveData<MutableList<SwedishRadioModel.Program>>()

    fun callAPI(page : Int){
        val task = SwedishRadioAsyncTask(this,page)
        task.execute()
    }

    fun addRadioPrograms(result: SwedishRadioModel.SwedishRadioResult?) {
        val previousList = programObserver.value
        if (result != null) {
            if (previousList != null) {
                previousList.addAll(result.programs)
                programObserver.postValue(previousList)
            }
            else {
                programObserver.postValue(result.programs.toMutableList())
            }
        }
    }

    fun handleNetworkError() {


    }
}