package com.JAVA.DAO;

import com.JAVA.Beans.Blog;
import com.JAVA.Beans.Comment;
import com.JAVA.Beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogDaoImpl implements BlogDAO {

    private DAOFactory daoFactory;
    private static UserDaoImpl userDaoImpl;

    // Constructor
    public BlogDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static Blog map(ResultSet resultSet) throws SQLException {
        Blog blog = new Blog();
        blog.setBlogId(resultSet.getInt("blog_id"));
        blog.setTitle(resultSet.getString("title"));
        blog.setDescription(resultSet.getString("description"));
        blog.setPicture(resultSet.getBytes("picture"));
        blog.setPublicationDate(resultSet.getTimestamp("publicationDate"));
        blog.setUser(new User());
        return blog;
    }

    @Override
    public void addBlog(Blog blog) throws DAOException {
        // Vérifier si l'utilisateur existe et si la contrainte de clé étrangère est respectée
        validateUserAndForeignKey(blog);

        final String SQL_INSERT = "INSERT INTO blogs (title, description, picture, publicationDate, user_id) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, blog.getTitle());
            preparedStatement.setString(2, blog.getDescription());
            preparedStatement.setBytes(3, blog.getPicture());

            // Check if publicationDate is not null before setting it in the PreparedStatement
            if (blog.getPublicationDate() != null) {
                preparedStatement.setTimestamp(4, new Timestamp(blog.getPublicationDate().getTime()));
            } else {
                // Set publicationDate to the current date if it's null
                preparedStatement.setTimestamp(4, new Timestamp(new Date().getTime()));
            }

            preparedStatement.setLong(5, blog.getUser().getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating blog failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    blog.setBlogId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating blog failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    // Méthode privée pour valider l'existence de l'utilisateur et la contrainte de clé étrangère
    private void validateUserAndForeignKey(Blog blog) throws DAOException {
        if (!userExists(blog.getUser().getId())) {
            throw new DAOException("User does not exist. Cannot add the blog.");
        }

        if (!foreignkeyConstraintIsSatisfied(blog.getUser().getId())) {
            throw new DAOException("Foreign key constraint violated. Cannot add the blog.");
        }
    }
    private boolean userExists(long userId) {
        final String SQL_USER_EXISTS = "SELECT COUNT(*) FROM users WHERE id = ?";

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_USER_EXISTS)
        ) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            // Gérer l'exception ou logger selon vos besoins
            e.printStackTrace();
        }

        return false;
    }

    private boolean foreignkeyConstraintIsSatisfied(long userId) {
        // Ajoutez ici la logique pour vérifier si la contrainte de clé étrangère est respectée
        // Vous devrez peut-être effectuer une requête spécifique ou utiliser une autre approche en fonction de votre modèle de données

        // Exemple basique pour la démo
        return true;
    }



    @Override
    public Blog getBlogById(int blogId) throws DAOException {
        final String SQL_SELECT_BY_ID = "SELECT b.blog_id, b.title, b.description, b.picture, b.publicationDate, " +
                "u.id as user_id, u.name as user_name, u.password as user_password, u.picture as user_picture, u.email as user_email " +
                "FROM blogs b " +
                "INNER JOIN users u ON b.user_id = u.id " +
                "WHERE b.blog_id = ?";

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement preparedStatement = initRequestPrepare(connection, SQL_SELECT_BY_ID, blogId);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (resultSet.next()) {
                return map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return null;
    }


    @Override
    public List<Blog> getBlogsByUserId(int userId) throws DAOException {
        final String SQL_SELECT_BY_USER_ID = "SELECT b.blog_id, b.title, b.description, b.picture, b.publicationDate, " +
                "u.id as user_id, u.name as user_name, u.password as user_password, u.picture as user_picture, u.email as user_email " +
                "FROM blogs b " +
                "INNER JOIN users u ON b.user_id = u.id " +
                "WHERE b.user_id = ?";

        List<Blog> blogList = new ArrayList<>();

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement preparedStatement = initRequestPrepare(connection, SQL_SELECT_BY_USER_ID, userId);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Blog blog = map(resultSet);
                blogList.add(blog);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return blogList;
    }

    @Override
    public List<Blog> searchBlogsByTitle(String title) throws DAOException {
        final String SQL_SEARCH_BY_TITLE = "SELECT b.blog_id, b.title, b.description, b.picture, b.publicationDate, " +
                "u.id as user_id, u.name as user_name, u.password as user_password, u.picture as user_picture, u.email as user_email " +
                "FROM blogs b " +
                "INNER JOIN users u ON b.user_id = u.id " +
                "WHERE b.title LIKE ?";

        List<Blog> blogList = new ArrayList<>();

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement preparedStatement = initRequestPrepare(connection, SQL_SEARCH_BY_TITLE, "%" + title + "%");
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Blog blog = map(resultSet);
                blogList.add(blog);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return blogList;
    }

    @Override
    public void updateBlog(Blog blog) throws DAOException {
        final String SQL_UPDATE = "UPDATE blogs SET user_id = ?, title = ?, description = ?, picture = ? WHERE blog_id = ?";

        try (
            Connection connection = daoFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)
        ) {
            // Set parameters for the PreparedStatement
            preparedStatement.setInt(1, blog.getUser().getId());
            preparedStatement.setString(2, blog.getTitle());
            preparedStatement.setString(3, blog.getDescription());
            preparedStatement.setBytes(4, blog.getPicture());
            preparedStatement.setInt(5, blog.getBlogId());

            int rowsUpdated = preparedStatement.executeUpdate();
            
            // Log the number of rows updated
            System.out.println("Rows updated: " + rowsUpdated);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }



    @Override
    public void deleteBlog(int blogId) throws DAOException {
        final String SQL_DELETE = "DELETE FROM blogs WHERE blog_id = ?";

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement preparedStatement = initRequestPrepare(connection, SQL_DELETE, blogId)
        ) {
            preparedStatement.executeUpdate();
            System.out.println("Blog deleted successfully");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }



    private static PreparedStatement initRequestPrepare(Connection connection, String sql, Object... objects)
            throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }
        return preparedStatement;
    }

    @Override
    public List<Blog> getAllBlogs() throws DAOException {
        final String SQL_SELECT_ALL = "SELECT b.blog_id, b.title, b.description, b.picture, b.publicationDate, " +
                "u.id as user_id, u.name as user_name, u.password as user_password, u.picture as user_picture, u.email as user_email " +
                "FROM blogs b " +
                "INNER JOIN users u ON b.user_id = u.id";

        List<Blog> blogList = new ArrayList<>();

        try (
            Connection connection = daoFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Blog blog = map(resultSet);
                // Create a User object and set it in the Blog
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("user_password"));
                user.setPicture(resultSet.getBytes("user_picture"));
                user.setEmail(resultSet.getString("user_email"));
                blog.setUser(user);

                blogList.add(blog);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return blogList;
    }
    
    
    @Override
    public List<Comment> getCommentsByBlogId(int blogId) throws DAOException {
        List<Comment> comments = new ArrayList<>();

        // Déclarez userDaoImpl ici et initialisez-le
        UserDaoImpl userDaoImpl = new UserDaoImpl(daoFactory);

        final String SQL_SELECT_COMMENTS = "SELECT comment_id, content, commentDate, user_id FROM comments WHERE blog_id = ?";

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement preparedStatement = initRequestPrepare(connection, SQL_SELECT_COMMENTS, blogId);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setCommentId(resultSet.getInt("comment_id"));
                comment.setContent(resultSet.getString("content"));
                comment.setCommentDate(resultSet.getTimestamp("commentDate"));

                // Utilisez userDaoImpl déclaré localement
                User user = userDaoImpl.getUserById(resultSet.getInt("user_id"));
                comment.setUser(user);

                // Ajoutez le commentaire à la liste
                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return comments;
    }



}
