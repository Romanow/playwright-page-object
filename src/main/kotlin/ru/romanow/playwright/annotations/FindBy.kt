package ru.romanow.playwright.annotations

import com.microsoft.playwright.options.AriaRole

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class FindBy(
    val parent: Parent = Parent(),
    val byCss: String = "",
    val byXpath: String = "",
    val byTestId: String = "",
    val byText: String = "",
    val byRole: AriaRole = AriaRole.NONE,
)
