package com.JAVA.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.JAVA.Beans.Blog;
import com.JAVA.Beans.Comment;
import com.JAVA.Beans.User;
import com.JAVA.DAO.CommentDAO;
import com.JAVA.DAO.DAOFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/commentServlet")
public class CommentServlet extends HttpServlet {
    private static final String ACTION_ADD_COMMENT = "addComment";
    private static final String ACTION_DELETE_COMMENT = "deleteComment";
    private static final String PARAM_ACTION = "action";
    private static final String PARAM_BLOG_ID = "blogId";
    private static final String PARAM_COMMENT_ID = "commentId";
    private static final String PARAM_CONTENT = "content";
    private CommentDAO commentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        commentDAO = DAOFactory.getInstance().getCommentDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String action = request.getParameter(PARAM_ACTION);

        if (ACTION_ADD_COMMENT.equals(action)) {
            addComment(request, response, user);
        } else if (ACTION_DELETE_COMMENT.equals(action)) {
            deleteComment(request, response, user);
        }
        // Add other actions if needed
    }

    private void addComment(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        try {
            int blogId = Integer.parseInt(request.getParameter(PARAM_BLOG_ID));
            String content = request.getParameter(PARAM_CONTENT);

            Comment newComment = new Comment();
            newComment.setContent(content);
            newComment.setUser(user);
            newComment.setCommentDate(new Date()); // Set the current date as the comment date

            // Assume you have a method to get the associated blog
            Blog blog = DAOFactory.getInstance().getBlogDao().getBlogById(blogId);
            newComment.setBlog(blog);

            // Add the comment to the database
            commentDAO.addComment(newComment);

            // Redirect back to the blog page after adding the comment
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/blogServlet?action=viewBlogWithComments&blogId=" + blogId);
        } catch (NumberFormatException e) {
            // Handle the case where blogId is not a valid integer
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }




    private void deleteComment(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        try {
            int commentId = Integer.parseInt(request.getParameter(PARAM_COMMENT_ID));

            Comment commentToDelete = commentDAO.getCommentById(commentId);

            // Check if the user is the owner of the comment
            if (commentToDelete != null && commentToDelete.getUser().getId() == user.getId()) {
                commentDAO.deleteComment(commentId);
            }

            // Redirect back to the blog page after deleting the comment
            response.sendRedirect(request.getHeader("Referer"));
        } catch (NumberFormatException e) {
            // Handle the case where commentId is not a valid integer
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
