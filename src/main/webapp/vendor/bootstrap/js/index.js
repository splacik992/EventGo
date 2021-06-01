window.addEventListener('DOMContentLoaded', () => {

    let order = [];
    let counter = 1;
    let result = document.getElementById('result');
    let hiddenInput = document.getElementById('a');


    function openForm() {
        document.getElementById('myForm').style.display = "block";
    }
    openForm()

    function closeForm() {
        document.getElementById('myForm').style.display = "none";
    }
    closeForm()
});