export function toastNotification(toastInstanse, severityToast, title, body, details) {
    toastInstanse.add({severity: severityToast, summary: title, detail: body, life: 3000});

    if ((severityToast === "success") || (severityToast === "info")) console.log(details)
    else if(severityToast === "warn") console.warn(details)
    else console.error(details)
};
