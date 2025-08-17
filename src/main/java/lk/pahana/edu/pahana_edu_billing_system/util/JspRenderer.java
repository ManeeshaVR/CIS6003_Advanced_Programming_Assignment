package lk.pahana.edu.pahana_edu_billing_system.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

public class JspRenderer {
    public static String renderJspToString(String jspPath, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharArrayWriter writer = new CharArrayWriter();
        HttpServletResponse capturingResponse = new HttpServletResponseWrapper(response) {
            @Override
            public PrintWriter getWriter() {
                return new PrintWriter(writer);
            }
            @Override
            public ServletOutputStream getOutputStream() {
                throw new IllegalStateException("getOutputStream not supported for capturing");
            }
        };

        RequestDispatcher rd = request.getRequestDispatcher(jspPath);
        rd.include(request, capturingResponse);

        return writer.toString();
    }
}
