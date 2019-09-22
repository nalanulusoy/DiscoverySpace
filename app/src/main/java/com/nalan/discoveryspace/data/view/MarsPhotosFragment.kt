package com.nalan.discoveryspace.data.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nalan.discoveryspace.R
import com.nalan.discoveryspace.data.data.model.Status
import com.nalan.discoveryspace.data.viewmodel.MarsPhotosViewModel
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_mars_photos.*
import kotlinx.android.synthetic.main.item_list_footer.*
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MarsPhotosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private lateinit var marsViewModel: MarsPhotosViewModel








    private lateinit var marsPhotosAdapter: MarsPhotosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        AndroidSupportInjection.inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_mars_photos, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        marsViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(MarsPhotosViewModel::class.java)
        initAdapter()
        initState()

    }

    private fun initAdapter() {
        marsPhotosAdapter = MarsPhotosAdapter { marsViewModel.retry() }
        rec_mars_photos.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rec_mars_photos.adapter = marsPhotosAdapter
        observeLiveData()

    }

    private fun initState() {
        txt_error_fragment.setOnClickListener { marsViewModel.retry() }
        marsViewModel.getState().observe(this, Observer { state ->
        progress_bar_fragment.visibility = if (marsViewModel.listIsEmpty() && state == Status.LOADING) View.VISIBLE else View.GONE
         txt_error_fragment.visibility = if (marsViewModel.listIsEmpty() && state == Status.ERROR) View.VISIBLE else View.GONE
            if (!marsViewModel.listIsEmpty()) {
                marsPhotosAdapter.setState(state ?: Status.SUCCESS)
            }
        })
    }

    private fun observeLiveData() {
        //observe live data emitted by view model
        marsViewModel.postsLiveData.observe(this, Observer {
            marsPhotosAdapter.submitList(it)
        })
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MarsPhotosFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
