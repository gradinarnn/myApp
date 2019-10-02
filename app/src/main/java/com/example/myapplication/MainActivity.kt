package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_iten_view.*



import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AlertDialog
import java.io.Serializable


class MainActivity : AppCompatActivity() {
    private lateinit var jsonAPI: MyAPI
    private lateinit var adapter: com.example.myapplication.Adapter
    var tag:String = ""





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitClient.instance
        jsonAPI = retrofit.create(MyAPI::class.java)
        adapter = Adapter()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val tags = "cats"
        makeSearch(tags)
        adapter.listener = object : ListClickListener {
            override fun onItemClicked(link_photo: String?) {
                val intent = Intent(recyclerView.context,Image::class.java)
                intent.putExtra(Image.link_ph,link_photo)
                startActivity(intent)
            }
        }
        var sort_btn:Button = findViewById(R.id.sort_btn)
        sort_btn.setOnClickListener{
            // Initialize a new instance of
            val change_sort = AlertDialog.Builder(this)
            change_sort.setTitle("");
            change_sort.setMessage("сортировать по")

            // Set a positive button and its click listener on alert dialog
            change_sort.setPositiveButton("дате"){dialog, which ->
                // Do something when user press the positive button
                sorting(tag,"date")

            }


            // Display a negative button on alert dialog
            change_sort.setNegativeButton("названию"){dialog,which ->
                sorting(tag," ")
            }


            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = change_sort.create()

            // Display the alert dialog on app interface
            dialog.show()


        }

    }
     fun makeTags(view: View){
         val tagsView: EditText = this.findViewById(R.id.tags_search)
        tag = tagsView.text.toString()
        makeSearch(tag)
    }
     private fun makeSearch(tags: String){
        val call = jsonAPI.listRepos(tags)
        call.enqueue(object : Callback<Post>{
            override fun onFailure(call: Call<Post>, t: Throwable){
                var errorTXT: TextView = findViewById(R.id.error)
                errorTXT.text = "Errrors"
            }
            override fun onResponse(call: Call<Post>, response: Response<Post>){
                if (response.isSuccessful){
                    val list = response.body()!!
                    adapter.setData(list.items)
                } else{
                    var errorTXT: TextView = findViewById(R.id.error)
                    errorTXT.text = "Errrors"
                }
            }
        })
    }
    private fun sorting(tags: String, parametr_sort:String){
        val call = jsonAPI.listRepos(tags)
        call.enqueue(object : Callback<Post>{
            override fun onFailure(call: Call<Post>, t: Throwable){
                var errorTXT: TextView = findViewById(R.id.error)
                errorTXT.text = "Errrors"
            }
            override fun onResponse(call: Call<Post>, response: Response<Post>){
                if (response.isSuccessful){
                    val list = response.body()!!
                    if (parametr_sort=="date"){
                    adapter.setData(list.items.sortedBy{it.published})
                    }else{
                        adapter.setData(list.items.sortedBy{it.title})
                    }
                }
            }
        })
    }
    /*fun SetFullIMG(v: View){

        adapter.listener = object : ListClickListener {
            override fun onItemClicked(photo: Photo) {
                val intent = Intent(,Image::class.java)            }
        }
        val intent = Intent(this,Image::class.java)

        //intent.putExtra()
        //startActivity(intent)


    }*/

}

