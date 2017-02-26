package com.adamg.hnreader.views.storyview

import android.os.Bundle
import android.support.v4.view.ViewPager
import com.adamg.hnreader.AppConstants
import com.adamg.hnreader.R
import com.adamg.hnreader.adapter.ViewPagerAdapter
import com.adamg.hnreader.base.BaseActivity
import com.adamg.hnreader.models.Item
import com.adamg.hnreader.views.comments.CommentsFragment
import com.adamg.hnreader.views.webview.WebViewFragment
import com.evernote.android.state.State
import kotlinx.android.synthetic.main.activity_story.*
import kotlinx.android.synthetic.main.story_header.*

class StoryActivity : BaseActivity() {

    @State
    lateinit var story: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        story = intent.extras.getParcelable<Item>(AppConstants.ITEM)
        showStory(story)

        setupViewPager(viewPager)
        tabs.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        var adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(CommentsFragment.create(story.id), getString(R.string.story_comments) + " ("+story.commentsCount + ")")
        adapter.addFragment(WebViewFragment.create(story.url), getString(R.string.story_article))
        viewPager.adapter = adapter
    }

    private fun showStory(story: Item) {
        storyTitle.text = story.title
        storyDomain.text = story.domain
        storyPoints.text = story.points.toString()
    }

    override fun injectDependencies() {}

}
