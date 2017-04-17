package com.haulmont.testtask.View.util;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;

/**
 * Created by Cok on 16.04.2017.
 */
public class ShortcutUtil {
    private static ShortcutUtil ourInstance = new ShortcutUtil();

    public static ShortcutUtil getInstance() {
        return ourInstance;
    }

    private ShortcutUtil() {
    }

    public void setPrimaryShortcut(Button button) {
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER, ShortcutAction.ModifierKey.CTRL);
    }
}
