package com.adamg.hnreader.utils


//fun parseItemTypeForIntent(context: Context, item: Item) : Intent {
//    if (item.type == ItemType.LINK && item.domain != null) {
//        val intent = Intent(context, StoryActivity::class.java)
//        intent.putExtra(AppConstants.ITEM, item)
//        return intent
//    } else if ((item.type == ItemType.ASK || item.type == ItemType.LINK) && item.domain == null || item.domain == null) {
//        val intent = Intent(context, AskActivity::class.java)
//        intent.putExtra(AppConstants.ITEM, Ask.fromItem(item))
//        return intent
//    } else {
//        val intent = Intent(context, JobActivity::class.java)
//        intent.putExtra(AppConstants.ITEM, Job.fromItem(item))
//        return intent
//    }
//}