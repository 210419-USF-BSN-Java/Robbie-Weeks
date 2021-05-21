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

    window.location.href="http://localhost:8080/ERS/employeeMenu/viewPending.html";
}

function viewInfo(){

    window.location.href="http://localhost:8080/ERS/employeeMenu/personalInfo.html";
}

function logOut(){

    localStorage.removeItem("token");

    window.location.href="http://localhost:8080/ERS/index.html";

}