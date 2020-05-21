package com.annaalfiani.gmcapps.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.annaalfiani.gmcapps.R
import com.annaalfiani.gmcapps.models.Slide

class SlidePagerAdapter() :PagerAdapter()
{
    var mContext:Context? = null
    var mList:List<Slide>? = null

    constructor(context: Context, list: List<Slide>) : this()
    {
        mContext = context
        mList = list

    }



    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position)
        val inflater:LayoutInflater = mContext!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var slideLayout:View = inflater.inflate(R.layout.slide_item,null)

        var slideImage:ImageView = slideLayout.findViewById(R.id.slide_img)
        var slideText:TextView = slideLayout.findViewById(R.id.slide_title)
        slideImage.setImageResource(mList!!.get(position).image)
        slideText.setText(mList!!.get(position).title)
        container.addView(slideLayout)
        return slideLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return mList!!.count()
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
        container.removeView(`object` as View)
    }

}

private fun LayoutInflater.inflate(slideItem: Unit) {

}
