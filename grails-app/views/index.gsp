<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>IBN Manager</title>
</head>
<body>
    <content tag="nav">
        %{--<asset:image src="ibn_banner.png" alt="IBN Manager Logo"/>--}%
    </content>

    <div id="content" role="main">
        <section class="row colset-2-its">
            <h1>Welcome to Intent Based Networking Manager</h1>

            <p>
                Intent Based Networking Manager is a module for SD-ACCESS slicing on the KOREN SmartX Open Platform.
                IBN Manager can be used to send Host to Host intent to SDN ONOS Controller. IBN Manager also can be
                used to send Point to Point Intent to SDN ONOS Controller.

            </p>

            <div id="controllers" role="navigation">
                <h2>Available Functionality:</h2>
                <ul>
                    <li class="controller">
                        <g:link controller="hostToHostIntent">Host To Host Intent</g:link>
                    </li>
                    <li class="controller">
                        <g:link controller="pointToPointIntent">Point To Point Intent</g:link>
                    </li>
                </ul>
            </div>
        </section>
    </div>
</body>
</html>
