package com.JAVA.DAO;

import com.JAVA.Beans.Comment;
import java.util.List;

public interface CommentDAO {
    void addComment(Comment comment) throws DAOException;
    List<Comment> getCommentsByBlogId(int blogId) throws DAOException;
	List<Comment> getCommentsByUserId(int userId) throws DAOException;
    Comment getCommentById(int commentId) throws DAOException;
    void deleteComment(int commentId) throws DAOException;
}
