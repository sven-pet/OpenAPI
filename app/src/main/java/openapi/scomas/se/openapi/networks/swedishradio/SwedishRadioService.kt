package openapi.scomas.se.openapi.networks.swedishradio

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Project : OpenAPI
 * Package Name : openapi.scomas.se.openapi.networks.swedishradio
 * Created by svenpettersson on 2019-02-23.
 */
interface SwedishRadioService {

    @GET("programs")
    fun search(@Query("format") format: String,
               @Query("size") size: String,
               @Query("page") page: Int)
            : Observable<SwedishRadioModel.SwedishRadioResult>



    companion object {
        fun create(url : String): SwedishRadioService {

            val logging = HttpLoggingInterceptor()
            // set your desired log level

            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            // add your other interceptors …

            // add logging as last interceptor
            httpClient.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(url)
                    .client(httpClient.build())
                    .build()

            return retrofit.create(SwedishRadioService::class.java)
        }
    }
}