///////// 提供公用JavaScript方法 ///////////////

//////////获取半角全角字符串长度///////////
String.prototype.gblength = function(){
	var arr = this.match(/[^\x00-\xff]/ig);
	return this.length + (arr==null ? 0 : arr.length);
};

//////去除空格//////
String.prototype.trim = function(){
	return trimStr(this, null);
};

//////去除半角全角字符串空格，可指定去空格的方式//////
//参数：Flag,去空格的方式，l:去左空格，r:去右空格，
//不设置flag参数，则去前后的空格
//返回：去除空格后的字符串
function trimStr(str, Flag)
{
	Str	= "" + Str;
	if (Flag==undefined || Flag==null) {
		return str.replace(/(^[\s|　]*)|([\s|　]*$)/g, "");
	} else if (Flag=="l" || Flag=="L" ) {/*trim left side only*/
		return str.replace(/^\s+/gi, "");
	} else if (Flag=="r" || Flag=="R" ) {/*trim right side only*/
		return str.replace(/\s+$/gi, "");
	} else {/*defautly, trim both left and right side*/
		return str.replace(/(^[\s|　]*)|([\s|　]*$)/g, "");
	}
}

//////去除表单值域空格////////////////
function trimForm(form)
{
	var elArr = form.elements;
	for (var i = 0; i < elArr.length; i++) {
		if (elArr[i].nodeType!=1) continue;
		var tn = elArr[i].tagName.toLowerCase();
		if (tn!="input" && tn!="select" && tn!="textarea") continue;
		if (elArr[i].type=="button" || elArr[i].type=="reset"
			|| elArr[i].type=="submit" || elArr[i].type=="file") continue;
		if (!elArr[i].value) continue;
		var v = elArr[i].value;
		if (elArr[i].type=="textarea") {
			elArr[i].value = trimStr(v, "r");
		} else {
			elArr[i].value = trimStr(v);
		}
	}
}

//Check the value of textarea not exceed a specify line number
//检查文本录入域的行数
//参数：checkstr ，文本录入域中所录入内容
//参数：row，可录入的最大行数
//参数：column，每行的最大列数
//返回：真或假，行数没有超过限制为真，否则为假
function checkTextArea(checkstr, row, column)
{
	if (checkstr==null)
		return false;
	var str	= checkstr.trim();
    var linenum = 0;
    var strlen = str.gblength();
	for (var i = 0; i < strlen; i++) {
		var j = str.indexOf("\r\n",i);
		if (j>=0) {
			var substr = str.substring(i,j);
            var substrlen = substr.gblength();
			if(substrlen > column) {
				var n = Math.ceil(substrlen/column);
 		   	    linenum = linenum+n;
			} else {
		        linenum++;
			}
	        i = j+1;
		} else {
			var substr = str.substring(i);
            var substrlen = substr.gblength();
			var n = Math.ceil(substrlen/column);
	        linenum = linenum+n;
			break;
		}
	}
	if (linenum>row) return false;
    return true;
}

//判断一个值是否为浮点数
//参数：checkstr,要检查的数值串
//返回：真与假，是数值串为真，否则为假
function isFloat(checkstr)
{
	if(checkstr==null||checkstr.trim()=="") return false;
	var str	= checkstr.trim();
	if(str.substring(0,1)==".") return false;
	var temp=0;
	for(var i=0;i<str.length;i++)
	{
		var ch=str.substring(i,i+1);
		if(!((ch>="0" && ch<="9") || ch=="."))
			return false;
		if(ch==".") temp++;
		if(temp>1) return false;
	}
	return true;
}

//判断一个字符串的内容是不是全部为ASCII字符
//参数：checkstr, 被检查的字符串
//返回：真或为假，全部为ASCII字符为真，否则为假
function isAscii(checkstr)
{
	if(checkstr==null||checkstr.trim()=="")
		return false;		//alert(name+"项：您尚未填写。");
	var str	= checkstr.trim();
	for (var i = 0; i < str.length; i++) {
		var ch = str.substring(i, i + 1);
		if (ch < "A" || "z" < ch) {
			return false;
		}
	}
    return true;
}

//Check a float that has the limited decimal digits
//must check by isFloat first
//检查小数的位数是否超长
//调用此方法时，最好调用isFloat()方法进行数值判断
//参数：str, 要检查的数值串
//参数：decimal_digits, 小数位数的最大个数
//返回：真或假，小数位数不超长为真，否则为假
function CheckDecDig(str,decimal_digits)
{
	var i = str.indexOf(".");
	var substr = str.substring(i+1);
	var substrlen = substr.length;
	if(substrlen > decimal_digits)
		return false;
	else return true;
}

//Check number(0-9)
//判断是否为数值串是否为整数。
//参数：checkstr,被检查的数值串
//返回：真或假，只有含有数字为真，否则假
function isInteger(checkstr)
{
	if (checkstr==null||checkstr.trim()=="")
		return true;		//alert(name+"项：您尚未填写。");

	var str	= checkstr.trim();
	if (str!=null && str!="")
	{
		if (str.length>0)
		{
			for (var i = 0; i < str.length; i++)
			{
				var ch = str.substring(i, i + 1);
				if (ch < "0" || "9" < ch) return false;
			}
		}
	}
	else return false;
	return true;
}

