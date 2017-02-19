package com.adamg.hnreader.views.mainactivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.adamg.hnreader.R
import com.adamg.hnreader.adapter.ViewPagerAdapter
import com.adamg.hnreader.views.asks.AsksFragments
import com.adamg.hnreader.views.comments.CommentsFragment
import com.adamg.hnreader.views.jobs.JobsFragment
import com.adamg.hnreader.views.newstories.NewStoriesFragment
import com.adamg.hnreader.views.shows.ShowsFragments
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)

        setupViewPager(viewPager)

        tabs.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        var adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(NewStoriesFragment(), "new")
        adapter.addFragment(ShowsFragments(), "show")
        adapter.addFragment(AsksFragments(), "ask")
        adapter.addFragment(JobsFragment(), "jobs")
        viewPager.adapter = adapter
    }

}
