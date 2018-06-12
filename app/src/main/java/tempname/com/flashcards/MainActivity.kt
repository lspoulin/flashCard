package tempname.com.flashcards

import android.content.Context
import android.database.DataSetObserver
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView



public class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        setContentView(R.layout.activity_main)

        val lv = findViewById<ListView>(R.id.mainList)
        val adapter = ListExampleAdapter(this)
        lv.adapter = adapter

        adapter.setList(arrayOf("Metalica", "Greeday", "Nirvana"))
        
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            adapter.add("Red hot chilli pepper")
        }
    }

    private class ListExampleAdapter(context: Context) : BaseAdapter() {
        private var sList = arrayOf("Sunday", "Monday")

        fun setList(list:Array<String>){
            sList = list
            this.notifyDataSetChanged()
        }

        fun getList():Array<String>{
            return sList
        }

        fun add(value:String){
            sList+=value
            this.notifyDataSetChanged()
        }

        private val mInflator: LayoutInflater

        init {
            this.mInflator = LayoutInflater.from(context)
        }

        override fun getCount(): Int {
            return sList.size
        }

        override fun getItem(position: Int): Any {
            return sList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            val view: View?
            val vh: ListRowHolder
            if (convertView == null) {
                view = this.mInflator.inflate(R.layout.list_row, parent, false)
                vh = ListRowHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }

            vh.label.text = sList[position]
            return view
        }
    }

    private class ListRowHolder(row: View?) {
        public val label: TextView

        init {
            this.label = row?.findViewById<TextView>(R.id.label)!!
        }
    }
}


