
package storage_controlelr;

import account_controller1.BaseAuthController;
import dal.StorageDBContext;
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
import model.ItemTypes;
import model.Storage;


public class UpdateController extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        int id = Integer.parseInt(request.getParameter("id"));
        TypesDBContext db = new TypesDBContext();
        ArrayList<ItemTypes> types = db.getTypes();
        request.setAttribute("types", types);;

        StorageDBContext stoDB = new StorageDBContext();
        Storage storage = stoDB.getStorage(id, account.getUsername());
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
        String raw_id = request.getParameter("id");
        String raw_name = request.getParameter("name");
        String raw_purchaseMoney = request.getParameter("purchaseMoney");
        String raw_quantity = request.getParameter("quantityWarehousing");
        String raw_stocks = request.getParameter("stocks");
        String raw_doW = request.getParameter("dateofWarehousing");
        String raw_types = request.getParameter("types");
        String raw_unitprice = request.getParameter("unitprice");
        //validate data
        int unitprice = Integer.parseInt(raw_unitprice);
        int id = Integer.parseInt(raw_id);
        int purchaseMoney = Integer.parseInt(raw_purchaseMoney);
        int quantity = Integer.parseInt(raw_quantity);
        int stocks = Integer.parseInt(raw_stocks);
        String types = raw_types;

        String name = raw_name;

        Date doW = Date.valueOf(raw_doW);

        ItemTypes t = new ItemTypes();
        t.setName(types);
        Storage s = new Storage();
        s.setId(id);
        s.setName(name);
        s.setDateofWarehousing(doW);
        s.setQuantityWarehousing(quantity);
        s.setPurchaseMoney(purchaseMoney);
        s.setStocks(stocks);
        s.setTypes(t.getName());
        s.setUnitprice(unitprice);
        StorageDBContext db = new StorageDBContext();
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
