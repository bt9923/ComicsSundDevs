package app.example.comicbook.Interface.Adapter.Detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.example.comicbook.Data.model.ComicDetail
import app.example.comicbook.R
import kotlinx.android.synthetic.main.layout_detail.view.*

class TeamsAdapter(val items: ComicDetail, val context: Context)
    : RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.layout_detail, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.results.team_credits.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.tvTeam.text = items.results.team_credits[position].name
    }

    class TeamViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvTeam = view.tvDetail
    }
}
