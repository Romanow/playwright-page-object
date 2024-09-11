package ru.romanow.playwright.pages

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import org.assertj.core.api.Assertions.assertThat
import ru.romanow.playwright.BaseComponent
import ru.romanow.playwright.annotations.FindBy

class MainPage(page: Page) : BaseComponent(page) {

    @FindBy(byTestId = "page-header")
    private lateinit var pageHeader: Locator

    @FindBy(byCss = "p[data-testid='description'] > ul > li")
    private lateinit var elements: Locator

    @FindBy(byXpath = "//p//li[last()]")
    private lateinit var lastElement: Locator

    fun assertFindByTestId(expected: String) {
        assertThat(pageHeader).hasText(expected)
    }

    fun assertFindByCss() {
        assertThat(elements.count()).isEqualTo(5)
    }

    fun assertFindByXpath() {
        assertThat(lastElement).containsText("parent")
    }
}
