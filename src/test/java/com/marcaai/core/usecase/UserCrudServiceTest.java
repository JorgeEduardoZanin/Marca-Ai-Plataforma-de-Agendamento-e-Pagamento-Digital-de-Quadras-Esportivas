package com.marcaai.core.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.marcaai.core.domain.Role;
import com.marcaai.core.domain.User;
import com.marcaai.core.exception.UserCrudException;
import com.marcaai.core.port.out.RoleRepository;
import com.marcaai.core.port.out.UserCrudRepository;

@ExtendWith(MockitoExtension.class)
class UserCrudServiceTest {
	@Mock
	private UserCrudRepository userCrudRepository;

	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	
	@Mock
	private RoleRepository roleRepository;
	
	@InjectMocks
	private UserCrudService userCrudService;
	
	@Captor
	private ArgumentCaptor<User> userArgumentCaptor;
	
	@Captor
	private ArgumentCaptor<String> roleArgumentCaptor;
	
	@Captor
	private ArgumentCaptor<String> passwordArgumentCaptor;
	
	public  User createSampleDomainUser() {
	       
	        Role roleUser = new Role(1L, "BASIC");

	      
	        User user = new User(
	            "João Silva",              
	            "11988887777",            
	            "12345678901",             
	            "joao.silva@example.com",  
	            "SP",                     
	            "Rua das Flores",         
	            "123",                 
	            "São Paulo",            
	            "01001000",               
	            "Centro",                
	            "Apto 45",               
	            LocalDate.of(1990, 5, 20), 
	            "senhaCriptografada"      
	        );

	        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
	        
	        user.setId(UUID.fromString("22222222-2222-2222-2222-222222222222"));
	        user.setCreation_date(LocalDateTime.of(2025, 6, 10, 14, 30));

	        user.setRoles(Set.of(roleUser));

	        return user;
	    }
	
	@Nested 
	class createUser{
		
		@Test
		@DisplayName("Should create a user with sucess")
		void ShouldCreateAUserWithSucess() {
			
			 Role roleUser = new Role(1L, "BASIC");
			
			User user = createSampleDomainUser();
			
			doReturn(roleUser).when(roleRepository).findByName(roleUser.getName());
			
			userCrudService.createUser(user);

			verify(roleRepository, times(1)).findByName(roleArgumentCaptor.capture());
			verify(userCrudRepository, times(1)).createUser(user);
			
			assertEquals(roleArgumentCaptor.getValue(), roleUser.getName());
			assertNotNull(roleArgumentCaptor.getValue());
			
		}
		
		@Test
		@DisplayName("It should give an error on a null role")
		void ItShouldGiveAnErrorOnANullRole() {
			
			 Role roleUser = new Role(1L, "BASIC");
			
			User user = createSampleDomainUser();
			
			doReturn(null).when(roleRepository).findByName(roleUser.getName());
			
			assertThrows(NullPointerException.class, () ->{
				userCrudService.createUser(user);
			});

			verify(roleRepository, times(1)).findByName(roleArgumentCaptor.capture());
			
		}

	}
	
	@Nested
	class updateUser{
		
		@Test
		@DisplayName("Should update a user with sucess")
		void ShouldUpdateAUserWithSucess() {
			User user = new User();
			user.setAdress("rua batata palha");
			user.setCity("nova cidade");
			
			doReturn(user).when(userCrudRepository).updateUser(any());

			var input = userCrudService.updateUser(UUID.randomUUID(), user);
			
			verify(userCrudRepository, times(1)).updateUser(userArgumentCaptor.capture());
			assertEquals(input.getAdress(), userArgumentCaptor.getValue().getAdress());
			assertNotNull(input);
		}
		
