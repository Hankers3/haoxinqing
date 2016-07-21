/*--
//CommonScriptLib.js  :  This is a common script library;

//本js文件包括:
//公用的方法
function trimStr(str)                        //去掉左右空格
function round(m,n)                       //四舍五入，n为小数位数
function isBetween(val,lo,hi)             //判断val是否在lo和hi之间
function insertStr(Str,InsPlace,SubStr)	//插入字符串，被checkDate调用
function isEmpty(obj)                     //判断obj的value是否为空
function checkError(flag,obj)             //将检测到的error在显示上体现出来
function keyPress(ikey)                   //回车相当于tab

//内部自使用的方法，一般不对外
function isChar(theChar)                  //判断是否为字符（单个）
function isDigit(theNum)                  //判断是否为数字（单个）
function isInt(theInt)                    //判断是否为整数
function isReal(_theStr,decLen,intLen)    //判断是否实数
function isDate(theDate)                  //判断是否日期格式

//对外的接口
function chkspc(str) 					  //检查字符串中是否含有空格 
function checkReal(objReal,n,m)           //检测是否实数
function checkDot(_theStr,n)              //检测小数位
function checkDigit(obj,n,flag)           //检测不超过(或等于)n位的数字
function checkDigit2(obj,m,n,flag)        //当flag=true,等于n位，当flag=false,大于m位小于n位的数字
function checkInt(objInt,prompt)          //检测是否为整数
function checkMoney(objMoney,prompt)      //检测金额格式
function checkDate(objDate)               //检测是否日期格式，调用ChkDay(),ChkMon(),ChkYear()
function checkTime(objTime,prompt)        //检测时间格式
function checkDotvalue(objValue,prompt)   //检测整数位不超过一位，小数位不超过两位
function checkInt2(objInt,prompt)          //检测是否为整数，但值为空可以通过
function checkMoney2(objMoney,prompt)      //检测实数，小数位不超过两位，但值为空可以通过
function checkMoney4(objMoney,prompt)      //检测实数，小数位不超过4位

function round (n, d)                      //得到d位小数


function checkPostalcode(objPostalcode)   //检测是否邮政编码
function checkEmail(objEmail)             //检测是否E_mail地址
function CheckTEL(TELObj) 				  //检测是否电话号码
function checkPhonecode(num)              //检测是否电话号码

function checkLength(obj,n)               //检测对象的长度不超过n
function onlyNum()						  //检测只能输入数字和退格

function compareDate(startDate,endDate)   //比较两个日期（-1:开始日期大，0:一样大，1:终止日期大）


--*/


//--------------------------去掉字符串的前后空格-------------------------------//

function trimStr(str) {
	for(var i = 0 ; i<str.length && str.charAt(i)==" " ; i++ ) ;
	str=str.substring(i,str.length);
	for(var i = str.length-1 ;  i>=0 && str.charAt(i)==" " ; i-- ) ;
	str=str.substring(0,i+1);
	return str;
}

//------------------------四舍五入,n为小数位数---------------------------------//
function round(m,n) {
	return Math.round(m*Math.pow(10,n))/Math.pow(10,n);
}

//------------------------判断val是否在lo和hi之间-----------------------------------------------//
function isBetween(val,lo,hi){
	if( val<lo || val>hi ){
		return false;
	}
	else{
		return true;
	}
}

//-------------------------是否为空---------------------------------------------//
function isEmpty(obj){
	var str=obj.value;
	if(str==""){
		return true;
	}
	else{
		return false;
	}
}

//--------------------------插入字符串-----------------------------------------//
function insertStr(Str,InsPlace,SubStr)		//invoke in checkDate
{
  var s1;
  var s2;
  s1=Str.substring(0,InsPlace);
  s2=Str.substring(InsPlace,Str.length);
  s1=s1+SubStr;
  s1=s1+s2;
  return s1;
}

