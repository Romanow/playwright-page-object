package ru.romanow.playwright.annotations

import ru.romanow.playwright.BaseComponent
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class Component(
    val type: KClass<out BaseComponent>
)
