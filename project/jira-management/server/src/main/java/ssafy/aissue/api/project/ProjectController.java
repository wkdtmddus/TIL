package ssafy.aissue.api.project;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import ssafy.aissue.api.CommonResponse;
import ssafy.aissue.api.project.request.ProjectFunctionRequest;
import ssafy.aissue.api.project.request.ProjectUpdateRequest;
import ssafy.aissue.api.project.response.ProjectDetailsResponse;
import ssafy.aissue.api.project.response.ProjectFunctionResponse;
import ssafy.aissue.domain.project.service.ProjectService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
@Tag(name = "Project", description = "프로젝트관리")
public class ProjectController {

    private final ProjectService projectService;

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @Operation(summary = "프로젝트 정보 조회", description = "프로젝트 정보를 조회하는 API입니다.")
    @GetMapping("/{jiraProjectKey}")
    public CommonResponse<ProjectDetailsResponse> getProject(@PathVariable String jiraProjectKey) {
        log.info("[ProjectController] 프로젝트 상세 정보 조회 >>>> jiraProjectKey: {}", jiraProjectKey);
        ProjectDetailsResponse response = projectService.getProject(jiraProjectKey);
        return CommonResponse.ok(response);
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @Operation(summary = "프로젝트 정보 수정", description = "프로젝트 정보를 수정하는 API입니다.")
    @PutMapping(consumes = { "multipart/form-data" })
    public CommonResponse<ProjectDetailsResponse> updateProject(@ModelAttribute @Validated ProjectUpdateRequest request ) {
        log.info("[ProjectController] 프로젝트 정보 수정 >>>> request: {}", request);
        ProjectDetailsResponse response = projectService.updateProject(request.toCommand());
        return CommonResponse.ok(response);
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @Operation(summary = "프로젝트 기능 정보 수정", description = "프로젝트 기능 정보를 수정하는 API입니다.")
    @PutMapping("/{jiraProjectKey}/functions")
    public CommonResponse<List<ProjectFunctionResponse>> updateProjectFunctions(
            @PathVariable String jiraProjectKey,
            @RequestBody List<ProjectFunctionRequest> functions) {
        log.info("[ProjectController] 프로젝트 기능 정보 수정 >>>> jiraProjectKey: {}", jiraProjectKey);
        List<ProjectFunctionResponse> updatedFunctions = projectService.updateProjectFunctions(jiraProjectKey, functions);
        return CommonResponse.ok(updatedFunctions);
    }



    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @Operation(summary = "프로젝트 기능 목록 조회", description = "특정 프로젝트의 기능 목록을 조회하는 API입니다.")
    @GetMapping("/{jiraProjectKey}/functions")
    public CommonResponse<List<ProjectFunctionResponse>> getProjectFunctions(@PathVariable String jiraProjectKey) {
        log.info("[ProjectController] 프로젝트 기능 목록 조회 >>>> jiraProjectKey: {}", jiraProjectKey);
        List<ProjectFunctionResponse> functions = projectService.getProjectFunctions(jiraProjectKey);
        return CommonResponse.ok(functions);
    }
}
