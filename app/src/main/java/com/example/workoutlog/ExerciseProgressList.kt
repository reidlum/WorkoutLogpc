package com.example.workoutlog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutlog.databinding.FragmentExerciseProgressListBinding
import com.example.workoutlog.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseProgressList.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseProgressList : Fragment() {

    private var _binding: FragmentExerciseProgressListBinding? = null
    private val binding get() = _binding!!

    private val eviewModel: ExerciseViewModel by activityViewModels {
        ExerciseViewModelFactory(
            (activity?.application as WorkoutApplication).edatabase.exerciseDao()
        )
    }

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentExerciseProgressListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ExerciseListAdapter {
            //val action =
            //this.findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter
        eviewModel.allExercises().observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExerciseProgressList.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExerciseProgressList().apply {
                arguments = Bundle().apply {
                }
            }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}