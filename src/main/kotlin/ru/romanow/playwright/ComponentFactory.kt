package ru.romanow.playwright

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.options.AriaRole
import ru.romanow.playwright.annotations.Component
import ru.romanow.playwright.annotations.FindBy
import ru.romanow.playwright.annotations.Parent
import java.lang.reflect.Field
import kotlin.reflect.KClass
import kotlin.reflect.jvm.javaType

class ComponentFactory private constructor() {

    companion object {
        fun <T : BaseComponent> create(page: Page, cls: KClass<T>): T {
            val constructor =
                cls.constructors.first { it.parameters.size == 1 && it.parameters[0].type.javaType == Page::class.java }
            val element = constructor.call(page)

            fillLocators(page, cls, element)
            fillComponents(page, cls, element)

            return element
        }


        private fun <T : BaseComponent> fillComponents(page: Page, cls: KClass<T>, element: T) {
            val fields: List<Field> = cls.java.declaredFields.filter { it.isAnnotationPresent(Component::class.java) }

            for (field in fields) {
                val component = field.getAnnotation(Component::class.java)
                val internalElement = create(page, component.type)
                field.isAccessible = true
                field[element] = internalElement
            }
        }

        private fun <T : BaseComponent> fillLocators(page: Page, cls: KClass<T>, element: T) {
            val fields: List<Field> = cls.java.declaredFields.filter { it.isAnnotationPresent(FindBy::class.java) }
            for (field in fields) {
                val findBy = field.getAnnotation(FindBy::class.java)
                val locator = calculateLocator(page, findBy)
                field.isAccessible = true
                field[element] = locator
            }
        }

        private fun calculateLocator(page: Page, findBy: FindBy): Locator {
            return if (parentExists(findBy.parent)) {
                val parent: Locator = calculateLocator(page, buildSelector(findBy.parent))
                calculateLocator(parent, buildSelector(findBy))
            } else {
                calculateLocator(page, buildSelector(findBy))
            }
        }

        private fun calculateLocator(page: Page, selector: Selector): Locator {
            if (selector.byTestId != null) {
                // by [data-testid='<name>']
                return page.getByTestId(selector.byTestId)
            } else if (selector.byCss != null) {
                // by css locator
                return page.locator("css=" + selector.byCss)
            } else if (selector.byXpath != null) {
                // by xpath locator
                return page.locator("xpath=" + selector.byXpath)
            } else if (selector.byRole != null) {
                // by role and (optionally) accessible name
                val options: Page.GetByRoleOptions? =
                    if (selector.byText != null) Page.GetByRoleOptions().setName(selector.byText) else null
                return page.getByRole(selector.byRole, options)
            } else if (selector.byText != null) {
                // by text
                return page.getByText(selector.byText)
            }
            throw IllegalArgumentException(
                "Each @FindBy must have at least one selector (byTestId, byCss, byXPath, byText or byRole"
            )
        }

        private fun calculateLocator(parent: Locator, selector: Selector): Locator {
            if (selector.byTestId != null) {
                // by [data-testid='<name>']
                return parent.getByTestId(selector.byTestId)
            } else if (selector.byCss != null) {
                // by css locator
                return parent.locator("css=" + selector.byCss)
            } else if (selector.byXpath != null) {
                // by xpath locator
                return parent.locator("xpath=" + selector.byXpath)
            } else if (selector.byRole != null) {
                // by role and (optionally) accessible name
                val options: Locator.GetByRoleOptions? =
                    if (selector.byText != null) Locator.GetByRoleOptions().setName(selector.byText) else null
                return parent.getByRole(selector.byRole, options)
            } else if (selector.byText != null) {
                // by text
                return parent.getByText(selector.byText)
            }
            throw IllegalArgumentException(
                "Each @Parent must have at least one selector (byTestId, byCss, byXPath or byRole"
            )
        }

        private fun buildSelector(findBy: FindBy) = Selector(
            byCss = findBy.byCss,
            byXpath = findBy.byXpath,
            byTestId = findBy.byTestId,
            byText = findBy.byText,
            byRole = findBy.byRole
        )

        private fun buildSelector(parent: Parent) = Selector(
            byCss = parent.byCss,
            byXpath = parent.byXpath,
            byTestId = parent.byTestId,
            byRole = parent.byRole
        )

        private fun parentExists(selector: Parent): Boolean {
            return selector.byCss.isNotEmpty() ||
                selector.byXpath.isNotEmpty() ||
                selector.byTestId.isNotEmpty() ||
                selector.byRole !== AriaRole.NONE
        }
    }
}

internal class Selector(
    byTestId: String? = null,
    byCss: String? = null,
    byXpath: String? = null,
    byText: String? = null,
    byRole: AriaRole = AriaRole.NONE
) {
    val byTestId: String?
    val byCss: String?
    val byXpath: String?
    val byText: String?
    val byRole: AriaRole?

    init {
        this.byTestId = if (!byTestId.isNullOrEmpty()) byTestId else null
        this.byCss = if (!byCss.isNullOrEmpty()) byCss else null
        this.byXpath = if (!byXpath.isNullOrEmpty()) byXpath else null
        this.byText = if (!byText.isNullOrEmpty()) byText else null
        this.byRole = if (byRole != AriaRole.NONE) byRole else null
    }
}
