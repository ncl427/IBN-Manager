<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'pointToPointIntent.label', default: 'manager.PointToPointIntent')}" />
        <title>Point To Point Intent</title>
    </head>
    <body>
        <a href="#create-pointToPointIntent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index">List</g:link></li>
            </ul>
        </div>
        <div id="create-pointToPointIntent" class="content scaffold-create" role="main">
            <h1 style="text-align: center">Create Slice Intent</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.pointToPointIntent}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.pointToPointIntent}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.pointToPointIntent}" method="POST">
                <fieldset class="form">
                    <div class="fieldcontain required">
                        <label for="macAddressSrc">MacAddress Source
                            <span class="required-indicator">*</span>
                        </label>
                        <input type="text" name="macAddressSrc" value="" required="" id="macAddressSrc">
                    </div>
                    <div class="fieldcontain required">
                        <label for="applicationId">Application Id
                            <span class="required-indicator">*</span>
                        </label>
                        <input type="text" name="applicationId" value="" required="" id="applicationId">
                    </div>
                    <div class="fieldcontain required">
                        <label for="priority">Priority
                            <span class="required-indicator">*</span>
                        </label><input type="number" name="priority" value="0" required="" id="priority">
                    </div>
                    <div class="fieldcontain required">
                        <label for="deviceId">Device Id
                            <span class="required-indicator">*</span>
                        </label>
                        <input type="text" name="deviceId" value="" required="" id="deviceId">
                    </div>
                    <div class="fieldcontain required">
                        <label for="ingressPort">Ingress Port
                            <span class="required-indicator">*</span>
                        </label>
                        <input type="text" name="ingressPort" value="" required="" id="ingressPort">
                    </div>
                    <div class="fieldcontain required">
                        <label for="egressPort">Egress Port
                            <span class="required-indicator">*</span>
                        </label>
                        <input type="text" name="egressPort" value="" required="" id="egressPort">
                    </div>
                    <div class="fieldcontain required">
                        <label for="sliceId">Slice ID
                            <span class="required-indicator">*</span>
                        </label>
                        <input type="text" name="sliceId" value="" required="" id="sliceId">
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
