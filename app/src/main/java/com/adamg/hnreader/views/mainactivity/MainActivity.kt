package com.adamg.hnreader.views.mainactivity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.adamg.hnreader.R
import com.adamg.hnreader.views.shows.ShowsFragments
import com.adamg.hnreader.views.asks.AsksFragments
import com.adamg.hnreader.views.comments.CommentsFragment
import com.adamg.hnreader.views.newstories.NewStoriesFragment

class MainActivity : AppCompatActivity() {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.tabs)
    lateinit var tabLayout: TabLayout

    @BindView(R.id.viewpager)
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)

        setupViewPager(viewPager)

        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        var adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(NewStoriesFragment(), "new")
        adapter.addFragment(CommentsFragment(), "comments")
        adapter.addFragment(ShowsFragments(), "show")
        adapter.addFragment(AsksFragments(), "ask")
        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = mutableListOf<Fragment>()
        private val mFragmentTitleList = mutableListOf<String>()

        override fun getItem(position: Int) = mFragmentList[position]

        override fun getCount() = mFragmentList.size

        override fun getPageTitle(position: Int) = mFragmentTitleList[position]

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }
    }
}
