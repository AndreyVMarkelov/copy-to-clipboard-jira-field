package ru.andreymarkelov.atlas.plugins.copytoclipboard.field;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.impl.CalculatedCFType;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfigItemType;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import org.apache.commons.lang.StringEscapeUtils;
import ru.andreymarkelov.atlas.plugins.copytoclipboard.manager.PluginData;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public class CopyToClipboardCFType extends CalculatedCFType<String, String> {
    private final PluginData pluginData;

    public CopyToClipboardCFType(PluginData pluginData) {
        this.pluginData = pluginData;
    }

    @Override
    @Nonnull
    public List<FieldConfigItemType> getConfigurationItemTypes() {
        List<FieldConfigItemType> configurationItemTypes = super.getConfigurationItemTypes();
        configurationItemTypes.add(new CopyToClipboardCfConfig(pluginData));
        return configurationItemTypes;
    }

    @Override
    public String getSingularObjectFromString(String s) throws FieldValidationException {
        return s;
    }

    @Override
    public String getStringFromSingularObject(String s) {
        return s;
    }

    @Override
    public String getValueFromIssue(CustomField customField, Issue issue) {
        if (issue != null && issue.getKey() != null) {
            return String.format("%s %s: %s", issue.getIssueType().getName(), issue.getKey(), StringEscapeUtils.escapeJavaScript(issue.getSummary()));
        } else {
            return "";
        }
    }

    public Map<String, Object> getVelocityParameters(Issue issue, CustomField customField, FieldLayoutItem fieldLayoutItem) {
        Map<String, Object> params = super.getVelocityParameters(issue, customField, fieldLayoutItem);
        if (issue != null && issue.getId() != null) {
            params.put("issue_id", issue.getId());
        }
        return params;
    }
}
