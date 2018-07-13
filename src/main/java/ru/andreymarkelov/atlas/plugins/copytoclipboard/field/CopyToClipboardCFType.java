package ru.andreymarkelov.atlas.plugins.copytoclipboard.field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.impl.CalculatedCFType;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.fields.config.FieldConfigItemType;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.templaterenderer.TemplateRenderer;
import ru.andreymarkelov.atlas.plugins.copytoclipboard.manager.CopyToClipboardDataManager;

public class CopyToClipboardCFType extends CalculatedCFType<String, String> {
    private final CopyToClipboardDataManager copyToClipboardDataManager;
    private final TemplateRenderer renderer;
    private final CustomFieldManager customFieldManager;

    public CopyToClipboardCFType(
            CopyToClipboardDataManager copyToClipboardDataManager,
            TemplateRenderer renderer,
            CustomFieldManager customFieldManager) {
        this.copyToClipboardDataManager = copyToClipboardDataManager;
        this.renderer = renderer;
        this.customFieldManager = customFieldManager;
    }

    @Override
    @Nonnull
    public List<FieldConfigItemType> getConfigurationItemTypes() {
        List<FieldConfigItemType> configurationItemTypes = super.getConfigurationItemTypes();
        configurationItemTypes.add(new CopyToClipboardCfConfig(copyToClipboardDataManager));
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
        if (issue != null) {
            FieldConfig fieldConfig = customField.getRelevantConfig(issue);
            if (fieldConfig != null) {
                Map<String, Object> renderParameters = new HashMap<>();
                renderParameters.put("issue", issue);
                renderParameters.put("customFieldManager", customFieldManager);
                return renderer.renderFragment(copyToClipboardDataManager.getCopyPattern(fieldConfig), renderParameters);
            }
        }
        return "";
    }

    @Override
    public Map<String, Object> getVelocityParameters(Issue issue, CustomField customField, FieldLayoutItem fieldLayoutItem) {
        Map<String, Object> params = super.getVelocityParameters(issue, customField, fieldLayoutItem);
        if (issue != null) {
            FieldConfig fieldConfig = customField.getRelevantConfig(issue);
            if (fieldConfig != null) {
                params.put("copytext", copyToClipboardDataManager.getCopyButton(fieldConfig));
            }
        }
        return params;
    }
}
