<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'pointToPointIntent.label', default: 'manager.PointToPointIntent')}" />
        <title>Point To Point Intent</title>
    </head>
    <body>
        <a href="#list-pointToPointIntent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create">Create Path Intent</g:link></li>
                <li><g:link class="create" action="slice">Create Slice Intent</g:link></li>
            </ul>
        </div>
        <div id="list-pointToPointIntent" class="content scaffold-list" role="main">
            <h1 style="text-align: center">Point To Point Intent List</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                    <tr>
                        <th class="sortable" ><a href="/pointToPointIntent/index?sort=id&amp;max=10&amp;order=asc">ID</a></th>
                        <th class="sortable" ><a href="/pointToPointIntent/index?sort=intentKey&amp;max=10&amp;order=asc">Intent Key</a></th>
                        <th class="sortable" ><a href="/pointToPointIntent/index?sort=macAddressSrc&amp;max=10&amp;order=asc">Mac Address Src</a></th>
                        <th class="sortable" ><a href="/pointToPointIntent/index?sort=macAddressDes&amp;max=10&amp;order=asc">Mac Address Des</a></th>
                        <th class="sortable" ><a href="/pointToPointIntent/index?sort=deviceId&amp;max=10&amp;order=asc">Device ID</a></th>
                        <th class="sortable" ><a href="/pointToPointIntent/index?sort=egressPort&amp;max=10&amp;order=asc">Egress Port</a></th>
                        <th class="sortable" ><a href="/pointToPointIntent/index?sort=ingressPort&amp;max=10&amp;order=asc">Ingress Port</a></th>
                        <th class="sortable" ><a href="/pointToPointIntent/index?sort=sliceId&amp;max=10&amp;order=asc">Slice ID</a></th>
                        <th class="sortable" ><a href="/pointToPointIntent/index?sort=applicationId&amp;max=10&amp;order=asc">Application ID</a></th>
                        <th class="sortable" ><a href="/pointToPointIntent/index?sort=priority&amp;max=10&amp;order=asc">Priority</a></th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${pointToPointIntentList}" var="pointToPointIntent" >
                        <g:form method="DELETE">
                            <tr class="even">
                                <td><a href="/pointToPointIntent/show/${pointToPointIntent.id}">${pointToPointIntent.id}</a></td>
                                <td>${pointToPointIntent.intentKey}</td>
                                <td>${pointToPointIntent.macAddressSrc}</td>
                                <td>${pointToPointIntent.macAddressDes}</td>
                                <td>${pointToPointIntent.deviceId}</td>
                                <td>${pointToPointIntent.egressPort}</td>
                                <td>${pointToPointIntent.ingressPort}</td>
                                <td>${pointToPointIntent.sliceId}</td>
                                <td>${pointToPointIntent.applicationId}</td>
                                <td>${pointToPointIntent.priority}</td>
                                <td>
                                    <g:hiddenField name="id" value="${pointToPointIntent.id}" />
                                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                                </td>
                            </tr>
                        </g:form>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${pointToPointIntentCount ?: 0}" />
            </div>
        </div>
    </body>
</html>