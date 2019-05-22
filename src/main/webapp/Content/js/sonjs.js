function fdate(d) {
    if (d) {
        return (d.split("T"))[0];
    }
    else {
        return "";
    }
}
