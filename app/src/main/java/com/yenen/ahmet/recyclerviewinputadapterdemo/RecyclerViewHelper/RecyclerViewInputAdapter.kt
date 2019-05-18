package com.yenen.ahmet.recyclerviewinputadapterdemo.RecyclerViewHelper

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AbsListView
import androidx.recyclerview.widget.RecyclerView
import java.util.Timer
import java.util.TimerTask

abstract class RecyclerViewInputAdapter<T, E : RecyclerView.ViewHolder>
protected constructor(items: MutableList<T>, protected val activity: Activity,private val viewToken: RecyclerView) :
    BaseRecyclerViewAdapter<T, E>(items), TextWatcher, View.OnFocusChangeListener {

    // Listener Variable //
    protected var textWatcher: TextWatcher? = null
    protected var focusChangeListener: View.OnFocusChangeListener? = null
    // Listener Variable //

    // Class Variable //
    private val changeDelay: Long = 500
    private var timer: Timer? = null
    private var focusPosition: Int = -1
    private var focusView: View? = null
    private var isScroll: Boolean = false
    private val inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    // Class Variable //

    // constructor init //
    init {
        this.textWatcher = this
        this.focusChangeListener = this
        scrollListener()
    }
    // constructor init //

    // Text && Focus listener //
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        // ignore
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        clearTimer()
    }

    override fun afterTextChanged(s: Editable) {
        clearTimer()
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                activity.runOnUiThread {
                    runOnChange(focusPosition, focusView)
                    hideKeybord()
                }
            }
        }, changeDelay)
    }

    override fun onFocusChange(v: View, hasFocus: Boolean) {
        clearTimer()
        if (hasFocus) {
            focusView = v
            focusPosition = v.tag as Int
        } else {
            focusView = null
            runOnChange(v.tag as Int, v)
        }
    }
    // Text && Focus listener //

    // RecyclerView Scroll listener //
    private fun scrollListener() {
        viewToken.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                clearTimer()
                if (inputManager.isActive) {
                    clearFocusView()
                    hideKeybord()
                }
                isScroll = newState != AbsListView.OnScrollListener.SCROLL_STATE_IDLE
            }
        })
    }
    // RecyclerView Scroll listener //

    // Class Fun //
    private fun runOnChange(position: Int, view: View?) {
        clearTimer()
        val item = getItem(position)
        if (position>-1 && item != null && view != null && !isScroll) {
            onChange(position, view, item)
        }
    }

    fun hideKeybord() {
        inputManager.hideSoftInputFromWindow(viewToken.windowToken, 0)
    }

    fun unBind() {
        focusChangeListener = null
        textWatcher = null
        clearTimer()
        timer = null
        focusView = null
        clearItems()
    }

    private fun clearTimer() {
        timer?.cancel()
        timer?.purge()
    }

    fun clearFocusView() {
        focusView?.clearFocus()
    }
    // Class Fun //

    //  Abstract fun //
    protected abstract fun onChange(position: Int, view: View, item: T)
    //  Abstract fun //
}