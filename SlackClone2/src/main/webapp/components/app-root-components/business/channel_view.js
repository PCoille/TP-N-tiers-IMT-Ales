

function getChannelsView(){
    getJsonObjectWithCallback("channel/get", updateChannelDropdownList);
}

function updateChannelDropdownList(arrayOfChannels){
    $("#inputChannel").html("");
    arrayOfChannels.forEach(element => {
        $("#inputChannel").append(
            "<option value='" + element.id + "'>" + element.name + "</option>"
        )
    });
}

function getChannelLoginFormValue(){
    return {
        "channelId": $("#inputChannel option:selected").attr("value"),
        "username": $("#username").val(),
        "password": $("#password").val()
    }
}

function loginUserToChannel() {
    msgData = getChannelLoginFormValue();
    console.log(msgData);
    postJsonObjectWithCallback(msgData,
                            "business/channel_management/user_login", 
                            (response) => handleLogin(response, msgData))
}

function handleLogin(response, msgData) {
    console.log(response);
    let channelConnectedArea = $("connected-channel-area");
    if(response.status === "success"){
        channelConnectedArea.attr("userId", response.userId);
        channelConnectedArea.attr("channelId", msgData.channelId);
    }
    else {
        if (response.status === "wrong_id") {
            alert("Wrong credentials");
        }
        else if(response.status === "not_a_member") {
            alert("You are not a member");
        }
    }
}