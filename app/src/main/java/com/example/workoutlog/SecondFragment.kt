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
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutlog.data.Exercise
import com.example.workoutlog.databinding.ActivityMainBinding.inflate
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import androidx.recyclerview.widget.RecyclerView




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

    private val eviewModel: ExerciseViewModel by activityViewModels {
       ExerciseViewModelFactory(
            (activity?.application as WorkoutApplication).edatabase.exerciseDao()
        )
    }

    private fun deleteWorkout() {
        viewModel.deleteWorkout(workout)
        findNavController().navigateUp()
    }

    private fun editWorkout() {
        val action = SecondFragmentDirections.actionSecondFragmentToAddWorkoutFragment(
            getString(R.string.edit_fragment_title),
            workout.id
        )
        this.findNavController().navigate(action)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            workoutName = it.getString(WORKOUT_NAME).toString()
        }
        setHasOptionsMenu(true)
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
        val adapter = ExerciseListAdapter {
        }
        binding.recyclerView.adapter = adapter
        eviewModel.allExercises(id).observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)


        binding.buttonSecond.setOnClickListener {

        }
        //binding.fab.setOnClickListener {
            //val action = SecondFragmentDirections.actionSecondFragmentToAddExerciseFragment(workoutName = workoutName, workoutId = id)
            //findNavController().navigate(action)
        //}
        //viewModel.retrieveWorkout(id).observe(this.viewLifecycleOwner) { selectedItem ->
        //    workout = selectedItem
        //    bind(workout)
        //}
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_workout, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = navigationArgs.workoutId
        return when (item.itemId) {
            R.id.action_deleteWorkout -> {
                showConfirmationDialog()
                true
            }
            R.id.action_editWorkout -> {
                editWorkout()
                true
            }
            R.id.action_addExercise -> {
                val action = SecondFragmentDirections.actionSecondFragmentToAddExerciseFragment(workoutName = workoutName, workoutId = id)
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun bind(workout: Workout) {
        binding.apply {
            workoutName = workout.workoutName
        }
    }
    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                deleteWorkout()
            }
            .show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}