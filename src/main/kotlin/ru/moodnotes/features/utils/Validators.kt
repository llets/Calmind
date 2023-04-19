package ru.moodnotes.features.utils

val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

// we can define each rule as a separate checking function,
// adding more doesn't change the complexity
fun String.latinAndAllowedSigns() = Regex(
    "[A-Za-z0-9!#$%&'\\(\\)\\*\\+\\,\\-\\.\\/\\:\\;\\<\\>\\=\\?\\@\\[\\]\\{\\}\\\\\\^\\_\\`\\~]+$").matches(this)
fun String.isLongEnough() = length >= 8
fun String.hasEnoughDigits() = count(Char::isDigit) > 0
fun String.isMixedCase() = any(Char::isLowerCase) && any(Char::isUpperCase)
fun String.hasSpecialChar() = any { it in "!@#$%^&*()_+=;:,./?\\|`~[]{}" }

fun String.isValidEmail(): Boolean {
    return EMAIL_REGEX.toRegex().matches(this)
}

fun String.isValidPassword(): Pair<Boolean, String>{
//    if (!this.isLongEnough()) return Pair(false, "Слишком короткий пароль")
//    if (!this.isMixedCase()) return Pair(false, "Use upper and lower letters")
//    if (!this.hasEnoughDigits()) return Pair(false, "Add some digits")
//    if (!this.hasSpecialChar()) return Pair(false, "Add some specıal characters")
    if (!this.latinAndAllowedSigns()) return Pair(false, "Please use only latin letters, digits and the following signs " +
            "!#$%&'()*+,-.\\/:;<>=?@[]{}^_`~")
    if (!this.isLongEnough()) return Pair(false, "Too short password")
    if (!this.isMixedCase()) return Pair(false, "Use upper and lower letters")
    if (!this.hasEnoughDigits()) return Pair(false, "Add some digits")
    if (!this.hasSpecialChar()) return Pair(false, "Add some specıal characters")
    return Pair(true, " ")
}