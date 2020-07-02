var comboBox = document.querySelector('select');
var formElement = document.querySelector('form');

var acceptedValueHtmlCreater = () => {
    var newLabel = document.createElement('label');
    newLabel.className="newLabel";
    newLabel.htmlFor = `${comboBox.options[comboBox.selectedIndex].value}`
    var newAcceptedValues;

    //removes earlier labels corresponding to pripr selected types from html
    formElement.childNodes.forEach((x) => {
        if(x.className == "newLabel"){
            x.remove();

        }
    })

    for(var i=0; i<formElement.childNodes.length;i++){
        if(formElement.childNodes[i].className == "newLabel"){
            formElement.childNodes[i].remove();
        }
    }

    //names accordingly to selected combobox option
    switch(comboBox.options[comboBox.selectedIndex].value){
        case "TextBox":
            /*
            newLabel.innerText="TextBox";
            formElement.appendChild(newLabel)
            //handles input of accepted values
            newAcceptedValues = document.createElement('input');
            newAcceptedValues.type="text";
            newAcceptedValues.id = `${comboBox.options[comboBox.selectedIndex].value}`;
            newAcceptedValues.name = 'acceptedValues';
            newAcceptedValues.className = "newLabel";
            newAcceptedValues.placeholder="each line corresponds to an option"
            formElement.appendChild(newAcceptedValues);*/
            break;
        case "Date":/*
            newLabel.innerText = "Date"
            formElement.appendChild(newLabel)
            //handles input of accepted values
            newAcceptedValues = document.createElement('input');
            newAcceptedValues.type="date";
            newAcceptedValues.id = `${comboBox.options[comboBox.selectedIndex].value}`;
            newAcceptedValues.name = 'acceptedValues';
            newAcceptedValues.className = "newLabel";
            formElement.appendChild(newAcceptedValues);*/
            break;
        case "Number":/*
            newLabel.innerText= "Number";
            formElement.appendChild(newLabel)
            //handles input of accepted values
            newAcceptedValues = document.createElement('input');
            newAcceptedValues.type="number";
            newAcceptedValues.id = `${comboBox.options[comboBox.selectedIndex].value}`;
            newAcceptedValues.name = 'acceptedValues';
            newAcceptedValues.className = "newLabel";
            formElement.appendChild(newAcceptedValues);*/
            break;
        case "ComboBox":
            newLabel.innerText="ComboBox";
            formElement.appendChild(newLabel)
            //handles input of accepted values
            newAcceptedValues = document.createElement('textarea');
            newAcceptedValues.rows = "8";
            // newAcceptedValues.cols = "50";
            newAcceptedValues.id = `${comboBox.options[comboBox.selectedIndex].value}`;
            newAcceptedValues.name = 'acceptedValues';
            newAcceptedValues.className = "newLabel";
            formElement.appendChild(newAcceptedValues);
            newAcceptedValues.required=true;
            break;
    } 
    //newAcceptedValues.required=true;

    //add submit button last to render it at the bottom
    var submitBtn = document.createElement('input');
    submitBtn.type="submit";
    submitBtn.value="Finish";
    formElement.appendChild(submitBtn);

}

acceptedValueHtmlCreater(comboBox.options[comboBox.selectedIndex].value);

//Listens for ComboBox option changes and displays accepted values accordingly
comboBox.addEventListener('change', (event) => {
    event.preventDefault();
    acceptedValueHtmlCreater(comboBox.options[comboBox.selectedIndex].value)
  
})

//Takes care of id passed in and fills items accordingly

var queryString = window.location.search
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');//mongoDB object id

//get object with that id' data
fetch(`/questions/getQuestionById?id=${id.toString()}`,{
    method:"GET"
}).then(res => res.json()).then((data) => {
    //Work on filling page items from the data from fetch to db
    var nameElement = document.getElementById('name');
    var typeElement = document.getElementById('type');

    //Setup nameElement
    nameElement.value=data.name;

    //Setup typeElement
    typeElement.value=data.type;

    //Takes care of ComboBox's textArea element
    if(data.type=="ComboBox"){
        var newLabel = document.createElement('label');
        newLabel.className="newLabel";
        newLabel.htmlFor = `${comboBox.options[comboBox.selectedIndex].value}`
        newLabel.innerText="ComboBox";
        formElement.appendChild(newLabel)
        //handles input of accepted values
        var newAcceptedValues = document.createElement('textarea');
        newAcceptedValues.rows = "8";
        newAcceptedValues.id = `${comboBox.options[comboBox.selectedIndex].value}`;
        newAcceptedValues.name = 'acceptedValues';
        newAcceptedValues.className = "newLabel";
        var textBoxStr="";
        for(var i=0;i<data.options.length;i++){
            textBoxStr+= data.options[i] + "\n";

        }
        newAcceptedValues.value=textBoxStr;;//line added by me to fill textarea by the db info
        formElement.appendChild(newAcceptedValues);
        newAcceptedValues.required=true;
    }

    //handles PUT request
    formElement.action = `/questions/updateById?id=${data.id}`;


});




