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
import model.*;

@WebServlet(name = "AddCartController", urlPatterns = {"/AddToCart"})
public class AddCartController extends HttpServlet {

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
        String mobileId = request.getParameter("id");

// Assuming you have a User instance stored in the session
        HttpSession session = request.getSession(true);
        User u = (User) session.getAttribute("user");

// If the user is not logged in, redirect to the login page
        if (u == null) {
            response.sendRedirect("login.jsp");
            return; // Stop the execution of the logic for unauthenticated users
        }

        Cart userCart = (Cart) session.getAttribute("userCart");  // Use a consistent attribute key
        if (userCart == null) {
            userCart = new Cart();
            session.setAttribute("userCart", userCart);  // Set the cart using a consistent attribute key
        }

        MobileDAO mDAO = new MobileDAO();
        Mobile m = mDAO.getMobileById(mobileId);

// Check if the mobile is not null before adding to the cart
        if (m != null) {
            // Use the addItem method to handle quantity increment
            userCart.addItem(u, m);

            // Update the user's cart in the session
            session.setAttribute("userCart", userCart);  // Use a consistent attribute key

            // Set a success message for the user
            request.setAttribute("mess", "Added to cart successfully!");
        } else {
            // Set an error message if the mobile is not found
            request.setAttribute("mess", "Error: Mobile not found!");
        }

// Forward the request to the home page
        request.getRequestDispatcher("home").forward(request, response);

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
        processRequest(request, response);
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
        processRequest(request, response);
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
