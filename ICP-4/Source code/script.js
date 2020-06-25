function GitInfo(user) {
    //1. Creating an instance of XMLHttpRequest class and sending a GET request.
    var username='https://api.github.com/users/'+user;
    console.log(username);
    $.ajax({
        type: "GET",
        url: username,
        dataType: 'json',

    }).done(function(data){
        showUser(data);
    });
}

function showUser(user) {
    //2. Setting the contents of h2 in html page by adding profile
    document.getElementById('imgref').src=user.avatar_url;
    document.getElementById('textname').innerText=user.name;
    document.getElementById('id_text').innerText=user.id;
    document.getElementById('url_text').href=user.url;
    document.getElementById('url_text').innerText=user.html_url;
    document.getElementById('repository_text').innerText=user.public_repos;
    document.getElementById('followers').innerText=user.followers;
    document.getElementById('following').innerText=user.following;
}
function noSuchUser(username) {
    if(data.message == "Not Found" || username == '') {
        alert("User not found");
    }
}
$(document).ready(function () {
    $(document).on('keypress', '#username', function (e) {
        //check if the enter(i.e return) key is pressed
        if (e.which == 13) {
            //get what the user enters
            username = $(this).val();
            //reset the text typed in the input
            $(this).val("");
            //get the user's information and store the respsonse
            GitInfo(username);
            //if the response is successful show the user's details

        }
    })
});
