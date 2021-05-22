setInfo();

function setInfo(){
	let token = localStorage.getItem("token")
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/ERS/getInfo";
	xhr.open("GET", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
            let userInfo = xhr.getResponseHeader("userInfo");
			let jsonInfo = JSON.parse(userInfo);
			
			document.getElementById('firstName').placeholder=jsonInfo.firstName;
			document.getElementById('lastName').placeholder=jsonInfo.lastName;
			document.getElementById('email').placeholder=jsonInfo.email;

		} 
		else if (xhr.readyState == 4){
			alert("Failed to load your personal information!, please check your login status.");
		}
	}
	
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Authorization",token);
	xhr.send();
}

document.getElementById("update").addEventListener("click", updateInfo);
document.getElementById("back").addEventListener("click", back);

function updateInfo(){
    let firstName = document.getElementById("firstName").value;
	let lastName = document.getElementById("lastName").value;
    let email = document.getElementById("email").value;

	let token = localStorage.getItem("token")
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/ERS/updateInfo";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
            alert("You have successfully updated your personal information!");

		} 
		else if (xhr.readyState == 4){
			alert("Failed to updated your personal information!, please check your login status.");
		}
	}

	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Authorization",token);
	let requestBody = `firstName=${firstName}&lastName=${lastName}&email=${email}`;
	xhr.send(requestBody);
}

function back(){

    window.location.href="http://localhost:8080/ERS/employee.html"

}