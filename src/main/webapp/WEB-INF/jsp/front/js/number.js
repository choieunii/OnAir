var add_adult = document.querySelector('#add-adult');
var sub_adult = document.querySelector('#sub-adult');
var adult = document.querySelector('#adult');
var add_children = document.querySelector('#add-children');
var sub_children = document.querySelector('#sub-children');
var children = document.querySelector('#children');
var add_infant = document.querySelector('#add-infant');
var sub_infant = document.querySelector('#sub-infant');
var infant = document.querySelector('#infant');
                                        
add_adult.addEventListener('click',()=>{
    if (adult.value >= 10) {
    adult.value = 10;
    }
    else {
    adult.value = parseInt(adult.value) + 1;
    }
});
sub_adult.addEventListener('click',()=>{
    if (adult.value <= 0) {
        adult.value = 0;
    }
    else{
        adult.value = parseInt(adult.value) - 1;
    }
});
add_children.addEventListener('click',()=>{
    if (children.value >= 10) {
        children.value = 10;
    }
    else {
        children.value = parseInt(children.value) + 1;
    }
});

sub_children.addEventListener('click',()=>{
    if (children.value <= 0) {
        children.value = 0;
    }
    else{
        children.value = parseInt(children.value) - 1;
    }
});
add_infant.addEventListener('click',()=>{
    if (infant.value >= 10) {
        infant.value = 10;
    }
    else {
        infant.value = parseInt(infant.value) + 1;
    }
});

sub_infant.addEventListener('click',()=>{
    if (infant.value <= 0) {
        infant.value = 0;
    }
    else{
        infant.value = parseInt(infant.value) - 1;
    }
});                                       