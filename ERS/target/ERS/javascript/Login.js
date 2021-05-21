let token = localStorage.getItem("token");

loged();

function loged(){
	if(token != null){
		let tokenArray = token.split(":");
		if(tokenArray[2] == 1){
		
			window.location.href="http://localhost:8080/ERS/employee.html"
		
		} else if(tokenArray[2] == 2) {
			window.location.href="http://localhost:8080/ERS/manager.html"
		}
	}
}

document.getElementById("loginButton").addEventListener("click", requestLogin);


function requestLogin(e){
	e.preventDefault();
	let user = document.getElementById("username").value;
	let pass = document.getElementById("password").value;
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/ERS/login";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let auth = xhr.getResponseHeader("Authorization");

			localStorage.setItem("token", auth);
			token = localStorage.getItem("token");
			loged();

		} 
		else if (xhr.readyState == 4){
			document.getElementById("message").innerHTML='Incorrect credentials!';
		}
	}

	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `username=${user}&password=${pass}`;
	xhr.send(requestBody);
}