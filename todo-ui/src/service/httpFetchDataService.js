const URL = 'http://localhost:8080/'

export async function GET(context) {

    let response = ""

    const responseFetch = await fetch(URL+context, {method: "GET"}).catch(() => {undefined});

    if(responseFetch === undefined)
        response = createReturn(true, createObjToastToServerError())
    else
        response = decideSeverityToastOrCreateReturn(responseFetch, await responseFetch.json())

    return response

}

export async function DELETE(context) {

    let response = ""

    const responseFetch = await fetch(URL+context, {method: "DELETE"}).catch(() => {undefined});

    if(responseFetch === undefined)
        response = createReturn(true, createObjToastToServerError())
    else if(responseFetch.status == 204)
        response = null
    else
        response = decideSeverityToastOrCreateReturn(responseFetch, await responseFetch.json())

    return response

}

export async function POST(context, bodyJson) {

    const HEADERS = { 'Content-Type': 'application/json' }
    let response = ""

    const responseFetch = await fetch(URL+context,
        {
            method: 'POST',
            headers: HEADERS,
            body: bodyJson
        }).catch(() => {undefined});

    if(responseFetch === undefined)
        response = createReturn(true, createObjToastToServerError())
    else
        response = decideSeverityToastOrCreateReturn(responseFetch, await responseFetch.json())

    return response
}

export async function PATCH(context, bodyJson) {

    const HEADERS = { 'Content-Type': 'application/json' }
    let response = ""
    let responseFetch = ""

    if(bodyJson == undefined)
        responseFetch = await fetch(URL+context, {method: "PATCH"}).catch(() => {undefined});
    else
        responseFetch = await fetch(URL+context,
            {
                method: 'PATCH',
                headers: HEADERS,
                body: bodyJson
            }).catch(() => {undefined});

    if(responseFetch === undefined)
        response = createReturn(true, createObjToastToServerError())
    else
        response = decideSeverityToastOrCreateReturn(responseFetch, await responseFetch.json())

    return response
}







function decideSeverityToastOrCreateReturn(responseFetch, responseFetchJson) {
    const res = responseFetchJson;

    if (responseFetch.ok)
        return createReturn(false, res);
    if (responseFetch.status == 204)
        return null;
    else if((responseFetch.status < 500) && (responseFetch.status >= 400))
        if((responseFetch.status == 400) && (res.details === "Check the field(s) error"))
            return createReturn(true, createObjToast("error", 'Bad Request', res.fieldsMessage, res.developerMessage))
        else
            return createReturn(true, createObjToast("warn", res.title, res.details, res.developerMessage))
    else if((responseFetch.status < 400) && (responseFetch.status >= 300))
        return createReturn(true, createObjToast("info", res.title, res.details, res.developerMessage))
    else
        return createReturn(true, createObjToast("error", res.title, res.details, res.developerMessage))
}

function createReturn(isToast, obj) {
    let response = new Object();
    response.isToast = isToast
    response.obj = obj
    return response
}

function createObjToast(severityToast, title, body, details) {
    let resToast = new Object();
    resToast.severity = severityToast
    resToast.title =  title
    resToast.body = body
    resToast.details = details
    return resToast
}

function createObjToastToServerError() {
    return createObjToast(
        "error",
        "Internal Server Error",
        "An unexpected condition was encountered. We are solving it.",
        "Verify logs");
}

