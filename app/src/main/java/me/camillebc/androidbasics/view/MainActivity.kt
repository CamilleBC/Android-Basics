package me.camillebc.androidbasics.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import me.camillebc.androidbasics.R
import me.camillebc.androidbasics.view.fragment.DogEditorFragment
import me.camillebc.androidbasics.view.fragment.DogListFragment
import me.camillebc.androidbasics.view.model.Dog
import me.camillebc.androidbasics.view.model.DogViewModel

/**
 * The [MainActivity] implements the necessary callbacks from [DogEditorFragment] and [DogListFragment]
 *
 * It adds the [DogListFragment] on [MainActivity.onCreate] and then just waits until there is a callback from one of
 * the fragments.
 *
 * See the official android documentation for more information:
 * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
 */
class MainActivity : AppCompatActivity(),
    DogEditorFragment.OnCancelClickListener,
    DogEditorFragment.OnAddClickListener,
    DogListFragment.OnListFragmentInteractionListener,
    DogListFragment.OnAddClickListener {

    private lateinit var dogViewModel: DogViewModel

    /**
     * This function is the mandatory implementation of the [DogEditorFragment.OnAddClickListener] interface.
     *
     * If the data is validated:
     * We call the [FragmentManger.popBackStack] to pop the current fragment and return to the last one.
     * Else:
     * We give the user a feedback on why the data was not validated
     */
    override fun onEditorAddClick(name: String, breed: String, subBreed: String) {
        val dog = Dog(name, breed, subBreed)
        if ( dog.isValid() ) {
            dogViewModel.addDog(dog)
            supportFragmentManager.popBackStack()
        } else {
           validationFeedback(dog)
        }
    }

    /**
     * This function is the mandatory implementation of the [DogEditorFragment.OnCancelClickListener] interface.
     *
     * We call the [FragmentManger.popBackStack] to pop the current fragment and return to the last one.
     */
    override fun onEditorCancelClick() {
        supportFragmentManager.popBackStack()
    }

    /**
     * This function is the mandatory implementation of the [DogListFragment.OnAddClickListener] interface.
     *
     * We call [getSupportFragmentManager] and add a transaction to replace the existing fragment by
     * [DogEditorFragment] on the button callback ([DogListFragment.activityCallback])
     */
    override fun onListAddClick() {
        // TODO("not implemented") // There is no way to add to the database right now
        toast("not implemented")

        val dogEditorFragment = DogEditorFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.constraintLayout_main_fragment, dogEditorFragment)
            .addToBackStack(null)
            .commit()
    }

    /**
     * This function is the mandatory implementation of the [DogListFragment.OnListFragmentInteractionListener] interface.
     */
    override fun onListFragmentInteraction(dog: Dog) {
        // TODO("not implemented") // There is no list yet to interact with
        toast("Dog name: ${dog.name}\nDog breed: ${dog.breed}")
    }

    /**
     * First we initialize the [DogViewModel] and set the LifeCycle owner to the activity. The fragments will be abl
     * We call [getSupportFragmentManager] and add a transaction to start the [DogListFragment] on [MainActivity.onCreate]
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogViewModel = ViewModelProviders.of(this).get(DogViewModel::class.java)
        val dogListFragment = DogListFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.constraintLayout_main_fragment, dogListFragment)
            .addToBackStack(null)
            .commit()
    }

    /**
     * Give feedback on the [Dog] item's data
     *
     * @param dog the [Dog] item to feedback
     */
    private fun validationFeedback(dog: Dog) {
        if ( dog.name.isBlank() ) toast("The dog's name cannot be blank.")
        if ( dog.breed.isBlank() ) toast("The dog's breed cannot be blank.")
    }
}

/**
 * In Kotlin, this is called an extension function.
 * This is a simple function that gets added to the class.
 */
fun AppCompatActivity.toast(message: String) {
    Toast.makeText(this, "\n$message", Toast.LENGTH_SHORT).show()
}