		@Test
		@DisplayName("Should error a update from user null")
		void ShouldErrorAUpdateFromUserNull() {

			User user = createSampleDomainUser();
			
			assertThrows(NullPointerException.class, ()->{
				userCrudService.updateUser(UUID.randomUUID(), null);
			});
			
			assertThrows(IllegalArgumentException.class, ()->{
				userCrudService.updateUser(null, user);
			});
			
			verify(userCrudRepository, times(0)).updateUser(any());
			
		}		
	}
	
	@Nested
	class deleteUser{
	
		@Test
		@DisplayName("Should delete a user with sucess")
		void ShouldDeleteAUserWithSucess(){
			
			userCrudRepository.deleteUser(UUID.randomUUID());
			
			verify(userCrudRepository, times(1)).deleteUser(any());
			
		}
		
		@Test
		@DisplayName("Should error a delete from user null")
		void ShouldErrorADeleteFromUserNull() {
			
			UUID id = null;
			
			assertThrows(IllegalArgumentException.class, ()->{
				userCrudService.deleteUser(id);
			});
			verify(userCrudRepository, times(0)).deleteUser(id);
			
		}
		
	}
	
	@Nested
	class getUserById{
		
		@Test
		@DisplayName("Should get a user with sucess")
		void ShouldGetAUserWithSucess(){
			
			User user = createSampleDomainUser();
			UUID id = UUID.randomUUID();
			
			doReturn(user).when(userCrudRepository).getUserById(id);
			
			var input = userCrudService.getUserById(id);
			
			assertEquals(input.getDate_of_birth(), user.getDate_of_birth());
			assertNotNull(input);
			
			verify(userCrudRepository, times(1)).getUserById(id);
		
		}
		
		@Test
		@DisplayName("Should error a get from user null")
		void ShouldErrorAGetFromUserNull() {
			
			UUID id = null;
			
			assertThrows(IllegalArgumentException.class, ()->{
				userCrudService.getUserById(id);
			});
			verify(userCrudRepository, times(0)).deleteUser(id);
			
		}
	}
	
	@Nested
	class updatePassword{
		
		@Test
		@DisplayName("Should update password a user with sucess")
		void ShouldUpdatePasswordAUserWithSucess() {
			
			 UUID id = UUID.randomUUID();
			    String encryptedOld = new BCryptPasswordEncoder().encode("senhasecreta");
			    String encryptedNew = "hash-gerado-pelo-encoder";
			    
			   
			    doReturn(encryptedOld).when(userCrudRepository).findPasswordById(id);
			    doReturn(false).when(passwordEncoder).matches("senhaDiferenciada",encryptedOld);
			    doReturn(encryptedNew).when(passwordEncoder).encode("senhaDiferenciada");
			    
			    assertDoesNotThrow(() -> userCrudService.updatePassword(id, "senhaDiferenciada"));
			    
			    verify(userCrudRepository).findPasswordById(id);
			    verify(userCrudRepository).updatePassword(id, encryptedNew);	
			
		}
		
		@Test
		@DisplayName("Should error a update password from user")
		void ShouldErrorAUpdatePasswordFromUser() {
			
			UUID id = UUID.randomUUID();
			String encryptedOld = new BCryptPasswordEncoder().encode("novasenha");
			
			 doReturn(encryptedOld).when(userCrudRepository).findPasswordById(id);
			doReturn(true).when(passwordEncoder).matches("novasenha", encryptedOld);

			assertThrows(UserCrudException.class, ()->{
				userCrudService.updatePassword(id, "novasenha");
			});
			
			verify(userCrudRepository, times(0)).updatePassword(any(), any());
			
			
		}
		
		@Test
		@DisplayName("Should error a update password from user null")
		void ShouldErrorAUpdatePasswordFromUserNull() {
			
			UUID id = null;

			assertThrows(IllegalArgumentException.class, ()->{
				userCrudService.updatePassword(id, "novasenha");
			});
			
			verify(userCrudRepository, times(0)).findPasswordById(id);
			verify(userCrudRepository, times(0)).updatePassword(id, "novasenha");;
			
			
		}
		
		
		
	}
	
	
	
	
}
