package openapi.scomas.se.openapi.networks.swedishradio

import android.os.AsyncTask
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import openapi.scomas.se.openapi.viewmodels.MasterViewModel

/**
 * Project : OpenAPI
 * Package Name : openapi.scomas.se.openapi.networks.swedishradio
 * Created by svenpettersson on 2019-02-23.
 */

class SwedishRadioAsyncTask(var viewModel: MasterViewModel, var pageNum: Int) : AsyncTask<Void, Void, String>() {
    var disposable: Disposable? = null
    val srAPI = SwedishRadioService.create("http://api.sr.se/api/v2/")


    override fun doInBackground(vararg params: Void?): String {
        disposable =
                srAPI.search(
                        format = "json",
                        size = "20",
                        page = pageNum)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    viewModel.addRadioPrograms(result)
                                },
                                { error ->
                                    viewModel.handleNetworkError()
                                }
                        )

        return "ok"
    }
}