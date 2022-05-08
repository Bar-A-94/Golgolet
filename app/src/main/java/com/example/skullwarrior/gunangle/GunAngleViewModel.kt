package com.example.skullwarrior.gunangle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.round

class GunAngleViewModel : ViewModel() {
    // Set holders for true airspeed for each plain
    private var tasShooterSpeed: Float = 0F
    private var tasVictimSpeed: Float = 0F

    // Set variables for texts in the fragment
    private val _angle = MutableLiveData<String>()
    val angle: LiveData<String>
        get() = _angle
    val angleVisible = Transformations.map(angle) { it != null }

    /**
     * When calculate pressed, the function calculate the angel between
     * the two airplains according to their speed, closing speed and altitude
     */
    fun onClickCalculate(
        shooterSpeed: Float,
        victimSpeed: Float,
        altitude: Float,
        closingSpeed: Float
    ): Boolean {
        // find TAS for the shooter
        tasShooterSpeed = if (shooterSpeed < 2) {
            shooterSpeed * 630
        } else {
            shooterSpeed + altitude * 5 / 1000
        }
        // find TAS for the victim
        tasVictimSpeed = if (victimSpeed < 2) {
            victimSpeed * 630
        } else {
            victimSpeed + altitude * 5 / 1000
        }
        // calculate the angle according to trigonometric
        val aCosInside: Float = (closingSpeed - tasShooterSpeed) / tasVictimSpeed
        val rad = acos(aCosInside)
        _angle.value = (round((180 - rad * 180 / PI) * 100) / 100).toString()
        return ((round((180 - rad * 180 / PI) * 100) / 100) > 90)
    }
}