package ru.romanow.playwright.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.reflect.KClass

class PropertiesHelper private constructor() {
    private var holder: MutableMap<String, String>

    init {
        val stream = object {}.javaClass.classLoader.getResourceAsStream("config.properties")
        stream.use {
            this.holder = HashMap<String, String>()
            val properties = Properties().also { p -> p.load(it) }
            val pattern = Regex("\\$\\{([^}]+)}")
            for (key in properties.keys) {
                var value = properties[key] as String
                var matcher = pattern.find(value)
                while (matcher != null) {
                    val env = matcher.groups[1]!!
                    var envValue = env.value
                    envValue = if (envValue.contains(":")) {
                        val parts = envValue.split(":".toRegex(), limit = 2)
                        System.getenv(parts[0]) ?: parts[1]
                    } else {
                        System.getenv(envValue) ?: ""
                    }
                    value = pattern.replaceFirst(value, envValue)
                    matcher = matcher.next()
                }
                holder[key.toString()] = value
            }
        }
    }

    companion object {
        private val instance: PropertiesHelper by lazy { PropertiesHelper() }

        @Suppress("UNCHECKED_CAST")
        fun <T : Any> get(key: String, cls: KClass<T>): T? {
            val value = instance.holder[key]
            return when (cls) {
                String::class -> value as T?
                Boolean::class -> value.toBoolean() as T?
                Int::class -> value?.toInt() as T?
                Double::class -> value?.toDouble() as T?
                else -> throw ClassCastException(
                    "PropertyHolder supports only String, Integer, Double and Boolean types"
                )
            }
        }

        fun get(key: String): String? {
            return get(key, String::class)
        }
    }
}

internal class PropertiesHelperTest {

    @Test
    fun test() {
        assertThat(PropertiesHelper.get("test.base-url", String::class)).isEqualTo("http://localhost:8080")
        assertThat(PropertiesHelper.get("test.stop-on-error", Boolean::class)).isTrue
    }
}
