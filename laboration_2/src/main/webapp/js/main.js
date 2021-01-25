
function search() {
    //document.getElementById("search_result").innerHTML = "You searched for keyword: " + document.getElementById("search_form").value;
    //document.getElementById("search_form").value = "";
    var search_result = $('#search_result');
    search_request = $('input[type="search"]').val();
    search_result.html("You searched for keyword: " + search_request)
}
