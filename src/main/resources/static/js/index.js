var back_button = document.querySelector('#back-button');
var next_button = document.querySelector('#next-button');
var header = document.querySelector('.header');
var count = 3;

back_button.addEventListener('click',()=>{
    if(header.classList.contains('background1')){
        header.className = 'header'
    }else{
        header.className += ' background1'
    }
});

next_button.addEventListener('click',()=>{
        if(header.classList.contains('background2')){
            header.className = 'header'
        }else{
            header.className += ' background2'
        }
});
