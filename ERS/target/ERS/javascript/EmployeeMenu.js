let token = localStorage.getItem("token");

if(!token){
	window.location.href="http://localhost:8080/ERS/";
} else {

	let userInfo = token.split(":");
	if(userInfo[2] == 1){
		
		let welcome = document.getElementById("welcome");
		welcome.innerText="Welcome to the ERS, " + userInfo[1];

	} else if(userInfo[2] == 2){
		window.location.href="http://localhost:8080/ERS/manager.html";
	}
}

document.getElementById("addReim").addEventListener("click", addReim);
document.getElementById("viewPending").addEventListener("click", viewPending);
document.getElementById("viewSolved").addEventListener("click", viewSolved);
document.getElementById("viewInfo").addEventListener("click", viewInfo);
document.getElementById("logout").addEventListener("click", logOut);

function addReim(){

    window.location.href="http://localhost:8080/ERS/employeeMenu/reimbursement.html";
}

function viewPending(){

    window.location.href="http://localhost:8080/ERS/employeeMenu/viewPending.html";
}

function viewSolved(){

    window.location.href="http://localhost:8080/ERS/employeeMenu/viewSolved.html";
}

function viewInfo(){

    window.location.href="http://localhost:8080/ERS/employeeMenu/personalInfo.html";
}

function logOut(){

    localStorage.removeItem("token");

    window.location.href="http://localhost:8080/ERS/index.html";

}