	function highlightTableRows(tableId) {
    var previousClass = null;
    var table = document.getElementById(tableId); 
    var tbody = table.getElementsByTagName("tbody")[0];
    var rows;
    if (tbody == null) {
        rows = table.getElementsByTagName("tr");
    } else {
        rows = tbody.getElementsByTagName("tr");
    }
    // add event handlers so rows light up and are clickable
    $(table).find("tr").each(function(i)
        	{
        	  $(this).bind("click",function()
        			  {
        		         $(table).find("tr").each(function(k)
        		         {
        		        	 $(this).removeClass("d-selected");
        		         });
        		       
        		         $(this).addClass("d-selected");
        			  });
        	});
        
        
        // add event handlers so rows light up and are clickable
        for (i=0; i < rows.length; i++) {
            rows[i].onmouseover = function() { $(this).addClass("over");};
            rows[i].onmouseout = function() {  $(this).removeClass("over"); };
        }  
}