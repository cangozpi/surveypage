let deleteBtnElement = document.querySelectorAll('.fa-trash');

//handles Delete functionality of Admin Console
for(let i = 0 ; i < deleteBtnElement.length ; i++){
    deleteBtnElement[i].addEventListener('click', (e) => {
        let username = e.target.parentElement.parentElement.children[0].innerText;

        let deletePrompt = window.prompt("type \"DELETE\" to proceed with deleting ...");

        if(deletePrompt == "DELETE"){
            fetch(`/deleteUser?username=${username}`,{
                method: "POST",
            }).then(() => {
                window.location.reload();
            })
            window.alert("User: \""+(username) +"\" deleted")
        }else {
            window.alert(" Didn't delete user");
        }

    })
}