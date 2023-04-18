package br.com.monteoliva.githublist.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

import br.com.monteoliva.githublist.R

class BoxData(context: Context?, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var view: View? = null
    private var txtValue: TextView? = null

    init {
        initViews(attrs)
    }

    private fun initViews(attrs: AttributeSet) {
        var titleTxt: String?
        context?.let { ctx ->
            setBackgroundColor(ContextCompat.getColor(ctx, android.R.color.transparent))
            ctx.obtainStyledAttributes(attrs, R.styleable.BoxData).also {
                titleTxt = it.getString(R.styleable.BoxData_titleTxt)
                it.recycle()
            }
            (ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).also {
                it.inflate(R.layout.box_data, this).also { view ->
                    this.view = view
                    view.apply {
                        txtValue = findViewById(R.id.txtValue)
                        findViewById<TextView>(R.id.txtLabel).text = titleTxt
                    }
                }
            }
        }
    }

    fun show() { view?.visibility = View.VISIBLE }
    fun hide() { view?.visibility = View.GONE    }

    fun setValue(msg: String) { txtValue?.text = msg }
    fun setValue(msg: Int)    { txtValue?.text = context.getString(msg) }
}