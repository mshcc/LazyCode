package com.mshcc.plugin.lazycode.complex;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.mshcc.plugin.lazycode.entity.SettingConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * @Author: mshcc
 * @Date: 2021/10/18  14:31
 * @Description: 设置文件持久化策略
 */
@State(name = "Settings", storages = {@Storage("LazyCodeSettings.xml")})
public class SettingState implements PersistentStateComponent<SettingConfig> {
    SettingConfig settingConfig;

    @Override
    public @Nullable
    SettingConfig getState() {
        if (settingConfig == null) {
            settingConfig = new SettingConfig();
            settingConfig.fields = new ArrayList<>();
            settingConfig.templates = new ArrayList<>();
        }
        return settingConfig;
    }

    @Override
    public void loadState(@NotNull SettingConfig settingConfig) {
        this.settingConfig = settingConfig;
    }

    public static SettingState getInstance() {
        return ServiceManager.getService(SettingState.class);
    }

    private SettingState() {
    }
}
