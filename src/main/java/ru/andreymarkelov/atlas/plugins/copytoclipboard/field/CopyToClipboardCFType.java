package ru.andreymarkelov.atlas.plugins.copytoclipboard.field;

import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.customfields.impl.GenericTextCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.view.CustomFieldParams;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.fields.config.FieldConfigItemType;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.util.ErrorCollection;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.sal.api.UrlMode;
import com.atlassian.templaterenderer.TemplateRenderer;
import ru.andreymarkelov.atlas.plugins.copytoclipboard.manager.CopyToClipboardDataManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

import static org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4;

public class CopyToClipboardCFType extends GenericTextCFType {
    private final CopyToClipboardDataManager copyToClipboardDataManager;
    private final TemplateRenderer renderer;
    private final CustomFieldManager customFieldManager;
    private final ApplicationProperties applicationProperties;

    public CopyToClipboardCFType(
            CustomFieldValuePersister customFieldValuePersister,
            GenericConfigManager genericConfigManager,
            CopyToClipboardDataManager copyToClipboardDataManager,
            TemplateRenderer renderer,
            CustomFieldManager customFieldManager,
            ApplicationProperties applicationProperties) {
        super(customFieldValuePersister, genericConfigManager);
        this.copyToClipboardDataManager = copyToClipboardDataManager;
        this.renderer = renderer;
        this.customFieldManager = customFieldManager;
        this.applicationProperties = applicationProperties;
    }

    @Override
    @Nonnull
    public List<FieldConfigItemType> getConfigurationItemTypes() {
        List<FieldConfigItemType> configurationItemTypes = new ArrayList<>();
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

    @Nullable
    @Override
    protected Object getDbValueFromObject(String s) {
        return null;
    }

    @Nullable
    @Override
    protected String getObjectFromDbValue(@Nonnull Object o) throws FieldValidationException {
        return null;
    }

    @Override
    public String getValueFromIssue(CustomField customField, Issue issue) {
        if (issue != null) {
            FieldConfig fieldConfig = customField.getRelevantConfig(issue);
            if (fieldConfig != null) {
                Map<String, Object> renderParameters = new HashMap<>();
                renderParameters.put("issue", issue);
                renderParameters.put("customFieldManager", customFieldManager);
                renderParameters.put("baseUrl", applicationProperties.getBaseUrl(UrlMode.ABSOLUTE));
                String pattern = copyToClipboardDataManager.getCopyPattern(fieldConfig);
                return unescapeHtml4(renderer.renderFragment(pattern, renderParameters));
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

    @Override
    public void createValue(CustomField field, Issue issue, @Nonnull String value) {
    }

    @Override
    public void updateValue(CustomField customField, Issue issue, String value) {
    }

    @Override
    public void setDefaultValue(FieldConfig fieldConfig, String value) {
    }

    @Override
    public String getDefaultValue(FieldConfig fieldConfig) {
        return null;
    }

    @Override
    public String getChangelogValue(CustomField field, String value) {
        return null;
    }

    @Override
    public void validateFromParams(CustomFieldParams relevantParams, ErrorCollection errorCollectionToAddTo, FieldConfig config) {
    }

    @Override
    public Set<Long> remove(CustomField field) {
        return Collections.emptySet();
    }

    @Override
    public String getValueFromCustomFieldParams(CustomFieldParams parameters) throws FieldValidationException {
        Object firstValueForKey = parameters.getFirstValueForKey(null);
        return this.getSingularObjectFromString((String) firstValueForKey);
    }

    @Override
    public Object getStringValueFromCustomFieldParams(CustomFieldParams parameters) {
        return parameters.getFirstValueForNullKey();
    }

    @Override
    public Object accept(VisitorBase visitor) {
        return visitor instanceof CopyToClipboardCFType.Visitor ? ((CopyToClipboardCFType.Visitor)visitor).visitCalculated(this) : super.accept(visitor);
    }

    public interface Visitor<X> extends VisitorBase<X> {
        X visitCalculated(CopyToClipboardCFType cfType);
    }
}
