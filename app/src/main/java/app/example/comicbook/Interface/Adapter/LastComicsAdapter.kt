package app.example.comicbook.Interface.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import app.example.comicbook.Data.model.ComicsBook
import app.example.comicbook.Interface.DetailActivity
import app.example.comicbook.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.last_comics.view.*
import java.util.ArrayList

class LastComicsAdapter(val items: ComicsBook, val context: Context)
    : RecyclerView.Adapter<LastComicsAdapter.MyViewHolder>() {

    val itemsFull : List<ComicsBook> = listOf(items)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.last_comics, parent, false))
    }

    override fun getItemCount(): Int {
        return items.limit
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var name : String?
        if (items.results[position].name == null)
            name = ""
        else
            name = items.results[position].name

        holder.tvTitleLastComics.text = "$name #${items.results[position].issue_number}"
        holder.tvDateLastComics.text = items.results[position].date_added

        Glide.with(context)
            .load(items.results[position].image.original_url)
            .into(holder.imgLastComics)
//        .centerCrop()0

        holder.cardLastComics.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("detail_url", items.results[position].api_detail_url)
            context.startActivity(intent)
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardLastComics: CardView = view.cardLastComics
        val imgLastComics = view.imgLastComics
        val tvTitleLastComics = view.titleLastComics
        val tvDateLastComics = view.dateLastComics
    }
}

