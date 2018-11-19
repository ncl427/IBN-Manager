<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <asset:stylesheet src="application.css"/>
    <asset:stylesheet src="style.css"/>
    <asset:javascript src="application.js"/>
    <asset:javascript src="jquery-2.2.0.min.js"/>
    <asset:javascript src="menu-collapsed.js"/>
    <asset:javascript src="menu.js"/>

    <g:layoutHead/>
</head>
<body>

    <g:javascript>
      function callMyAjax(){
        $.ajax({
          url:'${g.createLink( controller:'yourcontroller', action:'youraction')}',
          data:{
               param1:param1,
               param2:param2
          }
        });
      }
    </g:javascript>

    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div style="">
            <asset:image src="ibn_banner.png" alt="IBN Manager Logo" width="110%" height="150px"  />
        </div>
    </div>

    <div style="float: left; width: 20%; padding: 1em; height: 60%">
        <ul id="menu">
            <li><a class="home" href="${createLink(uri: '/')}">Home</a></li>
            <li>
                <a href="#">Point To Point Intent</a>
                <ul>
                    <li><a class="home" href="${createLink(uri: '/pointToPointIntent/index')}">List</a></li>
                    <li><a class="home" href="${createLink(uri: '/pointToPointIntent/create')}">Create Path Intent</a></li>
                    <li><a class="home" href="${createLink(uri: '/pointToPointIntent/slice')}">Create Slice Intent</a></li>
                </ul>
            </li>
            <li>
                <a href="#">Host To Host Intent</a>
                <ul>
                    <li><a class="home" href="${createLink(uri: '/hostToHostIntent/index')}">List</a></li>
                    <li><a class="home" href="${createLink(uri: '/hostToHostIntent/create')}">Create New</a></li>
                </ul>
            </li>
        </ul>
    </div>

    <div class="layout-body">
        <g:layoutBody/>
    </div>

    <div class="footer" role="contentinfo">
        <p>© Copyright 2018 | NCL-JEJU National University Korea</p>
    </div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>
</body>
</html>
