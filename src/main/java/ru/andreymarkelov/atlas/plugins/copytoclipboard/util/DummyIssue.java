package ru.andreymarkelov.atlas.plugins.copytoclipboard.util;

import com.atlassian.jira.bc.project.component.ProjectComponent;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.attachment.Attachment;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.renderer.IssueRenderContext;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.label.Label;
import com.atlassian.jira.issue.priority.Priority;
import com.atlassian.jira.issue.resolution.Resolution;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.version.Version;
import com.atlassian.jira.user.ApplicationUser;
import org.ofbiz.core.entity.GenericValue;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

public class DummyIssue implements Issue {
    @Override
    public Long getId() {
        return null;
    }

    @Override
    public GenericValue getProject() {
        return null;
    }

    @Override
    public Project getProjectObject() {
        return null;
    }

    @Override
    public Long getProjectId() {
        return null;
    }

    @Override
    public IssueType getIssueType() {
        return null;
    }

    @Override
    public IssueType getIssueTypeObject() {
        return null;
    }

    @Override
    public String getIssueTypeId() {
        return null;
    }

    @Override
    public String getSummary() {
        return null;
    }

    @Override
    public ApplicationUser getAssigneeUser() {
        return null;
    }

    @Override
    public ApplicationUser getAssignee() {
        return null;
    }

    @Override
    public String getAssigneeId() {
        return null;
    }

    @Override
    public Collection<ProjectComponent> getComponentObjects() {
        return null;
    }

    @Override
    public Collection<ProjectComponent> getComponents() {
        return null;
    }

    @Override
    public ApplicationUser getReporterUser() {
        return null;
    }

    @Override
    public ApplicationUser getReporter() {
        return null;
    }

    @Override
    public String getReporterId() {
        return null;
    }

    @Override
    public ApplicationUser getCreator() {
        return null;
    }

    @Override
    public String getCreatorId() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getEnvironment() {
        return null;
    }

    @Override
    public Collection<Version> getAffectedVersions() {
        return null;
    }

    @Override
    public Collection<Version> getFixVersions() {
        return null;
    }

    @Override
    public Timestamp getDueDate() {
        return null;
    }

    @Override
    public GenericValue getSecurityLevel() {
        return null;
    }

    @Override
    public Long getSecurityLevelId() {
        return null;
    }

    @Nullable
    @Override
    public Priority getPriority() {
        return null;
    }

    @Nullable
    @Override
    public Priority getPriorityObject() {
        return null;
    }

    @Override
    public String getResolutionId() {
        return null;
    }

    @Override
    public Resolution getResolution() {
        return null;
    }

    @Override
    public Resolution getResolutionObject() {
        return null;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public Long getNumber() {
        return null;
    }

    @Override
    public Long getVotes() {
        return null;
    }

    @Override
    public Long getWatches() {
        return null;
    }

    @Override
    public Timestamp getCreated() {
        return null;
    }

    @Override
    public Timestamp getUpdated() {
        return null;
    }

    @Override
    public Timestamp getResolutionDate() {
        return null;
    }

    @Override
    public Long getWorkflowId() {
        return null;
    }

    @Override
    public Object getCustomFieldValue(CustomField customField) {
        return null;
    }

    @Override
    public Status getStatus() {
        return null;
    }

    @Override
    public String getStatusId() {
        return null;
    }

    @Override
    public Status getStatusObject() {
        return null;
    }

    @Override
    public Long getOriginalEstimate() {
        return null;
    }

    @Override
    public Long getEstimate() {
        return null;
    }

    @Override
    public Long getTimeSpent() {
        return null;
    }

    @Override
    public Object getExternalFieldValue(String s) {
        return null;
    }

    @Override
    public boolean isSubTask() {
        return false;
    }

    @Override
    public Long getParentId() {
        return null;
    }

    @Override
    public boolean isCreated() {
        return false;
    }

    @Override
    public Issue getParentObject() {
        return null;
    }

    @Override
    public GenericValue getParent() {
        return null;
    }

    @Override
    public Collection<GenericValue> getSubTasks() {
        return null;
    }

    @Override
    public Collection<Issue> getSubTaskObjects() {
        return null;
    }

    @Override
    public boolean isEditable() {
        return false;
    }

    @Override
    public IssueRenderContext getIssueRenderContext() {
        return null;
    }

    @Override
    public Collection<Attachment> getAttachments() {
        return null;
    }

    @Override
    public Set<Label> getLabels() {
        return null;
    }

    @Override
    public GenericValue getGenericValue() {
        return null;
    }

    @Override
    public String getString(String s) {
        return null;
    }

    @Override
    public Timestamp getTimestamp(String s) {
        return null;
    }

    @Override
    public Long getLong(String s) {
        return null;
    }

    @Override
    public void store() {
    }

    @Override
    public boolean isArchived() {
        return false;
    }

    @Override
    public ApplicationUser getArchivedByUser() {
        return null;
    }

    @Override
    public String getArchivedById() {
        return null;
    }

    @Override
    public Timestamp getArchivedDate() {
        return null;
    }
}
