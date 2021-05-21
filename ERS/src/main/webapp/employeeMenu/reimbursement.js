document.getElementById("submit").addEventListener("click", submitReim);
document.getElementById("back").addEventListener("click", back);

function submitReim(){
    let amount = document.getElementById("amount").value;
	let type = document.getElementById("type").value;
    let description = document.getElementById("description").value;
    let file = document.getElementById("image").value;
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/ERS/submitReim";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
            alert("You have successfully submited your reimbursement request! You can now return to the main menu or submit another request.");

		} 
		else if (xhr.readyState == 4){
			alert("Failed to submit your reimbursement, please check your login status.");
		}
	}

	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `amount=${amount}&type=${type}&description=${description}&file=${file}`;
	xhr.send(requestBody);
}

function back(){

    window.location.href="http://localhost:8080/ERS/employee.html"

}
