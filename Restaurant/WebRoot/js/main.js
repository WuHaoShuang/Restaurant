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
(function( factory ) {
    if ( typeof define === "function" && define.amd ) {
        define( ["jquery", "../jquery.validate"], factory );
    } else {
        factory( jQuery );
    }
}
(function( $ ) {

    /*
     * Translated default messages for the jQuery validation plugin.
     * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
     */
    $.extend($.validator.messages, {
        required: "这是必填字段",
        remote: "请修正此字段",
        email: "请输入有效的电子邮件地址",
        url: "请输入有效的网址",
        date: "请输入有效的日期",
        dateISO: "请输入有效的日期 (YYYY-MM-DD)",
        number: "请输入有效的数字",
        digits: "只能输入数字",
        creditcard: "请输入有效的信用卡号码",
        equalTo: "你的输入不相同",
        extension: "请输入有效的后缀",
        maxlength: $.validator.format("最多可以输入 {0} 个字符"),
        minlength: $.validator.format("最少要输入 {0} 个字符"),
        rangelength: $.validator.format("请输入长度在 {0} 到 {1} 之间的字符串"),
        range: $.validator.format("请输入范围在 {0} 到 {1} 之间的数值"),
        max: $.validator.format("请输入不大于 {0} 的数值"),
        min: $.validator.format("请输入不小于 {0} 的数值")
    });

}));
jQuery.validator.addMethod("price", function(value, element) {
    return this.optional(element) || /^\d{0,4}(\.\d{0,2})?$/.test(value);
}, "请输入合适的价格");
jQuery.validator.addMethod("checkPic", function(value, element) {
    var filepath=$("#pictureAjax").val();
    //获得上传文件名
    var fileArr=filepath.split("\\");
    var fileTArr=fileArr[fileArr.length-1].toLowerCase().split(".");
    var filetype=fileTArr[fileTArr.length-1];
    //切割出后缀文件名
    if(filetype != "png"){
        return false;
    }else{
        return true;
    }
}, "上传图片格式不适合");