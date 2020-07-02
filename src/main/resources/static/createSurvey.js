

//Setting up and displaying the table by retrieving data from db
var tableElement = document.querySelector('table');

fetch("/getAllSurveys", {
    method:'GET'
}).then(res => res.json())
    .then((data) => {
        for(let i=0; i<data.length;i++){
            var trElement = document.createElement('tr');
            var tdIdElement = document.createElement('td');
            var tdNameElement = document.createElement('td');
            var tdTrashIconElement = document.createElement('td');
            var tdEditIdconElement = document.createElement('td');
            var tdCheckBoxElement = document.createElement('td');


            //configure tdCheckBoxElement
            tdCheckBoxElement.innerHTML= `<input type="checkbox" name="checkbox" value="${data[i].id}">`
            tdCheckBoxElement.addEventListener('change',checkboxDelete);
            trElement.appendChild(tdCheckBoxElement);

            //configure tdNameElement
            tdNameElement.className="name";
            tdNameElement.innerText = `${data[i].name}`;
            trElement.appendChild(tdNameElement);

            //configure tdIdElement
            tdIdElement.className="link";
            tdIdElement.innerText="link";
            tdIdElement.addEventListener('click', (e) => {
                const linkId = e.target.parentNode.getAttribute('id');
                let linkPrompt = window.confirm("Click OK to redirect to SurveyAnswering Page");

                const redirectUrl = `/answerSurvey.html?home=false&id=${linkId}`;
                if(linkPrompt){
                    window.location.href = redirectUrl;
                }
            })
            trElement.appendChild(tdIdElement);

            //configure tdEditIconElement
            tdEditIdconElement.innerHTML=`<i class="fa fa-pencil-square-o" aria-hidden="true"></i>`;
            tdEditIdconElement.addEventListener('click',editIconClicked);
            trElement.appendChild(tdEditIdconElement);

            //configure tdTrashIconElement
            tdTrashIconElement.innerHTML = `<i class="fa fa-trash" aria-hidden="true"></i>`;
            tdTrashIconElement.addEventListener('click',trashIconClicked);
            trElement.appendChild(tdTrashIconElement);

            //configure trElement
            trElement.id= `${data[i].id}`;
            tableElement.appendChild(trElement);

        }


    });


//Handling Delete operation on a row
var trashIconClicked = (e)=> {
    let rowId = e.target.parentElement.parentElement.id; //id of the row element in MongoDb

    //Pop Up a Prompt window to make sure they want to proceed with deleting
    var deletePrompt = window.prompt("Type \"DELETE\" to proceed !","type here ...")

    //According to Prompt Result it handles DELETE request for the row element
    if(deletePrompt=="DELETE"){
        //fetch a DELETE request to our Servie
        var formData = new FormData();
        formData.append("id", rowId.toString());

        fetch('/deleteSurveyById',{
            method: 'DELETE',
            body: formData
        }).then(res=> res.json()).then((data) => {
            if(data==true){
                window.location.reload();
            }else{
                window.alert("Couldn't remove successfully!");
            }
        })
    }else {
        window.alert("Did not proceed with deleting !");
    }

}

//Handling PUT/EDIT operation on a row

var editIconClicked = (e) => {
    let rowId = e.target.parentElement.parentElement.id; // MongoDB id of the selected row element
    window.location.replace(`/editSurvey.html?id=${rowId}`);

}

//Handling checkBox Delete functionalities event callback function
//event to fire when checkbox state changes and deleteALL btn should be investigated
const checkDeleteEvent = new Event("checkDeleteBtn");

const deleteAllBtn = document.getElementById("deleteAllBtn");

deleteAllBtn.addEventListener('checkDeleteBtn', () => {
    if(checkedBoxLeft>0){//checked element present make DeleteAllBtn visible
        deleteAllBtn.style.display="block";
    }else{ // no checked left hide DeleteAllBtn
        deleteAllBtn.style.display="none";
    }
})

let checkedBoxLeft = 0;// its value > 0 means deleteALL btn should be visible
let checkboxDelete = (e) => {
    let targetId = e.target.value;
    if(e.target.checked){ //if checkbox is  checked
        checkedBoxLeft++;
    }else{
        checkedBoxLeft--;
    }
    deleteAllBtn.dispatchEvent(checkDeleteEvent);
}

//handle DeleteAllBtn's deleting functionality
deleteAllBtn.addEventListener('click', () => {
    let allCheckBoxes = document.querySelectorAll('input[type="checkbox"]');
    //search for checked items and emit event to trigger delete operations on each selected row item

    var deletePrompt = window.prompt("Type \"DELETE\" to proceed !","type here ...")

    //Pop Up a Prompt window to make sure they want to proceed with deleting
    if(deletePrompt=="DELETE"){

        for(let i=0 ; i<allCheckBoxes.length ; i++){
            if(allCheckBoxes[i].checked) {
                //allCheckBoxes[i].parentNode.parentNode.children[4];
                let rowId = allCheckBoxes[i].value; //id of the row element in MongoDb

                //fetch a DELETE request to our Service
                let formData = new FormData();
                formData.append("id", rowId.toString());

                fetch('/deleteSurveyById', {
                    method: 'DELETE',
                    body: formData
                }).then(res => res.json()).then((data) => {
                    if (data == true) {

                    } else {
                        window.alert("Couldn't remove successfully!");
                    }
                })

            }

        }
        //reload page to remove deleted items from the table content displaying no the page
        window.location.reload();

    }else {
        window.alert("Did not proceed with deleting !");
    }

})




