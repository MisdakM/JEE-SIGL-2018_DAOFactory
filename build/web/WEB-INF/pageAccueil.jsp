<%-- 
    Document   : formulaireClient
    Created on : Mar 4, 2018, 10:41:59 PM
    Author     : Marouane
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timeago/1.6.3/jquery.timeago.min.js"></script>

        <script type="text/javascript">
            function confirmAction(e, msg) {
                var confirmation = confirm(msg);

                if (!confirmation)
                {
                    e.preventDefault();
                }

                return confirmation;
            }
        </script>
    </head>

    <body class="">
        <div class="wrapper ">
            <c:import url="/Includes/sideBar.jsp" />

            <div class="main-panel" >
                <!-- Navbar -->
                <c:import url="/Includes/menu.jsp"/>

                <!-- End Navbar -->
                <div class="panel-header panel-header-lg">
                    <canvas id="bigDashboardChart"></canvas>
                </div>
                <div class="content">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="card card-chart">
                                <div class="card-header">
                                    <h5 class="card-category">Les achats</h5>
                                    <h4 class="card-title">Les commandes livrées</h4>
                                </div>
                                <div class="card-body">
                                    <div class="chart-area">
                                        <canvas id="lineChartExample"></canvas>
                                    </div>
                                </div>
                                <div class="card-footer">
                                    <div class="stats">
                                        <i class="now-ui-icons arrows-1_refresh-69"></i> 
                                        <span id="ago" class="ago"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="" hidden>
                            <div class="card card-chart">
                                <div class="card-header">
                                    <h5 class="card-category">2018 Sales</h5>
                                    <h4 class="card-title">All products</h4>
                                    <div class="dropdown">
                                        <button type="button" class="btn btn-round btn-default dropdown-toggle btn-simple btn-icon no-caret" data-toggle="dropdown">
                                            <i class="now-ui-icons loader_gear"></i>
                                        </button>
                                        <div class="dropdown-menu dropdown-menu-right">
                                            <a class="dropdown-item" href="#">Action</a>
                                            <a class="dropdown-item" href="#">Another action</a>
                                            <a class="dropdown-item" href="#">Something else here</a>
                                            <a class="dropdown-item text-danger" href="#">Remove Data</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="chart-area">
                                        <canvas id="lineChartExampleWithNumbersAndGrid"></canvas>
                                    </div>
                                </div>
                                <div class="card-footer">
                                    <div class="stats">
                                        <i class="now-ui-icons arrows-1_refresh-69"></i> Just Updated
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-8 col-md-6">
                            <div class="card card-chart">
                                <div class="card-header">
                                    <h5 class="card-category">Les commandes</h5>
                                    <h4 class="card-title">Performance de la dernière 24 heures</h4>
                                </div>
                                <div class="card-body">
                                    <div class="chart-area">
                                        <canvas id="barChartSimpleGradientsNumbers"></canvas>
                                    </div>
                                </div>
                                <div class="card-footer">
                                    <div class="stats">
                                        <i class="now-ui-icons ui-2_time-alarm"></i>
                                        <span id="ago" class="ago"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-header">
                                    <h5 class="card-category">Les commandes</h5>
                                    <h4 class="card-title">Aperçu des commandes</h4>
                                </div>
                                <div class="card-body">
                                    <form method="POST" id="allCmds" >

                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-header">
                                    <h5 class="card-category">Historique</h5>
                                    <h4 class="card-title">Hstorique des operations</h4>
                                </div>
                                <div class="card-body">
                                    <div id="ajaxMsg" class="alert alert-warning">
                                        <button type="button" aria-hidden="true" class="close">
                                            <i class="now-ui-icons ui-1_simple-remove"></i>
                                        </button>
                                        <span id="msg"></span>
                                    </div>
                                    <form method="POST" id="histo" >

                                    </form>
                                </div>
                                <div class="card-footer">
                                    <hr>
                                    <div class="stats">
                                        <i class="now-ui-icons loader_refresh spin"></i> Actualisé chaque 3s
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>

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
    <script>

            var d = new Date();
            $(".ago").html("Actualisé " + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds());
            $(document).ready(function () {
                $("#ajaxMsg").hide();
                ajaxRefresh();
                setInterval(function () {
                    ajaxRefresh();
                }, 1000);

                function ajaxRefresh() {
                    $.post("pageAccueil",
                            {
                                allCmds: "all"
                            },
                            function (data, status) {
                                $("#allCmds").html(data);
                            });
                    $.post("pageAccueil",
                            {
                                history: "all"
                            },
                            function (data, status) {
                                $("#histo").html(data);
                            });
                }
            });

            function deleteHistory(e) {
                $.post("pageAccueil",
                        {
                            deleteH: e.id
                        },
                        function (data, status) {
                            showMsg(data);
                        });
            }

            function showMsg(data) {
                $("#msg").html(data);
                $("#ajaxMsg").show();
                
                setTimeout(function () {
                    $("#ajaxMsg").fadeOut();
                }, 3000);
            }

            var table = [];
            var hours = [];
            var nbrCmds = [];
            var shippedCmds = [];

        <c:forEach items="${table}" var="nbr">
            table.push("${nbr}");
        </c:forEach>

        <c:forEach items="${hours}" var="nbr">
            var from = ${nbr};
            var to = from + 1;
            hours.push("Depuis " + from + "h00min" + " vers " + to + "h00min");
        </c:forEach>

        <c:forEach items="${nbrCmds}" var="nbr">
            nbrCmds.push("${nbr}");
        </c:forEach>

        <c:forEach items="${shippedCmds}" var="nbr">
            shippedCmds.push("${nbr}");
        </c:forEach>

            $(document).ready(function () {
                // Javascript method's body can be found in assets/js/demos.js
                demo.initDashboardPageCharts(table, hours, nbrCmds, shippedCmds);
            });
    </script>

</html>
