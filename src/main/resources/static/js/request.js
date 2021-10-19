
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
    var roomEdit = $('#roomEdit').val();
    var levelEdit = $('#levelEdit').val();
    var fromWhomEdit = $('#fromWhomEdit').val();
    var toWhomEdit = $('#toWhomEdit').val();
    var statusEdit = $('#statusEdit').val();
    var fnameEdit = $('#fnameEdit').val();
    var pnameEdit = $('#pnameEdit').val();
    var lnameEdit = $('#lnameEdit').val();
    var textEdit = $('#textEdit').val();

    if(roomEdit.length !== 0 && levelEdit.length !== 0 && fromWhomEdit.length !== 0 && toWhomEdit.length !== 0 && statusEdit.length !== 0  &&
        fnameEdit.length !== 0 && pnameEdit.length !== 0 && lnameEdit.length !== 0 && textEdit.length !== 0) {

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

/* Кнопка доступна, если все поля заполнены в форме для добавления новой должности position.html*/
function checkParamsAddNewPosition() {

    var addNewPosition = $('#addNewPosition').val();

    if(addNewPosition.length !== 0  ) {

        $('#btnAddNewPosition').removeAttr('disabled');
    } else {
        $('#btnAddNewPosition').attr('disabled', 'disabled');
    }
}

/* Кнопка доступна, если все поля заполнены в форме для добавления новой должности position.html*/
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
