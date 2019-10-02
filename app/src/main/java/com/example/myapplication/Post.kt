package com.example.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Date
import java.time.LocalDateTime
import java.util.*

class Post {

    @Expose
    @SerializedName("title")
    var  title : String? = null
    @Expose
    @SerializedName("link")
    var  link : String? = null
    @Expose
    @SerializedName("description")
    var  description : String? = null
    @Expose
    @SerializedName("modified")
    var  modified : String? = null
    @Expose
    @SerializedName("generator")
    var  generator : String? = null
    @Expose
    @SerializedName("items")
    var  items : List<Photo> = emptyList()

}

class Photo{
    @Expose
    @SerializedName("title")
    var  title: String? = null

    @Expose
    @SerializedName("link")
    var  link: String? = null

    @Expose
    @SerializedName("media")
    var  media: M_parametr? = null
    @Expose
    @SerializedName("date_taken")
    var  date_taken: String? = null
    @Expose
    @SerializedName("description")
    var  description: String? = null
    @Expose
    @SerializedName("published")
    var  published:  String? = null
    @Expose
    @SerializedName("author")
    var  author: String? = null
    @Expose
    @SerializedName("author_id")
    var  author_id: String? = null
    @Expose
    @SerializedName("tags")
    var  tags: String? = null

}
class M_parametr{
    @Expose
    @SerializedName("m")
    var  m: String? = null
}