//------------------检测错误,并做显示处理--------------------------------------//
function checkError(flag,obj) {
	if(flag) {
	   if(obj.style.backgroundColor=="red") {
	    obj.style.color="black";
		obj.style.backgroundColor="white";
	   }
		return true;
	} else {
		obj.style.color="white";
		obj.style.backgroundColor="red";
		obj.value="";
		obj.focus();
		return false;
	}
}

//-----------------------------Return 转化成 TAB-----------------------------------//
function keyPress(ikey) {
  var ieKey=event.keyCode;
  var obj;
//alert(ieKey);
  if (ieKey == 13) {
     for(i_loop = 0;i_loop < document.forms[0].elements.length;i_loop++) {
		if (document.forms[0].elements[i_loop] == ikey) {
			for(i=i_loop+1;;i++) {
				if(i==document.forms[0].elements.length) i=0;
				obj=document.forms[0].elements[i];
				if(obj.type=="hidden" || obj.type=="file" || obj.readOnly==true || obj.disabled==true) {
					continue;
				} else {
					event.keyCode=0;
					obj.focus();
					if(obj.type=="text") obj.select();
					return;
				}
			}
			return;
		}
     }
  }
}


//----------------------检测是否字符，一般不对外  --------------------------------------------//
function isChar(theChar){
	var theMask="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	if(theChar==""){
		return true;
	}
	else if(theMask.indexOf(theChar)==-1){
		return false;
	}
	return true;
}

//----------------------检测是否数字，一般不对外----------------------------------------------//
function isDigit(theNum){
	var theMask="0123456789";
	if(theNum==""){
		return true;
	}
	else if(theMask.indexOf(theNum)==-1){
		return false;
	}
	return true;
}



//-----------------------检测整数,不对外---------------------------------------//
function isInt(theInt){

	if(theInt=="") {
		return true;
	} else {
		for(var i=0;i<theInt.length;i++){
			if(isDigit(theInt.charAt(i))==false){
				return false;
			}
		}
		return true;
	}
}


//----------------------检测实数,不对外----------------------------------------//
function isReal(_theStr,decLen,intLen){
	var theStr=_theStr.value;
	if(theStr.indexOf("-")==0) theStr=theStr.substring(1,theStr.length);
	var dot1st=theStr.indexOf(".");
	var dot2nd=theStr.lastIndexOf(".");
	var OK=true;

	if(isEmpty(_theStr)){return true;}
	if(intLen==null) intLen=10;
	if(dot1st==-1){
		if(!isInt(theStr)){
			return false;
		}
		else if(theStr.length>intLen){
			//alert("整数位太长，最多10位整数！");
			return false;
		}
		else{
			return true;
		}
	}
	else if(dot1st!=dot2nd){return false;}
	else if(dot1st==0){return false;}
	else{
		var intPart=theStr.substring(0,dot1st);
		var decPart=theStr.substring(dot2nd+1);
		if(!isInt(intPart)||!isInt(decPart)){
			return false;
		}
		else if(intPart.length>intLen){
			//alert("整数位太长！");
			return false;
		}
		else if(decPart.length>decLen){
			//alert("小数位太长！");
			return false;
		}
		else if(decPart==""){
			return false;
		}
		else{
			return true;
		}
	}
}

//-------------------------------判断是否日期格式----------------------------------------------//
function isDate(theDate){
	//加入补齐位数为10的函数
	theStr=theDate.value;
	var the1st=theStr.indexOf("-");
	var the2nd=theStr.lastIndexOf("-");
	if(the1st==the2nd){
		return false;
	}
	else{
		var y=theStr.substring(0,the1st);
		var m=theStr.substring(the1st+1,the2nd);
		var d=theStr.substring(the2nd+1,theStr.length);
		var maxDays=31;
		if(isInt(y)==false||isInt(m)==false||isInt(d)==false){
			return false;
		}
		else if(y.length<4){return false}
		else if(!isBetween(m,1,12)){return false;}
		else if(m==4||m==6||m==9||m==11){maxDays=30;}
		else if(m==2){
			if(y%4>0){maxDays=28;}
			else if(y%100==0&&y%400>0){maxDays=28;}
			else{maxDays=29;}
		}
		if(isBetween(d,1,maxDays)==false){return false;}
		else{return true;}
	}
}



