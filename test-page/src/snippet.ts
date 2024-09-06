const snippet = `
@UsePlaywright(BrowserOptions::class)
internal class PageTest {
    @Test
    fun test(page: Page) {
        page.navigate("/")
        val mainPage = ComponentFactory.create(page, MainPage::class)
        mainPage.assertPageHeader("Тестовая страница")
        mainPage.assertListElements()
    }
}`;

export {snippet};
