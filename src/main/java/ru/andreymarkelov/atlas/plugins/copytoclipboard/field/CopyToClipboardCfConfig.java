package ru.andreymarkelov.atlas.plugins.copytoclipboard.field;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.fields.config.FieldConfigItemType;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import ru.andreymarkelov.atlas.plugins.copytoclipboard.manager.CopyToClipboardDataManager;

import static java.util.Collections.singletonMap;

public class CopyToClipboardCfConfig implements FieldConfigItemType {
    private final CopyToClipboardDataManager copyToClipboardDataManager;

    public CopyToClipboardCfConfig(CopyToClipboardDataManager copyToClipboardDataManager) {
        this.copyToClipboardDataManager = copyToClipboardDataManager;
    }

    @Override
    public String getDisplayName() {
        return "Copy Pattern";
    }

    @Override
    public String getDisplayNameKey() {
        return "ru.andreymarkelov.atlas.plugins.copy-to-clipboard.configedit";
    }

    @Override
    public String getViewHtml(FieldConfig fieldConfig, FieldLayoutItem fieldLayoutItem) {
        return copyToClipboardDataManager.getCopyPattern(fieldConfig);
    }

    @Override
    public String getObjectKey() {
        return "CopyToClipboardCfConfig";
    }

    @Override
    public Object getConfigurationObject(Issue issue, FieldConfig fieldConfig) {
        return singletonMap("copyPattern", copyToClipboardDataManager.getCopyPattern(fieldConfig));
    }

    @Override
    public String getBaseEditUrl() {
        return "CopyToClipboardCfEditConfiguration!default.jspa";
    }
}
