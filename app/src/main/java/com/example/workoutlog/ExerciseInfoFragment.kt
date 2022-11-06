package com.example.workoutlog

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androidplot.xy.LineAndPointFormatter
import com.androidplot.xy.SimpleXYSeries
import com.androidplot.xy.XYPlot
import com.androidplot.xy.XYSeries
import com.example.workoutlog.databinding.FragmentExerciseInfoBinding
import com.example.workoutlog.databinding.FragmentSecondBinding
import org.w3c.dom.Text
import java.nio.file.Files.find
import java.util.Arrays

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseInfoFragment : Fragment() {
    private val navigationArgs: ExerciseInfoFragmentArgs by navArgs()

    //private val exerciseName = navigationArgs.title
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentExerciseInfoBinding? = null
    private val binding get() = _binding!!
    private val eviewModel: ExerciseViewModel by activityViewModels {
        ExerciseViewModelFactory(
            (activity?.application as WorkoutApplication).edatabase.exerciseDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val exerciseName = navigationArgs.title
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
        _binding = FragmentExerciseInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (eviewModel.getExerciseMaxWeightt(navigationArgs.title) > 0) {
            val max = "${eviewModel.getExerciseMaxWeightt(navigationArgs.title).toString()} lbs"
            binding.maxWeight.text = max
        }
        val exerciseList = eviewModel.getExercisesByNamee(navigationArgs.title)
        var volumeList = arrayOf<Number>()
        var numworkouts = 0
        var numworkoutslist = arrayOf<Number>()
        for (item in exerciseList){
            var volume = 0
            if (item.set1weight > 0 && item.set1reps>0){
                volume += item.set1weight*item.set1reps
            }
            if (item.set2weight > 0 && item.set2reps>0){
                volume += item.set2weight*item.set2reps
            }
            if (item.set3weight > 0 && item.set3reps>0){
                volume += item.set3weight*item.set3reps
            }
            if (item.set4weight > 0 && item.set4reps>0){
                volume += item.set4weight * item.set4reps
            }
            if (item.set5weight > 0 && item.set5reps>0){
                volume += item.set5weight*item.set5reps
            }
            if (item.set6weight > 0 && item.set6reps>0){
                volume += item.set6weight*item.set6reps
            }
            if (item.set7weight > 0 && item.set7reps>0){
                volume += item.set7weight*item.set7reps
            }
            volumeList += volume
            numworkouts += 1
            numworkoutslist += numworkouts
        }
        val series1: XYSeries = SimpleXYSeries(Arrays.asList(* numworkoutslist),Arrays.asList(* volumeList),"Series 1")
        val series1Format = LineAndPointFormatter(Color.LTGRAY,Color.LTGRAY,null,null)
        val plot:XYPlot = view.findViewById(R.id.weight_plot)
        plot.addSeries(series1,series1Format)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExerciseInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExerciseInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}