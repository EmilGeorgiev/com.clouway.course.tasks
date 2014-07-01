/**
 * Show value on display.
 * @param elem is element which value is show on display.
 */
function showValue(elem) {

    var val = document.getElementById("expression");

    if(val.innerHTML == "") {
        val.innerHTML = elem.value;
    } else {
        val.innerHTML += elem.value;
    }
}

/**
 * Delete last symbol.
 */
function clearLastSymbol() {
    var val = document.getElementById("expression");
    var lastIndex = val.innerHTML.length - 1;
    val.innerHTML = val.innerHTML.substring(0, lastIndex);
}

/**
 * Delete expression.
 */
function clearExpression() {
    document.getElementById("expression").innerHTML = "";
}

/**
 * Calculate expression.
 */
function calculateExpression() {

    document.getElementById("hidden").value = document.getElementById("expression").innerHTML;

}

/**
 * Show operation on display and does not allow show the two operation one after the other.
 * @param elem is operation whose value will be displayed.
 */
function showOperation(elem) {
    var expression = document.getElementById("expression");
    var lastSymbol = expression.innerHTML.charAt(expression.innerHTML.length - 1);

    if (!isNaN(lastSymbol)) {
        showValue(elem);
    }



}