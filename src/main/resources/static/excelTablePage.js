
//Handles downloading table as excel
setTimeout(() => {
    exportToExcel("excelTable")
},1000)


//download table as excel function
function exportToExcel(mytblId){
    var htmltable= document.getElementById(mytblId);
    var html = htmltable.outerHTML;
    window.open('data:application/vnd.ms-excel,' + encodeURIComponent(html));
}
