package app.example.comicbook.Interface

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import app.example.comicbook.BuildConfig
import app.example.comicbook.Data.api.ComicsNetwork
import app.example.comicbook.Data.model.ComicDetail
import app.example.comicbook.Interface.Adapter.Detail.DetailAdapter
import app.example.comicbook.Interface.Adapter.Detail.LocationAdapter
import app.example.comicbook.Interface.Adapter.Detail.TeamsAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.recyclerview.widget.DividerItemDecoration
import app.example.comicbook.R

class DetailActivity : AppCompatActivity() {

    //<editor-fold desc="Life Cycle">
    lateinit var viewDialog : ViewDialog
    //</editor-fold>

    //<editor-fold desc="Life Cycle">

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        viewDialog = ViewDialog(this)
        val getExtras : Bundle? = intent.extras

        viewDialog.showDialog()
        connectionWithRetrofit(getExtras)
    }

    //</editor-fold>

    //<editor-fold desc="Retrofit Connection">

    private fun connectionWithRetrofit(extras: Bundle?) {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url()

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .addQueryParameter("format", "json")
                    .build()

                val requestBuilder = original.newBuilder()
                    .url(url)

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(extras?.getString("detail_url")!!)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ComicsNetwork::class.java)

        retrofit.getDetailComic().enqueue(object : retrofit2.Callback<ComicDetail> {
            override fun onFailure(call: Call<ComicDetail>, t: Throwable) {
                viewDialog.hideDialog()
                linearNoInternetConnection.visibility = VISIBLE
                linearDetail.visibility = GONE
            }

            override fun onResponse(call: Call<ComicDetail>, response: Response<ComicDetail>) {
                if (response.isSuccessful){
                    response.body().let {
                        linearNoInternetConnection.visibility = GONE
                        linearDetail.visibility = VISIBLE

                        toolbar_layout.title = it!!.results.name

                        Glide.with(applicationContext)
                            .load(it.results.image.original_url)
                            .centerInside()
                            .into(imgDetailComic)

                        character_credits.layoutManager = GridLayoutManager(applicationContext, 2)
                        character_credits.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))
                        character_credits.adapter = DetailAdapter(it, applicationContext)
                        character_credits.setHasFixedSize(true)

                        team_credits.layoutManager = GridLayoutManager(applicationContext, 2)
                        team_credits.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))
                        team_credits.adapter = TeamsAdapter(it, applicationContext)

                        location_credits.layoutManager = GridLayoutManager(applicationContext, 2)
                        location_credits.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))
                        location_credits.adapter = LocationAdapter(it, applicationContext)
                        viewDialog.hideDialog()

                    }
                }
            }
        })
    }

    //</editor-fold>
}
