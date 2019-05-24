package com.example.spbook.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.example.spbook.R
import com.example.spbook.entities.POJO.Place
import kotlinx.android.synthetic.main.search_layout.view.*
import java.util.regex.Pattern

@SuppressLint("ResourceType")
open class CustomAdapter(context: Context): ArrayAdapter<Place>(context,23){

    private val allPlaces = HashSet<Place>()


    fun addList(list:ArrayList<Place>){
        allPlaces.addAll(list)
    }

    private val filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): Filter.FilterResults {
            val results = Filter.FilterResults()
            val filterPlaces = java.util.ArrayList<Place>()
            if (constraint == null || constraint.isEmpty())
                filterPlaces.clear()
            else {
                val pattern = Pattern.compile("[«\"]?" + constraint.toString().toLowerCase())
                for (place in allPlaces) {
                    val matcher = pattern.matcher(place.getAddressAndName().toLowerCase())
                    if (matcher.find()) filterPlaces.add(place)
                }
            }
            results.values = filterPlaces
            results.count = filterPlaces.size

            return results
        }

        override fun publishResults(constraint: CharSequence, results: Filter.FilterResults) {
            clear()
            this@CustomAdapter.addAll(results.values as List<Place>)
            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any): CharSequence {
            return resultValue as String
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var listView = convertView
        if (convertView == null) {
            listView = LayoutInflater.from(context).inflate(R.layout.search_layout, parent, false)
        }
        val place = getItem(position)

        listView!!.adress_place.text = place!!.address
        listView.name_place.text = place.name
        return listView
    }

    override fun getFilter(): Filter {
        return filter
    }
}


