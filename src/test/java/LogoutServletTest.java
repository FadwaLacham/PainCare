import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import com.JAVA.servlets.LogoutServlet;

public class LogoutServletTest {

    private LogoutServlet logoutServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        logoutServlet = new LogoutServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void testDoGet_WithSession() throws Exception {
        // Simulate an existing session
        when(request.getSession(false)).thenReturn(session);

        // Act - call the servlet's doGet method
        logoutServlet.doGet(request, response);

        // Verify session was invalidated and redirection occurred
        verify(session).invalidate();
        verify(response).sendRedirect("login.jsp");
    }

    @Test
    public void testDoGet_WithoutSession() throws Exception {
        // Simulate no session existing
        when(request.getSession(false)).thenReturn(null);

        // Act - call the servlet's doGet method
        logoutServlet.doGet(request, response);

        // Verify no invalidation occurs, but redirection still happens
        verify(session, never()).invalidate();
        verify(response).sendRedirect("login.jsp");
    }
}
