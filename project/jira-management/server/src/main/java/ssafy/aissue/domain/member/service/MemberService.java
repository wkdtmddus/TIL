package ssafy.aissue.domain.member.service;

import ssafy.aissue.api.member.response.MemberJiraIdResponse;
import ssafy.aissue.api.member.response.MemberSignupResponse;
import ssafy.aissue.domain.member.command.MemberSignupCommand;

import java.util.List;


public interface MemberService {
    MemberSignupResponse signupMember(MemberSignupCommand signupCommand);
    MemberJiraIdResponse getJiraId();
    String deleteMember();
    List<String> getProjectKeysForMember(Long memberId);
}
