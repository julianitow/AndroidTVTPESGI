package fr.supinternet.androidtv.ui

import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import fr.supinternet.androidtv.data.network.model.Movie

class PresenterModel: Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder? {
        return CardViewHolder(ImageCardView(parent?.context));
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val movie = item as Movie
        val holder = viewHolder as CardViewHolder
        val img = holder.card
        img.titleText = movie.name
        if(movie.rating !== null){
            img.contentText = movie.rating.toString()
        } else {
            img.contentText = "N/A"
        }
        if(movie.posterURL !== null){
            holder.loadImage(movie.posterURL)
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        return
    }
}