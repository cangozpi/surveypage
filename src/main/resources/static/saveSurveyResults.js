

//Setting up and displaying the table by retrieving data from db
var tableElement = document.querySelector('table');

//method to render Items given a list of AnsweredSurveyModels
var renderItemsGivenData = (data) => {
    for(let i=0; i<data.length;i++){

        if(data[i] == null){ // handles null elements in a data which is supplied by renderFilteredItems() function
            continue;
        }

        var trElement = document.createElement('tr');
        var tdIdElement = document.createElement('td');
        var tdNameElement = document.createElement('td');
        var tdTrashIconElement = document.createElement('td');
        var tdEditIdconElement = document.createElement('td');

        //--- SurveySelect Drop menu configuration
        var selectSurveyNameElement = document.getElementById('surveyName');

        //configure SelectSurveyNameElements drop down options menu
        let surveyNameOptionElement = document.createElement('option');
        surveyNameOptionElement.innerText = data[i].surveyName;
        selectSurveyNameElement.appendChild(surveyNameOptionElement);

        //--------

        //configure tdNameElement
        tdNameElement.className="name";
        tdNameElement.innerText = `${data[i].surveyName}`;
        trElement.appendChild(tdNameElement);

        //configure tdIdElement
        tdIdElement.className="link";

//---------- Age Calculation
        let today = Date.now();
        let dateBirh = new Date(Number.parseInt(data[i].questions[2].answer.split("-")[0]), Number.parseInt(data[i].questions[2].answer.split("-")[1])-1, Number.parseInt(data[i].questions[2].answer.split("-")[2])).getTime();

        let ageDif = today-dateBirh;
        let ageDate = new Date(ageDif);
        let ageValue = Math.abs(ageDate.getUTCFullYear() -1970);

        tdIdElement.innerText=`${ageValue}`;
//----------
        tdIdElement.addEventListener('click', (e) => {
            const linkId = e.target.parentNode.getAttribute('id');
            let linkPrompt = window.confirm("Click OK to redirect to SurveyAnswering Page");

            const redirectUrl = `/answerSurvey.html?home=false&id=${linkId}`;
            if(linkPrompt){
                window.location.href = redirectUrl;
            }
        })
        trElement.appendChild(tdIdElement);

        //configure tdEditIconElement accordign to education
        switch (data[i].questions[1].answer) {
            case "someHighSchool":tdEditIdconElement.innerHTML="Some High School";
                break;
            case "GED":tdEditIdconElement.innerHTML="Some High School Diploma/ GED";
                break;
            case "someCollege":tdEditIdconElement.innerHTML="Some College";
                break;
            case "associatesDegree":tdEditIdconElement.innerHTML="Associate Degree";
                break;
            case"bachelorsDegree":tdEditIdconElement.innerHTML="Bachelor'Degree";
                break;
            case"mastersDegree":tdEditIdconElement.innerHTML="Master's Degree or Higher";
                break;
        }
        tdEditIdconElement.innerHTML=`${data[i].questions[1].answer}`;
        trElement.appendChild(tdEditIdconElement);

        //configure tdTrashIconElement
        tdTrashIconElement.innerHTML = `<span id="viewLink"><em>view</em></span>`;
        tdTrashIconElement.children[0].addEventListener('click',trashIconClicked);
        trElement.appendChild(tdTrashIconElement);

        //configure trElement
        trElement.id= `${data[i].id}`;
        tableElement.appendChild(trElement);

    }
}

fetch("/getAllAnsweredSurveys", {
    method:'GET'
}).then(res => res.json())
    .then((data) => {
        renderItemsGivenData(data);


    });


//Handling Delete operation on a row
var trashIconClicked = (e)=> {
    let rowId = e.target.parentElement.parentElement.parentElement.id; //id of the row element in MongoDb
   //redirect to view page by passing in rowId as query parameter "id" value
    window.location.href = `/surveyCreation?id=${rowId}`;

}

//method to render Filtered Items
var renderFilteredItems = (data) => {
    renderItemsGivenData(data);
}


