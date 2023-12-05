let chatroomno = document.currentScript.getAttribute('data-chatroomno');
let s_id = document.currentScript.getAttribute('data-s_id');

let chatBox = $("#chatBox");
let webSocket = new WebSocket("ws://localhost:9090/chatroom/chat/" + chatroomno + "/" + s_id);

webSocket.onopen = function(event){
	// console.log(event);
	let username = s_id;
	
	console.log(username);
	// webSocket.send(username); // onMessage가 반응함
} // onopen() ends 

webSocket.onclose = function(event){
	console.log(event);
	console.log(event.reason);
} // onclose() ends 

webSocket.onmessage = function(event){
	// 서버로부터 메시지를 받으면 처리할 내용을 작성합니다.
    let node = JSON.parse(event.data);
    
    // 연결 확인 
    console.log(node);
    
    // 입장시 메시지 
    if(node.status === "visit"){
		/*메시지를 전체적으로 감쌀 div*/
		let chatBox = $("#chatBox");
		let wrapChat = $("<div>");
		wrapChat.attr({
			"class": "wratChat"
		});
		
		
		let openMsg = $("<span>");
		openMsg.attr({
			"class": "openMsg"
		});
		
		openMsg.text(node.message);
		
		wrapChat.append(openMsg);
		chatBox.append(wrapChat);
	} else if(node.status === "input"){
		// 포커스 확인 
		if (!document.hasFocus()) {
			// 팝업 열기
		  	let newWindow = window.open('chatroompopup.jsp?crno=' + chatroomno + '&username=' + node.username + '&message=' + node.message, 'popup', 'width=300, height=200, top=' + (window.screen.availHeight - 100) + ', left=' + (window.screen.availWidth - 100) + ', status=no, toolbar=no, scrollbars=0, location=0');
		  			  
		  	// 창의 크기 및 위치 변경 애니메이션
        	animateWindowResize(newWindow, 300, 0, 300, 200, 2000); // 부드러운 크기 변경
		} // if ends 대화창에 포커스 여부 확인 
	
		// 대화창 찾기
	    let chatBox = $("#chatBox");
		
		// 대화를 감싸는 div
		let wrapChat = $("<div>").addClass("wrapChat");
		chatBox.append(wrapChat);
		
		let myChat = $("<div>").addClass("urChat");
		wrapChat.append(myChat);
		
		// 대화의 구조 table
		let chatTable = $("<table>").addClass("chatTable"); /*.attr("border", 1);*/
		myChat.append(chatTable);
		
		// tr
		let chatTr1 = $("<tr>");
		chatTable.append(chatTr1);
		
		// 이미지를 감싸는 td
		let chatTdImg = $("<td>").addClass("wrapImg").attr("rowspan", 2);
		chatTr1.append(chatTdImg);
		
		let userImg = $("<img>").addClass("userImg").attr("src", "../images/user.png");
		chatTdImg.append(userImg);
	
		let chatTdName = $("<td>").addClass("wrapName").html(node.username);
		chatTr1.append(chatTdName);
		
		// 현재 날짜 및 시간 얻기
		let today = new Date();
		
		// 함수 호출하여 포맷된 시간 얻기
		let chatTdTime = $("<td>").addClass("wrapTime").attr("rowspan", 2).html(showTime(today));
		chatTr1.append(chatTdTime);
		
		let chatTr2 = $("<tr>");
		chatTable.append(chatTr2);
		
		let chatTdTalk = $("<td>").addClass("wrapTalk");
		chatTr2.append(chatTdTalk);
		
		let talk = $("<div>").addClass("urtalk").html(node.message);
		chatTdTalk.append(talk);	
	} else if(node.status === "leave"){
		/*메시지를 전체적으로 감쌀 div*/
		let chatBox = $("#chatBox");
		let wrapChat = $("<div>");
		wrapChat.attr({
			"class": "wratChat"
		});
		
		
		let closeMsg = $("<span>");
		closeMsg.attr({
			"class": "closeMsg"
		});
		
		closeMsg.text(node.message);
		
		wrapChat.append(closeMsg);
		chatBox.append(wrapChat);
	} // if ends 
    
} // onmessasge() ends 

// 전송 버튼을 클릭하면 발생하는 이벤트 
function sendMessage(){
	// 사용자가 입력한 메시지를 가져와서 서버로 전송합니다.
    let message = $("#messageInput").val();
    
    // 대화창 찾기
    let chatBox = $("#chatBox");
	
	// 대화를 감싸는 div
	let wrapChat = $("<div>").addClass("wrapChat");
	chatBox.append(wrapChat);
	
	let myChat = $("<div>").addClass("myChat");
	wrapChat.append(myChat);
	
	// 대화의 구조 table
	let chatTable = $("<table>").addClass("chatTable"); /*.attr("border", 1);*/
	myChat.append(chatTable);
	
	// tr
	let chatTr1 = $("<tr>");
	chatTable.append(chatTr1);
	
	// 이미지를 감싸는 td
	let chatTdImg = $("<td>").addClass("wrapImg").attr("rowspan", 2);
	chatTr1.append(chatTdImg);
	
	let userImg = $("<img>").addClass("userImg").attr("src", "../images/user.png");
	chatTdImg.append(userImg);

	let chatTdName = $("<td>").addClass("wrapName").html(s_id);
	chatTr1.append(chatTdName);
	
	// 현재 날짜 및 시간 얻기
	let today = new Date();
	
	// 함수 호출하여 포맷된 시간 얻기
	let chatTdTime = $("<td>").addClass("wrapTime").attr("rowspan", 2).html(showTime(today));
	chatTr1.append(chatTdTime);
	
	let chatTr2 = $("<tr>");
	chatTable.append(chatTr2);
	
	let chatTdTalk = $("<td>").addClass("wrapTalk");
	chatTr2.append(chatTdTalk);
	
	let talk = $("<div>").addClass("mytalk").html(message);
	chatTdTalk.append(talk);
    
    
    webSocket.send(message);
    
    $("#messageInput").val('');
    $("#messageInput").focus();
} // sendMessage() ends 

// 텍스트 박스에서 엔터키를 누른 경우
$(document).on("keydown", "#messageInput", function(){
	if(event.keyCode === 13){
		$(this).siblings(".sendBtn").trigger("click");
		return false;
	} // if ends 엔터가 눌렸는지 여부 확인 
	return true;
}); // keydown event ends 

function showTime(date) {
  
  let amPm = date.getHours() < 12 ? "오전" : "오후";  
  let hours = date.getHours().toString().padStart(2, "0");
  let mins = date.getMinutes().toString().padStart(2, "0");

  return amPm + " " + hours + ":" + mins;
}

// 창 크기 변경 애니메이션 함수
function animateWindowResize(win, startWidth, startHeight, endWidth, endHeight, duration) {
  var startTime = Date.now();
  
  function resizeStep() {
    var currentTime = Date.now();
    var progress = (currentTime - startTime) / duration;

    if (progress < 1) {
      var newWidth = startWidth + (endWidth - startWidth) * progress;
      var newHeight = startHeight + (endHeight - startHeight) * progress;

      win.resizeTo(newWidth, newHeight);
      win.moveTo(window.screen.width - newWidth, window.screen.height - newHeight); // 수정된 부분: 우측 하단으로 이동
      requestAnimationFrame(resizeStep);
    } else {
      // 애니메이션 종료 후 최종 크기 설정 (부드러운 종료)
      win.resizeTo(endWidth, endHeight);
      win.moveTo(window.screen.width - endWidth, window.screen.height - endHeight); // 수정된 부분: 우측 하단으로 이동
    }
  }

  resizeStep();
}
