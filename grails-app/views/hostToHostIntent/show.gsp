<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Host To Host Intent</title>
        <g:set var="entityName" value="${message(code: 'hostToHostIntent.label', default: 'HostToHostIntent')}" />
    </head>
    <body>
        <a href="#show-hostToHostIntent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index">List</g:link></li>
                <li><g:link class="create" action="create">Create New</g:link></li>
            </ul>
        </div>
        <div id="show-hostToHostIntent" class="content scaffold-show" role="main">
            <h1 style="text-align: center">Show Host To Host Intent</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:display bean="hostToHostIntent" />
            <g:form resource="${this.hostToHostIntent}" method="DELETE">
                <fieldset class="buttons">
                    %{--<g:link class="edit" action="edit" resource="${this.hostToHostIntent}"><g:message code="default.button.edit.label" default="Edit" /></g:link>--}%
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
