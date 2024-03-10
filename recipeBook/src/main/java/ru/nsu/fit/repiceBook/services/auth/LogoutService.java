package ru.nsu.fit.repiceBook.services.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import ru.nsu.fit.repiceBook.model.Token;
import ru.nsu.fit.repiceBook.services.repositories.TokenRepository;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
  private final TokenRepository tokenRepository;

  @Override
  public void logout(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication
  ) {
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    jwt = authHeader.substring(7);
    Token storedToken = tokenRepository.findByAccessToken(jwt)
        .orElse(null);
    if (storedToken != null) {
      storedToken.setActive(false);
      tokenRepository.save(storedToken);
      SecurityContextHolder.clearContext();
    }
  }
}
