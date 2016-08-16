<%@ page contentType="text/html; charset=utf-8" language="java"%>
<style type="text/css">
  #allmap {width: 100%;height: 100%;overflow: hidden;hidden;margin:0;}
  #locationcontent {width: 100px;height: 20px;overflow: hidden;hidden;margin:0;position:absolute;right:130;top:100;z-index:99999;}
</style>

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=66349aba8648570fab53ac289f7f9f99"></script>

<div id="allmap">
<div id="locationcontent" >
</div>
</div>

<input type="hidden" id="pointlng">
<input type="hidden" id="pointlat">
<script type="text/javascript">
var map = new BMap.Map("allmap");  
var point = new BMap.Point(121.473754,31.23037);  
map.centerAndZoom(point,20);  
map.addEventListener("click", SetLngLat);
var curMarker=null;


var geolocation = new BMap.Geolocation();  
var gc = new BMap.Geocoder();   
  

var opts = {  
        width : 250,     // 信息窗口宽度  
        height: 50,     // 信息窗口高度  
        title : "<delmar:message key='customerext.dialog.currentlocation'/>："  // 信息窗口标题  
      } 
      

geolocation.getCurrentPosition(function(r){  
    if(this.getStatus() == BMAP_STATUS_SUCCESS){  
        var mk = new BMap.Marker(r.point);  
        curMarker=mk;
        map.addOverlay(mk);  
        map.panTo(r.point);  
        var pt = r.point;  
        var message = "";  
        gc.getLocation(pt, function(rs){  
            var addComp = rs.addressComponents;  
            //alert(addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber);  
            var infoWindow = new BMap.InfoWindow(addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber, opts);  // 创建信息窗口对象  
            map.openInfoWindow(infoWindow,r.point); //开启信息窗口  
  
            mk.addEventListener("click", function(){map.openInfoWindow(infoWindow,r.point);});  
  
        });       
  
          
    }  
    else {  
        alert('failed'+this.getStatus());  
    }          
})  
  
map.enableScrollWheelZoom(true);  
map.addControl(new BMap.ScaleControl());                    // 添加默认比例尺控件  
map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件  
 
var localSearch = new BMap.LocalSearch(map);
localSearch.enableAutoViewport(); //允许自动调节窗体大小

function searchByLocation(longtitude,lalitude,address)
{
	if ((longtitude*1!=-1))
	{
		map.clearOverlays();//清空原来的标注
		var currentr=new BMap.Point(longtitude,lalitude); 
		var marker = new BMap.Marker(currentr);  // 创建标注，为要查询的地方对应的经纬度
		curMarker=marker;
        map.addOverlay(marker);		
        map.panTo(currentr);  
        var pt = currentr;  
        var message = "";  
        gc.getLocation(pt, function(rs){  
            var addComp = rs.addressComponents;  
            //alert(addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber);  
  
            var infoWindow = new BMap.InfoWindow(addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber, opts);  // 创建信息窗口对象  
            map.openInfoWindow(infoWindow,currentr); //开启信息窗口  
  
            marker.addEventListener("click", function(){map.openInfoWindow(infoWindow,currentr);});  		
        });     
		
		
	} else if (address!="")
	{
		map.clearOverlays();//清空原来的标注
		localSearch.setSearchCompleteCallback(function (searchResult) {
		        
			    var poi = searchResult.getPoi(0);

		        $("#pointlng").val(poi.point.lng);
		        $("#pointlat").val(poi.point.lat);
		        map.centerAndZoom(poi.point, 13);
		        var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
		        curMarker=marker;
		        map.addOverlay(marker);
		        var content = "<delmar:message key='customerext.column.longtitude'/>：" + poi.point.lng + "<br/><delmar:message key='customerext.column.latitude'/>：" + poi.point.lat;
		        $("#locationcontent").val(content);
		        var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
	            map.openInfoWindow(infoWindow,poi.point); //开启信息窗口  		        
		        marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
		        // marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		    });
		  localSearch.search(address);
		
		}
	
}


function SetLngLat(e)
{
	
    $("#pointlng").val(e.point.lng);
    $("#pointlat").val(e.point.lat);
	map.clearOverlays();//清空原来的标注
    var marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));  // 创建标注，为要查询的地方对应的经纬度
    map.addOverlay(marker);
    var content = "<delmar:message key='customerext.column.longtitude'/>：" + e.point.lng + "<br/><delmar:message key='customerext.column.latitude'/>：" + e.point.lat;
    var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
    map.openInfoWindow(infoWindow,e.point); //开启信息窗口  		        
    marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
}
//关于状态码  
//BMAP_STATUS_SUCCESS   检索成功。对应数值“0”。  
//BMAP_STATUS_CITY_LIST 城市列表。对应数值“1”。  
//BMAP_STATUS_UNKNOWN_LOCATION  位置结果未知。对应数值“2”。  
//BMAP_STATUS_UNKNOWN_ROUTE 导航结果未知。对应数值“3”。  
//BMAP_STATUS_INVALID_KEY   非法密钥。对应数值“4”。  
//BMAP_STATUS_INVALID_REQUEST   非法请求。对应数值“5”。  
//BMAP_STATUS_PERMISSION_DENIED 没有权限。对应数值“6”。(自 1.1 新增)  
//BMAP_STATUS_SERVICE_UNAVAILABLE   服务不可用。对应数值“7”。(自 1.1 新增)  
//BMAP_STATUS_TIMEOUT   超时。对应数值“8”。(自 1.1 新增)  


</script>

