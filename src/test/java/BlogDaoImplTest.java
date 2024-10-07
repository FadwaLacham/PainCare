import com.JAVA.Beans.Blog;
import com.JAVA.Beans.Comment;
import com.JAVA.Beans.User;
import com.JAVA.DAO.BlogDaoImpl;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.DAOFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BlogDaoImplTest {

    private DAOFactory daoFactory;
    private BlogDaoImpl blogDao;
    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        daoFactory = Mockito.mock(DAOFactory.class);
        connection = Mockito.mock(Connection.class);
        when(daoFactory.getConnection()).thenReturn(connection);
        blogDao = new BlogDaoImpl(daoFactory);
    }



    @Test
    public void testGetBlogById() throws SQLException, DAOException {
        Blog expectedBlog = new Blog();
        expectedBlog.setBlogId(1);
        expectedBlog.setTitle("Test Title");
        expectedBlog.setDescription("Test Description");

        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("blog_id")).thenReturn(expectedBlog.getBlogId());
        when(resultSet.getString("title")).thenReturn(expectedBlog.getTitle());
        when(resultSet.getString("description")).thenReturn(expectedBlog.getDescription());

        Blog actualBlog = blogDao.getBlogById(1);

        assertEquals(expectedBlog.getBlogId(), actualBlog.getBlogId());
        assertEquals(expectedBlog.getTitle(), actualBlog.getTitle());
        assertEquals(expectedBlog.getDescription(), actualBlog.getDescription());
    }

    @Test
    public void testGetBlogsByUserId() throws SQLException, DAOException {
        List<Blog> expectedBlogs = new ArrayList<>();
        Blog blog1 = new Blog();
        blog1.setBlogId(1);
        blog1.setTitle("Test Title 1");
        expectedBlogs.add(blog1);
        
        Blog blog2 = new Blog();
        blog2.setBlogId(2);
        blog2.setTitle("Test Title 2");
        expectedBlogs.add(blog2);

        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("blog_id")).thenReturn(1, 2);
        when(resultSet.getString("title")).thenReturn("Test Title 1", "Test Title 2");

        List<Blog> actualBlogs = blogDao.getBlogsByUserId(1);

        assertEquals(expectedBlogs.size(), actualBlogs.size());
        assertEquals(expectedBlogs.get(0).getTitle(), actualBlogs.get(0).getTitle());
        assertEquals(expectedBlogs.get(1).getTitle(), actualBlogs.get(1).getTitle());
    }

    @Test
    public void testDeleteBlog() throws SQLException, DAOException {
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        blogDao.deleteBlog(1);

        // Verify the actual method that is called
        verify(preparedStatement, times(1)).setObject(1, 1); // Adjusted to match actual call
    }

    @Test
    public void testGetAllBlogs() throws SQLException, DAOException {
        List<Blog> expectedBlogs = new ArrayList<>();
        Blog blog1 = new Blog();
        blog1.setBlogId(1);
        blog1.setTitle("Test Title 1");
        expectedBlogs.add(blog1);

        Blog blog2 = new Blog();
        blog2.setBlogId(2);
        blog2.setTitle("Test Title 2");
        expectedBlogs.add(blog2);

        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("blog_id")).thenReturn(1, 2);
        when(resultSet.getString("title")).thenReturn("Test Title 1", "Test Title 2");

        List<Blog> actualBlogs = blogDao.getAllBlogs();

        assertEquals(expectedBlogs.size(), actualBlogs.size());
        assertEquals(expectedBlogs.get(0).getTitle(), actualBlogs.get(0).getTitle());
        assertEquals(expectedBlogs.get(1).getTitle(), actualBlogs.get(1).getTitle());
    }

    @Test
    public void testGetCommentsByBlogId() throws SQLException, DAOException {
        List<Comment> expectedComments = new ArrayList<>();
        Comment comment = new Comment();
        comment.setCommentId(1);
        comment.setContent("Test Comment");
        expectedComments.add(comment);

        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("comment_id")).thenReturn(1);
        when(resultSet.getString("content")).thenReturn("Test Comment");

        List<Comment> actualComments = blogDao.getCommentsByBlogId(1);

        assertEquals(expectedComments.size(), actualComments.size());
        assertEquals(expectedComments.get(0).getContent(), actualComments.get(0).getContent());
    }
}
