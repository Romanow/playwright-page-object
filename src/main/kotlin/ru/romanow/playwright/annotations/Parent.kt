package ru.romanow.playwright.annotations

import com.microsoft.playwright.options.AriaRole

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.LOCAL_VARIABLE)
annotation class Parent(
    val byCss: String = "",
    val byXpath: String = "",
    val byTestId: String = "",
    val byRole: AriaRole = AriaRole.NONE
)
