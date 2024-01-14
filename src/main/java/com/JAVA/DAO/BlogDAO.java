package com.JAVA.DAO;

import com.JAVA.Beans.Blog;
import com.JAVA.Beans.Comment;

import java.util.List;

public interface BlogDAO {
    void addBlog(Blog blog) throws DAOException;
    Blog getBlogById(int blogId) throws DAOException;
    List<Blog> getAllBlogs() throws DAOException;
    List<Blog> getBlogsByUserId(int userId) throws DAOException;
    List<Blog> searchBlogsByTitle(String title) throws DAOException;
    void updateBlog(Blog blog) throws DAOException;
    void deleteBlog(int blogId) throws DAOException;
	List<Comment> getCommentsByBlogId(int blogId) throws DAOException;
}
