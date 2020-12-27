function createUser() {
    let user = {};

    user["name"] = $("#username").val();
    user["email"] = $("#email").val();
    user["forename"] = $("#forename").val();
    user["nickname"] = $("#nickname").val();
    user["password"] = $("#password").val();

    console.log(user)
    sendUser(user);
}

function sendUser(user) {
    postJsonObject(user, "user/post")
}