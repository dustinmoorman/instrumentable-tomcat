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
            config.beaconUrlHttp = 'http://dmoormandevelopmentlab-kfk90btr.appd-sales.com:7001';
            config.beaconUrlHttps = 'https://dmoormandevelopmentlab-kfk90btr.appd-sales.com:7002';
            config.xd = {enable : true};
        })(window["adrum-config"] || (window["adrum-config"] = {}));
    </script>
    <script src='//cdn.appdynamics.com/adrum/adrum-4.5.17.2890.js'></script>
  </head>
  <body>
    <div id="player"></div>
    <script>
      var tag = document.createElement('script');

      tag.src = "https://www.youtube.com/iframe_api";
      var firstScriptTag = document.getElementsByTagName('script')[0];
      firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

      var player;
      
      function onYouTubeIframeAPIReady() {
    	console.log("Youtube iFrame Ready...")
        player = new YT.Player('player', {
          height: '390',
          width: '640',
          videoId: 'N2nGMTz9SG0',
          events: {
            'onReady': onPlayerReady,
            'onStateChange': onPlayerStateChange
          }
        });
      }

      function onPlayerReady(event) {
    	console.log("Player ready...")
        event.target.playVideo();
    	report_player_state("ready");
      }

      var done = false;
      function onPlayerStateChange(event) {
    	  
    	  switch (event.data) {
    	  case -1:
    		  state = "unstarted";
    		  break;
    	  case 0:
    		  state = "ended";
    		  break;
    	  case 1:
    		  state = "playing";
    		  break;
    	  case 2:
    		  state = "paused";
    		  break;
    	  case 3:
    		  state = "buffering";
    		  break;
    	  case 5:
    		  state = "video cued";
    		  break;
          default:
        	  state = "Unknown";
    	  }
    		
    	  console.log("Player state changed! State: " + state);
          if (event.data == YT.PlayerState.PLAYING && !done) {
            setTimeout(stopVideo, 6000);
            done = true;
          }
          report_player_state(state);	
      }

      function stopVideo() {
    	console.log("Video stopped");
        player.stopVideo();
      }
      
      function report_player_state(state) {
    	  
    	  if (ADRUM) {
    	   console.log("Reporting player state to ADRUM: " + state);
           sendAjaxUpdate(state);

           function sendAjaxUpdate(state) {

               var ajaxCall = new ADRUM.events.Ajax({
                   url: state,
                   method: "GET"
               });
               ajaxCall.markSendTime(window['adrum-start-time'] - new Date().getTime());
               ADRUM.report(ajaxCall);
            
           }
    	 } else {
    		 console.log("No ADRUM found.")
    	 }
       }
    </script>
  </body>
</html>
