package com.example.workoutlog

import android.content.Context
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutlog.data.Workout
import com.example.workoutlog.databinding.WorkoutListItemBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class WorkoutListAdapter(private val onWorkoutClicked: (Workout) -> Unit) :
    ListAdapter<Workout, WorkoutListAdapter.WorkoutViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val viewHolder = WorkoutViewHolder(
            WorkoutListItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onWorkoutClicked(getItem(position))
        }
        return viewHolder
    }


    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onWorkoutClicked(current)
        }
        holder.bind(current)
    }

    class WorkoutViewHolder(private var binding: WorkoutListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(workout: Workout) {
            binding.apply {
                itemName.text = workout.workoutName
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Workout>() {
            override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
                return oldItem.workoutName == newItem.workoutName
            }
        }
    }

}