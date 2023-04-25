
package order_controller;

import account_controller1.BaseAuthController;
import dal.OrderDBContext;
import dal.StorageDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Order;
import model.OrderDetail;
import model.Storage;


public class ConfirmController extends BaseAuthController {

    
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        
        request.setAttribute("currentdate", date);
        
        request.getRequestDispatcher("view/order/check.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        Order neworder = (Order) session.getAttribute("neworder");

        String[] sids = request.getParameterValues("sid");
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
            Storage s = new Storage();
            StorageDBContext dbs = new StorageDBContext();
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
