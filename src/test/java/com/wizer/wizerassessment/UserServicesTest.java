package com.wizer.wizerassessment;

import static org.mockito.Mockito.*;

import com.wizer.wizerassessment.repositories.UserRepository;
import com.wizer.wizerassessment.service.UserService;
import org.junit.jupiter.api.Test;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServicesTest {

    // Assuming UserController and UserRepository are properly injected or instantiated

    @Test
    public void testCreateAdmin() {
        // Mock UserRepository
        UserRepository userRepository = mock(UserRepository.class);

        // Mock BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = mock(BCryptPasswordEncoder.class);

        // Create an instance of UserController with the mocked dependencies
        UserService userController = new UserController(userRepository, passwordEncoder);

        // Create a UserRequestDto for testing
        UserRequestDto userRequest = new UserRequestDto();
        userRequest.setFirstName("John");
        userRequest.setLatName("Doe");
        userRequest.setEmail("john.doe@example.com");
        userRequest.setPassword("password");
        userRequest.setConfirmPassword("password");
        userRequest.setPhoneNumber("1234567890");

        // Mock the behavior of UserRepository.findByEmail
        when(userRepository.findByEmail(anyString())).thenReturn(java.util.Optional.empty());

        // Mock the behavior of BCryptPasswordEncoder.encode
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // Call the method to test
        UserResponse userResponse = userController.createAdmin(userRequest);

        // Verify that UserRepository.save is called with the correct arguments
        verify(userRepository, times(1)).save(any());

        // Verify that the returned UserResponse has the expected values
        assertEquals("John", userResponse.getFirstName());
        assertEquals("Doe", userResponse.getLastName());
        assertEquals("john.doe@example.com", userResponse.getEmail());
    }
}