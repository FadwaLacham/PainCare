import static org.mockito.Mockito.*;

import org.junit.Test;

import com.JAVA.servlets.RegisterServlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServletTest {

    @Test
    public void testDoPost_SuccessfulRegistration() throws Exception {
        // Arrange
        RegisterServlet servlet = new RegisterServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        
        when(request.getParameter("name")).thenReturn("Test User");
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("confirmPassword")).thenReturn("password");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(dispatcher, times(1)).forward(request, response);
        // Add more assertions as necessary
    }
}