//---------------------检测实数,小数位为n位,整数位为m位------------------------//
function checkReal(objReal,n,m) {
	if(objReal.value=="") {
		return true;
	}
	if (!isReal(objReal,n,m)) {
		//alert("请输入正确的数字！");
		return false;
	} else {
		return true;
	}
}

//---------------------检测小数,小数点后为n位----------------------------------//
function checkDot(_theStr,n) {
	var theStr=_theStr.value;
	if(_theStr.value=="") {
		return true;
	}
	if(checkReal(_theStr,n)) {
		if(eval(theStr)<1) {
			return true;
		} else {
			//alert("数字要小于1！");
			return false;
		}
	} else {
		return false;
	}
}

//-------------------检查不超过（或等于）n位的数字----------------------------//
function checkDigit(obj,n,flag) {		//flag=true 等于;flag=false 不超过.
	var num=obj.value;
	if(num=="") return true;
	for(var i=0;i<num.length;i++){
		if(isDigit(num.charAt(i))==false){
			//alert("请输入数字！");
			return false;
		}
	}
	if(flag) {
		if(num.length != n) {
			//alert("请输入"+n+"位数字！");
			return false;
		}
	} else {
		if(num.length > n) {
			//alert("请输入不超过"+n+"位的数字！");
			return false;
		}
	}
	return true;
}
//-------------------大于等于m位小于等于n位的数字----------------------------//
function checkDigit2(obj,m,n,flag) {		//flag=true 等于;flag=false 不超过.
	var num=obj.value;
	if(num=="") return true;
	for(var i=0;i<num.length;i++){
		if(isDigit(num.charAt(i))==false){
			//alert("请输入数字！");
			return false;
		}
	}
	if(flag) {
		if(num.length != n) {
			//alert("请输入"+n+"位数字！");
			return false;
		}
	} else {
		if(num.length > n) {
			//alert("请输入不超过"+n+"位的数字！");
			return false;
		}
		if(num.length < m) {
			//alert("请输入不少于"+m+"位的数字！");
			return false;
		}
	}
	return true;
}

//----------------------------------判断是否为整数---------------------------------------//
function checkInt(objInt,prompt)
{
	var info;
	var strInt;
	var obj;

	info=prompt;
	obj=objInt;
	strInt=obj.value;

	if (strInt== "")
    {
        //alert(info+"不能为空！");
        return false;
    }
    else if(!isInt(strInt))
    {
           //alert(info+"必须为整数！");
           return false;
    }

	return true;
}
//----------------------------------判断是否为整数---------------------------------------//
function checkInt(objInt)
{
	var strInt;
	var obj;

	obj=objInt;
	strInt=obj.value;

    if(!isInt(strInt))
    {
           alert("必须为整数！");
           return false;
    }
	return true;
}

//----------------------------------判断是否为整数---------------------------------------//
function checkInt2(objInt,prompt)
{
	var info;
	var strInt;
	var obj;

	info=prompt;
	obj=objInt;
	strInt=obj.value;

   if(!isInt(strInt))
    {
           //alert(info+"必须为整数！");
           return false;
    }

	return true;
}


//-------------------------------检测金额格式--------------------------------------//
function checkMoney(objMoney,prompt)
{
	var info;
	var strMoney;
	var obj;

	info=prompt;
	obj=objMoney;
	strMoney=obj.value;

	if (strMoney== "")
    {
        //alert(info+"不能为空");
        return false;
    }
    else if(!(isReal(obj,2,10)))
    {
           //alert(info+"必须为数字,保留两位小数");
           return false;
    }

	return true;
}
//-------------------------------检测价值格式--------------------------------------//
function checkMoney4(objMoney,prompt)
{
	var info;
	var strMoney;
	var obj;

	info=prompt;
	obj=objMoney;
	strMoney=obj.value;

	if (strMoney== "")
    {
        //alert(info+"不能为空");
        return false;
    }
    else if(!(isReal(obj,4,15)))
    {
           //alert(info+"必须为数字,最多保留四位小数");
           return false;
    }

	return true;
}
//-------------------------------检测实数，小数位不超过两位，但值为空可以通过--------------------------------------//
function checkMoney2(objMoney,prompt)
{
	var info;
	var strMoney;
	var obj;

	info=prompt;
	obj=objMoney;
	strMoney=obj.value;

    if(!(isReal(obj,2,10)))
    {
           //alert(info+"必须为数字,保留两位小数");
           return false;
    }

	return true;
}

