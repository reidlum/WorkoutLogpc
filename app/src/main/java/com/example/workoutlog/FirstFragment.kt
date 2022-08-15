package com.example.workoutlog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutlog.data.Datasource
import com.example.workoutlog.databinding.FragmentFirstBinding




/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var recyclerView: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

        //@TODO FIGURE OUT RECYCLER VIEW FOR WORKOUTS

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = WorkoutListAdapter {
            this.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.recyclerView.adapter = adapter
        viewModel.allWorkouts.observe(this.viewLifecycleOwner) { workouts ->
            workouts.let {
                adapter.submitList(it)
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_addWorkoutFragment)
        }
    }
    private val viewModel: WorkoutViewModel by activityViewModels {
        WorkoutViewModelFactory(
            (activity?.application as WorkoutApplication).database
                .workoutDao()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}