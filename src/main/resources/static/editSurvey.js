// make searchBar Drop Menu interactive
var dropMenuElement = document.getElementById('dropMenu');
var searchBarElement = document.getElementById('questions');
var checkBoxesDiv = document.querySelector('.checkboxes');

//Load previously saved data to form
let loadPrevData = () => {
    //parses id param from URL params
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const surveyId = urlParams.get("id"); // ID of the survey in the db


    fetch(`/editSurvey?id=${surveyId}`, {
        method:"GET",
        headers: {
            //'Content-Type': 'application/x-www-form-urlencoded'
            //'Content-Type': 'application/json'
        }
    }).then(res => res.json()).then((data) => {
        //fill elements wih prior survey data
        var nameElement = document.getElementById('name');
        var idSenderInput = document.getElementById('id-sender');

        //fill id-sender' id value
        idSenderInput.value=surveyId;
        //fill name textbox
        nameElement.value=data.name;
        //fill selectedQuestions

        for(let i=0; i< data.questions.length;i++){
            //fetch questions using id from QuestionRepository
            fetch(`/questions/getQuestionById?id=${data.questions[i].id}`, {
                method:'GET'
            }).then(res => res.json()).then((data) => {
                //adds CheckBoxes
                addChosenCheckBoxes(data.id,data.name);
            })

        }

    })
}

loadPrevData();

//event handles blurring of dropMenu after chosen p elements event listener fires
let dropMenuEvent = new Event("blur")

searchBarElement.addEventListener('blur', () => {
    pElementAddEventListener();

    dropMenuElement.addEventListener('blur', (e) => {
        dropMenuElement.style.visibility="hidden"
    })
    setTimeout(() => {
        dropMenuElement.dispatchEvent(dropMenuEvent);
    },1000)
})

searchBarElement.addEventListener("input", (e) => {
    let searchValue = e.target.value;

    //get available Questions from db
    fetch('/questions/newQuestion', {
        method:'GET'
    }).then(res => res.json()).then((data) => {
        //iterate through data(List of SurveyModels) fill SearchDropDown autofill

        //clearing dropDown Menu each time
        var dropElementSize = dropMenuElement.children.length;
        for(let j=0; j<dropElementSize ;j++){
            dropMenuElement.children[0].remove();
        }

        //AutoFilling part below
        for(let i=0;i< data.length;i++){
            if(data[i].name.toLowerCase().startsWith(searchValue.toLowerCase().trim())){// if searchQuery matches surveyModel's name
                let matchingSurveyP = document.createElement('p');
                matchingSurveyP.innerText=data[i].name;
                matchingSurveyP.id = data[i].id;
                dropMenuElement.appendChild(matchingSurveyP);
                dropMenuElement.style.visibility="visible";
            }

        }
    })
});

//handles adding checboxes for the selected questions from drop Menu
let addChosenCheckBoxes = ( id, name)=> {
    let questionflag=true;
    let existingCheckLabels = document.querySelectorAll('label');
    for(let i=0; i< existingCheckLabels.length;i++){
        if(existingCheckLabels[i].getAttribute('for') == id){
            questionflag = false;
        }
    }

    if(questionflag){
        let checkboxesRowDiv = document.createElement('div');
        let inputElement = document.createElement('input');
        let labelElement = document.createElement('label');

        inputElement.setAttribute('type',"checkbox");
        inputElement.id=id;
        inputElement.setAttribute('name',"checkboxId");
        inputElement.setAttribute('value',id);
        inputElement.checked="true";
        inputElement.addEventListener('change', (e) => {
            checkboxesRowDiv.remove();
        })
        labelElement.innerText= name;
        labelElement.setAttribute('for',id);
        checkboxesRowDiv.className="checkboxesRow";

        checkboxesRowDiv.appendChild(inputElement);
        checkboxesRowDiv.appendChild(labelElement);

        checkBoxesDiv.appendChild(checkboxesRowDiv);
    }
}

//handles adding eventlisteners to drop menu and triggers blurring event of drop menu afterwards
var pElementAddEventListener = () => {
    let pElements = document.querySelectorAll('p');
    for (var i =0; i< pElements.length;i++){
        pElements[i].addEventListener('click',(e) => {
            let targetPName = e.target.innerText;
            let targetPId = e.target.id;
            addChosenCheckBoxes(targetPId,targetPName);
            // event below handles visibility:none of dropMenuElement
            dropMenuElement.dispatchEvent(dropMenuEvent);
        })
    }
}



