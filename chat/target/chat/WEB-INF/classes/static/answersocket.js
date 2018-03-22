var stompClient = null;
var owneruser, otheruser,sessionid = 1;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('http://192.168.43.99:8083/discussionchat');
    console.log('socket object ' );
    stompClient = Stomp.over(socket);
    console.log('going to connect ');
    stompClient.connect({"user" : document.getElementById("sender").value}, function (frame) 
    {if($("#sender").val()== "disha1308"){
    	owneruser = 6;
    	otheruser = 2;
    }
    else
    	{ owneruser = 2;
    	  otheruser = 6;
    	}

        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/queue/reply', function (greeting) {
            showGreeting(JSON.parse(greeting.body).otheruserid,JSON.parse(greeting.body).answerText,"Me");
        });
        stompClient.send("/chatsession/"+owneruser, {}, JSON.stringify({'chatsessionid':null,'seekerid':'2','expertid':'6','questionid':'21','seekerstatus':null,'expertstatus':null}));
        
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
	//need userid which will be stored in session attribute.
    stompClient.send("/chatanswer", {}, JSON.stringify({'chatanswerid': null, 'userid':owneruser, 'chatsessionid': 1,'answerText':$("#msgContent").val(),'timestamp':null}));
    showGreeting("Me",$("#msgContent").val(), otheruser);
        
}

function showGreeting(sender,message,receiver) {
    $("#greetings").append("<tr><td>" + sender + "</td><td>"+message+"</td><td>"+receiver+"</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});
