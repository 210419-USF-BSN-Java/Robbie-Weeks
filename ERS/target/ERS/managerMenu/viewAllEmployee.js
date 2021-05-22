viewEmployees();

function viewEmployees(){
	let token = localStorage.getItem("token")
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/ERS/viewAllEmployee";
	xhr.open("GET", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
            let employeeList = xhr.getResponseHeader("employeeList");
			let jsonList = JSON.parse(employeeList);
			
			let content = document.getElementById("employees");

			for(i = 0; i < jsonList.length; i++){
				let employee = "<td>" + jsonList[i].userID + "</td><td>" + jsonList[i].firstName + "</td><td>" + jsonList[i].lastName + "</td><td>" + jsonList[i].email + "</td>";
				content.insertAdjacentHTML('beforeend', employee);
			}
		} 
		else if (xhr.readyState == 4){
			alert("Failed to load employee information!, please check your login status.");
		}
	}
	
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Authorization",token);
	xhr.send();
}

document.getElementById("back").addEventListener("click", back);

function back(){

    window.location.href="http://localhost:8080/ERS/manager.html"

}