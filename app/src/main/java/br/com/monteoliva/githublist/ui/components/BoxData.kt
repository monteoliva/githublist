package br.com.monteoliva.githublist.ui.components

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

import br.com.monteoliva.githublist.R

class BoxData(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private lateinit var view: View
    private lateinit var txtValue: TextView

    init {
        initViews(context, attrs)
    }

    private fun initViews(context: Context, attrs: AttributeSet) {
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.BoxData);

        val titleTxt: String? = a.getString(R.styleable.BoxData_titleTxt)

        a.recycle()

        view = inflater.inflate(R.layout.box_data, this)
        view.apply {
            txtValue = findViewById(R.id.txtValue)
            findViewById<TextView>(R.id.txtLabel).text = titleTxt
        }
    }

    fun show() { view.visibility = View.VISIBLE }
    fun hide() { view.visibility = View.GONE    }

    fun setValue(msg: String) { txtValue.text = msg }
    fun setValue(msg: Int)    { txtValue.text = context.getString(msg) }
}