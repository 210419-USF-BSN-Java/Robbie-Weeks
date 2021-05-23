viewPending();

function viewPending(){
	let token = localStorage.getItem("token")
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/ERS/viewAllPending";
	xhr.open("GET", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
            let pendingList = xhr.getResponseHeader("pendingList");
			let jsonList = JSON.parse(pendingList);
			
			let content = document.getElementById("pendingReims");
			
			for(i = 0; i < jsonList.length; i++){
				let request = "<td>" + jsonList[i].reimID + "</td><td>" + jsonList[i].amount + "</td><td>" + jsonList[i].submitDate + "</td><td>" + jsonList[i].description + "</td><td>" + jsonList[i].authorID + "</td><td>" + jsonList[i].typeID + "</td>";
				content.insertAdjacentHTML('beforeend', request);
			}
		} 
		else if (xhr.readyState == 4){
			alert("Failed to load your personal information!, please check your login status.");
		}
	}
	
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Authorization",token);
	xhr.send();
}

document.getElementById("resolve").addEventListener("click", requestAction);
document.getElementById("back").addEventListener("click", back);

function requestAction(){
	let reimID = document.getElementById("reimID").value;
	let action = document.getElementById("action").value;
	console.log(action);
	let token = localStorage.getItem("token")
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/ERS/reimAction";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
            alert("You have successfully resolved this reimbursement request!");

		} 
		else if (xhr.readyState == 4){
			alert("Failed to resolved this reimbursement request!, please check your login status.");
		}
	}

	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Authorization",token);
	let requestBody = `reimID=${reimID}&action=${action}`;
	xhr.send(requestBody);
}

function back(){

    window.location.href="http://localhost:8080/ERS/manager.html"

}
