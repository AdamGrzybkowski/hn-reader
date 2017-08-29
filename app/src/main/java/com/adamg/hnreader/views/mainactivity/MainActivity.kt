package com.adamg.hnreader.views.mainactivity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.adamg.hnreader.R
import com.adamg.hnreader.adapter.ViewPagerAdapter
import com.adamg.hnreader.views.listfragments.newstories.AsksFragment
import com.adamg.hnreader.views.listfragments.newstories.NewStoriesFragment
import com.adamg.hnreader.views.listfragments.shows.JobsFragment
import com.adamg.hnreader.views.listfragments.shows.ShowsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        setupViewPager(viewPager)
        tabs.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        var adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(NewStoriesFragment(), "new")
        adapter.addFragment(ShowsFragment(), "show")
        adapter.addFragment(AsksFragment(), "ask")
        adapter.addFragment(JobsFragment(), "jobs")
        viewPager.adapter = adapter
    }

}
