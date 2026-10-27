package com.student;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
@WebServlet("/calculate")
public class MarkServelet extends HttpServlet {


	private static final long serialVersionUID = 1L;

protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    String name = request.getParameter("name");

    try {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Enter student name.");

        int m1 = Integer.parseInt(request.getParameter("m1"));
        int m2 = Integer.parseInt(request.getParameter("m2"));
        int m3 = Integer.parseInt(request.getParameter("m3"));

        if (m1 < 0 || m1 > 100 ||
            m2 < 0 || m2 > 100 ||
            m3 < 0 || m3 > 100)
            throw new IllegalArgumentException(
                "Marks must be between 0 and 100.");

        int total = m1 + m2 + m3;
        double avg = total / 3.0;

        String grade;

        if (avg >= 90)
            grade = "A";
        else if (avg >= 75)
            grade = "B";
        else if (avg >= 60)
            grade = "C";
        else if (avg >= 40)
            grade = "D";
        else
            grade = "Fail";

        out.println("<html><body>");
        out.println("<h2>Student Result</h2>");
        out.println("<p>Name: " + escape(name.trim()) + "</p>");
        out.println("<p>Total: " + total + "</p>");
        out.printf(java.util.Locale.US,
            "<p>Average: %.2f</p>%n", avg);
        out.println("<p>Grade: " + grade + "</p>");
        out.println("<a href='index.html'>Go Back</a>");
        out.println("</body></html>");

    } catch (IllegalArgumentException e) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        out.println("<h2>Invalid Input</h2>");
        out.println("<p>" + escape(e.getMessage()) + "</p>");
        out.println("<a href='index.html'>Try Again</a>");
    }
}

private String escape(String s) {
    return s.replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#39;");
}
}
