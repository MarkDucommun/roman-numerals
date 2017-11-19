package io.ducommun.kata.romannumerals

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.DynamicTest.dynamicTest

interface RomanNumeralConverterTestBase {

    val `roman to arabic successes`: List<RomanNumeralTestCase>

    val `roman to arabic failures`: List<RomanNumeralErrorCase<Unit>>

    @TestFactory
    fun `roman numeral to arabic successes`(): List<DynamicTest> {
        1..2
        return `roman to arabic successes`.map { dynamicTest(it.testName) { convert(it.numeral) succeedsAndShouldReturn it.expected } }
    }

    @TestFactory
    fun `roman numeral to arabic failures`(): List<DynamicTest> {
        return `roman to arabic failures`.map { dynamicTest(it.testName) { convert(it.numeral) failsAndShouldReturn  it.expectedFailure } }
    }

    data class RomanNumeralTestCase(
            val numeral: RomanNumeralExpression,
            val expected: Int,
            val description: String? = null
    ) {
        val testName: String = description ?: "it converts $numeral to $expected"
    }

    data class RomanNumeralErrorCase<out failure>(
            val numeral: RomanNumeralExpression,
            val expectedFailure: failure,
            val description: String? = null
    ) {
        val testName: String = description ?: "it fails to convert $numeral and throws a ${expectedFailure} instead"
    }

    data class RomanNumeralExpression(
            val list: List<RomanNumeral>
    ) : List<RomanNumeral> by list {

        override fun toString(): String = map { it.toString() }.joinToString("")
    }

    infix fun RomanNumeralExpression.to(expected: Int) = RomanNumeralTestCase(numeral = this, expected = expected)

    infix fun <failure> RomanNumeralExpression.to(expected: failure) = RomanNumeralErrorCase(numeral = this, expectedFailure = expected)

    infix fun RomanNumeralTestCase.because(description: String) = copy(description = description)

    infix fun <failure> RomanNumeralErrorCase<failure>.because(description: String) = copy(description = description)

    fun numeral(vararg chars: RomanNumeral): RomanNumeralExpression = chars.toList().let { RomanNumeralExpression(it) }
}