package io.ducommun.kata.romannumerals

import org.assertj.core.api.KotlinAssertions
import org.junit.Assert


infix fun <fail, success> Result<fail, success>.succeedsAnd(fn: (success) -> Unit): Unit {
    when (this) {
        is Success -> fn(this.value)
        is Failure -> Assert.fail("Should have succeeded")
    }
}

infix fun <fail, success> Result<fail, success>.succeedsAndShouldReturn(expected: success): Unit {
    this.succeedsAnd { KotlinAssertions.assertThat(it).isEqualTo(expected) }
}

infix fun <fail, success> Result<fail, success>.failsAnd(fn: (fail) -> Unit): Unit {
    when (this) {
        is Success -> Assert.fail("Should have failed")
        is Failure -> fn(this.value)
    }
}

infix fun <fail, success> Result<fail, success>.failsAndShouldReturn(expected: fail): Unit {
    this.failsAnd { KotlinAssertions.assertThat(it).isEqualTo(expected) }
}