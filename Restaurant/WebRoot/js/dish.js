/**
 * Created by HiCat on 2016/8/1.
 */
var dish_json = {};
var cart_json ={};
var total = 0 ;
var restname = $("#restname").val();
var tableid = $("#tableid").val();
$(function()
    {
        is_wechat();
        //绑定购物车点击事件，生成购物车页面
        $(".cart").bind("click",function(){
            $(this).addClass("classify_1");
            $(".right,.classify_1").not(this).removeClass("classify_1");
            $(".right").hide();
            $("#right_cart").remove();
            var rignt_cart = c$("div");
            $(rignt_cart).attr({"class":"right","id":"right_cart"});
            $("body").append($(rignt_cart));
            for(var o in cart_json)
            {
                $(rignt_cart).show();
                var dish = cart_json[o];
                var dish_li = c$("div");
                var dish_img = c$("img")
                var dish_name = c$("strong");
                var dish_information = c$("div");
                var dish_price = c$("strong");
                var spinner = c$("div");
                var add = c$("a");
                var dish_num = c$("input");
                var reduce = c$("a");
                $(dish_li).attr("class","dish_li");
                $(dish_img).attr({"class":"dish_img","src":dish.img});
                $(dish_name).attr("class","dish_name");
                $(dish_name).text(o);
                $(dish_information).attr("class","dish_information");
                $(dish_price).attr("class","dish_price");
                $(dish_price).text("价格："+dish.price);
                $(spinner).attr("class","spinner");
                $(add).attr("class","add");
                $(add).bind("click",function(){
                    var price = parseFloat($(this).parent().siblings('.dish_price').text().replace("价格：",""));
                    var name = $(this).parent().parent().siblings(".dish_name").text();
                    cart_json[name]["num"]++;
                    $(this).siblings(".dish_num").val(cart_json[name]["num"]);
                    total += price;
                    console.log(cart_json);
                    totalRefresh()
                });
                $(dish_num).attr({"type":"text","class":"dish_num","value":dish.num,"onkeyup":"this.value=this.value.replace(/[^0-9]\\D*$/,'')","maxlength":"1"});
                $(dish_num).bind("blur",function(){
                    if(parseInt($(dish_num).val())===NaN)
                    {
                        $(dish_num).val(0)
                    }
                    else
                    {
                        var price = parseFloat($(this).parent().siblings('.dish_price').text().replace("价格：",""));
                        var name = $(this).parent().parent().siblings(".dish_name").text();
                        var num = cart_json[name]["num"];
                        cart_json[name]["num"] = parseInt($(dish_num).val());
                        total += price * (cart_json[name]["num"] - num);
                        totalRefresh()
                    }
                })
                $(reduce).attr("class","reduce");
                $(reduce).bind("click",function(){
                    var price = parseFloat($(this).parent().siblings('.dish_price').text().replace("价格：",""));
                    var name = $(this).parent().parent().siblings(".dish_name").text();
                    cart_json[name]["num"]--;
                    $(this).siblings(".dish_num").val(cart_json[name]["num"]);
                    total -= price;
                    console.log(cart_json);
                    console.log(total);
                    totalRefresh();
                });
                $(dish_li).append(  $(dish_img));
                $(dish_li).append(  $(dish_name));
                $(dish_li).append(  $(dish_information));
                $(dish_information).append($(dish_price));
                $(dish_information).append($(spinner));
                $(spinner).append($(add));
                $(spinner).append($(dish_num));
                $(spinner).append($(reduce));
                $("#right_cart").append($(dish_li));
            }
            var cart_total = c$("strong");
            $(cart_total).attr("class","cart_total");
            $(cart_total).text("总价："+total);
            var cart_check = c$("a");
            $(cart_check).attr("class","cart_check");
            $(cart_check).text("确认下单");
            $(cart_check).bind("click",function()
            {
                $.ajax
                ({
                    data:{"cart":json2str(cart_json),"id":tableid,"total":total},
                    type: 'post',
                    url: "/Restaurant/order",
                    dataType: 'json',
                    success: function (data)
                    {
                        alert(data);
                        window.location.reload();
                    }
                })
            })
            $("#right_cart").append($(cart_total));
            $("#right_cart").append($(cart_check));
        })
        //ajax获取菜单数据
        $.ajax
        ({
            type: 'post',
            url: "/Restaurant/dish/dish",
            dataType: 'json',
            success: function (data)
            {
                console.log(data)
                for (var o in data)
                {
                    var classname = data[o].classname ;
                    if(!(classname in dish_json))
                    {
                        dish_json[classname] = [data[o]];
                    }
                    else dish_json[classname].push(data[o]);
                }
                init(dish_json);
            }
        })

    }
)
//初始化页面
function init(json)
{
    var i = 0 ;
    for (var o in json)
    {
        (function(){
            var div = c$("div");
            $(div).attr({"class":"classify","id":"classify"+i});
            $(div).text(o);
            $(".left").append($(div));
            var dish_list = json[o];
            var dish_class = o ;
            $("#classify"+i).bind("click",function(){
                $(this).addClass("classify_1");
                $(".right,.classify_1").not(this).removeClass("classify_1");
                (function(){
                    $(".right").hide();
                    if($("#right_"+dish_class).length===0)
                    {
                        var list = dish_list;
                        var right = c$("div");
                        $(right).attr({"class": "right", "id": "right_" + dish_class});
                        $("body").append($(right));
                        for(var j in json[dish_class])
                        {
                            (function(){
                                console.log(dish_class)
                                console.log(list)
                                $(right).show();
                                var dish = list[j];
                                var dish_li = c$("div");
                                var dish_img = c$("img")
                                var dish_name = c$("strong");
                                var dish_information = c$("div");
                                var dish_price = c$("strong");
                                var spinner = c$("div");
                                var add = c$("a");
                                var dish_num = c$("input");
                                var reduce = c$("a");
                                $(dish_li).attr("class","dish_li");
                                $(dish_img).attr({"class":"dish_img","src":dish.img});
                                $(dish_name).attr("class","dish_name");
                                $(dish_name).text(dish.name);
                                $(dish_information).attr("class","dish_information");
                                $(dish_price).attr("class","dish_price");
                                $(dish_price).text("价格："+dish.price);
                                $(spinner).attr("class","spinner");
                                $(add).attr("class","add");
                                $(add).bind("click",function(){
                                    var price = parseFloat($(this).parent().siblings('.dish_price').text().replace("价格：",""));
                                    var name = $(this).parent().parent().siblings(".dish_name").text();
                                    if(!(name in cart_json))
                                    {
                                        cart_json[name] = {"num":1,"price":price,"img":$(this).parent().parent().siblings(".dish_img").attr("src")};
                                    }
                                    else cart_json[name]["num"]++;
                                    $(this).siblings(".dish_num").val(cart_json[name]["num"]);
                                    total += price;
                                    console.log(cart_json);
                                });
                                $(dish_num).attr({"type":"text","class":"dish_num","value":0,"onkeyup":"this.value=this.value.replace(/[^0-9]\\D*$/,'')","maxlength":"1"});
                                $(dish_num).bind("blur",function(){
                                    if(parseInt($(dish_num).val())===NaN)
                                    {
                                        $(dish_num).val(0)
                                    }
                                    else
                                    {
                                        var price = parseFloat($(this).parent().siblings('.dish_price').text().replace("价格：",""));
                                        var name = $(this).parent().parent().siblings(".dish_name").text();
                                        if(!(name in cart_json))
                                        {
                                            cart_json[name] ={"num":parseInt($(dish_num).val()),"price":price,"img":$(this).parent().parent().siblings(".dish_img").attr("src")} ;
                                            total += price*cart_json[name]["num"];

                                        }
                                        else {

                                            var num = cart_json[name]["num"];
                                            cart_json[name]["num"] = parseInt($(dish_num).val());
                                            total += price * (cart_json[name]["num"] - num);
                                        }

                                    }
                                })
                                $(reduce).attr("class","reduce");
                                $(reduce).bind("click",function(){
                                    var price = parseFloat($(this).parent().siblings('.dish_price').text().replace("价格：",""));
                                    var name = $(this).parent().parent().siblings(".dish_name").text();
                                    if(!(name in cart_json)||cart_json[name]["num"]===0)
                                    {
                                        return true;
                                    }
                                    else
                                    {
                                        cart_json[name]["num"]--;
                                        $(this).siblings(".dish_num").val(cart_json[name]["num"]);
                                        total -= price;
                                        console.log(cart_json);
                                        console.log(total);
                                    }
                                });
                                $(dish_li).append(  $(dish_img));
                                $(dish_li).append(  $(dish_name));
                                $(dish_li).append(  $(dish_information));
                                $(dish_information).append($(dish_price));
                                $(dish_information).append($(spinner));
                                $(spinner).append($(add));
                                $(spinner).append($(dish_num));
                                $(spinner).append($(reduce));
                                $("#right_"+dish_class).append($(dish_li));
                            })();
                        }
                    }
                    else $("#right_"+ dish_class).show();
                })();
            })
            i++;
        })();
    }
}
function  totalRefresh()
{
    console.log(total);
    $(".cart_total").text("总价："+total);
}