//检查一个字符串是否只包含ASIIC字符及数字字符
//参数：str,要检查的字符串
//返回：真或假，只包含ASIIC字符及数字字符为真，否则为假
function isAsciiAndNumber(str)
{
    for (var i = 0; i < str.length; i++)
    {
      var ch = str.substring(i, i + 1);
      if (ch < "A" || "z" < ch) {
	    if (ch < "0" || "9" < ch) return false;
      }
    }
    return true;
}

//判断一个项的值是否为空
//参数：要检查的项
//返回：真或假，空为真，不空为假
function isEmpty(textcontrol)
{
    var str = textcontrol.value.trim();
	if (str==null || str=="") return true;
    return false;
}

//检查邮箱名称的书写是否合法
//参数：email,邮箱名称的字符串
//返回：真或假,合法为真，不合法为假
function check_email(email)
{
	if (email==null||email.trim()=="")
		return false;		//alert(name+"项：您尚未填写。");
	if (email.indexOf('@') <2) {return false;}
	if (email.indexOf('.') <5 || email.indexOf('.') > email.length -3) {return false;} //ab@cd.com
	if (email.indexOf('@') >email.indexOf('.') -3) {return false;}
	return true;
}

//文本框内只允许输入数字、小数点、负号
function JHshNumberText()
{
	if ( !(((window.event.keyCode >= 48) && (window.event.keyCode <= 57))
		|| (window.event.keyCode == 13) || (window.event.keyCode == 46)
		|| (window.event.keyCode == 45))) {
		window.event.keyCode = 0 ;
	}
}

//文本框内只允许输入数字、负号
function JHshText()
{
	if (!(((window.event.keyCode >= 48) && (window.event.keyCode <= 57))
		|| (window.event.keyCode == 13) || (window.event.keyCode == 45))) {
		window.event.keyCode = 0 ;
	}
}

//文本框内只允许输入数字
function JIntegerText()
{
	if (!(((window.event.keyCode >= 48) && (window.event.keyCode <= 57))
		|| (window.event.keyCode == 13))) {
		window.event.keyCode = 0 ;
	}
}

function CharToUpper() {
	if (!(((window.event.keyCode >= 48) && (window.event.keyCode <= 57))
		|| (window.event.keyCode == 13)
		|| ((window.event.keyCode >= 97) && (window.event.keyCode <= 122))
		|| ((window.event.keyCode >= 65) && (window.event.keyCode <= 90)))) {
		window.event.keyCode = 0 ;
		return;
	}
	if ((window.event.keyCode >= 97) && (window.event.keyCode <= 122)) {
		window.event.keyCode = window.event.keyCode - 32;
	}
}

//只能输入汉字的输入框
function onlychinese(){
	if ((window.event.keyCode <32) && (window.event.keyCode > 126)) {
		window.event.keyCode = 0 ;
	}
}

////////////检查是否有中文//////////////////////
//检查字符串中是否含有中文
//参数：strchk，要检查的字符串
//返回：真或假,真：含有中文汉字,假：没有中文
function checkcn(strchk)
{
	if (strchk==null || strchk.trim()=="") return false;
	var strInv = strchk.trim();
	var nPos = 0;
	while (nPos < strInv.length) {
	    if (strInv.charCodeAt(nPos) > 127) return true;
	    nPos++;
	}
	return false;
}

///////////////////////////////////
//检查输入浮点数是否符合数据库规范
function checkFloatValue(str,integerValue,decimal_digits)
{
	var i = str.indexOf(".");
	if (i > integerValue) return false;
	if (i == "-1") {
		if (str.length > integerValue) return false;
	} else {
		var strleng = str.length;
		strleng --;
		strleng -= i;
		if (strleng > decimal_digits) return false;
	}
	return true;
}

///////////////////////////////////
//检查输入浮点数是否符合数据库规范
///////////////////////////////////
function checkFloLen(str,integer_digits,decimal_digits)
{
   	var i=str.indexOf(".");
   	if (i == -1) {
   		if (!(str.length > integer_digits)) return true;
   		return false;
   	}
	var intstr = str.substring(0,i);
	var intstrlen = intstr.length;
	var decstr = str.substring(i+1);
    var decstrlen = decstr.length;
    if (intstrlen > integer_digits) return false;
	if (decstrlen > decimal_digits) return false;
	return true;
}

/**
 * 在按键事件中，把回车换成TAB键
 * 使用方法：该函数的查询域所在的表单的onKeyDown事件调用。
 */
function formEnterAsTab(e)
{
	var key = window.event ? e.keyCode : e.which;
	if (key != 13) {
		return true;
	}
	var element = e.target ? e.target : e.srcElement;
	if (!element) {
		return true;
	} else if (element.nodeType == 3) {// defeat Safari bug
		element = element.parentNode;
	}
	if (!element || element.tagName.toLowerCase() != "input") {
		return true;
	}
	var type = element.type.toLowerCase();
	if (!type || type == "button" || type == "submit" || type == "reset") {
		return true;
	}
	if (window.event) {
		e.keyCode = 9;
	} else {
		e.which = 9;
	}
	return false;
}