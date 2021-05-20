
var a;
function show_hide() {
    if (a === 1) {
        document.getElementById("commentt").style.display = "none";
        return a = 0;
    } else {
        document.getElementById("commentt").style.display = "inline";
        return a = 1;
    }

}
