package ru.romanow.playwright.tests

import com.microsoft.playwright.Page
import com.microsoft.playwright.junit.UsePlaywright
import org.junit.jupiter.api.Test
import ru.romanow.playwright.ComponentFactory
import ru.romanow.playwright.pages.MainPage
import ru.romanow.playwright.utils.BrowserOptions

@UsePlaywright(BrowserOptions::class)
internal class PageTest {

    @Test
    fun test(page: Page) {
        page.navigate("/")
        val mainPage = ComponentFactory.create(page, MainPage::class)

        mainPage.assertFindByTestId("Тестовая страница")
        mainPage.assertFindByCss()
        mainPage.assertFindByXpath()
    }
}
