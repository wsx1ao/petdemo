var xmlHttpRequest;
function createXMLHttpRequest()
{
        xmlHttpRequest = new XMLHttpRequest();
}

function showInform(categoryId) {
    sendRequest("categoryShowJsServlet?categoryId=" + categoryId);
}

function sendRequest(url) {
    createXMLHttpRequest();
    xmlHttpRequest.open("GET", url, true);
    xmlHttpRequest.onreadystatechange = processResponse;
    xmlHttpRequest.send(null);
}
function processResponse() {
    if (xmlHttpRequest.readyState == 4) {
        if (xmlHttpRequest.status == 200) {
            var resp = xmlHttpRequest.responseText;
            //显示悬浮层
            var inform = document.getElementById("inform");
            inform.innerText = resp;
            inform.style.display = "block";
        }
    }
}
function hiddenInform(event){
    var informDiv = document.getElementById('inform');
    var x=event.clientX;
    var y=event.clientY;
    var divx1 = informDiv.offsetLeft;
    var divy1 = informDiv.offsetTop;
    var divx2 = informDiv.offsetLeft + informDiv.offsetWidth;
    var divy2 = informDiv.offsetTop + informDiv.offsetHeight;
    if( x < divx1 || x > divx2 || y < divy1 || y > divy2){
        document.getElementById('inform').style.display='none';
    }
}