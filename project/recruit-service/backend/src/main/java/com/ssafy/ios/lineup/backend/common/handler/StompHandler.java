package com.ssafy.ios.lineup.backend.common.handler;

//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class StompHandler implements ChannelInterceptor {
//
//    private final JWTUtil jwtUtil;
//
//    //websocket을 통해 들어온 요청이 처리되기 전에 실행됨.
//    @Override
//    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        return message;
//    }

//    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//
//
//        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//            String authorization = accessor.getNativeHeader("Authorization").get(0);
//
//            if (authorization != null && !authorization.isEmpty()) {
//                String token = authorization.replace("Bearer ", "");
//                try {
//                    if (jwtUtil.isExpired(token)) {
//                        log.info("StompHandler : 만료된 JWT 토큰입니다.");
//                        return null; // 연결 거부
//                    }
//                    log.info("StompHandler : 유효한 JWT 토큰입니다.");
//
//                    // 유저 정보 저장하기
//                    log.info("StompHandler : 유저 정보를 저장합니다.");
//                    setAuthenticationToContext(token);
//                    accessor.setUser(SecurityContextHolder.getContext().getAuthentication());
//                } catch (Exception e) {
//                    log.error("StompHandler : JWT 토큰 처리 중 예외 발생", e);
//                    return null; // 연결 거부
//                }
//            } else {
//                log.info("StompHandler : 유효한 Authorization 헤더가 없습니다.");
//                return null; // 연결 거부
//            }
//        }
//
//        return message;
//    }

//    private void setAuthenticationToContext(String token) {
//        String email = jwtUtil.getUsername(token);
//        String role = jwtUtil.getRole(token);
//
//        User user = User.builder()
//                .email(email)
//                .role(role)
//                .build();
//
//        CustomUserDetails customUserDetails = new CustomUserDetails(user);
//        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null,
//                customUserDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//    }
//}
