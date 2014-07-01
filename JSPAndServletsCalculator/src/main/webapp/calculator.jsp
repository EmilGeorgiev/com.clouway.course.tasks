<%--
  Created by IntelliJ IDEA.
  User: clouway
  Date: 6/27/14
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="calculator.css"/>
    <script type="text/javascript" src="calculator.js"></script>
    <title></title>
</head>
<body>
<form action="/calculatorServlet" method="post">
    <div id="calculator1" class="calculator" align="center">
        <div class="allButtons">
            <div id="display" class="row">
                <label type="text" id="expression">${result}</label>
            </div>
            <div id="top" class="row">
                <input type="button" id="7" onclick="showValue(this)" class="button"  value="7"/>
                <input type="button" id="8"  onclick="showValue(this)" class="button"  value="8"/>
                <input type="button" id="9"  onclick="showValue(this)" class="button"  value="9"/>
                <input type="button" id="CE" onclick="clearExpression()" class="button" value="CE"/>
                <input type="button" id="C" onclick="clearLastSymbol()" class="endButton" value="C"/>
            </div>
            <div id="belowTheTop" class="row">
                <input type="button" id="4" onclick="showValue(this)" class="button"  value="4"/>
                <input type="button" id="5" onclick="showValue(this)" class="button"  value="5"/>
                <input type="button" id="6" onclick="showValue(this)" class="button"  value="6"/>
                <input type="button" id="X" onclick="showOperation(this)" class="button" value="*"/>
                <input type="button"  id="/" onclick="showOperation(this)" class="endButton" value="/"/>
            </div>
            <div id="aboveTheBottom" class="row">
                <input type="button" onclick="showValue(this)" class="button" value="1"/>
                <input type="button" onclick="showValue(this)" class="button" value="2"/>
                <input type="button" onclick="showValue(this)" class="button" value="3"/>
                <input type="button" onclick="showOperation(this)" accept="" class="button" value="+"/>
                <input type="button" onclick="showOperation(this)" class="endButton" value="-"/>
            </div>
            <div id="bottom" class="row">
                <form method="post" action="/calculatorServlet">
                </form>
                <input type="button" onclick="showValue(this)" class="button" value="0"/>
                <input type="button" onclick="showOperation(this)" id="point" class="button" value="."/>
                <input type="submit" onclick="calculateExpression()" id="equals" class="evaluate" value="=">
                <input type="hidden" id="hidden" name="expression">
            </div>
        </div>
    </div>
</form>
</body>
</html>
