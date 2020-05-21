package com.annaalfiani.gmcapps.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.annaalfiani.gmcapps.Adapter.SlidePagerAdapter
import com.annaalfiani.gmcapps.models.Slide
import com.annaalfiani.gmcapps.R

class HomeActivity : AppCompatActivity() {

    var lstSlide:MutableList<Slide> = ArrayList()
    var slidePage:ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        slidePage = this.findViewById(R.id.slider_pager)

        lstSlide.add(
            Slide(
                R.drawable.posterbumimanusia,
                "Slide Title 1"
            )
        )
        lstSlide.add(
            Slide(
                R.drawable.postermariposa,
                "Slide Title 2"
            )
        )
        lstSlide.add(
            Slide(
                R.drawable.posterparasite,
                "Slide Title 3"
            )
        )

        var adapter : SlidePagerAdapter = SlidePagerAdapter(this, lstSlide)
        slidePage!!.adapter = adapter

//        Thread(Runnable {
//            if(Utilities.getToken(this@HomeActivity) == null){
//                startActivity(Intent(this@HomeActivity,Sign_inActivity::class.java))
//                finish()
//            }
//         }).start()
    }

}

private fun <E> MutableList<E>.add(index: Int, element: String) {

}
