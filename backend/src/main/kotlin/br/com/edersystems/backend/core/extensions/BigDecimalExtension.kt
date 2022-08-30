/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 29/06/2022 19:05:31
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.core.extensions

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.Objects

const val TWO = 2
const val THREE = 3
const val FOUR = 4
const val HUNDRED = 100

fun BigDecimal.setScaleTwo(): BigDecimal = this.setScale(TWO, RoundingMode.HALF_EVEN)

fun BigDecimal.setScaleThree(): BigDecimal = this.setScale(THREE, RoundingMode.HALF_EVEN)

fun BigDecimal.setScaleFour(): BigDecimal = this.setScale(FOUR, RoundingMode.HALF_EVEN)

fun BigDecimal.moneyFormat(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    return format.format(this)
}

fun BigDecimal.putZero(): String {
    val stringNumberToReturn = StringBuilder()
    when {
        this.toInt() < BigDecimal.TEN.toInt() -> {
            stringNumberToReturn.append("00${this.toInt()}")
        }

        ((this.toInt() >= BigDecimal.TEN.toInt()) && (this.toInt() < HUNDRED)) -> {
            stringNumberToReturn.append("0${this.toInt()}")
        }

        else -> {
            stringNumberToReturn.append(this.toInt())
        }
    }

    return stringNumberToReturn.toString()
}

fun BigDecimal.isValid() = (Objects.nonNull(this) && (this.compareTo(BigDecimal.ZERO) > BigDecimal.ZERO.toInt()))

fun BigDecimal.isInvalid() = this.isValid().not()
