package pe.edu.upc.catchup.controllers.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_sources.view.*

import pe.edu.upc.catchup.R
import pe.edu.upc.catchup.adapters.SourcesAdapter
import pe.edu.upc.catchup.models.Source
import pe.edu.upc.catchup.network.NewsApi



/**
 * A simple [Fragment] subclass.
 *
 */
class SourcesFragment : Fragment() {
    private lateinit var sourcesRecyclerView: RecyclerView

    private var sources = ArrayList<Source>()
    private var sourcesAdapter = SourcesAdapter(sources)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sources, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sourcesRecyclerView = view.sourcesRecyclerView
        sourcesRecyclerView.apply {
            adapter = sourcesAdapter
            this.layoutManager = GridLayoutManager(view.context, 2)
        }
        NewsApi.apiKey = view.context.getString(R.string.new_api_key)
        NewsApi.requestSources({
            it?.sources?.apply {
                Log.d("NewsApi", "Sources count: $size")
                sourcesAdapter.sources = this
                sourcesAdapter.notifyDataSetChanged()
            }
        }, {
            Log.d("NewsApi", "${it.errorBody} ${it.localizedMessage}")
            sourcesAdapter.sources = ArrayList()
            sourcesAdapter.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
        sourcesAdapter.verifyItemChanged()
    }
}
