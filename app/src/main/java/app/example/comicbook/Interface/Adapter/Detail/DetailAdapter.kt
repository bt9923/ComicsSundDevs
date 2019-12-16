package app.example.comicbook.Interface.Adapter.Detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.example.comicbook.Data.model.ComicDetail
import app.example.comicbook.R
import kotlinx.android.synthetic.main.layout_detail.view.*

class DetailAdapter(val items: ComicDetail, val context: Context)
    : RecyclerView.Adapter<DetailAdapter.CharacterViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.layout_detail, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.results.character_credits.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.tvCharacter.text = items.results.character_credits[position].name
    }

    class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvCharacter = view.tvDetail
    }
}

