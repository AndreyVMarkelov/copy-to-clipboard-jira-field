package ru.andreymarkelov.atlas.plugins.copytoclipboard.manager;

import com.atlassian.jira.issue.fields.config.FieldConfig;

public interface PluginData {
    String getCopyButton(FieldConfig config);
    void setCopyButton(FieldConfig config, String copyButton);

    String getCopyPattern(FieldConfig config);
    void setCopyPattern(FieldConfig config, String copyPattern);
}
