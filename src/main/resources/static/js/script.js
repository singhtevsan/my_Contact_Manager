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
};


const deleteContact = (cId,page) => {
	swal({
	  title: "Are you sure?",
	  text: "Once deleted, you will not be able to recover this contact !!",
	  icon: "warning",
	  buttons: true,
	  dangerMode: true,
	})
	.then((willDelete) => {
	  if (willDelete) {
		
			window.location = '/contactManager/user/'+ cId +'/deleteContact/'+page;
	  } else {
	    	swal("Your contact is safe !!");
	  }
	});
}