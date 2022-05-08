package com.example.skullwarrior.hevertip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HeverTipViewModel : ViewModel() {
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





    fun onClickCalculate(amount: Int, tipPercents: Int, numOfPayers: Int): List<Boolean> {
        val tipInt: Int = (amount * tipPercents / 100)
        val total: Int = (amount * 0.7 + tipInt).toInt()
        val per: Int = total / numOfPayers
        val nonHeverInt: Int = tipInt / per
        val splitter = (tipInt % per != 0).toInt()

        _tip.value = tipInt.toString()
        _perHever.value = (per * 10 / 7).toString()
        _perCard.value = per.toString()
        _nonHever.value = nonHeverInt.toString()
        _hevers.value = (numOfPayers - nonHeverInt - splitter).toString()
        _heverSplit.value = (amount - (numOfPayers - nonHeverInt - splitter) * (per * 10 / 7)).toString()
        _cardSplit.value = (tipInt % per).toString()

        return listOf(nonHeverInt.toBoolean(), splitter.toBoolean())
    }
}

fun Boolean.toInt() = if (this) 1 else 0
fun Int.toBoolean() = this != 0
