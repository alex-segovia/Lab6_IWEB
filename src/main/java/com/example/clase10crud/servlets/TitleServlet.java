package com.example.clase10crud.servlets;

import com.example.clase10crud.beans.Employee;
import com.example.clase10crud.beans.Title;
import com.example.clase10crud.daos.TitleDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "TitleServlet", value = "/TitleServlet")
public class TitleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        TitleDao titleDao = new TitleDao();

        //TODO: complete
        switch (action){
            case "lista":
                int limit=Integer.parseInt(request.getParameter("limit"));
                int offset=Integer.parseInt(request.getParameter("offset"));
                ArrayList<Title> list = titleDao.list(limit,offset);
                //saca del modelo
                //mandar la lista a la vista -> job/lista.jsp
                request.setAttribute("lista",list);
                request.setAttribute("limit",limit);
                request.setAttribute("offset",limit);


                //mandar la lista a la vista -> job/lista.jsp
                request.setAttribute("lista",list);
                RequestDispatcher rd = request.getRequestDispatcher("title/lista.jsp");
                rd.forward(request,response);
                break;
            case "new":

            case "edit":

            case "del":
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
