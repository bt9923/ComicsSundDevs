package app.example.comicbook.Interface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import androidx.recyclerview.widget.GridLayoutManager
import app.example.comicbook.Data.api.ComicsNetwork
import app.example.comicbook.Data.model.ComicsBook
import app.example.comicbook.Interface.Adapter.LastComicsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import android.view.View.VISIBLE
import app.example.comicbook.R

class MainActivity : AppCompatActivity() {

    //<editor-fold desc="Vars">

    private val api by lazy { ComicsNetwork.api }
    lateinit var viewDialog : ViewDialog
    lateinit var adapter : LastComicsAdapter

    //</editor-fold>

    //<editor-fold desc="Life Cycle">

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewDialog = ViewDialog(this)

        viewDialog.showDialog()
        connectionWithRetrofit()
    }

    //</editor-fold>

    //<editor-fold desc="Retrofit Connection">

    private fun connectionWithRetrofit() {
        api.getLastComics().enqueue(object : retrofit2.Callback<ComicsBook> {
            override fun onFailure(call: Call<ComicsBook>, t: Throwable) {
                linearNoInternetConnection.visibility = VISIBLE
                recyclerView.visibility = GONE
                viewDialog.hideDialog()
            }

            override fun onResponse(call: Call<ComicsBook>, response: Response<ComicsBook>) {
                if (response.isSuccessful){
                    response.body()?.let{
                        recyclerView.layoutManager = GridLayoutManager(applicationContext, 3)
                        recyclerView.adapter = LastComicsAdapter(it, applicationContext)
                        linearNoInternetConnection.visibility = GONE
                        recyclerView.visibility = VISIBLE
                        viewDialog.hideDialog()
                    }
                }
            }
        })
    }

    //</editor-fold>
}
