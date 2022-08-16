package com.example.workoutlog

import android.content.ClipData
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.workoutlog.data.Workout
import com.example.workoutlog.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private val navigationArgs: SecondFragmentArgs by navArgs()
    lateinit var workout: Workout

    companion object {
        var WORKOUT_NAME = "workoutName"
    }

    private var _binding: FragmentSecondBinding? = null



    private lateinit var workoutName: String

    private val viewModel: WorkoutViewModel by activityViewModels {
        WorkoutViewModelFactory(
            (activity?.application as WorkoutApplication).database.workoutDao()
        )
    }

    private fun deleteWorkout() {
        viewModel.deleteWorkout(workout)
        findNavController().navigateUp()
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            workoutName = it.getString(WORKOUT_NAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.workoutId

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_addExerciseFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}