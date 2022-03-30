<%-- 
    Document   : filtreDate
    Created on : 30 mars 2022, 14:50:35
    Author     : NIKO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body><br>
        <div class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h1> Filtre date</h1><br>
                    <form action="servletServeurs" method="post">
                        <p><b> DATE 1 : </b><input type="date" name="date1" class="form-control"></p>            
                        <p><b> DATE 2 : </b><input type="date" name="date2" class="form-control"></p>
                        <p><input type="submit" class="btn btn-info" value="ok" style="width: 50%;margin-left: 20%"></p>
                    </form>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
    </body>
</html>
