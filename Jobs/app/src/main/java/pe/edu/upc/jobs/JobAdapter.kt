package pe.edu.upc.jobs

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.prototype_job.view.*

class JobAdapter(val jobs: List<Job>) : RecyclerView.Adapter<JobPrototype>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobPrototype {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.prototype_job, parent, false)

        return JobPrototype(view)
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    override fun onBindViewHolder(prototype: JobPrototype, position: Int) {
        prototype.bind(jobs[position])

    }

}

class JobPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvTitle = itemView.tvTitle
    private val tvDescription = itemView.tvDescription
    private val tvCompany = itemView.tvCompany
    private val ivLogo = itemView.ivLogo

    fun bind(job: Job) {

        tvCompany.text = job.company
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvDescription.text = Html.fromHtml(job.description,1)
        }
        tvTitle.text = job.title

        Glide.with(itemView).load(job.companyLogo).into(ivLogo)

    }


}
