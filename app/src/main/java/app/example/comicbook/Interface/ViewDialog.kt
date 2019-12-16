package app.example.comicbook.Interface

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.ImageView
import app.example.comicbook.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget

class ViewDialog {
    internal lateinit var activity: Context
    internal lateinit var dialog: Dialog
    //..we need the context else we can not create the dialog so get context in constructor
    constructor(context: Context) {
        this.activity = context
    }

    fun showDialog() {
        dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //...set cancelable false so that it's never get hidden
        dialog.setCancelable(false)
        //...that's the layout i told you will inflate later
        dialog.setContentView(R.layout.custom_dialog_loading)

        //...initialize the imageView form infalted layout
        val gifImageView = dialog.findViewById<ImageView>(R.id.custom_loading_imageView)

        /*
        it was never easy to load gif into an ImageView before Glide or Others library
        and for doing this we need DrawableImageViewTarget to that ImageView
        */

        //...now load that gif which we put inside the drawble folder here with the help of Glide

        Glide.with(activity)
            .load(R.drawable.loading)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
            .placeholder(R.drawable.loading)
            .centerCrop()
            .into(DrawableImageViewTarget(gifImageView))

        //...finaly show it
        dialog.show()
    }

    //..also create a method which will hide the dialog when some work is done
    fun hideDialog() {
        dialog.dismiss()
    }
}

