package com.example.clase10crud.servlets;

import com.example.clase10crud.beans.Employee;
import com.example.clase10crud.beans.Title;
import com.example.clase10crud.daos.EmployeeDao;
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
                request.getRequestDispatcher("title/form_new.jsp").forward(request,response);
                break;
            case "edit":
                String id = request.getParameter("id");
                String title1 = request.getParameter("title");
                String date = request.getParameter("date");
                Title title = titleDao.buscarPorId(id,title1,date);

                if(title != null){
                    request.setAttribute("title", title);
                    request.getRequestDispatcher("title/form_edit.jsp").forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/TitleServlet?limit=10&offset=0");
                }
                break;
            case "del":
                String id2 = request.getParameter("id");
                String title2 = request.getParameter("title");
                String date2 = request.getParameter("date");
                Title title3 = titleDao.buscarPorId(id2,title2,date2);

                if(title3 != null){
                    try {
                        titleDao.borrar(id2,title2,date2);
                    } catch (SQLException e) {
                        System.out.println("Log: excepcion: " + e.getMessage());
                    }
                }
                response.sendRedirect(request.getContextPath() + "/TitleServlet?limit=10&offset=0");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        TitleDao titleDao = new TitleDao();

        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");

        switch (action){
            case "crear":
                String empNo = request.getParameter("empNo");
                String title = request.getParameter("title");
                String fromDate = request.getParameter("fromDate");
                String toDate = request.getParameter("toDate");

                Title title1 = new Title();
                title1.setEmpNo(Integer.parseInt(empNo));
                title1.setTitle(title);
                title1.setFromDate(fromDate);
                title1.setToDate(toDate);

                titleDao.create(title1);
                response.sendRedirect(request.getContextPath()+"/TitleServlet?limit=10&offset=0");
                break;
            case "e":
                String empNo2 = request.getParameter("empNo");
                String title2 = request.getParameter("title");
                String fromDate2 = request.getParameter("fromDate");
                String toDate2 = request.getParameter("LastName");

                Title title3 = new Title();
                title3.setEmpNo(Integer.parseInt(empNo2));
                title3.setTitle(title2);
                title3.setFromDate(fromDate2);
                title3.setToDate(toDate2);

                titleDao.actualizar(title3);
                response.sendRedirect(request.getContextPath()+"/TitleServlet?limit=10&offset=0");
                break;
        }
    }
}
