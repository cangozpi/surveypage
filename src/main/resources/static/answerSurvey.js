var questionsContainerElement = document.querySelector('.QuestionsContainer');

//get SurveyModel's id from url query params
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const surveyId = urlParams.get("id"); // ID of the survey in the db
const homeParam = urlParams.get("home");

//separate between Answer Survey home page and answering individual survey page via link
//check for home query param
if(homeParam== "true"){ // serve/ redirect to Answer survey home page
window.location.href = "/answerSurveyHome.html";

}else{

//retrieve questions from db
    fetch(`/answerSurvey?id=${surveyId}`, {
        method:'GET'
    }).then(res => res.json()).then((data) => {
        const surveyNameTitleElement = document.getElementById('surveyName');
        let questionClassContainer = document.querySelector('.QuestionsContainer');
        var surveyNameElement = document.createElement('p');
        var formElement = document.querySelector('form');

        //config formElement action url to include userName as query
        formElement.action = `/saveSurveyResults?userName=${data.userName}`;

        //handles surveyNameElement configuration
        surveyNameElement.setAttribute('id', "userName");
        surveyNameElement.innerText = data.userName;
        surveyNameElement.style.visibility = "hidden";
        questionClassContainer.appendChild(surveyNameElement);

        //set title Survey name
        surveyNameTitleElement.innerText=data.name;

        //fill surveyName input for the form POST
        let surveyNameInputElement = document.getElementById('surveyNameInput');
        surveyNameInputElement.value= data.name;

        //inlay Questions into the survey
        for(let i = 0; i < data.questions.length; i++) {
            //retrieve questions itself from db
            fetch(`/questions/getQuestionById?id=${data.questions[i].id}&userName=${data.userName}`,{
                method:'GET'
            }).then(res => res.json()).then((data) => {
                //check for Question type
                switch (data.type) {
                    case "TextBox":
                        var questionRowElement = document.createElement('div');
                        var labelElement = document.createElement('label');
                        var inputTextElement = document.createElement('input');
                        var hrElement = document.createElement('hr');

                        questionRowElement.className="questionRow";

                        labelElement.setAttribute('for', data.name);
                        labelElement.innerText = data.name;

                        inputTextElement.setAttribute('type','text');
                        inputTextElement.setAttribute('name',data.name);
                        inputTextElement.setAttribute('id', data.name);
                        inputTextElement.setAttribute('placeholder',"type here ...");

                        //put together and append to html view
                        questionRowElement.appendChild(labelElement);
                        questionRowElement.appendChild(inputTextElement);
                        questionRowElement.appendChild(hrElement);

                        questionClassContainer.appendChild(questionRowElement);
                        break;
                    case "Number":
                        var questionRowElement = document.createElement('div');
                        var labelElement = document.createElement('label');
                        var inputTextElement = document.createElement('input');
                        var hrElement = document.createElement('hr');

                        questionRowElement.className="questionRow";

                        labelElement.setAttribute('for', data.name);
                        labelElement.innerText = data.name;

                        inputTextElement.setAttribute('type','number');
                        inputTextElement.setAttribute('name',data.name);
                        inputTextElement.setAttribute('id', data.name);

                        //put together and append to html view
                        questionRowElement.appendChild(labelElement);
                        questionRowElement.appendChild(inputTextElement);
                        questionRowElement.appendChild(hrElement);

                        questionClassContainer.appendChild(questionRowElement);

                        break;
                    case "ComboBox":
                        var questionRowElement = document.createElement('div');
                        var labelElement = document.createElement('label');
                        var selectElement = document.createElement('select');
                        var hrElement = document.createElement('hr');

                        questionRowElement.className="questionRow";

                        labelElement.setAttribute('for', data.name);
                        labelElement.innerText = data.name;

                        selectElement.setAttribute('name', data.name)
                        selectElement.setAttribute('id', data.name)

                        //create <options> and append to <select> element
                        for(let i=0 ; i < data.options.length ; i++){
                            var tempOptionElement = document.createElement('option');
                            tempOptionElement.setAttribute('value', data.options[i]);
                            tempOptionElement.innerText = data.options[i];

                            //append to select element
                            selectElement.appendChild(tempOptionElement);
                        }

                        //put together and append to html view
                        questionRowElement.appendChild(labelElement);
                        questionRowElement.appendChild(selectElement);
                        questionRowElement.appendChild(hrElement);

                        questionClassContainer.appendChild(questionRowElement);

                        break;
                    case "Date":
                        var questionRowElement = document.createElement('div');
                        var labelElement = document.createElement('label');
                        var inputTextElement = document.createElement('input');
                        var hrElement = document.createElement('hr');

                        questionRowElement.className="questionRow";

                        labelElement.setAttribute('for', data.name);
                        labelElement.innerText = data.name;

                        inputTextElement.setAttribute('type','date');
                        inputTextElement.setAttribute('name',data.name);
                        inputTextElement.setAttribute('id', data.name);

                        //put together and append to html view
                        questionRowElement.appendChild(labelElement);
                        questionRowElement.appendChild(inputTextElement);
                        questionRowElement.appendChild(hrElement);

                        questionClassContainer.appendChild(questionRowElement);
                        break;
                }

            })
        }

    }).catch((err) => console.log(err))

}

//handles sending current Date along with form data to db
let today = new Date();

//fill date input of form with current date to send as FillDate of answered Survey
let fillDateElement = document.getElementById('fillDate');
fillDateElement.value = today.toISOString().substr(0,10);




