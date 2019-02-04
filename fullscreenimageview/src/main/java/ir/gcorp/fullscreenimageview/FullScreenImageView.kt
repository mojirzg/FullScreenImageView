package ir.gcorp.fullscreenimageview

import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cardview_picturs.view.*
import kotlinx.android.synthetic.main.fragment_full_screen_image_view.view.*


class FullScreenImageView : DialogFragment(){

    private val picturesKey = "PICTURES"

    private lateinit var count : TextView
    private lateinit var imageView : ImageView

    private lateinit var pictures : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pictures = it.getStringArrayList(picturesKey)!!
        }
        setStyle(DialogFragment.STYLE_NORMAL,
            android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    override fun onResume() {
        // Store access variables for window and blank point
        val window = dialog.window!!
        val size = Point()
        // Store dimensions of the screen in `size`
        val display = window.windowManager.defaultDisplay
        display.getSize(size)
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout(size.x, size.y )
        window.setGravity(Gravity.CENTER)
        super.onResume()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_full_screen_image_view, container, false)

        count = view.count
        imageView = view.imageView

        setImage(pictures[0])


        view.recyclerView.let {
            it.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            it.adapter = RecAdapter(pictures)
        }


        return view
    }

    @SuppressLint("SetTextI18n")
    private fun changeCount(number : Int = 0){
        count.text = "${number+1} - ${pictures.size}"
    }

    private fun setImage(url : String){

        Glide.with(context!!)
            .load(url)
            .into(imageView)

        changeCount(pictures.indexOf(url))
    }


    inner class RecAdapter(private val pictures: ArrayList<String>) : RecyclerView.Adapter<RecAdapter.CustomViewHolder>() {

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder = CustomViewHolder(layoutInflater.inflate(R.layout.cardview_picturs,p0,false))
        override fun getItemCount(): Int = pictures.size
        override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) = p0.onBind()

        inner class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

            private val image : ImageView = itemView.image

            fun onBind() {

                Glide.with(itemView)
                    .load(pictures[layoutPosition])
                    .into(image)

                image.setOnClickListener {
                    setImage(pictures[layoutPosition])
                }


            }

        }

    }



    companion object {

        private fun newInstance(pictures: ArrayList<String>): FullScreenImageView =
            FullScreenImageView().apply {
                arguments = Bundle().apply {
                    putStringArrayList(picturesKey, pictures)
                }
            }


        @JvmStatic
        fun show(activity : AppCompatActivity,pictures: ArrayList<String>) {
            val fm = activity.supportFragmentManager
            val sellerDialog = FullScreenImageView.newInstance(pictures)
            sellerDialog.show(fm, "FullScreenImageView")
        }

    }


}

