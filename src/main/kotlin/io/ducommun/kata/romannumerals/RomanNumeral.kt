package io.ducommun.kata.romannumerals

sealed class RomanNumeral(val value: Int) : Comparable<RomanNumeral> {

    override fun toString(): String {
        return this::class.simpleName!!
    }

    override fun compareTo(other: RomanNumeral): Int = value.compareTo(other.value)
}

object I : RomanNumeral(1)
object V : RomanNumeral(5)
object X : RomanNumeral(10)
object L : RomanNumeral(50)
object C : RomanNumeral(100)
object D : RomanNumeral(500)
object M : RomanNumeral(1000)
object Start : RomanNumeral(-1)