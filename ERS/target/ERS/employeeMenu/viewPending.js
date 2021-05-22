viewPending();

function viewPending(){
	let token = localStorage.getItem("token")
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/ERS/viewPending";
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
			alert("Failed to load your pending reimbursement!, please check your login status.");
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