//---------------------------检测整数位不超过一位，小数位不超过两位-----------------------------------------------//
function checkDotvalue(objValue,prompt)
{
	var info;
	var strValue;
	var obj;

	info=prompt;
	obj=objValue;
	strValue=obj.value;

	if (strValue== "")
    {
        //alert(info+"不能为空");
        return false;
    }
    else if(!(isReal(obj,2,1)))
    {
           //alert(info+"必须为数字,整数位为一位，保留两位小数");
           return false;
    }

	return true;
}

//-----------------------------检测日期格式----yyyy-mm-dd----------------------//
function checkDate(objDate)
{
   var str=objDate.value;
   var date_month="";
   var date_day="";
   var date_year="";
   var i=0;
   var y_m=0;
   var m_d=0;
 if (objDate.value!="") {
	if ((str.length>10)||(str.length<8))
		{
			//alert("输入的日期格式错误,应该为：YYYY-MM-DD！");
			return false;
		}
	for(i_loop=0;i_loop<str.length;i_loop++) {
		if (str.charAt(i_loop)=="-") {
			i++;
			if (i==1) {
				if (i_loop!=4) {
					//alert("输入的日期中年份的格式错误,应该为：YYYY-MM-DD！");
					return false;
				}
				y_m=i_loop;
			} else if(i==2) {
				m_d=i_loop;
			}
		}
	}
	if ((i==2)&&(m_d==str.length-1)) {
		//alert("输入的日期格式错误,应该为：YYYY-MM-DD！");
		return false;
    }
	for(i_loop=0;i_loop<4;i_loop++) {
		date_year=date_year+str.charAt(i_loop);
	}
	if (i==2) {
		for(i_loop=y_m+1;i_loop<m_d;i_loop++) {
			date_month=date_month+str.charAt(i_loop);
        }
    } else {
		for(i_loop=y_m+1;i_loop<str.length;i_loop++) {
			date_month=date_month+str.charAt(i_loop);
        }
    }
	if (i==2) {
		for(i_loop=m_d+1;i_loop<str.length;i_loop++) {
			date_day=date_day+str.charAt(i_loop);
		}
	}
	if (!(ChkYear(date_year) && ChkMon(date_month))){
		return false;
	}
	if (i==2) {
		if (!(ChkDay(date_year,date_month,date_day))) {
			return false;
		}
    }
	if (m_d==6) {
		objDate.value=insertStr(objDate.value,m_d-1,'0');
	}
	if ((i==1)&&(str.length==6)) {
		objDate.value=insertStr(objDate.value,5,'0');
	}
	if (((str.length-m_d-1)==1)&&(i==2)) {
		objDate.value=insertStr(objDate.value,objDate.value.length-1,'0');
	}
  }

  return true;
}

////invoke in checkDate
function ChkYear(num)
{
  if (!isInt(num)) {
	//alert("输入的日期中的年份不正确，必须为数字");
    return false;
  }
  if (num<1900 || num>2500) {
     //alert("输入的日期中的年份不正确，必须处于1900年--2500年之间");
     return false;
  }
  return true;
}

////invoke in checkDate
function ChkMon(num)
{
  if (!isInt(num)) {
     //alert("输入的日期中的月份不正确，必须为数字");
     return false;
  }
  if (num<1 || num>12) {
     //alert("输入的日期中的月份不正确，必须处于1--12之间");
     return false;
  }
  return true;
}

