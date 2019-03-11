function createOutWork(){
	 // var layer = layui.layer;
	//基于准备好的dom，初始化echarts实例
	  var outwork = echarts.init(document.getElementById('outwork'));
      var inwork= echarts.init(document.getElementById('inwork'));
	  var kdata=[];
	  var vdata=[];

	  $.ajax({
	      url: "outdata",
	      type: "post",
	      // data: {"trainType":trainType,"groupBy":groupBy},
	      datatype: "json",
	      success: function (res) {
	    	//layer.msg(JSON.stringify(res));
	      	if(res.status==0){
	      		
	      		$.each(res.data,function(i,c){
	      			kdata.push(c.taskname);
	      			vdata.push(c.tasksize);
	      		})
	      		
	      		// 使用刚指定的配置项和数据显示图表。
	      		outwork.setOption(option);
	      		//inwork.setOption(option);
	      		    		
	      	}else{
	      		layer.msg(JSON.stringify(res));
	      	}
	          
	      }
	  })



	  // 指定图表的配置项和数据
	  var option = {
	      title: {
	          text: '出库工作中心'
	      },
	      tooltip: {},
	      legend: {data:['行数']},
	      xAxis: {
	          data: kdata
	      },
	      yAxis: {},
	      series: [{
	          name: '行数',
	          type: 'bar',
	          data: vdata
	      }]
	  };
	
	
}





function createInWork(){
	 // var layer = layui.layer;
	//基于准备好的dom，初始化echarts实例
     var inwork= echarts.init(document.getElementById('inwork'));
	  var kdata=[];
	  var vdata=[];

	  $.ajax({
	      url: "indata",
	      type: "post",
	      // data: {"trainType":trainType,"groupBy":groupBy},
	      datatype: "json",
	      success: function (res) {
	    	//layer.msg(JSON.stringify(res));
	      	if(res.status==0){
	      		
	      		$.each(res.data,function(i,c){
	      			kdata.push(c.taskname);
	      			vdata.push(c.tasksize);
	      		})
	      		
	      		// 使用刚指定的配置项和数据显示图表。
	      		inwork.setOption(option);
	      		//inwork.setOption(option);
	      		    		
	      	}else{
	      		layer.msg(JSON.stringify(res));
	      	}
	          
	      }
	  })



	  // 指定图表的配置项和数据
	  var option = {
	      title: {
	          text: '入库工作中心'
	      },
	      tooltip: {},
	      legend: {data:['行数']},
	      xAxis: {
	          data: kdata
	      },
	      yAxis: {},
	      series: [{
	          name: '行数',
	          type: 'bar',
	          data: vdata
	      }]
	  };
	
	
}




function createMap(){
	
	// 百度地图API功能
	var map = new BMap.Map("map");    // 创建Map实例
	map.centerAndZoom(new BMap.Point(121.150628, 28.812285), 10);  // 初始化地图,设置中心点坐标和地图级别
	//添加地图类型控件
	map.addControl(new BMap.MapTypeControl({
		mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]}));	  
	map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	
}



var socketSupportLogo="ws";

function initSocket(socketServer){
	
	var websocket = null;
	
	
	if(typeof(WebSocket) == "undefined") {
		console.log("您的浏览器不支持WebSocket");
		$("#socketsupport").html('<span class="layui-badge" style="text-decoration:line-through;">'+socketSupportLogo+'</span>');
	    $("#socketsupport").attr("title","您的浏览器不支持被动更新.");
	}else{
		console.log("您的浏览器支持WebSocket");
		$("#socketsupport").html('<span class="layui-badge layui-bg-blue">'+socketSupportLogo+'</span>');
		$("#socketsupport").attr("title","您的浏览器支持被动更新.");
		//ws://localhost:端口/${server.servlet.context-path}/websocket服务端名称
		//websocket = new WebSocket("ws://192.168.0.117:8099/ezyyboot/websocket");
		 websocket = new WebSocket(socketServer);
		 //连接发生错误的回调方法
	    websocket.onerror = function(){
	        setMessageInnerHTML("error");
	    };

	    //连接成功建立的回调方法
	    websocket.onopen = function(event){
	        setMessageInnerHTML("open");
	    }

	    //接收到消息的回调方法
	    websocket.onmessage = function(event){
	        
	        var msg =event.data;
	        switch (msg) {
			case "1":
				createInWork();
				break;
			case "2":
				createOutWork()
				break;

			default:
				setMessageInnerHTML(msg);
				break;
			}
	    }

	    //连接关闭的回调方法
	    websocket.onclose = function(){
	        setMessageInnerHTML("close");
	    }

	  //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	    window.onbeforeunload = function(){
	        websocket.close();
	    }

	}
	
	
}



//将消息显示在网页上
function setMessageInnerHTML(innerHTML){
    document.getElementById('message').innerHTML += innerHTML + '<br/>';
}

//关闭连接
function closeWebSocket(){
    websocket.close();
}

//发送消息
function send(){
    var message = document.getElementById('text').value;
    websocket.send(message);
}


