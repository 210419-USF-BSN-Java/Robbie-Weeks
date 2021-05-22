viewResolved();

function viewResolved(){
	let token = localStorage.getItem("token")
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/ERS/viewAllResolved";
	xhr.open("GET", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
            let resolvedList = xhr.getResponseHeader("resolvedList");
			let jsonList = JSON.parse(resolvedList);
			
			let content = document.getElementById("resolvedReims");

			for(i = 0; i < jsonList.length; i++){
				let request = "<td>" + jsonList[i].reimID + "</td><td>" + jsonList[i].amount + "</td><td>" + jsonList[i].submitDate + "</td><td>" + jsonList[i].resolveDate + "</td><td>" + jsonList[i].description + "</td><td>" + jsonList[i].authorID + "</td><td>" + jsonList[i].resolverID + "</td><td>" + jsonList[i].statusID + "</td><td>" + jsonList[i].typeID + "</td>";
				content.insertAdjacentHTML('beforeend', request);
			}
		} 
		else if (xhr.readyState == 4){
			alert("Failed to load resolved information!, please check your login status.");
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
