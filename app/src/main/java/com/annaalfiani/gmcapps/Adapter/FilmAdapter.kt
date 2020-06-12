package com.annaalfiani.gmcapps.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.annaalfiani.gmcapps.DetailActivity
import com.annaalfiani.gmcapps.R
import com.annaalfiani.gmcapps.models.Film
import kotlinx.android.synthetic.main.list_item_movie.view.*

class FilmAdapter (private var films : MutableList<Film>, private var context: Context) : RecyclerView.Adapter<FilmAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false))
    }

    override fun getItemCount() = films.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(films[position], context)

    fun updateList(it: List<Film>){
        films.clear()
        films.addAll(it)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(f : Film, context: Context){
            with(itemView){
                movie_poster.load("https://gmcweb.herokuapp.com/uploads/admin/"+f.foto)
                movie_title.text = f.judul
                movie_genre.text = f.genre?.let { it } ?: kotlin.run { "Belum ada genre" }
                setOnClickListener {
                    context.startActivity(Intent(context, DetailActivity::class.java).apply {
                        putExtra("FILM", f)
                    })
                    Toast.makeText(context, f.judul, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}