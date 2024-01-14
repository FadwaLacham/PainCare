package com.JAVA.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.JAVA.Beans.Alarm;
import com.JAVA.Beans.User;
import com.JAVA.DAO.AlarmDAO;
import com.JAVA.DAO.DAOFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/alarmServlet")
public class AlarmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final AlarmDAO alarmDAO;

    public AlarmServlet() {
        super();
        alarmDAO = DAOFactory.getInstance().getAlarmDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser != null) {
            try {
                List<Alarm> userAlarms = alarmDAO.getAlarmsByUserId(loggedInUser.getId());

                request.setAttribute("userAlarms", userAlarms);

                request.getRequestDispatcher("displayAlarms.jsp").forward(request, response);
            } catch (SQLException e) {
                log("Error retrieving alarms", e);
                response.sendRedirect("error.jsp?message=Error retrieving alarms");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    addAlarm(request, response);
                    break;
                case "delete":
                    deleteAlarm(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is required");
        }
    }

    private void addAlarm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String timeString = request.getParameter("time"); // Obtenir la chaîne de temps

        // Convertir la chaîne de temps en objet Time
        Time time = Time.valueOf(LocalTime.parse(timeString));

        String[] repeatDays = request.getParameterValues("repeatDays");

        if (title != null && time != null && repeatDays != null) {
            Alarm newAlarm = new Alarm();
            newAlarm.setTitle(title);
            newAlarm.setTime(time);
            newAlarm.setRepeatDays(new HashSet<>(Arrays.asList(repeatDays)));

            HttpSession session = request.getSession();
            User loggedInUser = (User) session.getAttribute("user");

            if (loggedInUser != null) {
                try {
                    alarmDAO.insertAlarm(newAlarm, loggedInUser.getId());
                } catch (SQLException e) {
                    log("Error adding alarm", e);
                    response.sendRedirect("error.jsp?message=Error adding alarm");
                    return;
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
            return;
        }

        response.sendRedirect("alarmServlet");
    }

    private void deleteAlarm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int alarmId;

        try {
            alarmId = Integer.parseInt(request.getParameter("alarmId"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid alarmId");
            return;
        }

        try {
            alarmDAO.deleteAlarm(alarmId);
        } catch (SQLException e) {
            log("Error deleting alarm", e);
            response.sendRedirect("error.jsp?message=Error deleting alarm");
            return;
        }

        response.sendRedirect("alarmServlet");
    }
}
