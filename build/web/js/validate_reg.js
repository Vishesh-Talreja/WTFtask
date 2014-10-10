function validateReg() {
    var x1 = document.forms["reg"]["fname"].value;
	var x2 = document.forms["reg"]["lname"].value;
	var x3 = document.forms["reg"]["number"].value;
	var x4 = document.forms["reg"]["email"].value;
	var x5 = document.forms["reg"]["password"].value;
	var x6 = document.forms["reg"]["cpassword"].value;
    var atpos = x4.indexOf("@");
    var dotpos = x4.lastIndexOf(".");
    if (atpos< 1 || dotpos<atpos+2 || dotpos+2>=x4.length) {
        alert("Not a valid e-mail address");
        return false;
    }
}