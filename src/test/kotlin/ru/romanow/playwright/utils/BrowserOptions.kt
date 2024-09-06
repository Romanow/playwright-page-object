package ru.romanow.playwright.utils

import com.microsoft.playwright.Browser.NewContextOptions
import com.microsoft.playwright.BrowserType.LaunchOptions
import com.microsoft.playwright.junit.Options
import com.microsoft.playwright.junit.OptionsFactory
import ru.romanow.playwright.utils.PropertiesHelper.Companion.get

class BrowserOptions : OptionsFactory {
    override fun getOptions(): Options {
        return Options()
            .also {
                it.headless = get("headless-mode", Boolean::class)
                it.contextOptions = NewContextOptions().also { opt ->
                    opt.baseURL = get("base-url")
                }
                it.launchOptions = LaunchOptions().also { opt ->
                    opt.slowMo = if (get("slow-mode", Boolean::class) == true) 2000.0 else 0.0
                }
            }
    }
}
