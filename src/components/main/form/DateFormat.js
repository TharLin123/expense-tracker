const formatDate = (date ) => {

    const d = new Date(date);
    let month = `${d.getMonth() + 1}`;
    let day = `${d.getDate()}`;
    let year = `${d.getFullYear()}`;

    if (month.length < 2)
        month = `0${d.getMonth() + 1}`;

    if (day.length < 2)
        day = `0${d.getDate()}`;

    return [day, month, year].join("-");
}

export default formatDate;