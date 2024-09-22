package ru.romanow.playwright.pages

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import org.assertj.core.api.Assertions.assertThat
import ru.romanow.playwright.BaseComponent
import ru.romanow.playwright.annotations.Component
import ru.romanow.playwright.annotations.FindBy
import ru.romanow.playwright.components.FunctionsDialog
import ru.romanow.playwright.components.LicenseDialog

class MainPage(page: Page) : BaseComponent(page) {

    @FindBy(byTestId = "page-header")
    private lateinit var pageHeader: Locator

    @FindBy(byCss = ".features > li")
    private lateinit var elements: Locator

    @FindBy(byXpath = "//*[contains(@class, 'features')]/li[last()]")
    private lateinit var lastElement: Locator

    @FindBy(byRole = AriaRole.BUTTON, byText = "Лицензия")
    private lateinit var licenseDialogButton: Locator

    @Component(type = LicenseDialog::class)
    private lateinit var licenseDialog: LicenseDialog

    @FindBy(byText = "Описание функций")
    private lateinit var functionsDialogButton: Locator

    @Component(type = FunctionsDialog::class)
    private lateinit var functionsDialog: FunctionsDialog

    fun assertPageHeaderText(expected: String) {
        assertThat(pageHeader).hasText(expected)
    }

    fun assertFeatures() {
        assertThat(elements.count()).isEqualTo(5)
        assertThat(lastElement).containsText("parent")
    }

    fun openLicenseDialog(): LicenseDialog {
        licenseDialogButton.click()
        return licenseDialog
    }

    fun openFunctionsDialog(): FunctionsDialog {
        functionsDialogButton.click()
        return functionsDialog
    }
}
