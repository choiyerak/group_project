let chatroomno = document.currentScript.getAttribute('data-chatroomno');
let s_id = document.currentScript.getAttribute('data-s_id');
let username = document.currentScript.getAttribute('data-username');
let message = document.currentScript.getAttribute('data-message');

let popupChatBox = document.createElement("div");
popupChatBox.setAttribute("class", "popupChatBox");
document.body.appendChild(popupChatBox);

let popupWrapChat = document.createElement("div");
popupWrapChat.setAttribute("class", "popupWrapChat");
popupChatBox.appendChild(popupWrapChat);

let popupUsername = document.createElement("div");
popupUsername.setAttribute("class", "popupUsername");
popupUsername.innerText = username;
popupWrapChat.appendChild(popupUsername);

let popupMsg = document.createElement("div");
popupMsg.setAttribute("class", "popupMsg");
popupMsg.innerText = message;
popupWrapChat.appendChild(popupMsg);
/*$(document).ready(function() {
	// console.log("hi");
	console.log(document.body);
	let popupChatBox = $("div").addClass('popupChatBox');
	$(document.body).append(popupChatBox)
	console.log(popupChatBox);
	let popupWrapChat = $('div').addClass('popupWrapChat');
	popupChatBox.append(popupWrapChat);
	
	let popupUsername = $('div').addClass('popupUsername').text("hi");
	popupWrapChat.append(popupUsername);
	console.log(popupUsername);
	let popupMsg = $('div').addClass('popupMsg').text('message');
	popupWrapChat.append(popupMsg);
});*/

function closeWin(){
	window.close();
}

document.addEventListener("DOMContentLoaded", function(){
	setTimeout(closeWin, 5000);
});

function openParentWindow(){
	// window.opener.location.href = "chatroomEnter.jsp?crno=" + chatroomno;
	
	// 상태 변화 없이 부모창으로 이동 방법 
	window.opener.history.pushState({}, '', "chatroomEnter.jsp?crno=" + chatroomno);
	window.opener.focus();
	window.close();
}

window.addEventListener('click', openParentWindow);
document.body.style.cursor = 'pointer';
