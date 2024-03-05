/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Mobile;
import model.MobileDAO;
import model.User;


@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        User u = (User) session.getAttribute("user");
        if (u != null) {
            if (u.getRole() != 1) {
                request.getRequestDispatcher("home").forward(request, response);
                return;
            }
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        request.getRequestDispatcher("AddMobile.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mobileId = request.getParameter("mobileId");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String mobileName = request.getParameter("mobileName");
        int yearOfProduction = Integer.parseInt(request.getParameter("yearOfProduction"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        boolean notSale = request.getParameter("notSale") != null;

        // Create a Mobile object and use setters to populate the attributes
        Mobile mobile = new Mobile();
        mobile.setMobileId(mobileId);
        mobile.setDescription(description);
        mobile.setPrice(price);
        mobile.setMobileName(mobileName);
        mobile.setYearOfProduction(yearOfProduction);
        mobile.setQuantity(quantity);
        mobile.setNotSale(notSale);

        // Save the mobile to the database using MobileDAO (assuming MobileDAO has a createMobile method)
        MobileDAO mobileDAO = new MobileDAO();
        try {
            mobileDAO.createMobile(mobile);
        } catch (Exception e) {
            request.setAttribute("messError", e.getMessage());
            request.getRequestDispatcher("AddMobile.jsp").forward(request, response);
            return;
        }
        request.setAttribute("mess", "Add Mobile Successfuly!");
        request.getRequestDispatcher("home").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
