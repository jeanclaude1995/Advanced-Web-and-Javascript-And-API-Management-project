package fr.john.project.security;

import fr.john.project.dto.AuthRequest;
import fr.john.project.dto.UserPrincipal;
import java.time.Instant;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/public")
@RequiredArgsConstructor
public class AuthApi
{
  private final CustomAuthenticationProvider provider;
  private final JwtEncoder jwtEncoder;
  
  @PostMapping("login")
  public ResponseEntity<UserPrincipal> login(@RequestBody @Valid AuthRequest request)
  {
    try
    {
      Authentication authentication = provider
        .authenticate(
          new UsernamePasswordAuthenticationToken(
            request.getUsername(), request.getPassword()
          )
        );
      
      String scope = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));
      
      UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
      
      Instant now = Instant.now();
      long expiry = 36000L;
      
      JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("fr.john")
        .issuedAt(now)
        .expiresAt(now.plusSeconds(expiry))
        .subject(user.subject())
        .claim("roles", scope)
        .build();
      
      String token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
      
      return ResponseEntity.ok()
        .header(HttpHeaders.AUTHORIZATION, token)
        .body(user);
    }
    catch (BadCredentialsException ex)
    {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  
  @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
  public String fetchSignoutSite(HttpServletRequest request, HttpServletResponse response)
  {
    
    HttpSession session = request.getSession(false);
    SecurityContextHolder.clearContext();
    
    session = request.getSession(false);
    if (session != null)
    {
      session.invalidate();
    }
    
    return "redirect:/login?logout";
  }

}