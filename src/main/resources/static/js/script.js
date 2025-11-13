console.log("Hello from script");

const toggleSidebar = () => {
    
    if($('.sidebar').is(":visible")){
       
       $(".sidebar").css("display","none");
       $(".content").css("margin-left","2%");
    }
    else{
        
        $(".sidebar").css("display","block");
       $(".content").css("margin-left","22%");
    }
};

const uploadImage = () => {
	$('#imgupload').trigger('click');
}

const profile = document.getElementById("profile-pic");
if(profile) {
	const input = document.getElementById("imgupload");
	
	input.addEventListener("change", () => {
		profile.src = URL.createObjectURL(input.files[0]);
	});
}