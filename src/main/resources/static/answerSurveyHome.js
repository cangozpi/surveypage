

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
            var tdCheckBoxElement = document.createElement('td');


            //configure tdCheckBoxElement
            tdCheckBoxElement.innerHTML= `<input type="checkbox" name="checkbox" value="${data[i].id}">`
            trElement.appendChild(tdCheckBoxElement);

            //configure tdNameElement
            tdNameElement.className="name";
            tdNameElement.innerText = `${data[i].name}`;
            trElement.appendChild(tdNameElement);

            //configure tdIdElement
            tdIdElement.className="link";
            tdIdElement.innerHTML=`<em>link</em>`;
            trElement.id= `${data[i].id}`;

            tdIdElement.addEventListener('click', (event) => {
                var linkId = event.path[2].getAttribute('id');
                let linkPrompt = window.confirm("Click OK to redirect to Survey Answering Page");

                const redirectUrl = `/answerSurvey.html?home=false&id=${linkId}`;
                if(linkPrompt){
                    window.location.href = redirectUrl;
                }
            })
            trElement.appendChild(tdIdElement);

            //configure trElement
            tableElement.appendChild(trElement);

        }


    });

