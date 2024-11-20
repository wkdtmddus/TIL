package ssafy.aissue.api.issue.response;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class IssueResponse {

    private Long id;
    private String key;
    private String summary;
    private String description;
    private String status;
    private String priority;
    private String issuetype;
    private String assignee;

    private ParentIssue parent;
    private List<Subtask> subtasks;

    @Getter
    @Builder
    public static class ParentIssue {
        private Long id;
        private String key;
        private String summary;
        private String description;
        private String priority;
        private String status;
        private String issuetype;
    }

    @Getter
    @Builder
    public static class Subtask {
        private Long id;
        private String key;
        private String summary;
        private String description;
        private String priority;
        private String status;
        private String issuetype;
    }
}