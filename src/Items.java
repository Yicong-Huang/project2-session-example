import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ItemServlet", urlPatterns = "/items")
public class Items extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        ArrayList<String> previousItems = (ArrayList<String>) session.getAttribute("previousItems");
        if (previousItems == null) {
            previousItems = new ArrayList<>();
            session.setAttribute("previousItems", previousItems);
        }

        String newItem = request.getParameter("newItem");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Items Purchased";
        String docType =
                "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n";
        out.println(String.format("%s<html>\n<head><title>%s</title></head>\n<body bgcolor=\"#FDF5E6\">\n<h1>%s</h1>", docType, title, title));

        synchronized (previousItems) {
            if (newItem != null) {
                previousItems.add(newItem);
            }
            if (previousItems.size() == 0) {
                out.println("<i>No items</i>");
            } else {
                out.println("<ul>");
                for (Object previousItem : previousItems) {
                    out.println("<li>" + previousItem);
                }
                out.println("</ul>");
            }
        }



        // The following two statements show how this thread can access an
        // object created by a thread of the Session servlet
        // Integer accessCount = (Integer)session.getAttribute("accessCount");
        // out.println("<p>accessCount = " + accessCount);

        out.println("</body></html>");
    }
}
