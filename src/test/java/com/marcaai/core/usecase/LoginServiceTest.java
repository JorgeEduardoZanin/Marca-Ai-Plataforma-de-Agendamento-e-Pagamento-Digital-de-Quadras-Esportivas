package com.marcaai.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import com.marcaai.adapter.dto.response.login.LoginResponse;
import com.marcaai.core.domain.Login;
import com.marcaai.core.domain.Role;
import com.marcaai.core.exception.LoginException;
import com.marcaai.core.port.out.internal.LoginRepository;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

	@Mock
	private LoginRepository loginRepository;
	
	@InjectMocks
	private LoginService loginService;
	
	@Mock
	private BCryptPasswordEncoder passwordEncoder;

	@Mock
	private JwtEncoder jwtEncoder;
	
	@Captor
	private ArgumentCaptor<LoginResponse> loginResponseArgumentCaptor;
	


	@Nested
	class login{
		
		public  Login createSampleLogin() {
	      
	        Login login = new Login("joao.silva@example.com","senha");
	        Role role = new Role(1L, "BASIC");
	        login.setRoles(Set.of(role));
	        login.setId(UUID.randomUUID());
	        return login;
	    }
		
		@Test
		@DisplayName("Should login a user with sucess")
		void ShouldLoginAUserWithSucess() {
			
			
			Login login = createSampleLogin();
			String encryptPassword = new BCryptPasswordEncoder().encode("senha");
			
			Login stored = login;
			stored.setPassword(encryptPassword);
			
			doReturn(stored).when(loginRepository).findByEmail(login.getEmail());
			doReturn(true).when(passwordEncoder).matches(login.getPassword(), encryptPassword);
			Instant now = Instant.now();
			Jwt realJwt = new Jwt(
			    "dummy-token",                 
			    now,                          
			    now.plusSeconds(300),        
			    Map.of("alg", "none"),        
			    Map.of("sub", "2222")          
			);
			doReturn(realJwt).when(jwtEncoder).encode(any());
			
			var input = loginService.login(login);
			
			assertEquals("dummy-token", input.getToken());
			assertEquals(300L, input.getExpireIn());			
			
		}
		
		
		@Test
		@DisplayName("It should give an error when the login is invalid")
		void InvalidPasswordOrEmail() {
			
			
			Login login = createSampleLogin();
			String encryptPassword = new BCryptPasswordEncoder().encode("batata");
			
			Login stored = login;
			stored.setPassword(encryptPassword);
			
			doReturn(stored).when(loginRepository).findByEmail(login.getEmail());
			doReturn(false).when(passwordEncoder).matches(login.getPassword(), stored.getPassword());
			
			assertThrows(LoginException.class, () -> {
				loginService.login(login);
			});
			
		}
		
	}

}
