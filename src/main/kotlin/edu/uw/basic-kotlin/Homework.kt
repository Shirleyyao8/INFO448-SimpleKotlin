package edu.uw.basickotlin

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String

// If it is "Hello", return "world"
// If it is any other string, return "I don't understand"
// If it is 0, return "zero"
// If it is 1, return "one"
// If it is any number between 2 and 10, return "low number"
// If it is any other number, return "a number"
// Otherwise, return "I don't understand"
fun whenFn(value: Any): String {
    if (value is String) {
        if (value == "Hello") {   
            return "world"
        } else if (value == "Howdy" || value == "Bonjour") {
            return "Say what?"
        }
    } else if (value is Number) {
        if (value == 0) {
            return "zero";
        } else if (value == 1) {
            return "one"
        } else if (value in 2..10) {
            return "low number"
        } 
    } 
        return "I don't understand"
} 


// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(a: Int, b: Int): Int {
    return a + b;
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(a: Int, b: Int): Int {
    return a - b;
}
// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    // Call the passed-in function with the two integer arguments
    return operation(a, b);
}

// typealias Function = (Int, Int) -> Int

// fun mathOp(a: Int, b: Int, function: Function): Int {
//     return function(a,b)
// }

// write a class "Person" with first name, last name and age
class Person(var firstName: String, val lastName: String, var age: Int) {
    val debugString: String
        get() = "[Person firstName:${firstName} lastName:${lastName} age:${age}]"

    // init {
    //     return "Person firstName:${firstName} lastName:${lastName} age:${age}"
    // }
}

// write a class "Money" with amount and currency, and define a convert() method and the "+" operator
class Money(val amount: Int, val currency: String) {
    init {
        if (amount < 0) {
            throw IllegalArgumentException("Amount cannot be less than zero")
        }
        if (currency !in setOf("USD", "EUR", "CAN", "GBP")) {
            throw IllegalArgumentException("Invalid currency")
        }
    }

    fun convert(targetCurrency: String): Money {
        val conversionRate= when (currency) {
            "USD" -> when (targetCurrency) {
                "GBP" -> 0.5
                "EUR" -> 1.5
                "CAN" -> 1.25
                else -> 1.0
            }
            "GBP" -> when (targetCurrency) {
                "USD" -> 2.0
                "EUR" -> 3.0 
                "CAN" -> 1.6 
                else -> 1.0
            }
            "EUR" -> when (targetCurrency) {
                "USD" -> 2.0 / 3.0
                "GBP" -> 1.0 / 3.0
                "CAN" -> 2.5 / 3.0
                else -> 1.0
            }
            "CAN" -> when (targetCurrency) {
                "USD" -> 0.8
                "GBP" -> 5.0 / 8.0
                "EUR" -> 3.0 / 2.5
                else -> 1.0
            }
            // or say else -> 1.0 ??
            else -> throw IllegalArgumentException("Invalid source currency")
        }
        val convertedAmount = (amount * conversionRate).toInt()
        return Money(convertedAmount, targetCurrency)
    }

    operator fun plus(other: Money): Money {
        return if (currency == other.currency) {
            Money(amount + other.amount, currency)
        } else {
            val convertedOther = other.convert(currency)
            Money(amount + convertedOther.amount, currency)
        }
    }

    override fun toString(): String {
        return "$amount $currency"
    }
}