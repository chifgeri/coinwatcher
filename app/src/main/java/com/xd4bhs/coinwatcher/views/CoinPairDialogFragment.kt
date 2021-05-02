package com.xd4bhs.coinwatcher.views

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.xd4bhs.coinwatcher.R
import com.xd4bhs.coinwatcher.data.database.entities.CurrencyPair

class CoinPairDialogFragment(private val title: String, private val positiveIconText: String ): DialogFragment() {
    lateinit var listener: CoinPairDialogListener

    interface CoinPairDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment, coinPair: CurrencyPair)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Build the dialog and set up the button click handlers
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater;
            val view = inflater.inflate(R.layout.fragment_coin_add_edit, null)

            val tvTitle: TextView = view.findViewById(R.id.tvFragmentTitle)
            val editTicker: EditText = view.findViewById(R.id.editTicker)
            val editPrice: EditText = view.findViewById(R.id.editPrice)
            val editMarketCap: EditText = view.findViewById(R.id.editMarketCap)
            val editVsCurr: EditText = view.findViewById(R.id.editVsCurrency)

            tvTitle.text = title


            builder.setView(view)
                    .setPositiveButton(positiveIconText,
                            DialogInterface.OnClickListener { dialog, id ->
                                // Send the positive button event back to the host activity
                                val price = editPrice.text.toString().toDouble()
                                listener.onDialogPositiveClick(this,
                                CurrencyPair(
                                        id = editTicker.text.toString(),
                                        vsCurrency = editVsCurr.text.toString(),
                                        ticker = editTicker.text.toString().toUpperCase(),
                                        price = price,
                                        marketCap = editMarketCap.text.toString().toDouble(),
                                        totalVolume = price*1_000_000
                                        )
                                )
                            })
                    .setNegativeButton(getString(R.string.cancel),
                            DialogInterface.OnClickListener { dialog, id ->
                                // Send the negative button event back to the host activity
                                listener.onDialogNegativeClick(this)
                            })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as CoinPairDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement CoinPairDialoggListener"))
        }
    }


}