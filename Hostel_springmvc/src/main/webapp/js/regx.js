/**
 * Created by disinuo on 17/5/17.
 */
function checkIDCard(str) {
    var pattern=/\d{18,18}/g;
    return pattern.test(str);
}

function checkEmail(str) {
    var pattern=/^\w+@\w+(\.)\w+/g;
    return pattern.test(str);
}

