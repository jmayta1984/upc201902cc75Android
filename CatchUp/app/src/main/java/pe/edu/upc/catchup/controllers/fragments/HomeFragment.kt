package pe.edu.upc.catchup.controllers.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

import pe.edu.upc.catchup.R
import pe.edu.upc.catchup.adapters.ArticlesAdapter
import pe.edu.upc.catchup.models.Article
import pe.edu.upc.catchup.network.NewsApi

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {
    lateinit var articlesAdapter: ArticlesAdapter
    var articles: List<Article> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articlesAdapter = ArticlesAdapter(articles)
        articlesRecyclerView.apply {
            adapter = articlesAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
        NewsApi.apply {
            apiKey = getString(R.string.new_api_key)
            requestTopHeadlines({
                it?.apply {
                    articlesAdapter.articles = this.articles
                    articlesAdapter.notifyDataSetChanged()
                }
            }, {
                articlesAdapter.articles = articles
                articlesAdapter.notifyDataSetChanged()
            })
        }

    }
}
