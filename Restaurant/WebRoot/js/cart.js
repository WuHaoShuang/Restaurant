/**
 * Created by HiCat on 2016/8/1.
 */
var dish_json = {};
var cart_json = {};
var add_json = {}
var total = 0;
var restname = $("#restname").val();
var tableid = $("#tableid").val();
$(function () {
       is_wechat();
        //绑定新加菜品点击事件，生成页面
        $(".add_page").bind("click", function () {
            $(".right").hide();
            $("#right_add").remove();
            var right_add = c$("div");
            $(right_add).attr({"class": "right", "id": "right_add"});
            $("body").append($(right_add));
            $(this).addClass("classify_1");
            $(".right,.classify_1").not(this).removeClass("classify_1");
            for (var o in add_json) {
                $(right_add).show();
                var dish = add_json[o];
                var dish_li = c$("div");
                var dish_img = c$("img")
                var dish_name = c$("strong");
                var dish_information = c$("div");
                var dish_price = c$("strong");
                var spinner = c$("div");
                var add = c$("a");
                var dish_num = c$("input");
                var reduce = c$("a");
                $(dish_li).attr("class", "dish_li");
                $(dish_img).attr({"class": "dish_img", "src": dish.img});
                $(dish_name).attr("class", "dish_name");
                $(dish_name).text(o);
                $(dish_information).attr("class", "dish_information");
                $(dish_price).attr("class", "dish_price");
                $(dish_price).text("价格：" + dish.price);
                $(spinner).attr("class", "spinner");
                $(add).attr("class", "add");
                $(add).bind("click", function () {
                    var price = parseFloat($(this).parent().siblings('.dish_price').text().replace("价格：", ""));
                    var name = $(this).parent().parent().siblings(".dish_name").text();
                    add_json[name]["num"]++;
                    $(this).siblings(".dish_num").val(add_json[name]["num"]);
                    total += price;

                    totalRefresh()
                });
                $(dish_num).attr({
                    "type": "text",
                    "class": "dish_num",
                    "value": dish.num,
                    "onkeyup": "this.value=this.value.replace(/[^0-9]\\D*$/,'')",
                    "maxlength": "1"
                });
                $(dish_num).bind("blur", function () {
                    if (parseInt($(dish_num).val()) === NaN) {
                        $(dish_num).val(0)
                    }
                    else {
                        var price = parseFloat($(this).parent().siblings('.dish_price').text().replace("价格：", ""));
                        var name = $(this).parent().parent().siblings(".dish_name").text();
                        var num = add_json[name]["num"];
                        add_json[name]["num"] = parseInt($(dish_num).val());
                        total += price * (add_json[name]["num"] - num);
                        totalRefresh()
                    }
                })
                $(reduce).attr("class", "reduce");
                $(reduce).bind("click", function () {
                    var price = parseFloat($(this).parent().siblings('.dish_price').text().replace("价格：", ""));
                    var name = $(this).parent().parent().siblings(".dish_name").text();
                    add_json[name]["num"]--;
                    $(this).siblings(".dish_num").val(add_json[name]["num"]);
                    total -= price;

                    totalRefresh();
                });
                $(dish_li).append($(dish_img));
                $(dish_li).append($(dish_name));
                $(dish_li).append($(dish_information));
                $(dish_information).append($(dish_price));
                $(dish_information).append($(spinner));
                $(spinner).append($(add));
                $(spinner).append($(dish_num));
                $(spinner).append($(reduce));
                $("#right_add").append($(dish_li));
            }
            var cart_total = c$("strong");
            $(cart_total).attr("class", "cart_total");
            $(cart_total).text("总价：" + total);
            var cart_check = c$("a");
            $(cart_check).attr("class", "cart_check");
            $(cart_check).text("确认下单");
            $(cart_check).bind("click", function () {

                $.ajax
                ({
                    data: {"cart": json2str(add_json), "total": total},
                    type: 'post',
                    url: "/Restaurant/order",
                    dataType: 'json',
                    success: function (data) {
                        alert(data);
                        window.location.reload();
                    }
                })
            })
            $("#right_add").append($(cart_total));
            $("#right_add").append($(cart_check));
        })
        //已点菜品绑定事件
        $(".cart").bind("click", function () {
            $(this).addClass("classify_1");
            $(".right,.classify_1").not(this).removeClass("classify_1");
            $.ajax
            ({
                data: {"id": tableid},
                type: 'post',
                url: "/Restaurant/selectTable",
                dataType: 'json',
                success: function (data) {
                    initcart(data);
                }
            })
        })
        //ajax获取菜单数据
        $.ajax
        ({
            type: 'post',
            url: "/Restaurant/dish/dish",
            dataType: 'json',
            success: function (data) {


                for (var o in data) {
                    var classname = data[o].classname;
                    if (!(classname in dish_json)) {
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
function init(json) {
    var i = 0;
    for (var o in json) {
        (function () {
            var div = c$("div");
            $(div).attr({"class": "classify", "id": "classify" + i});
            $(div).text(o);
            $(".left").append($(div));
            var dish_list = json[o];
            var dish_class = o;
            $("#classify" + i).bind("click", function () {
                $(this).addClass("classify_1");
                $(".right,.classify_1").not(this).removeClass("classify_1");
                (function () {
                    $(".right").hide();
                    if ($("#right_" + dish_class).length === 0) {
                        var list = dish_list;
                        var right = c$("div");
                        $(right).attr({"class": "right", "id": "right_" + dish_class});
                        $("body").append($(right));
                        for (var j in json[dish_class]) {
                            (function () {
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
                                $(dish_li).attr("class", "dish_li");
                                $(dish_img).attr({"class": "dish_img", "src": dish.img});
                                $(dish_name).attr("class", "dish_name");
                                $(dish_name).text(dish.name);
                                $(dish_information).attr("class", "dish_information");
                                $(dish_price).attr("class", "dish_price");
                                $(dish_price).text("价格：" + dish.price);
                                $(spinner).attr("class", "spinner");
                                $(add).attr("class", "add");
                                $(add).bind("click", function () {
                                    var price = parseFloat($(this).parent().siblings('.dish_price').text().replace("价格：", ""));
                                    var name = $(this).parent().parent().siblings(".dish_name").text();
                                    if (!(name in add_json)) {
                                        add_json[name] = {
                                            "num": 1,
                                            "price": price,
                                            "img": $(this).parent().parent().siblings(".dish_img").attr("src")
                                        };
                                    }
                                    else add_json[name]["num"]++;
                                    $(this).siblings(".dish_num").val(add_json[name]["num"]);
                                    total += price;
                                });
                                $(dish_num).attr({
                                    "type": "text",
                                    "class": "dish_num",
                                    "value": 0,
                                    "onkeyup": "this.value=this.value.replace(/[^0-9]\\D*$/,'')",
                                    "maxlength": "1"
                                });
                                $(dish_num).bind("blur", function () {
                                    if (parseInt($(dish_num).val()) === NaN) {
                                        $(dish_num).val(0)
                                    }
                                    else {
                                        var price = parseFloat($(this).parent().siblings('.dish_price').text().replace("价格：", ""));
                                        var name = $(this).parent().parent().siblings(".dish_name").text();
                                        if (!(name in add_json)) {
                                            add_json[name] = {
                                                "num": parseInt($(dish_num).val()),
                                                "price": price,
                                                "img": $(this).parent().parent().siblings(".dish_img").attr("src")
                                            };
                                            total += price * add_json[name]["num"];

                                        }
                                        else {

                                            var num = add_json[name]["num"];
                                            add_json[name]["num"] = parseInt($(dish_num).val());
                                            total += price * (add_json[name]["num"] - num);
                                        }

                                    }
                                })
                                $(reduce).attr("class", "reduce");
                                $(reduce).bind("click", function () {
                                    var price = parseFloat($(this).parent().siblings('.dish_price').text().replace("价格：", ""));
                                    var name = $(this).parent().parent().siblings(".dish_name").text();
                                    if (!(name in add_json) || add_json[name]["num"] === 0) {
                                        return true;
                                    }
                                    else {
                                        add_json[name]["num"]--;
                                        $(this).siblings(".dish_num").val(add_json[name]["num"]);
                                        total -= price;

                                    }
                                });
                                $(dish_li).append($(dish_img));
                                $(dish_li).append($(dish_name));
                                $(dish_li).append($(dish_information));
                                $(dish_information).append($(dish_price));
                                $(dish_information).append($(spinner));
                                $(spinner).append($(add));
                                $(spinner).append($(dish_num));
                                $(spinner).append($(reduce));
                                $("#right_" + dish_class).append($(dish_li));
                            })();
                        }
                    }
                    else $("#right_" + dish_class).show();
                })();
            })
            i++;
        })();
    }
}
function totalRefresh() {

    $(".cart_total").text("总价：" + total);
}

function initcart(json) {
    $(".right").hide();
    if ($("#right_cart").length != 0) {
        $("#right_cart").remove();
    }

    var table_json = $.parseJSON(json[0]["cart"]);
    var already_json = $.parseJSON(json[0]["already"]);

    var right = c$("div");
    $(right).attr({"class": "right", "id": "right_cart"});
    $("body").append($(right));
    $(right).show();
    for (var o in table_json) {

        var li = c$("div");
        var name = c$("div");
        var img = c$("img");
        var num = c$("input");
        var label1 = c$("label");
        var num2 = c$("input");
        var label2 = c$("label");
        var already = c$("div");
        var should = c$("div");
        $(already).attr({"style": "float:right;margin-top:2vh", "class": "already_div"});
        $(should).attr("style", "float:right;");
        $(label1).html("应上数量:&nbsp;");
        $(label1).attr({"style": "float:right;"})
        $(label2).html("已上数量:&nbsp;");
        $(label2).attr({"style": "float:right;"})
        $(li).attr({"class": "kitchen_li", "id": "kitchen_" + o});
        $(name).text(o);
        $(name).attr({"class": "kitchen_name"});
        $(img).attr({"class": "kitchen_img", "src": table_json[o].img});
        $(num).attr({
            "type": "text",
            "value": table_json[o].num,
            "class": "kitchen_num",
            "onkeyup": "this.value=this.value.replace(/[^0-9]\\D*$/,'')",
            "maxlength": "1",
            "disabled": "disabled"
        });
        $(num2).attr({
            "type": "text",
            "value": 0,
            "class": "kitchen_num",
            "onkeyup": "this.value=this.value.replace(/[^0-9]\\D*$/,'')",
            "maxlength": "1",
            "disabled": "disabled"
        });
        $(li).append($(name));
        $(li).append($(img));
        $(should).append($(num));
        $(should).append($(label1));
        $(li).append($(should));
        $(li).append($(already));
        $(already).append($(num2));
        $(already).append($(label2));
        $(right).append($(li));

        if (already_json == null) already_json = {};
        if (o in already_json) {
            if (already_json[o] == table_json[o].num) {
                var shade = s$("kitchen_" + o);
                shade.width($(".right").width());
                shade.html("<strong class='already_strong'>已上完</strong>")
                $(num2).val(already_json[o]);
            }
            else {
                $(num2).val(already_json[o]);
            }
        }
    }
}