package app.example.comicbook.Interface.Adapter.Detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.example.comicbook.Data.model.ComicDetail
import app.example.comicbook.R
import kotlinx.android.synthetic.main.layout_detail.view.*

class LocationAdapter(val items: ComicDetail, val context: Context)
    : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.layout_detail, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.results.location_credits.size
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.tvLocation.text = items.results.location_credits[position].name
    }

    class LocationViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvLocation = view.tvDetail
    }

}

