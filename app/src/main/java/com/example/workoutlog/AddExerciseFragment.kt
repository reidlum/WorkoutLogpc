package com.example.workoutlog

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.workoutlog.data.Exercise
import com.example.workoutlog.databinding.FragmentAddExerciseBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

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

    private fun updateExercise() {
        val id = navigationArgs.workoutId
        if (isEntryValid()) {
            viewModel.updateExercise(
                navigationArgs.exerciseId,
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

    private fun bind(exercise: Exercise) {
        binding.apply {
            nameInput.setText(exercise.exerciseName, TextView.BufferType.SPANNABLE)
        }
        if (exercise.set1weight != -1) {
            binding.apply { weightInput1.setText(exercise.set1weight.toString(), TextView.BufferType.SPANNABLE) }
        }
        if (exercise.set2weight != -1) {
            binding.apply { weightInput2.setText(exercise.set2weight.toString(), TextView.BufferType.SPANNABLE) }
        }
        if (exercise.set3weight != -1) {
            binding.apply { weightInput3.setText(exercise.set3weight.toString(), TextView.BufferType.SPANNABLE) }
        }
        if (exercise.set4weight != -1) {
            binding.apply { weightInput4.setText(exercise.set4weight.toString(), TextView.BufferType.SPANNABLE) }
        }
        if (exercise.set5weight != -1) {
            binding.apply { weightInput5.setText(exercise.set5weight.toString(), TextView.BufferType.SPANNABLE) }
        }
        if (exercise.set6weight != -1) {
            binding.apply { weightInput6.setText(exercise.set6weight.toString(), TextView.BufferType.SPANNABLE) }
        }
        if (exercise.set7weight != -1) {
            binding.apply { weightInput7.setText(exercise.set7weight.toString(), TextView.BufferType.SPANNABLE) }
        }
        if (exercise.set1reps != -1) {
            binding.apply { repInput1.setText(exercise.set1reps.toString(), TextView.BufferType.SPANNABLE) }
        }
        if (exercise.set2reps != -1) {
            binding.apply { repInput2.setText(exercise.set2reps.toString(), TextView.BufferType.SPANNABLE) }
        }
        if (exercise.set3reps != -1) {
            binding.apply { repInput3.setText(exercise.set3reps.toString(), TextView.BufferType.SPANNABLE) }
        }
        if (exercise.set4reps != -1) {
            binding.apply { repInput4.setText(exercise.set4reps.toString(), TextView.BufferType.SPANNABLE) }
        }
        if (exercise.set5reps != -1) {
            binding.apply { repInput5.setText(exercise.set5reps.toString(), TextView.BufferType.SPANNABLE) }
        }
        if (exercise.set6reps != -1) {
            binding.apply { repInput6.setText(exercise.set6reps.toString(), TextView.BufferType.SPANNABLE) }
        }
        if (exercise.set7reps != -1) {
            binding.apply { repInput7.setText(exercise.set7reps.toString(), TextView.BufferType.SPANNABLE) }
        }
        binding.apply { saveBtn.setOnClickListener { updateExercise() } }

    }

    private fun deleteExercise() {
        viewModel.deleteItem(exercise)
        findNavController().navigateUp()
    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext(),R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Background)
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_exercise_question))
            .setCancelable(true)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                deleteExercise()
            }
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            workoutName = it.getString(SecondFragment.WORKOUT_NAME).toString()
            //param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)

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
        val id = navigationArgs.exerciseId
        if (id > 0) {
            viewModel.retrieveExercise(id).observe(this.viewLifecycleOwner) { selectedItem ->
                exercise = selectedItem
                bind(exercise)
            }
        } else {
        binding.saveBtn.setOnClickListener {
            addNewExercise()
            //val action = AddExerciseFragmentDirections.actionAddExerciseFragmentToSecondFragment(workoutName = workoutName, workoutId = id)
            //findNavController().navigate(action)
            }
        }
        /*
        val auto = view?.findViewById<AutoCompleteTextView>(R.id.name_input)
        val autoList = arrayOf(viewModel.getExerciseNames())
        val adapter = ArrayAdapter(view.context,android.R.layout.simple_list_item_1,autoList)
        if (auto != null) {
            auto.setAdapter(adapter)
        }
         */
        val autoList = viewModel.getExerciseNames()
        val adapter = ArrayAdapter(view.context,android.R.layout.simple_list_item_1,autoList)
        val actv = view.findViewById(R.id.name_input) as AutoCompleteTextView
        actv.setThreshold(1)
        actv.setAdapter(adapter)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (navigationArgs.exerciseId > 0) {
            inflater.inflate(R.menu.menu_exercise, menu)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_deleteExercise -> {
                showConfirmationDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*
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
     */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}