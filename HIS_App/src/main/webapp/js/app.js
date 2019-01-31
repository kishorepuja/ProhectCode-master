/*// Wait for the DOM to be ready
$(function() {

	// Initialize form validation on the registration form.
	// It has the name attribute "registration"
	$("form[name='regUser']").validate({

		// Specify validation rules
		rules : {
			// The key name on the left side is the name attribute
			// of an input field. Validation rules are defined
			// on the right side
			firstName : "required",
			lastName : "required",
			dob : "required",
			userRole : "required",
			phno : "required",
			email : {
				required : true,
				// Specify that email should be validated
				// by the built-in "email" rule
				email : true
			},
			pwd : {
				required : true,
				minlength : 5
			}
		},
		// Specify validation error messages
		messages : {
			firstName : "Please enter your firstname",
			lastName : "Please enter your lastname",
			dob : "Please select Date of birth",
			userRole : "Please select role",
			phno : "Please enter phno",
			pwd : {
				required : "Please provide a password",
				minlength : "Your password must be at least 5 characters long"
			},
			email : "Please enter a valid email address"
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler : function(form) {
			form.submit();
		}
	});
	
	
	$("form[name='loginForm']").validate({
		rules : {
			email : {
				required : true,
				email : true
			},
			pwd : {
				required : true,
			}
		},
		// Specify validation error messages
		messages : {
			pwd : {
				required : "Please provide a password",
			},
			email : "Please enter a valid email address"
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler : function(form) {
			form.submit();
		}
	});
	
});*/