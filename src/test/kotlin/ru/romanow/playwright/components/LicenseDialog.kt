package ru.romanow.playwright.components

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import ru.romanow.playwright.BaseComponent
import ru.romanow.playwright.annotations.FindBy
import ru.romanow.playwright.annotations.Parent

class LicenseDialog(page: Page) : BaseComponent(page) {

    @FindBy(parent = Parent(byCss = "#license"), byText = "Лицензия")
    private lateinit var licenseHeader: Locator

    @FindBy(parent = Parent(byCss = "#license"), byCss = ".modal-body")
    private lateinit var licenseContent: Locator

    @FindBy(parent = Parent(byCss = "#license"), byCss = ".btn-close")
    private lateinit var closeButton: Locator

    fun assertLicenseOwner(owner: String) {
        assertThat(licenseHeader).hasId("license-header")
        assertThat(licenseContent).containsText(owner)
    }

    fun close() {
        closeButton.click()
    }
}
