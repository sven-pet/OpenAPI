package openapi.scomas.se.openapi.adapters

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import openapi.scomas.se.openapi.R
import openapi.scomas.se.openapi.activities.SwedishRadioDetailedActivity
import openapi.scomas.se.openapi.networks.swedishradio.SwedishRadioModel

/**
 * Project : OpenAPI
 * Package Name : openapi.scomas.se.openapi
 * Created by svenpettersson on 2019-02-24.
 */
class SwedishRadioChannelAdapter(var channels:MutableList<SwedishRadioModel.Program>, var context: Activity) : RecyclerView.Adapter<SwedishRadioChannelAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_cardview_picture, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  channels.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val channel = channels.get(position)
        val titleText = channel.name
        holder.channelText.setText(titleText)

        val imageUrl = channel.programimage
        Glide.with(context).load(imageUrl).into(holder.channelPicture)

       holder.channelRoot.setOnClickListener {
            click ->
            val intent = Intent(context, SwedishRadioDetailedActivity::class.java)
            intent.putExtra("radioURL", channel.programimage)
            intent.putExtra("radioName", channel.name)
            intent.putExtra("radioDescription", channel.description)

            // start your next activity
            context.startActivity(intent)
            context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var channelPicture : ImageView
        var channelText: TextView
        var channelRoot: LinearLayout

        init {
            channelPicture = view.findViewById<ImageView>(R.id.card_view_image) as ImageView
            channelText = view.findViewById<View>(R.id.card_view_image_title) as TextView
            channelRoot = view.findViewById<View>(R.id.card_view) as LinearLayout

        }
    }
}