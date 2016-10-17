function c$(o){return document.createElement(o);}
function json2str(o) {
    var arr = [];
    var fmt =function(s) {
        if (typeof s =='object'&& s !=null) return json2str(s);
        return/^(string|number)$/.test(typeof s) ?"\""+ s +"\"" : s;
    }
    for (var i in o) arr.push("\""+ i +"\":"+ fmt(o[i]));
    return'{'+ arr.join(',') +'}';
}
function is_wechat(){
    var ua = navigator.userAgent;
    if(ua.match(/MicroMessenger/i)!="MicroMessenger") {
        window.location.href="/Restaurant/error.html";
    }
}
function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
        "SymbianOS", "Windows Phone",
        "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}
function s$(id) {
    var shade = $("<div id='shade_'+id></div>");
    shade.css({
        position:'absolute',
        top:0,
        left:0,
        backgroundColor:"#808080",
        "text-align":"center",
        opacity:0.5,
        zIndex:300
    }).height($("#"+id).height()).width($("#"+id).width()).appendTo("#"+id);
return shade;
}

var editor ;