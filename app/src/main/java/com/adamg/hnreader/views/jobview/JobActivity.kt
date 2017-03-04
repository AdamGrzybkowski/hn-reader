package com.adamg.hnreader.views.askview

import android.os.Bundle
import com.adamg.hnreader.AppConstants
import com.adamg.hnreader.R
import com.adamg.hnreader.base.BaseActivity
import com.adamg.hnreader.models.Job
import com.adamg.hnreader.views.webview.WebViewFragment
import com.evernote.android.state.State
import kotlinx.android.synthetic.main.activity_story.*
import kotlinx.android.synthetic.main.job_header.*

class JobActivity : BaseActivity() {

    @State
    lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        job = intent.extras.getParcelable<Job>(AppConstants.ITEM)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, WebViewFragment.create(job.url))
                .commit()

        showJob()
    }

    private fun showJob() {
        jobTitle.text = job.title
        jobDomain.text = job.domain
        jobTime.text = job.timeAgo
    }

    override fun injectDependencies() {}

}
