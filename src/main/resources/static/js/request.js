// const $fname = document.querySelector('[name=fname]');
// const $lname = document.querySelector('[name=lname]');
// const $pname = document.querySelector('[name=pname]');
//
// document.querySelector('select').addEventListener(`change`, e => {
//     const $option = e.target.options[e.target.selectedIndex];
//     $fname.value = $option.dataset.fname;
//     $lname.value = $option.dataset.lname;
//     $pname.value = $option.dataset.pname;
// });








/* Кнопка доступна, если все поля заполнены в форме для написания заявки request.html*/
function checkParams() {
    // var room = $('#room').val();
    var level = $('#level').val();
    var fromWhom = $('#fromWhom').val();
    var toWhom = $('#toWhom').val();
    var text = $('#text').val();

    if(level.length !== 0 && fromWhom.length !== 0 && toWhom.length !== 0 && text.length !== 0  ) {

        $('#submit').removeAttr('disabled');
    } else {
        $('#submit').attr('disabled', 'disabled');
    }
}


/* Кнопка доступна, если поле поиска по фильтру заполнена request.html*/
    function checkParamsFilter() {
        var statusFilter = $('#statusFilter').val();

        if(statusFilter.length !== 0 ) {

            $('#submitFilter').removeAttr('disabled');
        } else {
            $('#submitFilter').attr('disabled', 'disabled');
        }
    }

/* Кнопка доступна, если все поля ответа на заявку заполнены requestEdit.html */
function checkParamsEdit () {
    // var roomEdit = $('#roomEdit').val();
    var levelEdit = $('#levelEdit').val();
    var fromWhomEdit = $('#fromWhomEdit').val();
    var toWhomEdit = $('#toWhomEdit').val();
    var statusEdit = $('#statusEdit').val();
    var nameEdit = $('#nameEdit').val();
    var textEdit = $('#textEdit').val();
    // var textCommentEdit = $('#textCommentEdit').val();

    if(levelEdit.length !== 0 && fromWhomEdit.length !== 0 && toWhomEdit.length !== 0 && statusEdit.length !== 0  &&
        nameEdit.length !== 0 && textEdit.length !== 0) {

        $('#submitEdit').removeAttr('disabled');
    } else {
        $('#submitEdit').attr('disabled', 'disabled');
    }
}

/* Кнопка доступна, если все поля заполнены в форме для добавления нового сотрудника admin.html*/
function checkParamsAddNewStaff() {
    var addLname = $('#addLname').val();
    var addFname = $('#addFname').val();
    var addPname = $('#addPname').val();
    var addPosition = $('#addPosition').val();

    if(addLname.length !== 0 && addFname.length !== 0 && addPname.length !== 0 && addPosition.length !== 0  ) {

        $('#btnAddNewStaff').removeAttr('disabled');
    } else {
        $('#btnAddNewStaff').attr('disabled', 'disabled');
    }
}

/* Кнопка доступна, если все поля заполнены в форме редактирования сотрудника staffEdit.html*/
function checkParamsUpdateStaffInAdmin() {
    var lname = $('#lname').val();
    var fname = $('#fname').val();
    var pname = $('#pname').val();
    var position = $('#position').val();

    if(lname.length !== 0 && fname.length !== 0 && fname.length !== 0 && position.length !== 0  ) {

        $('#btnUpdateStaff').removeAttr('disabled');
    } else {
        $('#btnUpdateStaff').attr('disabled', 'disabled');
    }
}



/* Кнопка доступна, если все поля заполнены в форме для добавления новой должности account.html*/
function checkParamsAddAccount() {

    var username = $('#username').val();
    var password = $('#password').val();
    var roles = $('#roles').val();

    if(username.length !== 0 && password.length !== 0 && roles.length !== 0 ) {

        $('#btnAddAccount').removeAttr('disabled');
    } else {
        $('#btnAddAccount').attr('disabled', 'disabled');
    }
}


/* Кнопка доступна, если все поля заполнены в форме для ответа на заявку у сотрудников requestEdit.html*/
function checkParamsStaffRequestEdit() {
    var toWhomEditStaff = $('#toWhomEditStaff').val();
    var statusEditStaff = $('#statusEditStaff').val();
    var nameEditStaff = $('#nameEditStaff').val();
    var textEditStaff = $('#textEditStaff').val();


    if(toWhomEditStaff.length !== 0 && statusEditStaff.length !== 0 && nameEditStaff.length !== 0 && textEditStaff.length !== 0 ) {

        $('#submitEditStaff').removeAttr('disabled');
    } else {
        $('#submitEditStaff').attr('disabled', 'disabled');
    }
}


    // В поле room можно вводить только цифры

function validator(evt) {
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode( key );
    var regex = /[0-9]|\./;
    if( !regex.test(key) ) {
        theEvent.returnValue = false;
        if(theEvent.preventDefault) theEvent.preventDefault();
    }
}


function show_hide_password(target){
    var input = document.getElementById('password-input');
    if (input.getAttribute('type') == 'password') {
        target.classList.add('view');
        input.setAttribute('type', 'text');
    } else {
        target.classList.remove('view');
        input.setAttribute('type', 'password');
    }
    return false;
}

