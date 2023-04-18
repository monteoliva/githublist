package br.com.monteoliva.githublist.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

import br.com.monteoliva.githublist.R

class Progress(context: Context?, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var view: View? = null
    private var textView: TextView? = null

    init {
        initViews()
    }

    private fun initViews() {
        context?.let { ctx ->
            setBackgroundColor(ContextCompat.getColor(ctx, android.R.color.transparent))
            (ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).also {
                it.inflate(R.layout.progress, this).also { view ->
                    this.view = view
                    textView  = view.findViewById(R.id.txtProgress)
                }
            }
        }
    }

    fun show() { view?.visibility = View.VISIBLE }
    fun hide() { view?.visibility = View.GONE    }

    fun setText(msg: String)         { textView?.text = msg }
    fun setText(@StringRes msg: Int) { textView?.text = context.getString(msg) }
}