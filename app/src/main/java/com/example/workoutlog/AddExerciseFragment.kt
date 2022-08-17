package com.example.workoutlog

import android.content.ClipData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.workoutlog.data.Exercise
import com.example.workoutlog.data.Workout
import com.example.workoutlog.databinding.FragmentAddExerciseBinding
import com.example.workoutlog.databinding.FragmentAddWorkoutBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddExerciseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddExerciseFragment : Fragment() {
    private val viewModel: ExerciseViewModel by activityViewModels {
        ExerciseViewModelFactory(
            (activity?.application as WorkoutApplication).edatabase
                .exerciseDao()
        )
    }
    lateinit var exercise: Exercise

    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentAddExerciseBinding? = null
    private val binding get() = _binding!!
    private lateinit var workoutName: String
    private val navigationArgs: AddExerciseFragmentArgs by navArgs()
    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.nameInput.text.toString()
        )
    }
    private fun addNewExercise() {
        val id = navigationArgs.workoutId
        if (isEntryValid()) {
            viewModel.addNewExercise(
                binding.nameInput.text.toString(),
                id,
                binding.weightInput1.text.toString(),
                binding.weightInput2.text.toString(),
                binding.weightInput3.text.toString(),
                binding.weightInput4.text.toString(),
                binding.weightInput5.text.toString(),
                binding.weightInput6.text.toString(),
                binding.weightInput7.text.toString(),
                binding.repInput1.text.toString(),
                binding.repInput2.text.toString(),
                binding.repInput3.text.toString(),
                binding.repInput4.text.toString(),
                binding.repInput5.text.toString(),
                binding.repInput6.text.toString(),
                binding.repInput7.text.toString(),

            )
            val action = AddExerciseFragmentDirections.actionAddExerciseFragmentToSecondFragment(workoutId = id, workoutName = workoutName)
            findNavController().navigate(action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            workoutName = it.getString(SecondFragment.WORKOUT_NAME).toString()
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_exercise, container, false)
        _binding = FragmentAddExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.workoutId
        binding.saveBtn.setOnClickListener {
            addNewExercise()
            //val action = AddExerciseFragmentDirections.actionAddExerciseFragmentToSecondFragment(workoutName = workoutName, workoutId = id)
            //findNavController().navigate(action)
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddExerciseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddExerciseFragment().apply {
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