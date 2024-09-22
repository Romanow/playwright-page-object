package ru.romanow.playwright.components

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import ru.romanow.playwright.BaseComponent
import ru.romanow.playwright.annotations.FindBy
import ru.romanow.playwright.annotations.Parent

class FunctionsDialog(page: Page) : BaseComponent(page) {

    @FindBy(parent = Parent(byTestId = "functions-dialog"), byTestId = "close-functions-dialog")
    private lateinit var closeButton: Locator

    @FindBy(parent = Parent(byTestId = "functions-dialog"), byCss = ".nav-item")
    private lateinit var tabs: Locator

    @FindBy(parent = Parent(byRole = AriaRole.DIALOG), byXpath = "//*[contains(@class, 'nav-item')][last()]")
    private lateinit var withParentTab: Locator

    fun assertFunctionsTabs() {
        assertThat(tabs).hasCount(5)
        assertThat(withParentTab).hasText("With Parent")
    }

    fun close() {
        closeButton.click()
    }
}
