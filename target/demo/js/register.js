var xmlHttpRequest;
document.getElementById("username").addEventListener("blur",usernameIsExist);
function usernameIsExist() {
    var username = document.getElementById("username").value;
    console.log(username);
    sendRequest("usernameIsExistServlet?username=" + username);
}

function sendRequest(url) {
    xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open("GET", url, true);
    xmlHttpRequest.onreadystatechange = processResponse;
    xmlHttpRequest.send(null);
}
function processResponse() {
    if (xmlHttpRequest.readyState == 4) {
        if (xmlHttpRequest.status == 200) {
            var responseInfo = xmlHttpRequest.responseXML.getElementsByTagName("msg")[0].firstChild.data;

            var div1 = document.getElementById('usernameMsg');

            if (responseInfo == "Exist") {
                div1.innerHTML = "<font color='red'>用户名已存在</font>";
            } else {
                div1.innerHTML = "<font color='green'>用户名可用</font>";
            }
        }
    }
}

