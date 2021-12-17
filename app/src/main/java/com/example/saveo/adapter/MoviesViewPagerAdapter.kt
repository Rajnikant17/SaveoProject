package com.example.saveo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.saveo.R

class MoviesViewPagerAdapter(val context: Context?): PagerAdapter() {
    val movieCarouselItemList = mutableListOf<String>()
    override fun getCount(): Int {
        return movieCarouselItemList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }

    override fun getPageWidth(position: Int): Float {
        return 0.83f
    }

    override fun getItemPosition(`object`: Any): Int {
        if (`object` is CardView) {
            val itemView = `object`
            if (itemView.id == R.id.cv_image && itemView.tag != null) {
                val position = itemView.tag as Int
                if (movieCarouselItemList != null && movieCarouselItemList.size > position) {
                    setData(itemView,position)
                    return position
                }
            }
        }

        return POSITION_NONE
    }

    fun refreshImageCarousel(imageList: List<String>) {
        with(movieCarouselItemList) {
            clear()
            addAll(imageList)
        }
        this.notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

            val itemView: View = LayoutInflater.from(container.context)
                .inflate(R.layout.viewpager_layout, container, false)
            itemView.tag = position
            setData(itemView,position)
            container.addView(itemView)
            return itemView
    }

    fun setData(itemView:View, position:Int){
        val ivMovie=itemView.findViewById<ImageView>(R.id.iv_movie)
        if(!movieCarouselItemList.isNullOrEmpty()) {
            context?.let {
                Glide.with(it).load(movieCarouselItemList.get(position))
                    .error(R.color.itemBackgroundNight).into(ivMovie)
            }
        }
    }

}