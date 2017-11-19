package io.ducommun.kata.romannumerals

class RomanNumeralConverterTest : RomanNumeralConverterTestBase {

    override val `roman to arabic successes` = listOf(
            numeral(I) to 1,
            numeral(I, I) to 2,
            numeral(I, I, I) to 3,
            numeral(I, V) to 4 because "it subtracts the value of a smaller numeral before a larger numeral",
            numeral(I, X) to 9,
            numeral(X, V, I, I, I) to 18,
            numeral(M, X, L, I, I) to 1042,
            numeral(C, M, X, C) to 990
    )

    override val `roman to arabic failures` = listOf(
            numeral(I, I, V) to Unit because "a value may never be preceded by two values smaller than it",
            numeral(I, X, I, V) to Unit because "doesnt work with 9 + 4",
            numeral(V, V) to Unit because "cant add two numbers together that make another number",
            numeral(L, L) to Unit because "cant add two numbers together that make another number",
            numeral(D, D) to Unit because "cant add two numbers together that make another number"
    )
}

