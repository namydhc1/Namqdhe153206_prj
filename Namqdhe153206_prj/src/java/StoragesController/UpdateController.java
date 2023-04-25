
package StoragesController;

import controller.BaseAuthController;
import dal.StoragesDBContext;
import dal.TypesDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Category;
import model.Product;


public class UpdateController extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        int id = Integer.parseInt(request.getParameter("pid"));
        TypesDBContext db = new TypesDBContext();
        ArrayList<Category> types = db.getTypes();
        request.setAttribute("types", types);;

        StoragesDBContext stoDB = new StoragesDBContext();
        Product storage = stoDB.getStorage(id, account.getUsername());
        request.setAttribute("storage", storage);

        request.getRequestDispatcher("view/storage/update.jsp").forward(request, response);
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
        String raw_pid = request.getParameter("pid");
        String raw_pname = request.getParameter("pname");
        String raw_purchaseMoney = request.getParameter("purchaseMoney");
        String raw_quantity = request.getParameter("quantityWarehousing");
        String raw_inventory = request.getParameter("inventory");
        String raw_doW = request.getParameter("dateofWarehousing");
        String raw_cid = request.getParameter("cid");
        String raw_unitprice = request.getParameter("unitprice");
        //validate data
        int unitprice = Integer.parseInt(raw_unitprice);
        int pid = Integer.parseInt(raw_pid);
        int purchaseMoney = Integer.parseInt(raw_purchaseMoney);
        int quantity = Integer.parseInt(raw_quantity);
        int inventory = Integer.parseInt(raw_inventory);
        String cid = raw_cid;

        String pname = raw_pname;

        Date doW = Date.valueOf(raw_doW);

        Category t = new Category();
        t.setName(cid);
        Product s = new Product();
        s.setId(pid);
        s.setPname(pname);
        s.setDateofWarehousing(doW);
        s.setQuantityWarehousing(quantity);
        s.setPurchaseMoney(purchaseMoney);
        s.setInventory(inventory);
        s.setCid(t.getId());
        s.setUnitprice(unitprice);
        StoragesDBContext db = new StoragesDBContext();
        db.updateItems(s, account.getUsername());

        //response.getWriter().println("done");
        response.sendRedirect("display");
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
