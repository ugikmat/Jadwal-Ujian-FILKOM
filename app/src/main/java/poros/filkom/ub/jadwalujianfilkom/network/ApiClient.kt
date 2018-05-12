package poros.filkom.ub.jadwalujianfilkom.network

import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private var mRetrofit: Retrofit? = null

        fun create(api: String): ApiHelper {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            if (mRetrofit == null) {
                if (api.equals("jadwal")) {
                    mRetrofit = Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/asamsulfat/nyamuk-book/master/")
                            .client(client).addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(rxAdapter).build()
                } else {
                    mRetrofit = Retrofit.Builder().baseUrl("https://siam.ub.ac.id/")
                            .client(client).addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(rxAdapter).build()
                }
            }
            return mRetrofit!!.create(ApiHelper::class.java)
        }
    }
}