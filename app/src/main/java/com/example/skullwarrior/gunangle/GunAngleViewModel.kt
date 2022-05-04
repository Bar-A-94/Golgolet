package com.example.skullwarrior.gunangle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.round

class GunAngleViewModel : ViewModel() {
    private var tasShooterSpeed: Float = 0F
    private var tasVictimSpeed: Float = 0F
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

        tasShooterSpeed = if (shooterSpeed < 2) {
            shooterSpeed * 630
        } else {
            shooterSpeed + altitude * 5 / 1000
        }
        tasVictimSpeed = if (victimSpeed < 2) {
            victimSpeed * 630
        } else {
            victimSpeed + altitude * 5 / 1000
        }
        val aCosInside: Float = (closingSpeed - tasShooterSpeed) / tasVictimSpeed
        val rad = acos(aCosInside)
        _angle.value = (round((180 - rad * 180 / PI) * 100) / 100).toString()
    }
}