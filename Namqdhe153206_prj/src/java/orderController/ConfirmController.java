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
import java.sql.Date;
import model.Account;
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author Namqd
 */
public class ConfirmController extends HttpServlet {
   
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
            out.println("<title>Servlet ConfirmController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmController at " + request.getContextPath () + "</h1>");
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
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        
        request.setAttribute("currentdate", date);
        
        request.getRequestDispatcher("view/order/check.jsp").forward(request, response);
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
        Order neworder = (Order) session.getAttribute("neworder");

        String[] pids = request.getParameterValues("pid");
        String[] quantity = request.getParameterValues("quantity");
        String raw_orderdate =  request.getParameter("orderdate");
        //validate data
        Date orderdate = Date.valueOf(raw_orderdate);
        
        
        Order o = new Order();
   
        o.setOrderdate(orderdate);
  
        o.setProfit(neworder.getTotalProfit());
        

        for (int i = 0 ; i < sids.length; i++) {
            int storageid = Integer.parseInt(sids[i]);
            int validatequantity = Integer.parseInt(quantity[i]);
            Product s = new Product();
            StoragesDBContext dbs = new StoragesDBContext();
            s = dbs.getOrderStorage(storageid);
            int newstocks = s.getStocks() - validatequantity;
            StorageDBContext dbstorage = new StorageDBContext();
            dbstorage.updateStocks(storageid, newstocks);
            OrderDetail od  = new OrderDetail();
            od.setStorage(s);
            od.setOrder(neworder);
            od.setQuantity(validatequantity);
            od.setUnitprice(s.getUnitprice());
            o.getDetails().add(od);
        }
        OrderDBContext db = new OrderDBContext();
        db.insert(o);
        request.getSession().setAttribute("neworder", null);
        
        

//        response.getWriter().println("done");
        request.getRequestDispatcher("view/order/confirmorder.jsp").forward(request, response);
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
