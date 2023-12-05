function openChatroom(chatroomno){
	let crno = chatroomno;
	let linkToChatroom = 'chatroomEnter.jsp?crno=' + crno;
	/*새 탭에서 열기*/
	/*window.open(linkToChatroom, "_blank");*/
	let popupWidth = 320;
	let popupHeight = 450;
	
	let popupX = (window.screen.width / 2) - (popupWidth / 2);
	console.log(popupX);
	let popupY= (window.screen.height / 2) - (popupHeight / 2);	
	window.open(
		linkToChatroom, 
		'win' + crno, 
		'width=' + popupWidth  + ', height=' + popupHeight  +  
		', left='+ popupX + ', top='+ popupY +
		', status=no, toolbar=no, scrollbars=0, location=0'
		);
}