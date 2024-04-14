package android.android_isu_iskra

import android.content.Context
import android.icu.text.CaseMap.Title
import android.telecom.Call.Details
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.compose.ui.text.font.Typeface

class ExpandableListAdapter(
    private val context: Context,
    private val expandableListTitle: List<String>,
    private val expandableListDetail:HashMap<String,List<String>>
):BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return expandableListTitle.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
       return expandableListDetail[expandableListTitle[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return expandableListTitle[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return expandableListDetail[expandableListTitle[groupPosition]]!!.get(childPosition)
    }

    override fun getGroupId(groupPosition: Int): Long {
       return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
       return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertViews = LayoutInflater.from(context).inflate(R.layout.list_group, null)
        val listTitle = getGroup(groupPosition) as String
        val listTitleTextView = convertViews.findViewById<View>(R.id.listTitle) as TextView
        listTitleTextView.setTypeface(null, android.graphics.Typeface.BOLD)
        listTitleTextView.text = listTitle
        return convertViews
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertViews = LayoutInflater.from(context).inflate(R.layout.list_item, null)
        val expandedListText = getChild(groupPosition,childPosition) as String
        val expandedListTextView= convertViews.findViewById<TextView>(R.id.expandedListTextIten)
        expandedListTextView.text = expandedListText
        return convertViews
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }


}