////invoke in checkDate
function ChkDay(y,m,d)
{
	var maxDays=31;
	if(!isInt(d)){
	     //alert("输入的日期中的日不正确，必须为数字");
		 return false;
	}
	else if(m==4||m==6||m==9||m==11){maxDays=30;}
	else if(m==2){
		if(y%4>0){maxDays=28;}
		else if(y%100==0&&y%400>0){maxDays=28;}
		else{maxDays=29;}
	}
	if(d<1 || d>maxDays) {
	    //alert("输入的日期中的日不正确，必须处于1--"+maxDays+"之间");
		return false;
	} else {
		return true;
	}
}


//-----------------------------检测时间格式----HH:MM----------------------//
function checkTime(objTime,prompt)
{
	var timestr;
	var info;
	info=prompt;
	var time_h="";
	var time_m="";
	var i_loop=0;
	var startpos;
	var endpos;

	timestr=objTime.value;
	if (timestr=="")
	{
	  	//alert(info+"不能为空！");
	  	return false;
	}
   else if(timestr.length>5)
   {
		//alert(info+"格式错误,应该为：HH:MM！")
		return false;
   }
   else
   {
		if(timestr.indexOf(":")<0)
		{
			//alert(info+"格式错误,应该为：HH:MM！")
			return false;
		}
		while(timestr.charAt(i_loop)!=":" && i_loop<2)
		{
			time_h=time_h+timestr.charAt(i_loop);
			i_loop++;
		}
		if(time_h=="")
		{
			//alert(info+"格式错误,应该为：HH:MM！")
			return false;
		}
		if (!isInt(time_h))
		{
			//alert(info+"中的小时不正确，必须为数字(0~24),时间格式：HH:MM！");
			return false;
		}
		if(parseInt(time_h)>24)
		{
			//alert(info+"中的小时超出了范围（0~24）！");
			return false;
		}

		startpos=timestr.indexOf(":")+1;
		endpos=timestr.length-time_h.length+1;
		for(i_loop=startpos;i_loop<=endpos;i_loop++)
	   	time_m=time_m+timestr.charAt(i_loop);
		if (!isInt(time_m))
		{
			//alert(info+"中的分钟格式不正确，必须为两位数字，时间格式：HH:MM！");
		 	return false;
		}
		if(time_m.length!=2)
		{
			//alert(info+"中的分钟格式不正确，必须为两位数字，时间格式：HH:MM！");
			return false;
		}
		if(time_h.length==2)
		{
			if(timestr.charAt(2)!=":")
			{
				//alert(info+"中的分钟不正确，必须为数字，时间格式：HH:MM！");
				return false;
			}
		}
		else if(time_h.length==1)
		{
			if(timestr.charAt(1)!=":")
			{
				//alert(info+"中的分钟不正确，必须为数字，时间格式：HH:MM！");
				return false;
			}
		}
		if(parseInt(time_m)>60)
		{
			//alert(info+"中的分钟超出了范围（00~60）！");
			return false;
		}
 	}
	return true;
}

//----------------------------检测邮政编码-----------------------------------//
function checkPostalcode(objPostalcode){
	if(isEmpty(objPostalcode)){
		return true;
	}
	else if(isBetween(objPostalcode.value.length,6,6)==false){
		//alert("邮政编码应为6个字符长度!");
		return false;
	}
	else if(isInt(objPostalcode.value)==false){
		//alert("邮政编码应为数字!");
		return false;
	}
		return true;
}

