# Page Object pattern for Playwright framework

[![Build project](https://github.com/Romanow/playwright-page-object/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/Romanow/playwright-page-object/actions/workflows/build.yml)
[![pre-commit](https://img.shields.io/badge/pre--commit-enabled-brightgreen?logo=pre-commit)](https://github.com/pre-commit/pre-commit)

## Шаблон проектирования _Page Object_

_Page Object_ – это шаблон проектирования, который помогает инкапсулировать работу с отдельными элементами страницы, что
позволяет уменьшить количество кода и его поддержку. Если, к примеру, дизайн одной из страниц изменён, то нам нужно
будет переписать только соответствующий класс, описывающий эту страницу.

Основные преимущества:

* Разделение кода тестов и описания страниц.
* Объединение всех действий по работе с веб-страницей в одном месте.

_Page Object_ описывает действия над элементами страницы, при этом не выставляя наружу элементы страницы: т.е.
коммуникация со страницей выполняются внутри метода, тест лишь вызывает действия более высокого уровня (обычно это
хорошо ложится на шаги теста).

## Основные элементы Playwright

* `Page` – предоставляет метод для взаимодействия со страницей браузера.
* `Locator` – объект для поиска элемента на странице. Вычисляется в момент обращения к
  элементу (`click()`, `isVisible()` и т.п.).

## Структура проекта

Т.к. страницы могут содержать большое количество элементов, для большей читабельности вводится понятие _Component
Object_ – элемент страницы. _Component Object_ на _Page Object_ описывается с помощью
аннотации [`@Component`](src/main/kotlin/ru/romanow/playwright/annotations/Component.kt) и инициализируется
автоматически с помощью `ComponentFactory` наравне с полями, помеченными аннотацией `@FindBy`.

### ComponentFactory

[`ComponentFactory`](src/main/kotlin/ru/romanow/playwright/ComponentFactory.kt) – фабрика для создания _Page Object_.
Принимает на вход `Page` (объект Playwright) и класс `PageObject`, который нужно создать. С помощью reflection обходит
аннотации `@FindBy` и `@Component`.

### Локаторы `@FindBy`

Локатор [`@FindBy`](src/main/kotlin/ru/romanow/playwright/annotations/FindBy.kt) используется для задания условия поиска
элемента на странице. В момент создания страницы элемент может отсутствовать на странице, поиск производится в момент
обращения к Location.

Есть 4 условия поиска, при этом если задано одно условие, то другие не выполняются (кроме `byRole` и `byText` – они
могут идти вместе). Условия поиска перечислены в порядке приоритета:

* `byTestId` – поиск по `data-testid`;
* `byCss` – поиск по CSS селектор;
* `byRole` – поиск по атрибуту `role`;
* `byXpath` – поиск XPath;
* `byText` – поиск по тексту (может применяться совместно с фильтром `byRole`);
* `parent` – используется, если нужно выделить поддерево, в рамках которого выполнять дальнейший поиск
  элементов ([`Parent`](src/main/kotlin/ru/romanow/playwright/annotations/Parent.kt) – `byCss`, `byTestId`, `byXpath`,
  `byRole`).

## Соглашения о разработке

### Структура пакетов теста

* _utils_ – утильные инструменты, константы и т.п.
* _pages_ и _components_ – классы, описывающие _Page Object_ и_Component Object_.
* _tests_ – тесты, внутри нужно делить по функциональности.
