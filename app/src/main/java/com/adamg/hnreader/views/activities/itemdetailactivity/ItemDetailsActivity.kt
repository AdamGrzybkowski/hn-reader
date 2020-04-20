package com.adamg.hnreader.views.activities.itemdetailactivity

import android.content.Context
import android.os.Bundle
import com.adamg.hnreader.HNApp
import com.adamg.hnreader.R
import com.adamg.hnreader.dagger.component.DaggerItemDetailsComponent
import com.adamg.hnreader.dagger.component.ItemDetailsComponent
import com.adamg.hnreader.models.Item
import com.adamg.hnreader.utils.fade
import com.adamg.hnreader.utils.show
import com.adamg.hnreader.views.base.BaseActivityMvp
import kotlinx.android.synthetic.main.activity_item_details.*
import kotlinx.android.synthetic.main.item_details.*
import org.jetbrains.anko.intentFor

class ItemDetailsActivity : BaseActivityMvp<ItemDetailsView, ItemDetailsPresenter>(), ItemDetailsView {

    lateinit var itemDetailsComponent: ItemDetailsComponent

    companion object {
        val ITEM_ID_KEY = "item_id_key"
        fun start(context: Context, itemId: Long) {
            context.startActivity(context.intentFor<ItemDetailsActivity>(ITEM_ID_KEY to itemId))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.loadItem(intent?.extras?.getLong(ITEM_ID_KEY)!!)
    }

    override fun showItemDetails(item: Item) {
        itemPointsCount.text = item.points?.let { resources.getQuantityString(R.plurals.points, it, it) }
        itemTitle.text = item.title
        if (item.content != null) {
            itemContent.show()
            itemContent.text = item.content
        } else {
            itemContent.fade()
            itemContent.text = ""
        }
        itemDomain.text = item.domain
        itemTimeAgo.text = item.time_ago
        item.user?.let { itemUser.text = getString(R.string.value_dot_separator, it) }
    }

    override fun createPresenter() = itemDetailsComponent.presenter()

    override fun injectDependencies() {
        itemDetailsComponent = DaggerItemDetailsComponent.builder()
                .applicationComponent(HNApp.applicationComponent)
                .build()
    }
}
