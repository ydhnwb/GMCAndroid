package com.annaalfiani.gmcapps.ui.detail_movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.annaalfiani.gmcapps.R
import com.annaalfiani.gmcapps.models.MovieSchedule
import kotlinx.android.synthetic.main.list_item_movie_schedule.view.*

class MovieScheduleAdapter (private val movieSchedules: MutableList<MovieSchedule>, private val detailMovieInterface: DetailMovieInterface): RecyclerView.Adapter<MovieScheduleAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(movieSchedule: MovieSchedule){
            with(itemView){
                schedule_date.text = movieSchedule.date
                schedule_studio.text = movieSchedule.studio?.nama
                schedule_hour.text = movieSchedule.hour
                setOnClickListener {
                    detailMovieInterface.itemClick(movieSchedule)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie_schedule, parent, false))
    }

    fun updateList(schedules: List<MovieSchedule>){
        movieSchedules.clear()
        movieSchedules.addAll(schedules)
        notifyDataSetChanged()
    }

    override fun getItemCount() = movieSchedules.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movieSchedules[position])
}