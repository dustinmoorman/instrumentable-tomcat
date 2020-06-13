<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  	<script charset='UTF-8'>
	  window['adrum-start-time'] = new Date().getTime();
      (function(config){
    	  config.appKey = 'EUM-AAB-AUZ';
    	  config.adrumExtUrlHttp = 'http://cdn.appdynamics.com';
    	  config.adrumExtUrlHttps = 'https://cdn.appdynamics.com';
    	  config.beaconUrlHttp = 'http://pdx-col.eum-appdynamics.com';
    	  config.beaconUrlHttps = 'https://pdx-col.eum-appdynamics.com';
    	  config.xd = {enable : false};
    	  (function(info) {
    	       info.PageView = function () {
    	           return {
    	               userData: {
    	            	   username: "dustin.moorman@appdynamics.com"
    	               }
    	           }
    	       }
    	  })(config.userEventInfo || (config.userEventInfo = {}))
    	 })(window['adrum-config'] || (window['adrum-config'] = {}));
</script>
<script src='//cdn.appdynamics.com/adrum/adrum-latest.js'></script>
  </head>
  <body>
  <iframe frameBorder="0" width="100%" height="1000px" src="http://dmoormandevelopmentlab-kfk90btr.appd-sales.com:8090/controller/dashboards.jsp?desktopView=false#/location=DASHBOARD_VIEWER&token=c4dffdc1-1889-45fc-86e7-35bfc0b25365&timeRange=last_30_minutes.BEFORE_NOW.-1.-1.30"></iframe>
  </body>
</html>