package com.mshcc.plugin.lazycode.window;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.mshcc.plugin.lazycode.complex.SettingState;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author mshcc
 */
public class SettingConfigComponent implements SearchableConfigurable {
    static {
        Setting.SETTING.init();
    }
    @Override
    public @NotNull
    @NonNls
    String getId() {
        return "lazycode.id";
    }

    @Override
    public String getDisplayName() {
        return "Lazy Code";
    }

    @Override
    public @Nullable
    @NonNls
    String getHelpTopic() {
        return "lazycode.helpTopic";
    }

    @Override
    public @Nullable
    Runnable enableSearch(String option) {
        return null;
    }

    @Override
    public @Nullable
    JComponent createComponent() {
        Setting.SETTING.refresh();
        return Setting.SETTING.getComponent();
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        SettingState.getInstance().loadState(Setting.SETTING.getConfig());
    }

    @Override
    public void reset() {
        Setting.SETTING.reset();
    }
}
