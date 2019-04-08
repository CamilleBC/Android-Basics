package me.camillebc.androidbasics.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.camillebc.androidbasics.R

private const val MAIN_ACTIVITY_BACKSTACK = "main_activity"

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
    DogListFragment.OnListFragmentInteractionListener,
    DogListFragment.OnAddClickListener {

    /**
     * This function is the mandatory implementation of the [DogListFragment.OnAddClickListener] interface.
     *
     * We call [getSupportFragmentManager] and add a transaction to replace the existing fragment by
     * [DogEditorFragment] on the button callback ([DogListFragment.activityCallback])
     */
    override fun onAddClick() {
        // TODO("not implemented") // There is no way to add to the database right now
        toast("not implemented")

        val dogEditorFragment = DogEditorFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.constraintLayout_main_fragment, dogEditorFragment)
            .addToBackStack(MAIN_ACTIVITY_BACKSTACK)
            .commit()
        Log.i("FragmentManager: ", supportFragmentManager.fragments.toString())
    }

    /**
     * This function is the mandatory implementation of the [DogEditorFragment.OnCancelClickListener] interface.
     *
     * We call [onBackPressed] to simulate a press on the back button.
     */
    override fun onCancelClick() {
        onBackPressed()
    }

    /**
     * This function is the mandatory implementation of the [DogListFragment.OnListFragmentInteractionListener] interface.
     */
    override fun onListFragmentInteraction() {
        // TODO("not implemented") // There is no list yet to interact with
        toast("not implemented")
    }

    /**
     * We call [getSupportFragmentManager] and add a transaction to start the [DogListFragment] on [MainActivity.onCreate]
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dogListFragment = DogListFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.constraintLayout_main_fragment, dogListFragment)
            .addToBackStack(MAIN_ACTIVITY_BACKSTACK)
            .commit()
    }
}

/**
 * In Kotlin, this is called an extension function.
 * This is a simple function that gets added to the class.
 */
fun AppCompatActivity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
