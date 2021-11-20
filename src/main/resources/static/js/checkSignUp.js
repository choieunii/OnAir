function checkPassword(){
    var password = document.getElementById('password').value;
    var NB = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]
    var SC = ["!","@","#","$","%"];
    var check_SC = 0, check_NB = 0;

    if(password.length < 8 || password.length>20){
        window.alert('비밀번호는 8글자 이상, 20글자 이하만 이용 가능합니다.');
        document.getElementById('pw').value='';
    }
    for(var i=0;i<SC.length;i++){
        if(password.indexOf(SC[i]) != -1){
            check_SC = 1;
        }
    }
    for(var i=0;i<NB.length;i++){
        if(password.indexOf(NB[i]) != -1){
            check_NB = 1;
        }
    }
    if(check_SC == 0){
        window.alert('!,@,#,$,% 의 특수문자가 반드시 포함되어야 합니다.')
        document.getElementById('password').value='';
    }
    if(check_NB == 0){
        window.alert('숫자가 반드시 포함되어야 합니다.')
        document.getElementById('password').value='';
    }
    if(document.getElementById('password').value !='' && document.getElementById('checkPassword').value!=''){
        if(document.getElementById('password').value==document.getElementById('checkPassword').value){
            document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
            document.getElementById('check').style.color='blue';
        }
        else{
            document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
            document.getElementById('check').style.color='red';
        }
    }
}
function checkValue() {
    if(document.userInfo.password.value != document.userInfo.passwordVerify.value) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }
    else return true;
}