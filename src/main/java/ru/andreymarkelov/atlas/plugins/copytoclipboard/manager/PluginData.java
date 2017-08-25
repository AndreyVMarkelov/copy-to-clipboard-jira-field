package ru.andreymarkelov.atlas.plugins.copytoclipboard.manager;

import com.atlassian.jira.issue.fields.config.FieldConfig;

public interface PluginData {
    String getCopyPattern(FieldConfig config);
    void setCopyPattern(FieldConfig config, String copyPattern);
}
