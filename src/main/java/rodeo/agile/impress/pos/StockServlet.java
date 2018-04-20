package rodeo.agile.impress.pos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockServlet extends HttpServlet {

    private static final long serialVersionUID = 6961400581681209885L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/stocks/input.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String priceString = request.getParameter("price");
        String numString = request.getParameter("num");

        int price = 0;
        int num = 0;
        try {
        	price = Integer.parseInt(priceString);
        	num = Integer.parseInt(numString);

        	String dbPath = getServletContext().getRealPath("WEB-INF/pos.db");
        	StockDao dao = new StockDao(dbPath);

        	StockLogic logic = new StockLogic(dao);
        	logic.add(name, price, num);
        } catch (Exception e) {
        	e.printStackTrace();

        	request.setAttribute("name", name);
            request.setAttribute("price", priceString);
            request.setAttribute("num", numString);
            request.getRequestDispatcher("jsp/stocks/input.jsp").forward(request, response);
            return;
        }
        
        request.getRequestDispatcher("jsp/stocks/success.jsp").forward(request, response);
    }

}
