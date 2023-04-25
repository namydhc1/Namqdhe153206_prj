/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package StoragesController;

import dal.StoragesDBContext;
import dal.TypesDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import model.Account;
import model.Category;
import model.Product;

/**
 *
 * @author Namqd
 */
public class InsertController extends HttpServlet {
   
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
            out.println("<title>Servlet InsertController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertController at " + request.getContextPath () + "</h1>");
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
        TypesDBContext db = new TypesDBContext();
        ArrayList<Category> types = db.getTypes();
        request.setAttribute("types", types);;
        request.getRequestDispatcher("view/storage/insert.jsp").forward(request, response);
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
        
        // String raw_id = request.getParameter("id");
        String raw_name = request.getParameter("pname");
        String raw_purchaseMoney = request.getParameter("purchaseMoney");
        String raw_quantity = request.getParameter("quantityWarehousing");
        String raw_stocks = request.getParameter("inventory");
        String raw_doW = request.getParameter("dateofWarehousing");
        String raw_types = request.getParameter("cid");
        String raw_unitprice = request.getParameter("unitprice");

        //validate data
        //  int id = Integer.parseInt(raw_id);
        int purchaseMoney = Integer.parseInt(raw_purchaseMoney);
        int quantity = Integer.parseInt(raw_quantity);
        int stocks = Integer.parseInt(raw_stocks);
        int unitprice = Integer.parseInt(raw_unitprice);
        String types = raw_types;
        int cid = Integer.parseInt(raw_types);
        String name = raw_name;

        Date doW = Date.valueOf(raw_doW);

        Category t = new Category();
        t.setName(types);
        t.setId(cid);
        Product s = new Product();
    
        s.setPname(name);
        s.setDateofWarehousing(doW);
        s.setQuantityWarehousing(quantity);
        s.setPurchaseMoney(purchaseMoney);
        s.setInventory(stocks);
        s.setCid(t.getId());
        s.setUnitprice(unitprice);
        StoragesDBContext db = new StoragesDBContext();
        db.insertItems(s , account.getUsername());

       
        response.sendRedirect("display");
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
