const example = `
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

const byTestId = `
@FindBy(byTestId = "page-header")
private lateinit var pageHeader: Locator
`;

const byCss = `
@FindBy(byCss = "p[data-testid='description'] > ul > li")
private lateinit var elements: Locator
`;

const byXpath = `
@FindBy(byXpath = "//p//li[last()]")
private lateinit var lastElement: Locator
`;

const byRoleAndText = `
@FindBy(byRole = AriaRole.BUTTON, byText = "Лицензия")
private lateinit var licenseDialogButton: Locator
`;

const withParent = `
@FindBy(parent = Parent(byCss = "#license"), byText = "Лицензия")
private lateinit var licenseHeader: Locator
`;

const license = `
MIT License

Copyright (c) 2024 Romanov Alexey

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
`

export {example, byTestId, byCss, byXpath, byRoleAndText, withParent, license};
