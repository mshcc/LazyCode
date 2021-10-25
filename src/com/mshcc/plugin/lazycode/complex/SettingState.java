package com.mshcc.plugin.lazycode.complex;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.mshcc.plugin.lazycode.complex.custom.Field;
import com.mshcc.plugin.lazycode.complex.custom.Template;
import com.mshcc.plugin.lazycode.entity.SettingConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @Author: mshcc
 * @Date: 2021/10/18  14:31
 * @Description: 设置文件持久化策略
 */
@State(name = "Settings", storages = {@Storage("LazyCodeSettings.xml")})
public class SettingState implements PersistentStateComponent<SettingConfig> {
    SettingConfig settingConfig;

    /**
     * 内置模板与字段，嵌入代码
     */
    public static List<Template> templates = List.of(
            new Template("JavaEntity", "inline/JavaEntity.ftl", true),
            new Template("JavaMapper", "inline/JavaMapper.ftl", true),
            new Template("JavaMapperXml", "inline/JavaMybatisXml.ftl", true),
            new Template("JavaService", "inline/JavaService.ftl", true),
            new Template("JavaServiceImpl", "inline/JavaServiceImpl.ftl", true),
            new Template("JavaController", "inline/JavaController.ftl", true)
    );
    public static List<Field> fields = List.of(
            new Field("test", "test", true)
    );

    @Override
    public @Nullable
    SettingConfig getState() {
        if (settingConfig == null) {
            settingConfig = new SettingConfig();
            settingConfig.fields = fields;
            settingConfig.templates = templates;
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
