package ru.andreymarkelov.atlas.plugins.copytoclipboard.action;

import com.atlassian.jira.config.managedconfiguration.ManagedConfigurationItemService;
import com.atlassian.jira.security.xsrf.RequiresXsrfCheck;
import com.atlassian.jira.web.action.admin.customfields.AbstractEditConfigurationItemAction;
import com.atlassian.templaterenderer.TemplateRenderer;
import ru.andreymarkelov.atlas.plugins.copytoclipboard.manager.PluginData;
import ru.andreymarkelov.atlas.plugins.copytoclipboard.util.DummyIssue;

import java.util.Collections;

import static com.atlassian.jira.permission.GlobalPermissionKey.ADMINISTER;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class CopyToClipboardCfEditConfiguration extends AbstractEditConfigurationItemAction {
    private final PluginData pluginData;
    private final TemplateRenderer renderer;

    private String copyPattern;

    public CopyToClipboardCfEditConfiguration(
            ManagedConfigurationItemService managedConfigurationItemService,
            PluginData pluginData,
            TemplateRenderer renderer) {
        super(managedConfigurationItemService);
        this.pluginData = pluginData;
        this.renderer = renderer;
    }

    @Override
    public String doDefault() throws Exception {
        String copyPattern = pluginData.getCopyPattern(getFieldConfig());
        if (isNotBlank(copyPattern)) {
            this.copyPattern = copyPattern;
        }
        return INPUT;
    }

    @Override
    @RequiresXsrfCheck
    protected String doExecute() throws Exception {
        if (!getGlobalPermissionManager().hasPermission(ADMINISTER, getLoggedInUser())) {
            return "securitybreach";
        }
        pluginData.setCopyPattern(getFieldConfig(), copyPattern);
        return getRedirect("/secure/admin/ConfigureCustomField!default.jspa?customFieldId=" + getFieldConfig().getCustomField().getIdAsLong().toString());
    }

    @Override
    protected void doValidation() {
        super.doValidation();
        if (isNotBlank(copyPattern)) {
            try {
                renderer.renderFragment(copyPattern, Collections.<String, Object>singletonMap("issue", new DummyIssue()));
            } catch (Exception e) {
                addError("copyPattern", getText("ru.andreymarkelov.atlas.plugins.copy-to-clipboard.config.pattern.error"));
            }
        }
    }

    public String getCopyPattern() {
        return copyPattern;
    }

    public void setCopyPattern(String copyPattern) {
        this.copyPattern = copyPattern;
    }
}
