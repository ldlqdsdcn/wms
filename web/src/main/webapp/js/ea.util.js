function getStringValue(value)
{
	if(value==null) return "";
	else return value;
}

function getSelectText(selectId)
{
	var select=document.getElementById(selectId);
	var selectIndex=select.selectedIndex;
	var text ='';
	if(select.options.length>0)
	if(selectIndex==null)
	{
		text=select.options[0].text;
	}
	else
	{
		text= select.options[selectIndex].text;
	}
	return text;
}
function formatDate(date)
{
	if(date==null)
	{
		date=new Date();
	}
	var year=date.getYear();
	var month=date.getMonth();
	var day=date.getDay();
	//var hour=date.getHours();
	//var minute=date.getMinutes();
	//var second=date.getSeconds();
	var s=year+"-"+(month+1)+"-"+day;
	return s;
}
function StringBuffer() {
	this.__strings__ = new Array();
}
StringBuffer.prototype.append = function (str) {
	this.__strings__.push(str);
	return this;    //方便链式操作
}
StringBuffer.prototype.toString = function () {
	return this.__strings__.join("");
}