//----------------------------检测Email地址------------------------------------//
function checkEmail(objEmail){
	//仅检查"."和"@"
	var theStr=objEmail.value;
	var atIndex=theStr.indexOf("@");
	var doIndex=theStr.indexOf(".",atIndex);
	var theSub=theStr.substring(0,doIndex+1);
	if(isEmpty(objEmail)){
		return true;
	}
	if((atIndex<1)||(atIndex!=theStr.lastIndexOf("@"))||(doIndex<atIndex+2)||(theStr.length<=theSub.length)){
		//alert("输入Mail地址格式有误！");
		return false;
	}
	return true;
}
//-----------------------
//函数名：checkTel 
//功能介绍：检查是否为电话号码 
//参数说明：要检查的字符串 
//返回值：1为是合法，0为不合法 
function checkTel(telObj) 
{ 
var tel = telObj.value;
var i,j,strTemp; 
strTemp="0123456789-()# "; 
for (i=0;i<tel.length;i++) 
{ 
j=strTemp.indexOf(tel.charAt(i)); 
if (j==-1) 
{ 
//说明有字符不合法 
//alert("电话号码的格式不正确，它只能包括数字-()#号!");
return 0; 
} 
} 
//说明合法 
return 1; 
} 

//----------------------------检测电话号码------------------------------------//
function checkPhonecode(objPhonecode)
{
	if(isEmpty(objPhonecode)){
		return true;
	}
	else{
		for(i_loop=0;i_loop<objPhonecode.value.length;i_loop++)
		{
			if (!(((objPhonecode.value.charAt(i_loop)>=0)&&(objPhonecode.value.charAt(i_loop)<=9))||(objPhonecode.value.charAt(i_loop)=="(")||(objPhonecode.value.charAt(i_loop)==")")||(objPhonecode.value.charAt(i_loop)=="-")||(objPhonecode.value.charAt(i_loop)==",")))
			{
				//alert("电话号码的格式不正确，它只能包括数字、‘，’、‘-’和括号！");
				return false;
			}
			if( ((objPhonecode.value.charAt(i_loop)==")")&&(objPhonecode.value.charAt(i_loop+1)!="-" )&&(objPhonecode.value.charAt(i_loop+1)!=","))||(objPhonecode.value.charAt(i_loop)=="-")||(objPhonecode.value.charAt(i_loop)==","))
			{
				if (((objPhonecode.value.length-i_loop-1)>8)||((objPhonecode.value.length-i_loop-1)<7))
				{
					//alert("电话号码的长度不正确，在区号之后应该是7位或者8位！");
					return false;
				}
			}
		}
		if (!((objPhonecode.value.length>=7)&&(objPhonecode.value.length<=20)))
		{
			//alert("电话号码的长度不正确，它只能大于或等于7位并且小于或等于20位！");
			return false;
		}
	}
	return true;
}
//---------------------------得到d位小数-----------------------------------

function round (n, d) {
  var f = Math.pow(10, d);
  n = Math.round(n * f) / f;
  n += Math.pow(10, - (d + 1));
  n += '';
  return d == 0 ? n.substring(0, n.indexOf('.')) :
      n.substring(0, n.indexOf('.') + d + 1);
}

//-------------------检测域的文字长度不超过n---------------------------------------//
function checkLength(obj,n) {
	if (obj.value.length > n) {
		obj.style.color="white";
		obj.style.backgroundColor="red";
		//alert("注意：文字的长度不能超过"+n+"个！");
		obj.focus();
		return false;
	} else {
	   if(obj.style.backgroundColor=="red") {
		obj.style.color="black";
		obj.style.backgroundColor="white";
	   }
		return true;
	}
}


//-----------------------------比较日期的大小-----------------------------------//
function compareDate(startDate,endDate) { //yyyy-mm-dd
	var sYear=eval(startDate.substring(0,4));
	var sMonth=eval(startDate.substring(5,7));
	var sDay=eval(startDate.substring(8,10));

	var eYear=eval(endDate.substring(0,4));
	var eMonth=eval(endDate.substring(5,7));
	var eDay=eval(endDate.substring(8,10));

	if(eYear>sYear) {
		return 1;
	} else if(eYear<sYear) {
		return -1;
	} else {
		if(eMonth>sMonth) {
			return 1;
		} else if(eMonth<sMonth) {
			return -1;
		} else {
			if(eDay>sDay) {
				return 1;
			} else if(eDay<sDay) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
