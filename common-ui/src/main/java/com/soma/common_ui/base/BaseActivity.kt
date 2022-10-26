package com.soma.common_ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
) : AppCompatActivity() {

    protected val binding: T by lazy {
        DataBindingUtil.setContentView(this, layoutId)
    }

    init {
        addOnContextAvailableListener {
            binding.notifyChange()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }

    protected inline fun bind(block: T.() -> Unit) {
        binding.apply(block)
    }

    protected fun shortShowToast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    protected fun longShowToast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}