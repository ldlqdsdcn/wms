
/**
 * 键盘上下移动
 * v1.0 2004.12.27
 */
 
 
//快捷保存	
function keyDown(e)
{
	if (event.srcElement.type == "textarea") 
	{
		return;
	}
	
	var obj;
	var found = false;
	var selPre = -1;
	var first = -1;
	var code = event.keyCode;
	if (code == 13) code = 40;
	if (code==40 || code==38){ //down
		event.returnValue = false;	
		var name = event.srcElement.name;
		obj = document.getElementsByTagName("INPUT")

		for(i=0;i<obj.length;i++)
		{
			if (obj[i].readOnly)
			{
				continue
			}
			else
			{
			   if (!(obj[i].type == "text" || obj[i].type == "textarea" ))
			 	  continue;
			}

			if(obj[i].name == name){
				found = true;
				continue;
			}
			if (!found) {
				if (first == -1)
					first = i;
				selPre = i;
			}
			else 
			{
				if (code==40){
					obj[i].focus();
				
				}
				else if (code==38){
					if (selPre > -1)
						obj[selPre].focus();
				}
				return;
			}				
			
		}

		if (selPre > -1 && code==38)
			obj[selPre].focus();

		if (first > -1 && code==40)
			obj[first].focus();		
		
	}
}

function moveKeyDown()
{

	if (event.srcElement.type == "textarea") 
	{
		return;
	}


    if (event.keyCode==40)   //向下按钮
	{
		event.keyCode=9;  
	}


	if (event.keyCode==13)   //回车
	{ 
		event.keyCode=9;  
	} 


    if (event.keyCode==38)
	{
        //event.shiftKey=true;   //模拟shift按键被按下
		event.keyCode= 9;   //向上按钮       
	}

	
}


document.onkeydown = moveKeyDown;	