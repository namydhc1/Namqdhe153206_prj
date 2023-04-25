
package order_controller;

import account_controller1.BaseAuthController;
import dal.StorageDBContext;
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
import model.Storage;

public class AddOrderController extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

        int id = Integer.parseInt(request.getParameter("id"));

        int quantity = Integer.parseInt(request.getParameter("quantity"));

        StorageDBContext db = new StorageDBContext();
        Storage storage = db.getStorage(id, account.getUsername());
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
