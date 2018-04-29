<%-- 
    Document   : formulaireClient
    Created on : Mar 4, 2018, 10:41:59 PM
    Author     : Marouane
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <!--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>-->


        <link rel="apple-touch-icon" sizes="76x76" href="Includes/assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="Includes/assets/img/favicon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <title>Now UI Dashboard by Creative Tim</title>
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
        <!--     Fonts and icons     -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
        <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
        <!-- CSS Files -->
        <link href="Includes/assets/css/now-ui-dashboard.css" rel="stylesheet" />
        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link href="Includes/assets/demo/demo.css" rel="stylesheet" />

        <script type="text/javascript">
            $("#modalBtn").on('click', function () {
                $("#modalBtn").attr("now-ui-icons loader_refresh spin");
            });
            function confirmAction(e, msg) {
                var confirmation = confirm(msg);

                if (!confirmation)
                {
                    e.preventDefault();
                }

                return confirmation;
            }
            function showOptions(s) {
                console.log(s[s.selectedIndex].id); // get id
                $("#idCl").val(s[s.selectedIndex].id);
            }
        </script>
    </head>
    <body>
        <div class="wrapper ">
            <c:import url="/Includes/sideBar.jsp" />
            <div class="main-panel">
                <!-- Navbar -->
                <c:import url="/Includes/menu.jsp"/>
                <!-- End Navbar -->
                <div class="panel-header panel-header-sm">
                </div>
                <div class="content">
                    <c:choose>
                        <c:when test="${ !empty existErr }">
                            <div class="form-group row">
                                <label for="nom" class="col-xs-12 alert alert-warning">
                                    <c:out value="${ existErr }"/>
                                </label>
                            </div>
                        </c:when>
                        <c:when test="${ !empty success}">
                            <div class="form-group row">
                                <label for="nom" class="col-xs-12 alert alert-success">
                                    <c:out value="${success}"/>
                                </label>
                            </div>
                        </c:when>
                        <c:when test="${ !empty deleted}">
                            <div class="form-group row">
                                <label for="nom" class="col-xs-12 alert alert-warning">
                                    <c:out value="${deleted}"/>
                                </label>
                            </div>
                        </c:when>
                        <c:when test="${ !empty edited}">
                            <div class="form-group row">
                                <label for="nom" class="col-xs-12 alert alert-info">
                                    <c:out value="${edited}"/>
                                </label>
                            </div>
                        </c:when>
                        <c:when test="${ !empty cannotDelete}">
                            <div class="form-group row">
                                <label for="nom" class="col-xs-12 alert alert-danger">
                                    <c:out value="${cannotDelete}"/>
                                </label>
                            </div>
                        </c:when>
                    </c:choose>

                    <form method="POST" action="<c:url value="creationCommande"/>">
                        <c:import url="/Includes/formCmd.jsp"/>
                    </form>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h5 class="title">
                                        Les Commandes Existants
                                    </h5>
                                </div>
                                <div class="card-body">
                                    <form method="POST" action="<c:url value="creationCommande"/>">
                                        <c:choose>
                                            <c:when test="${!empty allCmds}">
                                                <c:import url="/Includes/affichCmd.jsp"></c:import>
                                            </c:when>
                                        </c:choose>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        $(document).ready(function () {
            initDate();
        });

        Date.prototype.toDateInputValue = (function () {
            var local = new Date(this);
            local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
            return local.toJSON().slice(0, 10);
        });

        function initDate() {
            $('#dateCommande').val(new Date().toDateInputValue());
        }
        ;

//         $('#cmdTbl').find('tr').click(function (e) {
//             e.stopPropagation();
//             var row = $(this).find('td:first').text();
//             alert('You clicked ' + row);
//         });
    </script>
    <!--   Core JS Files   -->
    <script src="Includes/assets/js/core/jquery.min.js"></script>
    <script src="Includes/assets/js/core/popper.min.js"></script>
    <script src="Includes/assets/js/core/bootstrap.min.js"></script>
    <script src="Includes/assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>

    <!-- Chart JS -->
    <script src="Includes/assets/js/plugins/chartjs.min.js"></script>
    <!--  Notifications Plugin    -->
    <script src="Includes/assets/js/plugins/bootstrap-notify.js"></script>
    <!-- Control Center for Now Ui Dashboard: parallax effects, scripts for the example pages etc -->
    <script src="Includes/assets/js/now-ui-dashboard.js?v=1.0.1"></script>
    <!-- Now Ui Dashboard DEMO methods, don't include it in your project! -->
    <script src="Includes/assets/demo/demo.js"></script>
</html>


