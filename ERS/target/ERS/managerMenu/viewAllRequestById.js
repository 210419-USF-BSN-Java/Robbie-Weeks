
document.getElementById("check").addEventListener("click", viewRequest);

function viewRequest(){
	let token = localStorage.getItem("token")
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/ERS/viewAllRquestById";
	let employeeID = document.getElementById("employeeID").value;
	xhr.open("GET", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
            let requestList = xhr.getResponseHeader("requestList");
			let jsonList = JSON.parse(requestList);
			
			let content = document.getElementById("viewAllRequestById");

			for(i = 0; i < jsonList.length; i++){
				let request = "<td>" + jsonList[i].reimID + "</td><td>" + jsonList[i].amount + "</td><td>" + jsonList[i].submitDate + "</td><td>" + jsonList[i].resolveDate + "</td><td>" + jsonList[i].description + "</td><td>" + jsonList[i].authorID + "</td><td>" + jsonList[i].resolverID + "</td><td>" + jsonList[i].statusID + "</td><td>" + jsonList[i].typeID + "</td>";
				content.insertAdjacentHTML('beforeend', request);
			}
		} 
		else if (xhr.readyState == 4){
			alert("Failed to load request information!, please check your login status.");
		}
	}
	
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Authorization",token);
	xhr.setRequestHeader("employeeID",employeeID);
	xhr.send();
}

document.getElementById("back").addEventListener("click", back);

function back(){

    window.location.href="http://localhost:8080/ERS/manager.html"

}