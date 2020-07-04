package com.example.rxjavaretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rxjavaretrofit.R
import com.example.rxjavaretrofit.objectData.HotelList

class HotelsAdapter(val hotels: List<HotelList.Data>): RecyclerView.Adapter<HotelsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hotels_item, parent, false)
        return HotelsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hotels.size
    }

    override fun onBindViewHolder(holder: HotelsViewHolder, position: Int) {
        return holder.bind(hotels[position])
    }
}

class HotelsViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val hotelPhoto: ImageView = itemView.findViewById(R.id.hotel_photo)
    private val hotelName: TextView = itemView.findViewById(R.id.hotel_name)
    private val isOpened: TextView = itemView.findViewById(R.id.is_open)
    private val hotelRating: TextView = itemView.findViewById(R.id.hotel_rating)
    private val hotelRanking: TextView = itemView.findViewById(R.id.hotel_ranking)
    private val cardHotel: CardView = itemView.findViewById(R.id.cardHotel)

    fun bind(hotel: HotelList.Data) {
        Glide
            .with(itemView.context)
            .load({hotel.photo.images.medium.url})
            .into(hotelPhoto)
        hotelName.text = "Title: "+hotel.name
        isOpened.text = "Closed: "+hotel.is_closed
        hotelRating.text = "Rating : "+hotel.rating
        hotelRanking.text = "Ranking : "+hotel.ranking

//        cardHotel.setOnClickListener {

//           navigation with Bundle

//            val bundle = Bundle()
//            bundle.putInt("movie_id", movie.id)
//            itemView.findNavController().navigate(R.id.detailScreen, bundle)

//           navigation with safeArgs

//            itemView.findNavController().navigate(
//                MainScreenDirections.actionMainScreenToDetailScreen(
//                    hotel.id
//                )
//            )
//        }
    }
}