//method to clean table called in Filter 'click' event callback function
var cleanTable = () => {
    let allTableRows = document.querySelectorAll('tr');
    for(let i = 1 ; i < allTableRows.length ; i++){ // starts from index=1 to keep table headers
        allTableRows[i].remove();
    }
}

//method to render filtered elements on the table
var addFilteredElements = (surveyName, startDate, endDate, ageStarts, ageEnds) => {
    //fetch all data first
    fetch("/getAllAnsweredSurveys", {
        method:'GET'
    }).then(res => res.json()).then((data) => {


       //iterate over each AnsweredSurvey Element
        for(let i = 0; i < data.length; i++){
            //filter according to params
            if(surveyName != ""){ // if suvery Name is chosen
                if(data[i].surveyName != surveyName && surveyName != "Survey Name"){
                    delete data[i];
                    continue;
                }
            }

            //check for Dates
            let datesFlag = true; // if true means valid dates are entered
            for(let i = 0; i< 3; i++){
                if(Number.isInteger(parseInt(startDate.split("-")[i])) == false || Number.isInteger(parseInt(endDate.split("-")[i])) == false ){
                    datesFlag = false;
                }
            }


            //do dates filtering if datesFlag is true meaning valid dates were chosen
            if(datesFlag){
                //--------------------------------------------
                let dateCheck = new Date(parseInt(data[i].questions[3].answer.split("-")[0]),parseInt(data[i].questions[3].answer.split("-")[1])-1,parseInt(data[i].questions[3].answer.split("-")[2]))
                let dateStart= new Date(startDate.split("-")[0],startDate.split("-")[1]-1,startDate.split("-")[2])
                let dateEnd = new Date(endDate.split("-")[0],endDate.split("-")[1]-1,endDate.split("-")[2]);

                if(dateCheck > dateEnd || dateCheck < dateStart){
                    delete data[i];
                    continue;
                }

                //----------------------------------------------

            }

            //if ageFlag=true meaning valid Age range is given
            //check if it hold if not remove data at index=i;


            let today = Date.now();
            let dateBirh = new Date(Number.parseInt(data[i].questions[2].answer.split("-")[0]), Number.parseInt(data[i].questions[2].answer.split("-")[1])-1, Number.parseInt(data[i].questions[2].answer.split("-")[2])).getTime();

            let ageDif = today-dateBirh;
            let ageDate = new Date(ageDif);
            let ageValue = Math.abs(ageDate.getUTCFullYear() -1970);

            if(ageValue < Number.parseInt(ageStarts) || ageValue > Number.parseInt(ageEnds)){
                delete data[i];
                continue;
            }

        }

        renderFilteredItems(data);

    })
}

// Filter functionality below
let filterBtnElement = document.getElementById('filterBtn');
filterBtnElement.addEventListener('click', () => {
    let surveyNameElement = document.getElementById('surveyName');
    let startDateElement = document.getElementById('date1');
    let endDateElement = document.getElementById('date2');
    let ageStartsElement = document.getElementById('age1');
    let ageEndsElement = document.getElementById('age2');

    //extract values from DOM elements -->
    let surveyName = surveyNameElement.options[surveyNameElement.selectedIndex].value;
    let startDate = startDateElement.value;
    let endDate = endDateElement.value;
    let ageStarts = ageStartsElement.value;
    let ageEnds = ageEndsElement.value;

    //clean already existing row Data
    cleanTable();

    //adds filtered elements to table
    addFilteredElements(surveyName, startDate, endDate, ageStarts, ageEnds);

})

//Excel download functionality
let excelBtn = document.getElementById('excel');

excelBtn.addEventListener('click', () => {
    let trElements = document.querySelectorAll('tr');
    let idsParam = new Array(trElements.length-1);
    let j=0;
    for(let i = 1; i < trElements.length; i++){
        idsParam[j] = trElements[i].getAttribute('id');
        j++
    }
    //redirect to table page with passing ids as query param
    window.open(`/excelTablePage?ids=${idsParam}`);
})