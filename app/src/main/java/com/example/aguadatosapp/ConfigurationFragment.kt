package com.example.aguadatosapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.amplifyframework.core.Amplify.DataStore
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.predicate.QueryField
import com.amplifyframework.core.model.query.predicate.QueryPredicate
import com.amplifyframework.datastore.generated.model.Operator
import com.amplifyframework.datastore.generated.model.Plant

class ConfigurationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_configuration, container, false)

        Log.d("FragmentNavigation", "ConfigurationFragment is created")

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        val sharedPreferences = context?.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        //set variables to access necessary UI elements
        val opNameInput: EditText = view.findViewById(R.id.operator_name_input)
        val plantNameInput: EditText = view.findViewById(R.id.plant_name_input)
        val chemTypeSwitch: ImageView = view.findViewById(R.id.chem_type_switch)
        val chemConcInput: EditText = view.findViewById(R.id.chem_conc_input)
        val numFiltersInput: EditText = view.findViewById(R.id.num_filters_input)
        val saveButton: Button = view.findViewById(R.id.save_button)
        var switchChecked = false

        //set up UI using current configuration
        if (sharedPreferences != null) {
            opNameInput.setText(sharedPreferences.getString("operatorName", null))
            plantNameInput.setText(sharedPreferences.getString("plantName", null))
        }
        chemConcInput.setText(viewModel.chemConcentration.value.toString())
        numFiltersInput.setText(viewModel.numFilters.value.toString())
        if(viewModel.chemType.value == "PACl") {
            chemTypeSwitch.setImageResource(R.drawable.chem_type_switch_off)
            switchChecked = false
        }
        else if(viewModel.chemType.value == "Aluminum Sulfate") {
            chemTypeSwitch.setImageResource(R.drawable.chem_type_switch_on)
            switchChecked = true
        }

        //listener for chemical type switch
        chemTypeSwitch.setOnClickListener {
            if(switchChecked) {
                //flip switch off
                chemTypeSwitch.setImageResource(R.drawable.chem_type_switch_off)
            }
            else {
                //flip switch on
                chemTypeSwitch.setImageResource(R.drawable.chem_type_switch_on)
            }
            switchChecked = !switchChecked
        }

        // handle logic for save button
        saveButton.setOnClickListener {
            // update configuration data in viewModel
            viewModel.plantOperatorName.value = opNameInput.text.toString()
            viewModel.plantName.value = plantNameInput.text.toString()
            if(switchChecked) {
                viewModel.chemType.value = "Aluminum Sulfate"
            }
            else {
                viewModel.chemType.value = "PACl"
            }
            viewModel.chemConcentration.value = chemConcInput.text.toString().toDouble()
            //only accept filter input if it is valid (0<n<=6)
            val numFiltersValue = numFiltersInput.text.toString().toInt()
            if(numFiltersValue in 1..6) {
                viewModel.numFilters.value = numFiltersInput.text.toString().toInt()
            }
            else {
                //otherwise give error message explaining what's wrong
                Toast.makeText(context,"Please enter a valid number of filters (between 1 and 6).",Toast.LENGTH_SHORT).show()
            }
            // set up backend configuration: create plant if not created yet
            val currentPlantName = viewModel.plantName.value
            val currentOperatorName = viewModel.plantOperatorName.value
            var currentPlantId = ""
            var currentOperatorId = ""

            if (currentPlantName != null && currentOperatorName != null) {
                getPlantIdByName(currentPlantName) { plantId ->
                    if (plantId != null) {
                        currentPlantId = plantId
                        val editor = sharedPreferences?.edit()
                        if (editor != null) {
                            editor.putString("plantID", plantId)
                            editor.putString("plantName", currentPlantName)
                            editor.apply()
                        }
                        if (sharedPreferences != null) {
                            Log.d("msg","plantId after creation: ${sharedPreferences.getString("plantID", null).toString()}")
                        }
                        getOperatorIdByNameAndPlantId(currentOperatorName,currentPlantId) { operatorId ->
                            if (operatorId != null) {
                                currentOperatorId = operatorId
                                val editor = sharedPreferences?.edit()
                                if (editor != null) {
                                    editor.putString("operatorID", operatorId)
                                    editor.putString("operatorName", currentOperatorName)
                                    editor.apply()
                                }
                            } else {
                                createOperator(currentOperatorName,currentPlantId) { result ->
                                    result.onSuccess { operatorId ->
                                        Log.d("msg","Operator added successfully with ID: $operatorId")
                                        currentOperatorId = operatorId
                                        val editor = sharedPreferences?.edit()
                                        if (editor != null) {
                                            editor.putString("operatorID", operatorId)
                                            editor.putString("operatorName", currentOperatorName)
                                            editor.apply()
                                        }
                                    }.onFailure { error ->
                                        Log.d("msg","Error: ${error.message}")
                                        Toast.makeText(context,"Error: ${error.message}",Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    } else {
                        createPlant(currentPlantName) { result ->
                            result.onSuccess { plantId ->
                                Log.d("msg","Plant added successfully with ID: $plantId")
                                currentPlantId = plantId
                                val editor = sharedPreferences?.edit()
                                if (editor != null) {
                                    editor.putString("plantID", plantId)
                                    editor.putString("plantName", currentPlantName)
                                    editor.apply()
                                }
                                if (sharedPreferences != null) {
                                    Log.d("msg","plantId after creation: ${sharedPreferences.getString("plantId", null).toString()}")
                                }
                                getOperatorIdByNameAndPlantId(currentOperatorName,currentPlantId) { operatorId ->
                                    if (operatorId != null) {
                                        currentOperatorId = operatorId
                                        val editor = sharedPreferences?.edit()
                                        if (editor != null) {
                                            editor.putString("operatorID", operatorId)
                                            editor.putString("operatorName", currentOperatorName)
                                            editor.apply()
                                        }
                                    } else {
                                        createOperator(currentOperatorName,currentPlantId) { result ->
                                            result.onSuccess { operatorId ->
                                                Log.d("msg","Operator added successfully with ID: $operatorId")
                                                currentOperatorId = operatorId
                                                val editor = sharedPreferences?.edit()
                                                if (editor != null) {
                                                    editor.putString("operatorID", operatorId)
                                                    editor.putString("operatorName", currentOperatorName)
                                                    editor.apply()
                                                }
                                            }.onFailure { error ->
                                                Log.d("msg","Error: ${error.message}")
                                                Toast.makeText(context,"Error: ${error.message}",Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    }
                                }
                            }.onFailure { error ->
                                Log.d("msg","Error: ${error.message}")
                            }
                        }
                    }
                }
            }
            // pop a toast to let user know changes are saved
            Toast.makeText(context,"Your changes have been saved.",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("FragmentNavigation", "ConfigurationFragment is now visible")
    }

    private fun createPlant(
        plantName: String,
        callback: (Result<String>) -> Unit
    ) {
        // Create a Plant instance
        val newPlant = Plant.builder()
            .name(plantName)
            .build()

        Log.d("msg","Saved plant: ${newPlant.id}")

        // Save the Plant to DataStore
            DataStore.save(newPlant, {
                Log.d("msg","Successfully saved Plant: ${newPlant.id}")
                callback(Result.success(newPlant.id))
            }, { error ->
                Log.d("msg","Failed to save Plant: ${error.message}")
                callback(Result.failure(Exception("Failed to save Plant: ${error.message}")))
            })
    }

    private fun createOperator(
        operatorName: String,
        plantId: String,
        callback: (Result<String>) -> Unit
    ) {
        // Create an Operator instance
        val newOperator = Operator.builder()
            .name(operatorName)
            .plant(Plant.justId(plantId)) // Reference Plant by ID
            .build()

        Log.d("msg","Saved operator: ${newOperator.id}")

        // Save the operator to DataStore
        DataStore.save(newOperator, {
            Log.d("msg","Successfully saved Operator: ${newOperator.id}")
            callback(Result.success(newOperator.id))
        }, { error ->
            Log.d("msg","Failed to save Operator: ${error.message}")
            callback(Result.failure(Exception("Failed to save Operator: ${error.message}")))
        })
    }

    private fun getPlantIdByName(plantName: String, callback: (String?) -> Unit) {
        // Create a predicate to filter by plant name
        val predicate: QueryPredicate = Plant.NAME.eq(plantName)

        // Query the DataStore
        DataStore.query(
            Plant::class.java,
            predicate,
            { iterator ->
                if (iterator.hasNext()) {
                    val plant = iterator.next() // Get the first matching Plant
                    callback(plant.id) // Return the plant ID
                } else {
                    Log.d("msg","No plant found with the name: $plantName")
                    callback(null) // No match found
                }
            },
            { error ->
                Log.d("msg","Failed to query Plant: ${error.message}")
                callback(null) // Handle query failure
            }
        )
    }

    private fun getOperatorIdByNameAndPlantId(operatorName: String, plantId: String, callback: (String?) -> Unit) {
        // Create a predicate to filter by plant name
        val predicate1: QueryPredicate = Plant.ID.eq(plantId)
        val predicate2: QueryPredicate = Operator.NAME.eq(operatorName)

        // Query the DataStore
        DataStore.query(
            Plant::class.java,
            predicate1,
            { iterator ->
                if (iterator.hasNext()) {
                    val plant = iterator.next() // Get the first matching Plant
                    DataStore.query(
                        Operator::class.java,
                        predicate2,
                        { iterator ->
                            if (iterator.hasNext()) {
                                val op = iterator.next()
                                callback(op.id)
                            } else {
                                Log.d("msg","No operator found with the name: $operatorName")
                                callback(null) // No match found
                            }
                        },
                        { error ->
                            Log.d("msg","Failed to query Operator: ${error.message}")
                            callback(null) // Handle query failure
                        }
                    )
                } else {
                    Log.d("msg","No plant found with the ID: $plantId")
                    callback(null) // No match found
                }
            },
            { error ->
                Log.d("msg","Failed to query Plant: ${error.message}")
                callback(null) // Handle query failure
            }
        )
    }
}