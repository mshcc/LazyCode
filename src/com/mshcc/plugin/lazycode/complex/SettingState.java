package com.mshcc.plugin.lazycode.complex;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.mshcc.plugin.lazycode.complex.custom.Field;
import com.mshcc.plugin.lazycode.complex.custom.Template;
import com.mshcc.plugin.lazycode.entity.SettingConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * @Author: mshcc
 * @Date: 2021/10/18  14:31
 * @Description: 设置文件持久化策略
 */
@State(name = "Settings", storages = {@Storage(value="LazyCodeSettings.xml")})
public class SettingState implements PersistentStateComponent<SettingConfig> {
    public static final SettingState SETTING_STATE = new SettingState();
    SettingConfig settingConfig;

    @Override
    public @Nullable
    SettingConfig getState() {
        SettingConfig config = new SettingConfig();
        config.fields = new ArrayList<>();
        config.templates = new ArrayList<>();
        config.fields.add(new Field("user", "mshcc", true, false));
        config.templates.add(new Template("entity", "D://hello", true, true));
        SettingState.SETTING_STATE.loadState(config);
        return config;
    }

    @Override
    public void loadState(@NotNull SettingConfig settingConfig) {
        if(this.settingConfig==null){
            this.settingConfig = new SettingConfig();
        }
        XmlSerializerUtil.copyBean(settingConfig, this.settingConfig);
    }
}
