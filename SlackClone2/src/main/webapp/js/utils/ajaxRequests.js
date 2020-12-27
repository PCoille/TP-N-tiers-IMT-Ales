function postJsonObjectWithCallback(obj, path, func) {
    $.ajax({
    type: "POST",
    url: "http://localhost:8080/slack-1.0-SNAPSHOT/rest/" + path,
    data: JSON.stringify(obj),
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
    success: (data)=>func(data)})
}

function postJsonObject(obj, path) {
    postJsonObjectWithCallback(obj, path, ()=>0);
}

function getJsonObjectWithCallback(path, func) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/slack-1.0-SNAPSHOT/rest/" + path,
        headers: {
            'Accept': 'application/json'
        },
        success: (data) => func(data)})
}