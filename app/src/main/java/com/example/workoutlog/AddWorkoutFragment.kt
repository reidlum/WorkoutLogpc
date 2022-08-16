package com.example.workoutlog

import android.content.ClipData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.workoutlog.databinding.FragmentAddWorkoutBinding
import com.example.workoutlog.data.Workout
import com.example.workoutlog.databinding.FragmentFirstBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddWorkoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddWorkoutFragment : Fragment() {


    private val navigationArgs: AddWorkoutFragmentArgs by navArgs()
    lateinit var workout: Workout

    private val viewModel: WorkoutViewModel by activityViewModels {
        WorkoutViewModelFactory(
            (activity?.application as WorkoutApplication).database
                .workoutDao()
        )
    }

    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentAddWorkoutBinding? = null
    private val binding get() = _binding!!
    private fun bind(workout: Workout) {
        binding.apply {
            nameInput.setText(workout.workoutName, TextView.BufferType.SPANNABLE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_workout, container, false)
        _binding = FragmentAddWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.nameInput.text.toString()
        )
    }
    private fun addNewItem() {
        if (isEntryValid()) {
            viewModel.addNewWorkout(
                binding.nameInput.text.toString()
            )
            findNavController().navigate(R.id.action_addWorkoutFragment_to_FirstFragment2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.workoutId
        if (id > 0) {
            viewModel.retrieveWorkout(id).observe(this.viewLifecycleOwner) { selectedItem ->
                workout = selectedItem
                bind(workout)
            }
        } else {
            binding.saveBtn.setOnClickListener {
                addNewItem()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddWorkoutFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddWorkoutFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}