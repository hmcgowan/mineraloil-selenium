package com.lithium.mineraloil.selenium.generic_elements;

import java.util.concurrent.TimeUnit;

public interface ElementWaiters {
    void waitUntilDisplayed();

    void waitUntilNotDisplayed();

    void waitUntilDisplayed(final TimeUnit timeUnit, final int seconds);

    void waitUntilNotDisplayed(final TimeUnit timeUnit, final int seconds);

    void waitUntilEnabled();

    void waitUntilNotEnabled();

    void waitUntilEnabled(final TimeUnit timeUnit, final int time);

    void waitUntilNotEnabled(final TimeUnit timeUnit, final int time);
}
