package com.JAVA.DAO;

import com.JAVA.Beans.Blog;
import com.JAVA.Beans.Comment;
import com.JAVA.Beans.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl implements CommentDAO {

    private final DAOFactory daoFactory;

    public CommentDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void addComment(Comment comment) throws DAOException {
        String sql = "INSERT INTO comments (content, blog_id, user_id, commentDate) VALUES (?, ?, ?, NOW())";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, comment.getContent());
            preparedStatement.setInt(2, comment.getBlog().getBlogId());
            preparedStatement.setInt(3, comment.getUser().getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Creating comment failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    comment.setCommentId(generatedKeys.getInt(1));
                } else {
                    throw new DAOException("Creating comment failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error adding comment", e);
        }
    }

    @Override
    public List<Comment> getCommentsByBlogId(int blogId) throws DAOException {
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM comments WHERE blog_id = ? ORDER BY commentDate DESC")) {

            preparedStatement.setInt(1, blogId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Comment comment = extractCommentFromResultSet(resultSet);
                    comments.add(comment);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error getting comments", e);
        }

        return comments;
    }


    @Override
    public List<Comment> getCommentsByUserId(int userId) throws DAOException {
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM comments WHERE user_id = ? ORDER BY commentDate DESC")) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Comment comment = extractCommentFromResultSet(resultSet);
                    comments.add(comment);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error getting comments", e);
        }

        return comments;
    }



 // Helper method to extract a Comment from a ResultSet
    private Comment extractCommentFromResultSet(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(resultSet.getInt("comment_id"));
        comment.setContent(resultSet.getString("content"));
        comment.setCommentDate(resultSet.getTimestamp("commentDate"));

        User user = new User();
        user.setId(resultSet.getInt("user_id"));
        comment.setUser(user);

        Blog blog = new Blog();
        blog.setBlogId(resultSet.getInt("blog_id"));
        comment.setBlog(blog);

        return comment;
    }
    @Override
    public Comment getCommentById(int commentId) throws DAOException {
        Comment comment = null;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM comments WHERE comment_id = ?")) {

            preparedStatement.setInt(1, commentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    comment = extractCommentFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error getting comment by ID", e);
        }

        return comment;
    }

    @Override
    public void deleteComment(int commentId) throws DAOException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM comments WHERE comment_id = ?")) {

            preparedStatement.setInt(1, commentId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Deleting comment failed, no rows affected.");
            }

        } catch (SQLException e) {
            throw new DAOException("Error deleting comment", e);
        }
    }
}
