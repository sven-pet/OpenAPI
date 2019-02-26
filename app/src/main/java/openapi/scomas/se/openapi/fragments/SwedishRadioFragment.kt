package openapi.scomas.se.openapi.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_swedish_radio_overview.*
import openapi.scomas.se.openapi.R
import openapi.scomas.se.openapi.adapters.SwedishRadioChannelAdapter
import openapi.scomas.se.openapi.networks.swedishradio.SwedishRadioModel
import openapi.scomas.se.openapi.viewmodels.MasterViewModel


class SwedishRadioFragment : Fragment() {

    lateinit var mViewModel : MasterViewModel
    lateinit var mAdapter: RecyclerView.Adapter<*>
    var pageNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(MasterViewModel::class.java)
    }


    override fun onStart() {
        super.onStart()

        val llm = StaggeredGridLayoutManager(1, 1)
        my_recycler_view.layoutManager = llm
        llm.orientation = LinearLayoutManager.VERTICAL
        setupListeners()

        /*val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = llm.getItemCount()
                val lastVisibleItemPosition = llm.findLastVisibleItemPositions(null)[0]

               if (lastVisibleItemPosition == totalItemCount-1){
                    mViewModel.callAPI(pageNumber++)
                }

            }
        }

        my_recycler_view.addOnScrollListener(scrollListener)*/
        if(mViewModel.programObserver.value == null){
            mViewModel.callAPI(pageNumber++)
        }

    }

    fun setupListeners() {

        val pictureListener = Observer<MutableList<SwedishRadioModel.Program>> {
            result ->
            System.out.println()
            mAdapter = SwedishRadioChannelAdapter(result!!, this.activity!!)
            my_recycler_view.adapter=mAdapter
        }
        mViewModel.programObserver.observe(this, pictureListener)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_swedish_radio_overview, container, false)
    }


}
