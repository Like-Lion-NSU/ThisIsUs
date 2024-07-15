package thisisus.school.auth.infrastructure;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import thisisus.school.auth.dto.response.AuthResponse;
import thisisus.school.member.domain.Member;

@Component
@RequiredArgsConstructor
public class AuthTokenGenerator {

	private final JwtTokenProvider jwtTokenProvider;

	public AuthResponse generate(Member member) {
		String accessToken = jwtTokenProvider.createAccessToken(member.getId(), member.getRole().name());
		String refreshToken = jwtTokenProvider.createRefreshToken(member.getId(), member.getRole().name());
		return new AuthResponse(accessToken, refreshToken);
	}

	public boolean isValidToken(String token) {
		return jwtTokenProvider.validateToken(token);
	}

	public Long extractMemberId(String refreshToken) {
		return Long.valueOf(jwtTokenProvider.getMemberId(refreshToken));
	}
}