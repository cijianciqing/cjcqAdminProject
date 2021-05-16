(function($){
/*
* 获取表单内容，生成js object
* */
    $.fn.cjFormSerializeObject = function () {
        var ct = this.serializeArray();
        var obj = {};
        $.each(ct, function () {
            if (obj[this.name] !== undefined) {
                if (!obj[this.name].push) {
                    obj[this.name] = [obj[this.name]];
                }
                obj[this.name].push(this.value || "");
            } else {
                obj[this.name] = this.value || "";
            }
        });
        return obj;
    };

    /*
    * 设置表单内容，参数为js object
    * */
    $.fn.cjSetFormByJsonObject = function (jsonValue) {
        var obj = this;

        $.each(jsonValue, function (name, ival) {
            // console.log(name,"--->",ival);
            var $input = obj.find("input[name=" + name + "]");
            console.log("$input--->",$input);
            if ($input.attr("type") == "radio" || $input.attr("type") == "checkbox") {
                // console.log("cjSetFormByJsonObject this is radio.....");
                $input.each(function () {
                    if (Object.prototype.toString.apply(ival) == '[object Array]') { // 是复选框，并且是数组
                        for (var i = 0; i < ival.length; i++) {
                            if ($(this).val() == ival[i])
                                $(this).attr("checked", "checked");
                        }
                    } else {
                        console.log("$(this).val(): ",$(this).val());
                        if ($(this).val() == ival)
                            $(this).attr("checked", "checked");
                    }
                });
            } else if ($input.attr("type") == "textarea") { // 多行文本框
                obj.find("[name=" + name + "]").html(ival);
            } else {
                obj.find("[name=" + name + "]").val(ival);
            }
        });
    };
/*
* 表单内容清空
* 使用参考：
*  $("#cjUserAddForm").cjFormReset();
* */
    $.fn.cjFormReset = function () {
        var cjformObj = this;
        cjformObj[0].reset();
    };

/*
* 返回的json对象进行拼接，并返回string
* 使用参考：
* $.cjValidateErrorToString(cjUniversalExceptionMessage.cjMessage.data);
* */
    $.cjValidateErrorToString = function(cjMessage) {
        let cjErrorList = [];
        $.each(cjMessage, function (index, item) {
            cjErrorList.push(item.toString());
        })
        return cjErrorList.join(',');
    }
})(jQuery)
