package io.legado.app.ui.widget.number

import android.content.Context
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import io.legado.app.R
import io.legado.app.utils.applyTint
import io.legado.app.utils.hideSoftInput
import kotlinx.android.synthetic.main.dialog_number_picker.*


class NumberPickerDialog(context: Context) {
    private val builder = AlertDialog.Builder(context)
    private var numberPicker: NumberPicker? = null
    private var maxValue: Int? = null
    private var minValue: Int? = null
    private var value: Int? = null

    init {
        builder.setView(R.layout.dialog_number_picker)
    }

    fun setTitle(title: String): NumberPickerDialog {
        builder.setTitle(title)
        return this
    }

    fun setMaxValue(value: Int): NumberPickerDialog {
        maxValue = value
        return this
    }

    fun setMinValue(value: Int): NumberPickerDialog {
        minValue = value
        return this
    }

    fun setValue(value: Int): NumberPickerDialog {
        this.value = value
        return this
    }

    fun show(callBack: ((value: Int) -> Unit)?) {
        builder.setPositiveButton(R.string.ok) { _, _ ->
            numberPicker?.let {
                it.clearFocus()
                it.hideSoftInput()
                callBack?.invoke(it.value)
            }
        }
        builder.setNegativeButton(R.string.cancel, null)
        val dialog = builder.show().applyTint()
        numberPicker = dialog.number_picker
        numberPicker?.let { np ->
            minValue?.let {
                np.minValue = it
            }
            maxValue?.let {
                np.maxValue = it
            }
            value?.let {
                np.value = it
            }
        }
    }
}