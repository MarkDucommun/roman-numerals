package io.ducommun.kata.romannumerals

fun convert(value: List<RomanNumeral>): Result<Unit, Int> {

    val validNumeral = value
            .fold(::start.asSuccess) { holderResult, current ->
                when (holderResult) {
                    is Success -> holderResult.value.comparer(current)
                    is Failure -> holderResult
                }
            }

    return if (validNumeral is Success) {
        value
                .map { it.value }
                .fold(ValueHolder()) { holder, digitValue ->
                    if (digitValue > holder.previousDigitValue) {
                        digitValue - (holder.previousDigitValue * 2)
                    } else {
                        digitValue
                    }.let { holder.copy(previousDigitValue = digitValue, value = holder.value + it) }
                }
                .value
                .let(::Success)
    } else {
        Failure(Unit)
    }
}

data class CompareHolder(val comparer: (RomanNumeral) -> Result<Unit, CompareHolder>)

data class ValueHolder(
        val previousDigitValue: Int = 0,
        val value: Int = 0
)

fun convert(vararg value: RomanNumeral): Result<Unit, Int> = convert(value.toList())

val (RomanNumeral.() -> Result<Unit, CompareHolder>).asSuccess: Result<Unit, CompareHolder>
    get() = Success<Unit, CompareHolder>(CompareHolder(comparer = this))

fun start(romanNumeral: RomanNumeral): Result<Unit, CompareHolder> {

    return when (romanNumeral) {
        I -> ::iB.asSuccess
        V -> ::v.asSuccess
        X -> ::x.asSuccess
        L -> ::l.asSuccess
        C -> ::c.asSuccess
        D -> ::d.asSuccess
        M -> ::m.asSuccess
        Start -> Failure(Unit)
    }
}

fun iA(romanNumeral: RomanNumeral): Result<Unit, CompareHolder> {

    return when (romanNumeral) {
        I -> ::iB.asSuccess
        V -> ::v.asSuccess
        X -> ::x.asSuccess
        L -> Failure(Unit)
        C -> Failure(Unit)
        D -> Failure(Unit)
        M -> Failure(Unit)
        Start -> Failure(Unit)
    }
}

fun iB(romanNumeral: RomanNumeral): Result<Unit, CompareHolder> {

    return when (romanNumeral) {
        I -> ::iC.asSuccess
        V -> ::v.asSuccess
        X -> ::x.asSuccess
        L -> Failure(Unit)
        C -> Failure(Unit)
        D -> Failure(Unit)
        M -> Failure(Unit)
        Start -> Failure(Unit)
    }
}

fun iC(romanNumeral: RomanNumeral): Result<Unit, CompareHolder> {

    return when (romanNumeral) {
        I -> ::iC.asSuccess
        V -> Failure(Unit)
        X -> Failure(Unit)
        L -> Failure(Unit)
        C -> Failure(Unit)
        D -> Failure(Unit)
        M -> Failure(Unit)
        Start -> Failure(Unit)
    }
}

fun v(romanNumeral: RomanNumeral): Result<Unit, CompareHolder> {

    return when (romanNumeral) {
        I -> ::iB.asSuccess
        V -> Failure(Unit)
        X -> Failure(Unit)
        L -> Failure(Unit)
        C -> Failure(Unit)
        D -> Failure(Unit)
        M -> Failure(Unit)
        Start -> Failure(Unit)
    }
}


fun x(romanNumeral: RomanNumeral): Result<Unit, CompareHolder> {

    return when (romanNumeral) {
        I -> ::iB.asSuccess
        V -> ::v.asSuccess
        X -> ::x.asSuccess
        L -> ::l.asSuccess
        C -> ::c.asSuccess
        D -> ::d.asSuccess
        M -> ::m.asSuccess
        Start -> Failure(Unit)
    }
}

fun l(romanNumeral: RomanNumeral): Result<Unit, CompareHolder> {

    return when (romanNumeral) {
        I -> ::iB.asSuccess
        V -> ::v.asSuccess
        X -> ::x.asSuccess
        L -> Failure(Unit)
        C -> Failure(Unit)
        D -> Failure(Unit)
        M -> Failure(Unit)
        Start -> Failure(Unit)
    }
}

fun c(romanNumeral: RomanNumeral): Result<Unit, CompareHolder> {

    return when (romanNumeral) {
        I -> ::iB.asSuccess
        V -> ::v.asSuccess
        X -> ::x.asSuccess
        L -> ::l.asSuccess
        C -> ::c.asSuccess
        D -> ::d.asSuccess
        M -> ::m.asSuccess
        Start -> Failure(Unit)
    }
}


fun d(romanNumeral: RomanNumeral): Result<Unit, CompareHolder> {

    return when (romanNumeral) {
        I -> ::iB.asSuccess
        V -> ::v.asSuccess
        X -> ::x.asSuccess
        L -> ::l.asSuccess
        C -> ::c.asSuccess
        D -> Failure(Unit)
        M -> Failure(Unit)
        Start -> Failure(Unit)
    }
}

fun m(romanNumeral: RomanNumeral): Result<Unit, CompareHolder> {

    return when (romanNumeral) {
        I -> ::iB.asSuccess
        V -> ::v.asSuccess
        X -> ::x.asSuccess
        L -> ::l.asSuccess
        C -> ::c.asSuccess
        D -> ::d.asSuccess
        M -> ::m.asSuccess
        Start -> Failure(Unit)
    }
}