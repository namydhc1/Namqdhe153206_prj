
package order_controller;

import account_controller1.BaseAuthController;
import dal.OrderDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderDetail;

public class CheckoutController extends BaseAuthController {

    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long millis = System.currentTimeMillis();
        Date currentdate = new Date(millis);
        request.setAttribute("currentdate", currentdate);
        request.getRequestDispatcher("view/order/confirmorder.jsp").forward(request, response);

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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String raw_date = request.getParameter("orderdate");
        
        OrderDBContext dbo = new OrderDBContext();
   //     ArrayList<OrderDetail> orderDetails = dbo.getOrderDetails(raw_date);
        ArrayList<OrderDetail> orderDetails = dbo.getViewOrderDetails(raw_date);

        request.setAttribute("orderDetails", orderDetails);

        request.getRequestDispatcher("view/order/confirmorder.jsp").forward(request, response);

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
