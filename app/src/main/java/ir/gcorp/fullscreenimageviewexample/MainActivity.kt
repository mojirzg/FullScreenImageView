package ir.gcorp.fullscreenimageviewexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ir.gcorp.fullscreenimageview.FullScreenImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = arrayListOf("https://www.bluebellgray.com/media/catalog/product/cache/1/image/1200x/040ec09b1e35df139433887a97daa66f/b/o/botanical-lifestyle-1500_1.jpg",
            "https://images.homedepot-static.com/productImages/58347570-da34-4348-b45d-e79611241859/svn/beacon-house-wallpaper-2535-20647sam-64_1000.jpg",
            "https://www.ilovewallpaper.co.uk/images/sample-zara-shimmer-metallic-wallpaper-charcoal-copper-ilw980112-sample-p4931-13219_image.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHlqAnHlpnaBobf91MCBubpAws1DQ85sv4hfLSn1udrM3MBwBF")

        FullScreenImageView.show(this,images)
    }
}
