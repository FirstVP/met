package grp1.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import  java.util.ArrayList;

import grp1.DataBaseConnectionTester;
import grp1.model.News;

/**
 * Created by admin on 19.02.2017.
 */
@WebServlet(name = "Lab2")
public class Lab2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (DataBaseConnectionTester tester = new DataBaseConnectionTester()) {

        ArrayList<News> news = null;
        try {
            news = tester.getNews();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("news", news);
        request.getRequestDispatcher("/WEB-INF/jsp/lab2.jsp").forward(request, response);


         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
    }
}
