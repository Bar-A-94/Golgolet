package com.example.skullwarrior.hevertip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HeverTipViewModel : ViewModel() {
    // Set variables for texts in the fragment
    private var _tip = MutableLiveData<String>()
    val tip: LiveData<String>
        get() = _tip
    private var _perHever = MutableLiveData<String>()
    val perHever: LiveData<String>
        get() = _perHever
    private var _perCard = MutableLiveData<String>()
    val perCard: LiveData<String>
        get() = _perCard
    private var _nonHever = MutableLiveData<String>()
    val nonHever: LiveData<String>
        get() = _nonHever
    private var _hevers = MutableLiveData<String>()
    val hevers: LiveData<String>
        get() = _hevers
    private var _heverSplit = MutableLiveData<String>()
    val heverSplit: LiveData<String>
        get() = _heverSplit
    private var _cardSplit = MutableLiveData<String>()
    val cardSplit: LiveData<String>
        get() = _cardSplit

    /**
     * When calculate pressed, the function takes amount, tip percents and number of payers
     * and calculate the amount and form that each needs to pay
     */
    fun onClickCalculate(amount: Int, tipPercents: Int, numOfPayers: Int): List<Boolean> {
        // The amount of tip
        val tipInt: Int = (amount * tipPercents / 100)
        // Total true money value needed to pay
        val total: Int = (amount * 0.7 + tipInt).toInt()
        // True money value for each payer
        val per: Int = total / numOfPayers
        // The number of payers that will use only their credit card
        val nonHeverInt: Int = tipInt / per
        // Check if someone needs to split credit card and Hever card
        val splitter = (tipInt % per != 0).toInt()

        _tip.value = tipInt.toString()
        // The amount to pay per Hever card
        _perHever.value = (per * 10 / 7).toString()
        // The amount to pay per credit card
        _perCard.value = per.toString()
        _nonHever.value = nonHeverInt.toString()
        // The number of payers that will use only their Hever card
        _hevers.value = (numOfPayers - nonHeverInt - splitter).toString()
        // Calculate the splitter amount per card
        _heverSplit.value =
            (amount - (numOfPayers - nonHeverInt - splitter) * (per * 10 / 7)).toString()
        _cardSplit.value = (tipInt % per).toString()

        // Return booleans to make some text visible/invisible
        return listOf(nonHeverInt.toBoolean(), splitter.toBoolean())
    }
}

/**
 * convert boolean to int true = 1, false = 0
 */
fun Boolean.toInt() = if (this) 1 else 0

/**
 * convert int to boolean 0 = false, else = true
 */
fun Int.toBoolean() = this != 0
