import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "SessionServlet", urlPatterns = "/session")
public class Session extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Session Tracking Example";
        HttpSession session = request.getSession(true);
        String heading;
        Integer accessCount = (Integer) session.getAttribute("accessCount");

        if (accessCount == null) {
            accessCount = 0;
            heading = "Welcome, New-Comer";
        } else {
            heading = "Welcome Back";
            accessCount++;
        }

        session.setAttribute("accessCount", accessCount);
        out.println("<html><head><title>" + title + "</title></head>\n" +  // create title of the page

                "<body bgcolor=\"#FDF5E6\">\n" +
                "<h1 ALIGN=\"center\">" + heading + "</h1>\n" +  // set the greeting heading generated before
                "<h2>Information on Your Session:</H2>\n" +
                // create a <table>
                "<table border=1 align=\"center\">\n" +
                "  <tr bgcolor=\"#FFAD00\">\n" +  // create a <tr> (table row)
                "    <th>Info Type<th>Value\n" +  // create two <th>s (table header)

                // create a <tr> (table row)
                "  <tr>\n" +
                "    <td>ID\n" +  // create the first <td> (table data) in <tr>, which corresponding to the first column
                "    <td>" + session.getId() + "\n" + // create the second <td> (table data) in <tr>, which corresponding to the second column

                // repeat for more table rows and datas.
                "  <tr>\n" +
                "    <td>Creation Time\n" +
                "    <td>" +
                new Date(session.getCreationTime()) + "\n" +
                "  <tr>\n" +
                "    <td>Time of Last Access\n" +
                "    <td>" +
                new Date(session.getLastAccessedTime()) + "\n" +
                "  <tr>\n" +
                "    <td>Number of Previous Accesses\n" +
                "    <td>" + accessCount + "\n" +
                "  </tr>" +
                "</table>\n");

        // the following two statements show how to retrieve parameters in
        // the request.  The URL format is something like:
        //http://localhost:8080/project2-session-example/Session?myname=Chen%20Li
        String myname = request.getParameter("myname");
        if (myname != null)
            out.println("Hey " + myname + "<br><br>");

        out.println("</body></html>");
    }

    /**
     * Handle GET and POST requests identically.
     */
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException {
        doGet(request, response);
    }
}

