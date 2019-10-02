package com.example.myapplication

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.*
import java.time.format.FormatStyle


class Adapter(): RecyclerView.Adapter<Adapter.ViewHolder>(){

    var listener: ListClickListener? = null
    var values : List<Photo> = emptyList()
        fun setData(newValue: List<Photo>){
            this.values = newValue
            notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title_item?.text = values[position].title
        holder.date_item?.text = convertDateTime(values[position].published)
        holder.tags_item?.text = values[position].tags
        setImg(holder.img_itm, values[position].media!!.m)
        val link_photo = values[position].media!!.m
        holder.img_itm.setOnClickListener {
            listener?.onItemClicked(link_photo)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertDateTime(non_convert_dateTime:String?): String? {
        return ZonedDateTime.parse(non_convert_dateTime).toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"))

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.list_iten_view,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return values.size
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var title_item: TextView? = itemView.findViewById(R.id.title_item)
        var date_item: TextView? = itemView.findViewById(R.id.date_item)
        var img_itm: ImageView = itemView.findViewById(R.id.imageView)
        var tags_item: TextView? = itemView.findViewById(R.id.tags_item)

    }
    fun setImg(img: ImageView, link:String?) {
        var path = link

        var context = Adapter.ViewHolder(img).img_itm.context
        Picasso.with(context).load(path).into(img);
    }


}