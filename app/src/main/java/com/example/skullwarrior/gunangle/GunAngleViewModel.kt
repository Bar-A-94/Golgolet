package com.example.skullwarrior.gunangle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.round

class GunAngleViewModel : ViewModel() {
    var TASShooterSpeed: Float = 0F
    var TASVictimSpeed: Float = 0F
    private val _angle = MutableLiveData<String>()
    val angle: LiveData<String>
        get() = _angle
    val angleVisible = Transformations.map(angle) {
        it != null
    }

    fun onClickCalculate(
        shooterSpeed: Float,
        victimSpeed: Float,
        altitude: Float,
        closingSpeed: Float
    ) {

        if (shooterSpeed < 2) {
            TASShooterSpeed = shooterSpeed * 630
        } else {
            TASShooterSpeed = shooterSpeed + altitude * 5 / 1000
        }
        if (victimSpeed < 2) {
            TASVictimSpeed = victimSpeed * 630
        } else {
            TASVictimSpeed = victimSpeed + altitude * 5 / 1000
        }
        val aCosInside: Float = (closingSpeed - TASShooterSpeed) / TASVictimSpeed
        val rad = acos(aCosInside)
        _angle.value = (round((180 - rad * 180 / PI) * 100) / 100).toString()
    }
}