package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;

public final class task_005flogin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\" \"http://www.w3.org/TR/html4/frameset.dtd\">\n");
      out.write("<html lang=\"en\">\n");
      out.write("  <head>\n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("    <title>Welcome to crib!</title>\n");
      out.write("\n");
      out.write("    <!-- Bootstrap -->\n");
      out.write("    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"dist/css/bootstrapValidator.min.css\"/>\n");
      out.write("\n");
      out.write("    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\n");
      out.write("    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\n");
      out.write("    <!--[if lt IE 9]>\n");
      out.write("      <script src=\"https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js\"></script>\n");
      out.write("      <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\n");
      out.write("\t  \n");
      out.write("    <![endif]-->\n");
      out.write("\t\n");
      out.write("\t<style type=\"text/css\">\n");
      out.write("\t\t\n");
      out.write("\t\t@media (max-width: 480px) { \n");
      out.write("\n");
      out.write("\t\t\t.win {\n");
      out.write("\t\t\t\tpadding:2em;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\t\n");
      out.write("\t\t\thtml {height:100%;}\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t#break \n");
      out.write("\t\t\t{display:none;}\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tbackground-repeat:no-repeat;\n");
      out.write("\t\t}\n");
      out.write("\t\t\n");
      out.write("\t\t.win {\n");
      out.write("\t\t\tbackground-color:white;\n");
      out.write("\t\t\ttext-align:center;\n");
      out.write("\t\t\tborder-radius:20px;\n");
      out.write("\t\t\tborder:2px solid;\n");
      out.write("\t\t}\n");
      out.write("\t\t\t\n");
      out.write("\t\tbody {\n");
      out.write("\t\t  background: -webkit-gradient(#888888,white); /* Chrome,Safari4+ */\n");
      out.write("\t\t  background: -webkit-linear-gradient(#888888,white); /* Chrome10+,Safari5.1+ */\n");
      out.write("\t\t  background-repeat:no-repeat;\n");
      out.write("\t\t}\n");
      out.write("\t\t\n");
      out.write("\t\thtml {height:100%;padding-top:5%;padding-bottom:10%;}\n");
      out.write("\t\t\n");
      out.write("\t\t::-webkit-scrollbar { \n");
      out.write("                    display: none; \n");
      out.write("                }\n");
      out.write("\n");
      out.write("\t</style>\n");
      out.write("\t\n");
      out.write("  </head>\n");
      out.write("  \n");
      out.write("  <body>\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t\t<div class=\"col-md-4\"></div>\n");
      out.write("\t\t<div class=\"col-md-4\">\n");
      out.write("\t\t\n");
      out.write("            ");
 if ((String)request.getAttribute("logon")=="fail")
            {
                out.println("<div class='alert alert-danger alert-dismissible' role='alert'>");
                out.println("<button type='button' class='close' data-dismiss='alert'><span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span></button>");
                out.println("<strong>The entered username or password is incorrect</strong>");
                out.println("</div>");
            }
      out.write("\n");
      out.write("\t\t\n");
      out.write("\t\t\t\t\t<div class=\"win\" id=\"log\">\n");
      out.write("\t\t\t\t\t\t<img src=\"img/logo.jpg\" style=\"height:20%;\"/><br><br>\n");
      out.write("                                                <form role=\"form\" id=\"login_form\" class=\"form-inline\" method=\"get\" action=\"Login\">\n");
      out.write("\t\t\t\t\t\t  <div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"username\" class=\"form-control\" id=\"exampleInputEmail1\" placeholder=\"Username\">\n");
      out.write("\t\t\t\t\t\t  </div>\n");
      out.write("\t\t\t\t\t\t  <div id=\"break\">\n");
      out.write("\t\t\t\t\t\t\t<br>\n");
      out.write("\t\t\t\t\t\t  </div>\n");
      out.write("\t\t\t\t\t\t  <div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"password\" name=\"password\" class=\"form-control\" id=\"exampleInputPassword1\" placeholder=\"Password\">\n");
      out.write("\t\t\t\t\t\t  </div>\n");
      out.write("\t\t\t\t\t\t  <div id=\"break\">\n");
      out.write("\t\t\t\t\t\t\t<br>\n");
      out.write("\t\t\t\t\t\t  </div>\n");
      out.write("\t\t\t\t\t\t  <button type=\"submit\" class=\"btn btn-primary\">Login</button><br><br>\n");
      out.write("\t\t\t\t\t\t <a  class=\"formNotice\" href=\"#\" role=\"button\">\n");
      out.write("\t\t\t\t\t\t\tNeed an account? Sign up!\n");
      out.write("\t\t\t\t\t\t </a>\n");
      out.write("\t\t\t\t\t\t<div id=\"break\">\n");
      out.write("\t\t\t\t\t\t\t<br>\n");
      out.write("\t\t\t\t\t\t  </div>\n");
      out.write("\t\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t\t\t\t\t<div class=\"win\" id=\"reg\">\n");
      out.write("\t\t\t\t\t\t<img src=\"img/logo.jpg\" style=\"height:20%;\"/><br><br>\n");
      out.write("\t\t\t\t\t\t<form role=\"form\" class=\"form-inline\" method=\"get\" action=\"Registration\" id=\"reg_form\">\n");
      out.write("\t\t\t\t\t\t  <div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"fname\" class=\"form-control\" id=\"exampleInputEmail1\" placeholder=\"First Name\">\n");
      out.write("\t\t\t\t\t\t  </div>\n");
      out.write("\t\t\t\t\t\t  <div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"lname\" class=\"form-control\" id=\"exampleInputEmail1\" placeholder=\"Last Name\">\n");
      out.write("\t\t\t\t\t\t  </div>\n");
      out.write("\t\t\t\t\t\t  <div id=\"break\">\n");
      out.write("\t\t\t\t\t\t\t<br>\n");
      out.write("\t\t\t\t\t\t  </div>\n");
      out.write("\t\t\t\t\t\t  <div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"email\" name=\"email\" class=\"form-control\" id=\"exampleInputEmail1\" placeholder=\"Email address\">\n");
      out.write("\t\t\t\t\t\t  </div>\n");
      out.write("\t\t\t\t\t\t  <div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"username\" class=\"form-control\" id=\"exampleInputEmail1\" placeholder=\"Username\">\n");
      out.write("\t\t\t\t\t\t  </div>\n");
      out.write("\t\t\t\t\t\t  <div id=\"break\">\n");
      out.write("\t\t\t\t\t\t\t<br>\n");
      out.write("\t\t\t\t\t\t  </div>\n");
      out.write("\t\t\t\t\t\t  <div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"password\" name=\"password\" class=\"form-control\" id=\"exampleInputPassword1\" placeholder=\"Password\">\n");
      out.write("\t\t\t\t\t\t  </div>\n");
      out.write("\t\t\t\t\t\t  <div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"password\" name=\"cpassword\" class=\"form-control\" id=\"exampleInputPassword1\" placeholder=\"Confirm Password\">\n");
      out.write("\t\t\t\t\t\t  </div>\n");
      out.write("\t\t\t\t\t\t  <div id=\"break\">\n");
      out.write("\t\t\t\t\t\t\t<br>\n");
      out.write("\t\t\t\t\t\t  </div>\n");
      out.write("\t\t\t\t\t\t  <button class=\"btn btn-primary\">Sign up</button><br><br>\n");
      out.write("\t\t\t\t\t\t  <a  class=\"formNotice\" href=\"#\" role=\"button\">\n");
      out.write("\t\t\t\t\t\t\tHave an account? Login!\n");
      out.write("\t\t\t\t\t\t  </a>\n");
      out.write("\t\t\t\t\t\t  <div id=\"break\">\n");
      out.write("\t\t\t\t\t\t\t<br>\n");
      out.write("\t\t\t\t\t\t  </div>\n");
      out.write("\t\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t</div>\n");
      out.write("\n");
      out.write("    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->\n");
      out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script>\n");
      out.write("    <!-- Include all compiled plugins (below), or include individual files as needed -->\n");
      out.write("    <script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"dist/js/bootstrapValidator.min.js\"></script>\n");
      out.write("\t\n");
      out.write("\t<script>\n");
      out.write("\n");
      out.write("\t\t\t\t$(document).ready(function() {\n");
      out.write("\t\t\t\t\t$('#reg').hide();\n");
      out.write("\t\t\t\t\t$('.formNotice').click(function() {\n");
      out.write("\t\t\t\t\t\t$(\"#log\").toggle();\n");
      out.write("\t\t\t\t\t\t$(\"#reg\").toggle();\n");
      out.write("\t\t\t\t\t});\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\n");
      out.write("                                \n");
      out.write("                                    $('login_form').bootstrapValidator({\n");
      out.write("\t\t\t\t\t// To use feedback icons, ensure that you use Bootstrap v3.1.0 or later\n");
      out.write("\t\t\t\t\tcontainer:'tooltip',\n");
      out.write("                                        feedbackIcons: {\n");
      out.write("\t\t\t\t\t\tvalid: 'glyphicon glyphicon-ok',\n");
      out.write("\t\t\t\t\t\tinvalid: 'glyphicon glyphicon-remove',\n");
      out.write("\t\t\t\t\t\tvalidating: 'glyphicon glyphicon-refresh'\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\tfields: {\n");
      out.write("\t\t\t\t\t\tusername: {\n");
      out.write("\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\tvalidators: {\n");
      out.write("\t\t\t\t\t\t\t\tnotEmpty: {\n");
      out.write("\t\t\t\t\t\t\t\t\tmessage: 'Username required'\n");
      out.write("\t\t\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\t\t\tregexp: {\n");
      out.write("\t\t\t\t\t\t\t\t\tregexp: /^[a-zA-Z]+$/,\n");
      out.write("\t\t\t\t\t\t\t\t\tmessage: 'Alphabets only'\n");
      out.write("\t\t\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\tpassword: {\n");
      out.write("\t\t\t\t\t\t   \n");
      out.write("\t\t\t\t\t\t\tvalidators: {\n");
      out.write("\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\tnotEmpty: {\n");
      out.write("\t\t\t\t\t\t\t\t\tmessage: 'Password required'\n");
      out.write("\t\t\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\t\t\tregexp: {\n");
      out.write("\t\t\t\t\t\t\t\t\tregexp: /^[a-zA-Z0-9]+$/,\n");
      out.write("\t\t\t\t\t\t\t\t\tmessage: 'Alphabets and numbers only'\n");
      out.write("\t\t\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t}\n");
      out.write("                                    });\n");
      out.write("                                    $('reg_form').bootstrapValidator({\n");
      out.write("\t\t\t\t\t// To use feedback icons, ensure that you use Bootstrap v3.1.0 or later\n");
      out.write("\t\t\t\t\tcontainer:'tooltip',\n");
      out.write("                                        feedbackIcons: {\n");
      out.write("\t\t\t\t\t\tvalid: 'glyphicon glyphicon-ok',\n");
      out.write("\t\t\t\t\t\tinvalid: 'glyphicon glyphicon-remove',\n");
      out.write("\t\t\t\t\t\tvalidating: 'glyphicon glyphicon-refresh'\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\tfields: {\n");
      out.write("                                                username: {\n");
      out.write("\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\tvalidators: {\n");
      out.write("\t\t\t\t\t\t\t\tnotEmpty: {\n");
      out.write("\t\t\t\t\t\t\t\t\tmessage: 'Username required'\n");
      out.write("\t\t\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\t\t\tregexp: {\n");
      out.write("\t\t\t\t\t\t\t\t\tregexp: /^[a-zA-Z]+$/,\n");
      out.write("\t\t\t\t\t\t\t\t\tmessage: 'Alphabets only'\n");
      out.write("\t\t\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t\t},\n");
      out.write("                                                firstname: {\n");
      out.write("\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\tvalidators: {\n");
      out.write("\t\t\t\t\t\t\t\tnotEmpty: {\n");
      out.write("\t\t\t\t\t\t\t\t\tmessage: 'First name required'\n");
      out.write("\t\t\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\t\t\tregexp: {\n");
      out.write("\t\t\t\t\t\t\t\t\tregexp: /^[a-zA-Z]+$/,\n");
      out.write("\t\t\t\t\t\t\t\t\tmessage: 'Alphabets only'\n");
      out.write("\t\t\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\tlastname: {\n");
      out.write("\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\tvalidators: {\n");
      out.write("\t\t\t\t\t\t\t\tnotEmpty: {\n");
      out.write("\t\t\t\t\t\t\t\t\tmessage: 'Last name required'\n");
      out.write("\t\t\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\t\t\tregexp: {\n");
      out.write("\t\t\t\t\t\t\t\t\tregexp: /^[a-zA-Z]+$/,\n");
      out.write("\t\t\t\t\t\t\t\t\tmessage: 'Alphabets only'\n");
      out.write("\t\t\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\tpassword: {\n");
      out.write("\t\t\t\t\t\t   \n");
      out.write("\t\t\t\t\t\t\tvalidators: {\n");
      out.write("\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\tnotEmpty: {\n");
      out.write("\t\t\t\t\t\t\t\t\tmessage: 'Password required'\n");
      out.write("\t\t\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\t\t\tregexp: {\n");
      out.write("\t\t\t\t\t\t\t\t\tregexp: /^[a-zA-Z0-9]+$/,\n");
      out.write("\t\t\t\t\t\t\t\t\tmessage: 'Alphabets and numbers only'\n");
      out.write("\t\t\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t\t},\n");
      out.write("                                                cpassword: {\n");
      out.write("\t\t\t\t\t\t   \n");
      out.write("\t\t\t\t\t\t\tvalidators: {\n");
      out.write("\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\tnotEmpty: {\n");
      out.write("\t\t\t\t\t\t\t\t\tmessage: 'Retype password'\n");
      out.write("\t\t\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\t\t\tregexp: {\n");
      out.write("\t\t\t\t\t\t\t\t\tregexp: /^[a-zA-Z0-9]+$/,\n");
      out.write("\t\t\t\t\t\t\t\t\tmessage: 'Alphabets and numbers only'\n");
      out.write("\t\t\t\t\t\t\t\t},\n");
      out.write("\t\t\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t\t},\n");
      out.write("                                                email: {\n");
      out.write("                                                        validators: {\n");
      out.write("                                                                notEmpty: {\n");
      out.write("                                                                        message: 'Email is required'\n");
      out.write("                                                                },\n");
      out.write("                                                                regexp: {\n");
      out.write("                                                                        regexp: /^(([^<>()[\\]\\\\.,;:\\s@\\\"]+(\\.[^<>()[\\]\\\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$/,\n");
      out.write("                                                                        message: 'Email is invalid'\n");
      out.write("                                                                },\n");
      out.write("                                                        }\n");
      out.write("                                                },\n");
      out.write("\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t}\n");
      out.write("\t\t\t\t});\n");
      out.write("                                });\n");
      out.write("\t</script>\n");
      out.write("\n");
      out.write("  </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
