/*! jQuery Functions  2015-04-28
 * 
 *  */
(function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		// AMD. Register as an anonymous module.
		define([ "jquery" ], factory );
	} else {

		// Browser globals
		factory( jQuery );
	}
} (function($){
	
	$.fn.extend({
		

       
		D_ShowError:function(sMessage)
		{

			$.ligerDialog.error(sMessage);
			//alert(sMessage);
			/*	
			var sMsg="录入数据有错误......";
			var iWd=300;
			var iHg=70;
			var iLPos=this.position().left;
			var iTPos=this.position().top+this.height();
			
			//var content=this.data("errorMessage");			
			
			if ((sMessage)&&(sMessage!='')) sMsg=sMessage;
			
			
			
			var str_buffer = new String (
				"<span id='ErrorMessage' bgcolor=\"#AAAAAA\" style=\"font-family: 宋体; font-size: 12px;\">\n"+
				sMsg+
				"</span>");
			
			var errorDiv=$("#ErrorDiv");
			body = this.document.find( "body" );
			if (errorDiv==null)
			{
			    var ErrorDiv = $("<Div></Div>").appendTo(body);
			    ErrorDiv.attr("id","ErrorDiv");
			    ErrorDiv.addClass("d-errordiv");
				ErrorDiv.style.left=iLPos; 
				ErrorDiv.style.top=iTPos;
				ErrorDiv.style.width=iWd;
				ErrorDiv.style.height=iHg;
				
				ErrorDiv.style.visibility="visible";
				ErrorDiv.style.display="inline";		
				
			}else{

		        if ($("#ErrorMessage")!=null)    
				{
					if ((sMessage)&&(sMessage!='')) 
					{
						$("#ErrorMessage").html(sMessage);
					}
				}


				ErrorDiv.style.left=iLPos; 
				ErrorDiv.style.top=iTPos;
				ErrorDiv.style.width=iWd;
				ErrorDiv.style.height=iHg;
				ErrorDiv.style.visibility="visible";
				ErrorDiv.style.display="inline";

			}
			
			$('#ErrorDiv').delay(2000).hide(slow);			
           */	
		},
	
	   D_Validate:function(){
		
			 $("input[type=Text]").each(
			          function(i){

			        	  
		        	  var placetip=$(this).attr("title");
			       	   if (placetip!=undefined)
		       		   {
			       		   $(this).attr("placeholder",placetip);
		       		   }
			       	   
			       	   
			           var blurvalidate=$(this).attr("blurvalidate");
			       	   if ((blurvalidate!=undefined)&&(blurvalidate=="yes"))
			       	   {
			       		   var blurtype=$(this).attr("blurtype");
			       		  
			       		   if (blurtype=="telephone")
			       		   {
			       			  $(this).blur(function(event){
			       			     validateTelephoneObj(this);
			       			});	
			       		   }
			       		   
			       		   if (blurtype=="mobile")
			       		   {
			       			  $(this).blur(function(event){
			       			     validateMobileObj(this);
			       			});	
			       		   }
			       		   
			       		   
			       		   if (blurtype=="email")
			       		   {
			       			  $(this).blur(function(event){
			       				  
			       				validateEMailObj(this);
			       			});	
			       		   }
			       		   
			       		   if (blurtype=="integer")
			       		   {
			       			  $(this).blur(function(event){
			       				validateIntegerObj(this);
			       			});	
			       			  
			       			  $(this).keypress(function(event){
			       				 return keypressNumber(event);
				       			});	
			       			  
			       		   }
			       		   
			       		 if (blurtype=="numeric")
			       		   {
			       			  $(this).blur(function(event){
			       				validateNumberObj(this);
			       			});	
			       			  
			       			  $(this).keypress(function(event){
			       				 return keypressNumber(event);
				       			});	
			       			  
			       		   }
			       		 

			       		}	       	   
			        	  
			       	  
			   });		   
	   }
	   
		
	
	});

}))
