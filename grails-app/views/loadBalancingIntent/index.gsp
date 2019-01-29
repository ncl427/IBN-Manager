<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'loadBalancingIntent.label', default: 'LoadBalancingIntent')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-loadBalancingIntent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create">Create Load Balancing Intent</g:link></li>
            </ul>
        </div>
        <div id="list-loadBalancingIntent" class="content scaffold-list" role="main">
            <h1 style="text-align: center">Load Balancing Intent List</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                <tr>
                    <th class="sortable" ><a href="/loadBalancingIntent/index?sort=id&amp;max=10&amp;order=asc">ID</a></th>
                    <th class="sortable" ><a href="/loadBalancingIntent/index?sort=macAddressSrc&amp;max=10&amp;order=asc">Mac Address Src</a></th>
                    <th class="sortable" ><a href="/loadBalancingIntent/index?sort=deviceId&amp;max=10&amp;order=asc">Device ID</a></th>
                    <th class="sortable" ><a href="/loadBalancingIntent/index?sort=ingressPort&amp;max=10&amp;order=asc">Ingress Port</a></th>
                    <th class="sortable" ><a href="/loadBalancingIntent/index?sort=applicationId&amp;max=10&amp;order=asc">Application ID</a></th>
                    <th>Action</th>
                </tr>
                </thead>

                <tbody>
                <g:each in="${loadBalancingIntentList}" var="loadBalancingIntent" >
                    <g:form method="DELETE">
                        <tr class="even">
                            <td><a href="/loadBalancingIntent/show/${loadBalancingIntent.id}">${loadBalancingIntent.id}</a></td>
                            <td>${loadBalancingIntent.macAddressSrc}</td>
                            <td>${loadBalancingIntent.deviceId}</td>
                            <td>${loadBalancingIntent.ingressPort}</td>
                            <td>${loadBalancingIntent.applicationId}</td>
                            <td>
                                <g:hiddenField name="id" value="${loadBalancingIntent.id}" />
                                <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                            </td>
                        </tr>
                    </g:form>
                </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${loadBalancingIntentCount ?: 0}" />
            </div>
        </div>
    </body>
</html>