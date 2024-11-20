package ssafy.aissue.common.exception.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        String jsonResponse = """
                {
                    "code": 401,
                    "message": "엑세트 토큰 인증이 필요합니다.",
                    "result": {
                        "path": "%s"}
                }
                """.formatted(request.getRequestURI());

        response.getWriter().write(jsonResponse);
    }
}