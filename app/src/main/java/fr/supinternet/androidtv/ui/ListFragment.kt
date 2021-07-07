package fr.supinternet.androidtv.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import fr.supinternet.androidtv.R
import fr.supinternet.androidtv.data.network.NetworkManager
import fr.supinternet.androidtv.data.network.model.Movie
import kotlinx.coroutines.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : BrowseSupportFragment() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = fragmentAdapter

        GlobalScope.launch {
            prepareEntranceTransition()
            val boxOffice = NetworkManager.getBoxOffice()
            val anticipatedMovies = NetworkManager.getAnticipatedMovies()
            val boxOfficeAdapter = ArrayObjectAdapter(PresenterModel())
            val anticipatedMoviesAdapter = ArrayObjectAdapter(PresenterModel())
            for (movie in boxOffice) {
                boxOfficeAdapter.add(movie)
            }
            for (movie in anticipatedMovies) {
                anticipatedMoviesAdapter.add(movie)
            }
            fragmentAdapter.add(ListRow(HeaderItem(0, "Les sorties"), boxOfficeAdapter))
            fragmentAdapter.add(ListRow(HeaderItem(0, "Les Attendus"), anticipatedMoviesAdapter))
            startEntranceTransition()
        }
    }

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }*/

    override fun prepareEntranceTransition() {
        super.prepareEntranceTransition()
    }

    override fun startEntranceTransition() {
        super.startEntranceTransition()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}