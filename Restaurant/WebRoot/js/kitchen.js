/**
 * Created by HiCat on 2016/8/1.
 */
$(function(){
    is_wechat();
    var goEasy = new GoEasy({
        appkey: '45ec7ca2-d450-4411-bfbd-8e6ee9ea6ac3'
    });
    goEasy.subscribe({
        channel: 'restaurant',
        onMessage: function(message){  //自动接收推送信息
            console.log("接收到消息"+message.content);
            document.getElementById("voice").play();
            $("#"+message.content).append("<span class='redpoint'>1</span>");
        }
    });
    $.ajax
    ({
        type: 'post',
        url: "/Restaurant/alltable",
        dataType: 'json',
        success: function (data)
        {
            initleft(data);
        }
    })
})
function initleft(json)
{
    for(var o in json)
    {
        var div = c$("div");
        $(div).attr({"class":"classify","id":json[o]["id"]});
        $(div).text(json[o]["seatnum"]);
        $(".left").append($(div));
    }
    $(".classify").bind("click",function(){
        $(this).addClass("classify_1");
        $(".right,.classify_1").not(this).removeClass("classify_1");
        var id = $(this).attr("id");
        if($(this).find(".redpoint").length!=0) $(this).find(".redpoint").remove();
        $.ajax
        ({
            data:{"id":id},
            type: 'post',
            url: "/Restaurant/selectTable",
            dataType: 'json',
            success: function (data)
            {
                initright(data);
            }
        })
    })
}
function initright(json)
{
    $(".right").remove();
    var table_json = $.parseJSON(json[0]["cart"]);
    var already_json = $.parseJSON(json[0]["already"]);
    console.log(table_json)
    var right = c$("div");
    $(right).attr("class","right");
    $("body").append($(right));
    $(right).show();
        for(var o in table_json)
        {
            console.log(o);
            var li = c$("div");
            var name = c$("div");
            var img = c$("img");
            var num = c$("input");
            var label1 = c$("label");
            var num2 = c$("input");
            var label2 = c$("label");
            var already = c$("div");
            var should = c$("div");
            $(already).attr({"style":"float:right;margin-top:2vh","class":"already_div"});
            $(should).attr("style","float:right;");
            $(label1).html("应上数量:&nbsp;");
            $(label1).attr({"style":"float:right;"})
            $(label2).html("已上数量:&nbsp;");
            $(label2).attr({"style":"float:right;"})
            $(li).attr({"class":"kitchen_li","id":"kitchen_"+o});
            $(name).text(o);
            $(name).attr({"class":"kitchen_name"});
            $(img).attr({"class":"kitchen_img","src":table_json[o].img});
            $(num).attr({"type":"text","value":table_json[o].num,"class":"kitchen_num","onkeyup":"this.value=this.value.replace(/[^0-9]\\D*$/,'')","maxlength":"1","disabled":"disabled"});
            $(num2).attr({"type":"text","value":0,"class":"kitchen_num","onkeyup":"this.value=this.value.replace(/[^0-9]\\D*$/,'')","maxlength":"1"});
            $(li).append($(name));
            $(li).append($(img));
            $(should).append($(num));
            $(should).append($(label1));
            $(li).append($(should));
            $(li).append($(already));
            $(already).append($(num2));
            $(already).append($(label2));
            $(right).append($(li));
            console.log(already_json);
            if(already_json==null) already_json={};
            if(o in already_json)
            {
                if(already_json[o]==table_json[o].num)
                {
                    var shade = s$("kitchen_"+o);
                    shade.width($(".right").width());
                    shade.html("<strong class='already_strong'>已上完</strong>")
                    $(num2).val(already_json[o]);
                }
                else
                {
                    $(num2).val(already_json[o]);
                    var confirm = c$("a");
                    $(confirm).attr({"class":"kitchen_confirm","href":"javascript:void(0)"})
                    $(confirm).text("确认上菜");
                    $(confirm).appendTo($(li));
                    $(confirm).bind("click",function(){
                        $.ajax
                        ({
                            data:{"dishname":$(this).siblings(".kitchen_name").text(),"servenum":$(this).siblings(".already_div").find(".kitchen_num").val(),"id":json[0]["id"]},
                            type: 'post',
                            url: "/Restaurant/serve",
                            dataType: 'json',
                            success: function (data)
                            {
                                alert(data);
                            }
                        })
                    })
                }
            }
            else
            {
                var confirm = c$("a");
                $(confirm).attr({"class":"kitchen_confirm","href":"javascript:void(0)"})
                $(confirm).text("确认上菜");
                $(confirm).appendTo($(li));
                $(confirm).bind("click",function(){
                    $.ajax
                    ({
                        data:{"dishname":$(this).siblings(".kitchen_name").text(),"servenum":$(this).siblings(".already_div").find(".kitchen_num").val(),"id":json[0]["id"]},
                        type: 'post',
                        url: "/Restaurant/serve",
                        dataType: 'json',
                        success: function (data)
                        {
                            alert(data);
                            $("#"+json[0]["id"]).click();
                        }
                    })
                })
            }
        }
        var end = c$("a");
        $(end).attr({"class":"end_btn"});
        $(end).text("该桌就餐结束");
        $(end).appendTo($(right));
        $(end).bind("click",function(){
            if(window.confirm("确定该桌就餐结束吗？"))
            {
                $.ajax
                ({
                    data:{"id":json[0]["id"]},
                    type: 'post',
                    url: "/Restaurant/end",
                    dataType: 'json',
                    success: function (data)
                    {
                        alert(data);
                        window.location.reload();
                    }
                })
            }
        })
}


