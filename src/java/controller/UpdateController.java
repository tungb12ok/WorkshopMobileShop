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
import model.Mobile;
import model.MobileDAO;

@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from the form
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

        // Update the mobile information in the database using MobileDAO (assuming MobileDAO has an updateMobile method)
        MobileDAO mobileDAO = new MobileDAO();
        try {
            mobileDAO.updateMobile(mobile);

        } catch (Exception e) {
            request.setAttribute("mess", e.getMessage());
            request.getRequestDispatcher("UpdateMobile.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("home").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the mobileId parameter from the request
        String mobileId = request.getParameter("mobileId");

        // Retrieve the Mobile object from the database using MobileDAO (assuming MobileDAO has a getMobileById method)
        MobileDAO mobileDAO = new MobileDAO();
        Mobile mobile = mobileDAO.getMobileById(mobileId);

        // Set the Mobile object as an attribute in the request
        request.setAttribute("mobile", mobile);

        // Forward to the update_mobile.jsp page
        request.getRequestDispatcher("UpdateMobile.jsp").forward(request, response);
    }
}
