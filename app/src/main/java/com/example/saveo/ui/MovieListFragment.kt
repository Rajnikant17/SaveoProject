package com.example.saveo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapiservicesmodule.di.models.ResponseMovieList
import com.example.saveo.R
import com.example.saveo.adapter.MovieListAdapter
import com.example.saveo.adapter.MoviesViewPagerAdapter
import com.example.saveo.databinding.FragmentMovieListBinding
import com.example.saveo.utils.EndlessRecyclerOnScrollLisitener
import com.example.saveo.utils.MainStateEvent
import com.example.saveo.viewmodels.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AppCompatActivity

@AndroidEntryPoint
class MovieListFragment : BaseFragment<FragmentMovieListBinding>(), MovieListAdapter.ClickEventInterface {
    override val fragmentLayoutResId: Int
        get() = R.layout.fragment_movie_list
    private val movieListViewModel: MovieListViewModel by viewModels()
    var movieListAdapter: MovieListAdapter? = null
    var gridLayoutManager: GridLayoutManager? = null
    lateinit var navController: NavController
    lateinit var imageViewpageAdapterr:MoviesViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieListViewModel.getMovieList(MainStateEvent.GetMovieListResponse)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        observeMovieListDataFromApi()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = getString(R.string.movie)
        initiaLizeViewPagerAndRecyclerVew()

        //Recyclerview endscroll for pagination
        binding.rvParent.addOnScrollListener(object :
            EndlessRecyclerOnScrollLisitener(gridLayoutManager!!) {
            override fun onScrolledToEnd() {
                movieListViewModel.getMovieList(MainStateEvent.GetMovieListResponse)
            }
        })
    }

    fun observeMovieListDataFromApi() {
        movieListViewModel.movieListLiveData.observe(viewLifecycleOwner, observer)
    }

    fun initiaLizeViewPagerAndRecyclerVew(){
        movieListAdapter = null
        movieListViewModel.viewPagerAlreadySet=false
        gridLayoutManager = GridLayoutManager(requireActivity(), 3)
        imageViewpageAdapterr=MoviesViewPagerAdapter(context)
        binding.viewpager.adapter=imageViewpageAdapterr
        binding.viewpager.pageMargin=20
        binding.viewpager.clipToPadding=false
    }

    fun setMoviesAdapter() {

        //Set Up ViewPAgerAdapter
        if(!movieListViewModel.viewPagerAlreadySet) {
            movieListViewModel.viewPagerAlreadySet=true
            imageViewpageAdapterr.refreshImageCarousel(movieListViewModel.moviesViewpagerList)
        }

        // set Recyclerview Adapter
        if (movieListAdapter == null) {
            binding.rvParent.setLayoutManager(gridLayoutManager)
            movieListAdapter =
                MovieListAdapter(
                    requireActivity(),
                    movieListViewModel.finalmovieList, this@MovieListFragment
                )
            binding.rvParent.adapter = movieListAdapter
        } else {
            movieListAdapter?.notifyDataSetChanged()
        }
    }

    override fun clickEvent(position: Int) {
        val bundle = Bundle()
        bundle.putString("imdb_id", movieListViewModel.finalmovieList.get(position).imdbID)
        navController.navigate(R.id.action_movieListFragment_to_movieDetailsFragment, bundle)
    }

    override fun receivedResponse(item: Any?) {
        item?.let { response ->
            when (response) {
                is ResponseMovieList -> {
                    binding.tvNotShowing.visibility=View.VISIBLE
                    response.movieList?.let { it1 ->
                        movieListViewModel.finalmovieList.addAll(it1)
                        //this if condition will be executed for first time only to store the only 4 items in Viewpager
                        if(movieListViewModel.page_count==1) {
                            movieListViewModel.setDataForViewPager()
                        }
                        setMoviesAdapter()
                    }
                }
                else -> {
                }
            }
        }
    }
}