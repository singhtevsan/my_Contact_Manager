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
};

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
};


const searchContact = () => {
	
	let query = $('.search-input').val();
	
	if(query==''){
		$('.search-result').hide();
	}
	else{
		
		//sending request to backend
		let url = `http://localhost:8070/contactManager/searchBar/${query}`;
		
		fetch(url)
		.then((response)=>{
			return response.json();
		})
		.then((data)=>{
			
			let text = `<div class='list-group'>`
	
			data.forEach((contact)=>{
			
				text+= `<a href='/contactManager/user/${contact.cId}/showContact/0'
				class='list-group-item list-group-action'>${contact.name}</a>`
			});
			
			text+=`</div>`
			
			$('.search-result').html(text);
			$('.search-result').show();
		});
	}
}


const search = () => {
	
	let query = $('.input-search').val();
	
	if(query==''){
		$('.result-search').hide();
	}
	else{
		
		//sending request to backend
		let url = `http://localhost:8070/contactManager/searchBar/${query}`;
		
		fetch(url)
		.then((response)=>{
			return response.json();
		})
		.then((data)=>{
			
			let text = `<div class='list-group'>`
	
			data.forEach((contact)=>{
			
				text+= `<a href='/contactManager/user/${contact.cId}/showContact/0'
				class='list-group-item list-group-action'>${contact.name}</a>`
			});
			
			text+=`</div>`
			
			$('.result-search').html(text);
			$('.result-search').show();
		});
	}
}
