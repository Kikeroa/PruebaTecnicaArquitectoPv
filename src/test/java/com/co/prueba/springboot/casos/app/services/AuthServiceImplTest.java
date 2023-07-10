package com.co.prueba.springboot.casos.app.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;

import com.co.prueba.springboot.casos.app.models.entity.Usuario;
import com.co.prueba.springboot.casos.app.models.repository.IUsuarioRepository;

public class AuthServiceImplTest {

    private AuthServiceImpl authService;

    @Mock
    private IUsuarioRepository usuarioRepository;

    @Captor
    private ArgumentCaptor<Usuario> usuarioCaptor;


    @Test
    public void testValidarToken_ValidToken_NoExceptionThrown() {
        // Arrange
        String token = "validtoken";

        // Act
        assertDoesNotThrow(() -> authService.validarToken(token));
    }

    @Test
    public void testValidarToken_InvalidToken_ThrowsInvalidCsrfTokenException() {
        // Arrange
        String invalidToken = "invalidtoken";

        // Act & Assert
        assertThrows(InvalidCsrfTokenException.class, () -> authService.validarToken(invalidToken));
    }

    @Test
    public void testObtenerDispositivosRegistrados_ReturnsDispositivosRegistrados() {
        // Arrange
        String token = "validtoken";

        // Act
        String dispositivosRegistrados = authService.obtenerDispositivosRegistrados(token);

        // Assert
        assertEquals("DISPOSITIVOS_REGISTRADOS", dispositivosRegistrados);
    }

    @Test
    public void testFindByToken_ExistingToken_ReturnsUsuario() {
        // Arrange
        String token = "existingtoken";

        Usuario existingUser = new Usuario();
        existingUser.setNombre("testuser");
        existingUser.setToken(token);

        when(usuarioRepository.findByToken(token)).thenReturn(existingUser);

        // Act
        Usuario result = authService.findByToken(token);

        // Assert
        assertNotNull(result);
        assertEquals(existingUser.getNombre(), result.getNombre());
        assertEquals(existingUser.getToken(), result.getToken());
        Mockito.verify(usuarioRepository, Mockito.times(1)).findByToken(token);
    }

    @Test
    public void testFindByToken_NonExistingToken_ReturnsNull() {
        // Arrange
        String nonExistingToken = "nonexistingtoken";

        when(usuarioRepository.findByToken(nonExistingToken)).thenReturn(null);

        // Act
        Usuario result = authService.findByToken(nonExistingToken);

        // Assert
        assertNull(result);
        Mockito.verify(usuarioRepository, Mockito.times(1)).findByToken(nonExistingToken);
    }
}
