
package storage_controlelr;

import dal.StorageDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Storage;

public class DisplayController extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.getWriter().println("access denied");

        } else {
            int pagesize = 10;
            String page = request.getParameter("page");
            if (page == null || page.trim().length() == 0) {
                page = "1";
            }
            int pageindex = Integer.parseInt(page);

            StorageDBContext db = new StorageDBContext();
//            ArrayList<Storage> storages = db.getStorages(account.getUsername());
//            request.setAttribute("storages", storages);
            ArrayList<Storage> storages = db.getStoragesbyPage(account.getUsername(), pageindex, pagesize);
            request.setAttribute("storages", storages);
            int count = db.count();
            int totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize) + 1;
            request.setAttribute("totalpage", totalpage);
            request.setAttribute("pageindex", pageindex);

            request.getRequestDispatcher("view/storage/display.jsp").forward(request, response);
        }
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
