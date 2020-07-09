

//Setting up and displaying the table by retrieving data from db
var tableElement = document.querySelector('table');

fetch("/questions/newQuestion", {
    method:'GET'
}).then(res => res.json())
    .then((data) => {
        var rowIndex =1; // index of table rows displayed
        for(let i=0; i<data.length;i++){
            var trElement = document.createElement('tr');
            var tdIdElement = document.createElement('td');
            var tdNameElement = document.createElement('td');
            var tdTrashIconElement = document.createElement('td');
            var tdEditIdconElement = document.createElement('td');

            //configure tdIdElement
            tdIdElement.className="id";
            tdIdElement.innerText=rowIndex;
            trElement.appendChild(tdIdElement);

            //configure tdNameElement
            tdNameElement.className="name";
            tdNameElement.innerText = `${data[i].name}`;
            trElement.appendChild(tdNameElement);

            //configure tdTrashIconElement
            tdTrashIconElement.innerHTML = `<i class="fa fa-trash" aria-hidden="true"></i>`;
            tdTrashIconElement.addEventListener('click',trashIconClicked);
            trElement.appendChild(tdTrashIconElement);

            //configure tdEditIconElement
            tdEditIdconElement.innerHTML=`<i class="fa fa-pencil-square-o" aria-hidden="true"></i>`;
            tdEditIdconElement.addEventListener('click',editIconClicked);
            trElement.appendChild(tdEditIdconElement);

            //configure trElement
            trElement.id= `${data[i].id}`;
            tableElement.appendChild(trElement);

            rowIndex++;
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
        fetch('/questions/deleteById',{
            method: 'DELETE',
            body: JSON.stringify({"id":rowId.toString()})
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
    window.location.replace(`/editQuestion.html?id=${rowId}&userName=null`);

}







