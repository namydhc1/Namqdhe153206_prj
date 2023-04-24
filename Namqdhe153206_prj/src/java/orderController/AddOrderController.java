/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package orderController;

import dal.StoragesDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author Namqd
 */
public class AddOrderController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddOrderController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddOrderController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        int id = Integer.parseInt(request.getParameter("id"));

        int quantity = Integer.parseInt(request.getParameter("quantity"));

        StoragesDBContext db = new StoragesDBContext();
        Product storage = db.getStorage(id, account.getUsername());
        storage.setQuantitysell(quantity);
        int raw_odquantity = storage.getQuantitysell();
        Order order = (Order) request.getSession().getAttribute("neworder");
        if (order == null) {
            order = new Order();
        }

        boolean isExist = false;
        for (OrderDetail detail : order.getDetails()) {
            if (detail.getStorage().getId() == storage.getId()) {
                isExist = true;

                detail.setQuantity(detail.getQuantity() + raw_odquantity);
                break;
            }
        }
        //sản phẩm muốn mua chưa nằm trong giỏ hàng
        if (!isExist) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setStorage(storage);
            detail.setQuantity(storage.getQuantitysell());
            detail.setUnitprice(storage.getUnitprice());
            order.getDetails().add(detail);
        }

        request.getSession().setAttribute("neworder", order);
        
        response.sendRedirect("list");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
