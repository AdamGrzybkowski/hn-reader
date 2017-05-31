package com.adamg.hnreader.utils

import android.content.Context
import android.content.Intent
import com.adamg.hnreader.AppConstants
import com.adamg.hnreader.models.Ask
import com.adamg.hnreader.models.Item
import com.adamg.hnreader.models.ItemType
import com.adamg.hnreader.models.Job
import com.adamg.hnreader.views.askview.AskActivity
import com.adamg.hnreader.views.askview.JobActivity
import com.adamg.hnreader.views.storyview.StoryActivity


fun parseItemTypeForIntent(context: Context, item: Item) : Intent {
    if (item.type == ItemType.LINK && item.domain != null) {
        val intent = Intent(context, StoryActivity::class.java)
        intent.putExtra(AppConstants.ITEM, item)
        return intent
    } else if ((item.type == ItemType.ASK || item.type == ItemType.LINK) && item.domain == null || item.domain == null) {
        val intent = Intent(context, AskActivity::class.java)
        intent.putExtra(AppConstants.ITEM, Ask.fromItem(item))
        return intent
    } else {
        val intent = Intent(context, JobActivity::class.java)
        intent.putExtra(AppConstants.ITEM, Job.fromItem(item))
        return intent
    }
}