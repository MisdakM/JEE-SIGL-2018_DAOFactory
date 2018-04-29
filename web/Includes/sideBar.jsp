<div class="sidebar" data-color="blue">
    <!--Tip 1: You can change the color of the sidebar using: data-color="blue | green | orange | red | yellow"-->
    <div class="logo">
        <a href="#" class="simple-text logo-mini">
            JEE
        </a>
        <a href="#" class="simple-text logo-normal">
            Gestion Commande
        </a>
    </div>
    <div class="sidebar-wrapper">
        <ul class="nav">
            <li class="${pageContext.request.servletPath == '/WEB-INF/pageAccueil.jsp' ? 'active' : ''}">
                <a href="pageAccueil">
                    <i class="now-ui-icons design_app"></i>
                    <p>Page d'accueil</p>
                </a>
            </li>
            <li class="${pageContext.request.servletPath == '/WEB-INF/formulaireClient.jsp' ? 'active' : 'makayen walo'}">
                <a href="creationClient">
                    <i class="now-ui-icons users_single-02"></i>
                    <p>Gérer vos Client </p>
                </a>
            </li>
            <li class="${pageContext.request.servletPath == '/WEB-INF/formulaireCommande.jsp' ? 'active' : ''}">
                <a href="creationCommande">
                    <i class="now-ui-icons shopping_delivery-fast"></i>
                    <p>Gérer vos Commande</p>
                </a>
            </li>
<!--            <li class="${pageContext.request.servletPath == 'creationClient' ? 'active' : ''}">
                <a href="tables.html">
                    <i class="now-ui-icons design_bullet-list-67"></i>
                    <p>Affichage de qlq chose....</p>
                </a>
            </li>-->
        </ul>
    </div>
</div>