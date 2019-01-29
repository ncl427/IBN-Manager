<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Host To Host Intent</title>
        <g:set var="entityName" value="${message(code: 'hostToHostIntent.label', default: 'HostToHostIntent')}" />
    </head>
    <body>
        <a href="#list-hostToHostIntent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create">Create New</g:link></li>
            </ul>
        </div>
        <div id="list-hostToHostIntent" class="content scaffold-list" role="main">
            <h1>Host To Host Intent List</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                    <tr>
                        <th class="sortable" ><a href="/hostToHostIntent/index?sort=id&amp;max=10&amp;order=asc">ID</a></th>
                        <th class="sortable" ><a href="/hostToHostIntent/index?sort=intentKey&amp;max=10&amp;order=asc">Intent Key</a></th>
                        <th class="sortable" ><a href="/hostToHostIntent/index?sort=macAddressSrc&amp;max=10&amp;order=asc">Mac Address Src</a></th>
                        <th class="sortable" ><a href="/hostToHostIntent/index?sort=macAddressDes&amp;max=10&amp;order=asc">Mac Address Des</a></th>
                        <th class="sortable" ><a href="/hostToHostIntent/index?sort=applicationId&amp;max=10&amp;order=asc">Application Id</a></th>
                        <th class="sortable" ><a href="/hostToHostIntent/index?sort=priority&amp;max=10&amp;order=asc">Priority</a></th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${hostToHostIntentList}" var="hostToHostIntent" >
                        <g:form method="DELETE">
                            <tr class="even">
                                <td><a href="/hostToHostIntent/show/${hostToHostIntent.id}">${hostToHostIntent.id}</a></td>
                                <td>${hostToHostIntent.intentKey}</td>
                                <td>${hostToHostIntent.macAddressSrc}</td>
                                <td>${hostToHostIntent.macAddressDes}</td>
                                <td>${hostToHostIntent.applicationId}</td>
                                <td>${hostToHostIntent.priority}</td>
                                <td>
                                    <g:hiddenField name="id" value="${hostToHostIntent.id}" />
                                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                                </td>
                            </tr>
                        </g:form>
                    </g:each>
                </tbody>
            </table>

            <div class="pagination">
                <g:paginate total="${hostToHostIntentCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
